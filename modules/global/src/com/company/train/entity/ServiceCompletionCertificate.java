package com.company.train.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "TRAIN_SERVICE_COMPLETION_CERTIFICATE")
@Entity(name = "train_ServiceCompletionCertificate")
@NamePattern("%s|number")
public class ServiceCompletionCertificate extends StandardEntity {
    private static final long serialVersionUID = -6238700811443480308L;

    @Column(name = "NUMBER_")
    private Integer number;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_")
    private Date date;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "VAT")
    private BigDecimal vat;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(name = "TRAIN_SERVICE_COMPLETION_CERTIFICATE_FILE_DESCRIPTOR_LINK",
            joinColumns = @JoinColumn(name = "SERVICE_COMPLETION_CERTIFICATE_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    private List<FileDescriptor> files;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAGE_ID")
    private Stage stage;

    public void setFiles(List<FileDescriptor> files) {
        this.files = files;
    }

    public List<FileDescriptor> getFiles() {
        return files;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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