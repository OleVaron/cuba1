package com.company.train.service;

import com.company.train.config.NotificationTaskEmailBodyTemplateConfig;
import com.company.train.entity.Contract;
import com.haulmont.addon.bproc.entity.TaskData;
import com.haulmont.addon.bproc.events.UserTaskAssignedEvent;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.app.LocalizedMessageService;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service(ContractStatusService.NAME)
public class ContractStatusServiceBean implements ContractStatusService {

    private static final Logger log = LoggerFactory.getLogger(ContractStatusServiceBean.class);
    public static final String CONTRACT_VIEW = "contract-view";
    public static final String STATUS_FOR_CONTRACT_HAS_BEEN_CHANGED_ON = "statusForContractChangedMailTitle";

    @Inject
    private DataManager dataManager;
    @Inject
    private EmailService emailService;
    @Inject
    private Messages messages;
    @Inject
    private Configuration configuration;

    @Override
    public void setStatus(Contract contract, String status) {
        Contract reloadedContract = dataManager.reload(contract, View.LOCAL);
        reloadedContract.setStatus(status);
        dataManager.commit(reloadedContract);
        sendEmail(contract, status);
    }

    protected void sendEmail(Contract contract, String status) {
        Contract reloadedContract = dataManager.reload(contract , CONTRACT_VIEW);
        for (String email: Arrays.asList(reloadedContract.getPerformer().getEmail(), reloadedContract.getCustomer().getEmail())) {
            String emailTitle = messages.getMessage(this.getClass(), STATUS_FOR_CONTRACT_HAS_BEEN_CHANGED_ON) + status;
            EmailInfo emailInfo = EmailInfoBuilder.create()
                    .setAddresses(email)
                    .setCaption(emailTitle)
                    .setFrom(null)
                    .setTemplatePath(configuration.getConfig(NotificationTaskEmailBodyTemplateConfig.class).getTemplatePath())
                    .build();
//            emailService.sendEmailAsync(emailInfo);
            log.debug(emailTitle);
        }
    }

}