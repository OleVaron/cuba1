package com.company.train.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.DefaultString;

@Source(type = SourceType.DATABASE)
public interface NotificationTaskEmailBodyTemplateConfig extends Config {
    @Property("templatePath")
    @DefaultString("com/haulmont/bproc/ref/notification/task-email-body.template")
    String getTemplatePath();
}