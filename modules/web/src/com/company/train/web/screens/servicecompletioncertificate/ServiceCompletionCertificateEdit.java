package com.company.train.web.screens.servicecompletioncertificate;

import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.ServiceCompletionCertificate;

@UiController("train_ServiceCompletionCertificate.edit")
@UiDescriptor("service-completion-certificate-edit.xml")
@EditedEntityContainer("serviceCompletionCertificateDc")
@LoadDataBeforeShow
public class ServiceCompletionCertificateEdit extends StandardEditor<ServiceCompletionCertificate> {
}