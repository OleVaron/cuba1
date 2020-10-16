package com.company.train.web.screens.servicecompletioncertificate;

import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.ServiceCompletionCertificate;

@UiController("train_ServiceCompletionCertificate.browse")
@UiDescriptor("service-completion-certificate-browse.xml")
@LookupComponent("serviceCompletionCertificatesTable")
@LoadDataBeforeShow
public class ServiceCompletionCertificateBrowse extends StandardLookup<ServiceCompletionCertificate> {

}