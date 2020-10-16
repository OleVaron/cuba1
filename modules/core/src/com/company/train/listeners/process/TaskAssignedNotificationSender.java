package com.company.train.listeners.process;

import com.haulmont.addon.bproc.entity.TaskData;
import com.haulmont.addon.bproc.events.UserTaskAssignedEvent;
import com.haulmont.addon.bproc.events.UserTaskCompletedEvent;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.security.entity.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component(TaskAssignedNotificationSender.NAME)
public class TaskAssignedNotificationSender {

    public static final String NAME = "ref_TaskAssignedNotificationSender";

    @Inject
    protected EmailService emailService;

    @EventListener
    protected void onTaskAssigned(UserTaskAssignedEvent event) {
        User user = event.getUser();
        TaskData taskData = event.getTaskData();
        String emailTitle = "New process task " + taskData.getName();
        String emailBodyTemplatePath = "com/haulmont/bproc/ref/notification/task-email-body.template";
        Map<String, Serializable> templateParameters = new HashMap<>();
        templateParameters.put("user", user);
        templateParameters.put("taskData", taskData);
        EmailInfo emailInfo = new EmailInfo(
                user.getEmail(),
                emailTitle,
                null,
                emailBodyTemplatePath,
                templateParameters
        );
        emailService.sendEmailAsync(emailInfo);
    }

}
