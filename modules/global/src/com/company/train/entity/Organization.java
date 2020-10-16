package com.company.train.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Table(name = "TRAIN_ORGANIZATION")
@Entity(name = "train_Organization")
@NamePattern("%s|name")
public class Organization extends StandardEntity {
    private static final long serialVersionUID = 7155722904981782559L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL")
    @Email
    private String email;

    @Column(name = "TAX_NUMBER")
    private Integer taxNumber;

    @Column(name = "REGISTRATION_NUMBER")
    private Integer registrationNumber;

    @Column(name = "ESCAPE_VAT")
    @NotNull
    private Boolean escapeVat;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEscapeVat(Boolean escapeVat) {
        this.escapeVat = escapeVat;
    }

    public Boolean getEscapeVat() {
        return escapeVat;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(Integer taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}