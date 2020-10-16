package com.company.train.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "TRAIN_CONTRACT_TYPE")
@Entity(name = "train_ContractType")
public class ContractType extends StandardEntity {
    private static final long serialVersionUID = 7901513663807964143L;

    @Column(name = "FIX_PRICE")
    private BigDecimal fixPrice;

    @Column(name = "TIME_AND_MATERIAL")
    private String timeAndMaterial;

    public String getTimeAndMaterial() {
        return timeAndMaterial;
    }

    public void setTimeAndMaterial(String timeAndMaterial) {
        this.timeAndMaterial = timeAndMaterial;
    }

    public BigDecimal getFixPrice() {
        return fixPrice;
    }

    public void setFixPrice(BigDecimal fixPrice) {
        this.fixPrice = fixPrice;
    }
}