package com.company.train.service;

import com.company.train.entity.Invoice;
import com.company.train.entity.ServiceCompletionCertificate;
import com.company.train.entity.Stage;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.app.ExceptionReportService;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.View;
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
    private ReportingApi reportingApi;
    @Inject
    private ReportingWizardApi reportingWizardApi;
    @Inject
    private ReportWizardService reportWizardService;

    @Override
    public void generateInvoicesAndActsFromStages(List<Stage> stages) {
        for (Stage stage: stages) {
            Stage reloadedStage = reloadStageFromSeparatedTransaction(stage);
            generateInvoiceFromStage(reloadedStage);
            generateReportForInvoice(reloadedStage);
            generateServiceCompletionCertificateFromStage(reloadedStage);
        }
    }

    protected Stage reloadStageFromSeparatedTransaction(Stage stage) {
        Transaction tx = persistence.createTransaction();
        try {
            stage = persistence.getEntityManager().reload(stage, View.LOCAL);
        } finally {
            tx.end();
        }
        return stage;
    }


    protected void generateServiceCompletionCertificateFromStage(Stage stage) {
        Transaction tx = persistence.createTransaction();
        try {
            ServiceCompletionCertificate scc = metadata.create(ServiceCompletionCertificate.class);
            scc.setStage(stage);
            scc.setVat(stage.getVat());
            scc.setTotalAmount(stage.getTotalAmount());
            scc.setAmount(stage.getAmount());
            scc.setDate(new Date()); //todo change to service
            scc.setDescription(stage.getDescription());
            scc.setNumber(((Long)uniqueNumbersAPI.getNextNumber("SERVICE_COMPLETION_CERTIFICATE")).intValue());
            persistence.getEntityManager().persist(scc);
        } finally {
            tx.end();
        }
    }

    protected void generateInvoiceFromStage(Stage stage) {
        Transaction tx = persistence.createTransaction();
        try {
            Invoice invoice = metadata.create(Invoice.class);
            invoice.setStage(stage);
            invoice.setVat(stage.getVat());
            invoice.setTotalAmount(stage.getTotalAmount());
            invoice.setAmount(stage.getAmount());
            invoice.setDate(new Date()); //todo change to service
            invoice.setDescription(stage.getDescription());
            invoice.setNumber(((Long)uniqueNumbersAPI.getNextNumber("INVOICE")).intValue());
            persistence.getEntityManager().persist(invoice);
            tx.commit();
        } finally {
            tx.end();
        }
    }


    protected void generateReportForInvoice(Stage stage) {
        Transaction tx = persistence.createTransaction();
        try {
            Invoice invoice = stage.getInvoice();
            Report report = dataManager.load(Report.class)
                    .query("select r from report$Report r where r.name = :report_name")
                    .parameter("report_name", "Report for Invoice").one();

            Invoice reloaded = dataManager.load(Invoice.class)
                    .query("select i from train_Invoice i where i.number = :number")
                    .parameter("number", invoice.getNumber()).one();
            HashMap<String, Object> hm = new HashMap<>();
            hm.put("entity", reloaded);
            invoice.setFiles(Collections.singletonList(reportService.createAndSaveReport(report, hm, "Invoice")));
            persistence.getEntityManager().persist(invoice);
        } finally {
            tx.end();
        }
    }


}