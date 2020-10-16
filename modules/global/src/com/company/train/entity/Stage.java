package com.company.train.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "TRAIN_STAGE")
@Entity(name = "train_Stage")
@NamePattern("%s|name")
public class Stage extends StandardEntity {
    private static final long serialVersionUID = 5296568361003703034L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE_FROM")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column(name = "DATE_TO")
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "VAT")
    private BigDecimal vat;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID")
    private Contract contract;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "stage")
    private ServiceCompletionCertificate serviceCompletionCertificate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "stage")
    private Invoice invoice;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public ServiceCompletionCertificate getServiceCompletionCertificate() {
        return serviceCompletionCertificate;
    }

    public void setServiceCompletionCertificate(ServiceCompletionCertificate serviceCompletionCertificate) {
        this.serviceCompletionCertificate = serviceCompletionCertificate;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}