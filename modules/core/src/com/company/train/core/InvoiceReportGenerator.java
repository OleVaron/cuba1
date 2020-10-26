package com.company.train.core;

import com.company.train.entity.Invoice;
import com.company.train.entity.Stage;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.reports.app.service.ReportService;
import com.haulmont.reports.entity.Report;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashMap;

@Component(InvoiceReportGenerator.NAME)
public class InvoiceReportGenerator {
    public static final String NAME = "train_InvoiceReportGenerator";
    @Inject
    private Persistence persistence;
    @Inject
    private DataManager dataManager;
    @Inject
    private ReportService reportService;

    public static final String INVOICE = "Invoice";
    public static final String ENTITY = "entity";
    public static final String NUMBER = "number";
    public static final String STAGE_VIEW = "stage-view";
    public static final String REPORT_NAME = "report_name";
    public static final String REPORT_FOR_INVOICE = "Report for Invoice";

    @Transactional
    public void generateInvoiceReportFromStage(Stage stage) {
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
    }
}