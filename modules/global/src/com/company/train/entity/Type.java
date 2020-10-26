package com.company.train.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "TRAIN_TYPE")
@Entity(name = "train_Type")
public class Type extends StandardEntity {
    private static final long serialVersionUID = 565107601043639386L;

    @Column(name = "FIX_PRICE")
    private BigDecimal fixPrice;

    @Column(name = "TIME_AND_MATERIAL")
    private String timeAndMaterial;

    @Column(name = "OUT_STAFF")
    private String outStaff;

    public String getOutStaff() {
        return outStaff;
    }

    public void setOutStaff(String outStaff) {
        this.outStaff = outStaff;
    }

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