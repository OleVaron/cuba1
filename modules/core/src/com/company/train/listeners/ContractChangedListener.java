package com.company.train.listeners;

import com.company.train.entity.Contract;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;

@Component("train_ContractChangedListener")
public class ContractChangedListener {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ContractChangedListener.class);

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<Contract, UUID> event) {
        log.info("");
    }

}