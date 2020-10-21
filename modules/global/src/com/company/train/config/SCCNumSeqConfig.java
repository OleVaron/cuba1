package com.company.train.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.DefaultString;

@Source(type = SourceType.DATABASE)
public interface SCCNumSeqConfig extends Config {
    @Property("sequenceName")
    @DefaultString("SERVICE_COMPLETION_CERTIFICATE")
    String getSequenceName();
}