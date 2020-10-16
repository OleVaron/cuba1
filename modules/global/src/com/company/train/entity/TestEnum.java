package com.company.train.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum TestEnum implements EnumClass<String> {

    ;

    private String id;

    TestEnum(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TestEnum fromId(String id) {
        for (TestEnum at : TestEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}