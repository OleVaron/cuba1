package com.company.train.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.math.BigDecimal;

@MetaClass(name = "train_SummaryPrice")
public class SummaryPrice extends BaseUuidEntity {
    private static final long serialVersionUID = 8233814172168398014L;

    @MetaProperty
    private String contract;

    @MetaProperty
    private BigDecimal totalAmount;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }
}