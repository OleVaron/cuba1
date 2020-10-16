package com.company.train.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;
import com.haulmont.cuba.core.config.defaults.DefaultDouble;
import com.haulmont.cuba.core.config.defaults.DefaultInteger;
import com.haulmont.cuba.core.config.defaults.DefaultString;

import java.math.BigDecimal;

@Source(type = SourceType.DATABASE)
public interface VatConfig extends Config {
    @Property("vat")
    @DefaultDouble(0.1)
    double getVat();//%
}