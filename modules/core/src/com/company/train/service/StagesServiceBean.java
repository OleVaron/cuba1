package com.company.train.service;

import com.company.train.config.InvoiceNumSeqConfig;
import com.company.train.config.SCCNumSeqConfig;
import com.company.train.core.InvoiceReportGenerator;
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

import static com.company.train.core.InvoiceReportGenerator.STAGE_VIEW;

@Service(StagesService.NAME)
public class StagesServiceBean implements StagesService {

    @Inject
    private Persistence persistence;

    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    @Inject
    private Metadata metadata;
    @Inject
    private Configuration configuration;
    @Inject
    private TimeSource timeSource;
    @Inject
    private InvoiceReportGenerator invoiceReportGenerator;

    @Override
    public void generateInvoicesAndActsFromStages(List<Stage> stages) {
        for (Stage stage: stages) {
            generateDocuments(stage);
            invoiceReportGenerator.generateInvoiceReportFromStage(stage);
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

}