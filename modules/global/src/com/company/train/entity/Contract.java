package com.company.train.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.ReferenceToEntity;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table(name = "TRAIN_CONTRACT")
@Entity(name = "train_Contract")
@Listeners("train_ContractEntityListener")
@NamePattern("%s|number")
public class Contract extends StandardEntity {
    private static final long serialVersionUID = 2310625483122392286L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Organization customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFORMER_ID")
    private Organization performer;

    @Column(name = "NUMBER_")
    private Integer number;

    @Column(name = "SIGNED_DATE")
    private LocalDate signedDate;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    @AttributeOverrides({
            @AttributeOverride(name = "entityId", column = @Column(name = "TYPE_ENTITY_ID")),
            @AttributeOverride(name = "stringEntityId", column = @Column(name = "TYPE_STRING_ENTITY_ID")),
            @AttributeOverride(name = "intEntityId", column = @Column(name = "TYPE_INT_ENTITY_ID")),
            @AttributeOverride(name = "longEntityId", column = @Column(name = "TYPE_LONG_ENTITY_ID"))
    })
    private ReferenceToEntity type;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_FROM")
    private Date dateFrom;

    @Column(name = "DATE_TO")
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @Column(name = "AMOUNT", nullable = false)
    @PositiveOrZero
    @NotNull
    private BigDecimal amount;

    @Column(name = "VAT")
    private BigDecimal vat;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "CUSTOMER_SIGNER")
    private String customerSigner;

    @Column(name = "PERFORMER_SIGNER")
    private String performerSigner;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILES_ID")
    private FileDescriptor files;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "contract")
    private List<Stage> stages;

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public FileDescriptor getFiles() {
        return files;
    }

    public void setFiles(FileDescriptor files) {
        this.files = files;
    }

    public ReferenceToEntity getType() {
        return type;
    }

    public void setType(ReferenceToEntity type) {
        this.type = type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPerformerSigner() {
        return performerSigner;
    }

    public void setPerformerSigner(String performerSigner) {
        this.performerSigner = performerSigner;
    }

    public String getCustomerSigner() {
        return customerSigner;
    }

    public void setCustomerSigner(String customerSigner) {
        this.customerSigner = customerSigner;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(LocalDate signedDate) {
        this.signedDate = signedDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Organization getPerformer() {
        return performer;
    }

    public void setPerformer(Organization performer) {
        this.performer = performer;
    }

    public Organization getCustomer() {
        return customer;
    }

    public void setCustomer(Organization customer) {
        this.customer = customer;
    }

}