package com.company.train.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "TRAIN_STATUS")
@Entity(name = "train_Status")
@NamePattern("%s|name")
public class Status extends StandardEntity {
    private static final long serialVersionUID = -783600624748040419L;

    @Column(name = "CODE")
    private Integer code;

    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}