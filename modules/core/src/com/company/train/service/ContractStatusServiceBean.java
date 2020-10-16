package com.company.train.service;

import com.company.train.entity.Contract;
import com.haulmont.addon.bproc.entity.TaskData;
import com.haulmont.addon.bproc.events.UserTaskAssignedEvent;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.security.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service(ContractStatusService.NAME)
public class ContractStatusServiceBean implements ContractStatusService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ContractStatusServiceBean.class);
    @Inject
    private DataManager dataManager;
    @Inject
    private EmailService emailService;

    @Override
    public void setStatus(Contract contract, String status) {
        Contract reloadedContract = dataManager.reload(contract, View.LOCAL);
        reloadedContract.setStatus(status);
        dataManager.commit(reloadedContract);
        sendEmail(contract, status);
    }

    protected void sendEmail(Contract contract, String status) {
        //contract-view
        Contract reloadedContract = dataManager.reload(contract , "contract-view");
        for (String email: Arrays.asList(reloadedContract.getPerformer().getEmail(), reloadedContract.getCustomer().getEmail())) {
            String emailTitle = "Status for contract has been changed on " + status;
            String emailBodyTemplatePath = "com/haulmont/bproc/ref/notification/task-email-body.template";
            Map<String, Serializable> templateParameters = new HashMap<>();
            EmailInfo emailInfo = new EmailInfo(
                    email,
                    emailTitle,
                    null,
                    emailBodyTemplatePath,
                    templateParameters
            );
//            emailService.sendEmailAsync(emailInfo);
            log.debug(emailTitle);
        }
    }

}