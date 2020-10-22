package com.company.train.listeners.process;

import com.company.train.entity.Contract;
import com.company.train.service.ContractStatusService;
import com.haulmont.addon.bproc.data.Outcome;
import com.haulmont.addon.bproc.data.OutcomesContainer;
import com.haulmont.addon.bproc.events.UserTaskCompletedEvent;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component(TaskCompletedNotificationSender.NAME)
public class TaskCompletedNotificationSender {
    public static final String NAME = "ref_TaskCompletedNotificationSender";
    public static final String CONTRACT = "contract";

    @Inject
    private BprocRuntimeService bprocRuntimeService;
    @Inject
    private ContractStatusService contractStatusService;

    @EventListener
    protected void onTaskCompleted(UserTaskCompletedEvent event) {
        Date date = new Date(0);
        String outcomeId = "";
        Map<String, Object> variables = bprocRuntimeService.getVariables(event.getTaskData().getExecutionId());
        for (Map.Entry<String, Object> entry:variables.entrySet()) {
            if (entry.getValue() instanceof OutcomesContainer) {
                for (Outcome outcome: ((OutcomesContainer) entry.getValue()).getOutcomes()) {
                    if (outcome.getDate().after(date)) {
                        date = outcome.getDate();
                        outcomeId = outcome.getOutcomeId();
                    }
                }
            }
        }
        Contract contract = (Contract) bprocRuntimeService.getVariable(event.getTaskData().getExecutionId(), CONTRACT);
        contractStatusService.setStatus(contract, outcomeId);
    }
}
