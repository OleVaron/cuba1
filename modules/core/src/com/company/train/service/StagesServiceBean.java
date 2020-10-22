package com.company.train.service;

import com.company.train.config.InvoiceNumSeqConfig;
import com.company.train.config.SCCNumSeqConfig;
import com.company.train.entity.Invoice;
import com.company.train.entity.ServiceCompletionCertificate;
import com.company.train.entity.Stage;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.app.ExceptionReportService;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import com.haulmont.reports.ReportingApi;
import com.haulmont.reports.app.service.ReportService;
import com.haulmont.reports.app.service.ReportWizardService;
import com.haulmont.reports.entity.Report;
import com.haulmont.reports.entity.ReportRunParams;
import com.haulmont.reports.entity.ReportTemplate;
import com.haulmont.reports.wizard.ReportingWizardApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

@Service(StagesService.NAME)
public class StagesServiceBean implements StagesService {

    public static final String INVOICE = "Invoice";
    public static final String ENTITY = "entity";
    public static final String NUMBER = "number";
    public static final String REPORT_FOR_INVOICE = "Report for Invoice";
    public static final String STAGE_VIEW = "stage-view";
    public static final String REPORT_NAME = "report_name";

    @Inject
    private Persistence persistence;

    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private ReportService reportService;
    @Inject
    private Configuration configuration;

    @Override
    public void generateInvoicesAndActsFromStages(List<Stage> stages) {
        for (Stage stage: stages) {
            generateDocuments(stage);
            generateReportForInvoice(stage);

        }
    }

    protected void generateDocuments(Stage stage) {
        Transaction tx = persistence.createTransaction();
        try {
            stage = persistence.getEntityManager().reload(stage, STAGE_VIEW);
            generateInvoiceFromStage(stage);
            generateServiceCompletionCertificateFromStage(stage);
            tx.commit();
        } finally {
            tx.end();
        }
    }

    @Inject
    private TimeSource timeSource;

    protected void generateServiceCompletionCertificateFromStage(Stage stage) {
            ServiceCompletionCertificate scc = metadata.create(ServiceCompletionCertificate.class);
            scc.setStage(stage);
            scc.setVat(stage.getVat());
            scc.setTotalAmount(stage.getTotalAmount());
            scc.setAmount(stage.getAmount());
            scc.setDate(timeSource.currentTimestamp());
            scc.setDescription(stage.getDescription());
            scc.setNumber(((Long)uniqueNumbersAPI.getNextNumber(configuration.getConfig(SCCNumSeqConfig.class).getSequenceName())).intValue());
            persistence.getEntityManager().persist(scc);
    }

    protected void generateInvoiceFromStage(Stage stage) {
            Invoice invoice = metadata.create(Invoice.class);
            invoice.setStage(stage);
            invoice.setVat(stage.getVat());
            invoice.setTotalAmount(stage.getTotalAmount());
            invoice.setAmount(stage.getAmount());
            invoice.setDate(timeSource.currentTimestamp());
            invoice.setDescription(stage.getDescription());
            invoice.setNumber(((Long)uniqueNumbersAPI.getNextNumber(configuration.getConfig(InvoiceNumSeqConfig.class).getSequenceName())).intValue());
            persistence.getEntityManager().persist(invoice);
    }

    protected void generateReportForInvoice(Stage stage) {
        Transaction tx = persistence.createTransaction();
        try {
            stage = persistence.getEntityManager().reload(stage, STAGE_VIEW);
            Invoice invoice = stage.getInvoice();
            Report report = dataManager.load(Report.class)
                    .query("select r from report$Report r where r.name = :report_name")
                    .parameter(REPORT_NAME, REPORT_FOR_INVOICE).one();

            Invoice reloaded = dataManager.load(Invoice.class)
                    .query("select i from train_Invoice i where i.number = :number")
                    .parameter(NUMBER, invoice.getNumber()).one();
            HashMap<String, Object> hm = new HashMap<>();
            hm.put(ENTITY, reloaded);
            invoice.setFiles(Collections.singletonList(reportService.createAndSaveReport(report, hm, INVOICE)));
            persistence.getEntityManager().persist(invoice);
            tx.commit();
        } finally {
            tx.end();
        }
    }


}