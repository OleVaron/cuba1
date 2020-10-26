package com.company.train.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "TRAIN_INVOICE")
@Entity(name = "train_Invoice")
@NamePattern("%s|number")
public class Invoice extends StandardEntity {
    private static final long serialVersionUID = -1727865158963646325L;

    @Column(name = "NUMBER_")
    private Integer number;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_")
    private Date date;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAGE_ID")
    private Stage stage;

    @Column(name = "VAT")
    private BigDecimal vat;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(name = "TRAIN_INVOICE_FILE_DESCRIPTOR_LINK",
            joinColumns = @JoinColumn(name = "INVOICE_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    private List<FileDescriptor> files;

    @JoinColumn(name = "CONTACT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDeleteInverse(DeletePolicy.CASCADE)
    private Contract contact;

    public void setContact(Contract contact) {
        this.contact = contact;
    }

    public Contract getContact() {
        return contact;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setFiles(List<FileDescriptor> files) {
        this.files = files;
    }

    public List<FileDescriptor> getFiles() {
        return files;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}