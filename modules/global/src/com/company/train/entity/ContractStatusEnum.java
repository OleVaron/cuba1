package com.company.train.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ContractStatusEnum implements EnumClass<Integer> {

    NEW(10),
    ON_APPROVAL(20),
    ACTIVE(30),
    COMPLETE(40),
    CANCEL(50);

    private Integer id;

    ContractStatusEnum(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ContractStatusEnum fromId(Integer id) {
        for (ContractStatusEnum at : ContractStatusEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}