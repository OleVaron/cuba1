package com.company.train.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ContractStausEnum implements EnumClass<Integer> {

    NEW(10),
    ON_APPROVAL(20),
    ACTIVE(30),
    COMPLETE(40),
    CANCEL(50);

    private Integer id;

    ContractStausEnum(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ContractStausEnum fromId(Integer id) {
        for (ContractStausEnum at : ContractStausEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}