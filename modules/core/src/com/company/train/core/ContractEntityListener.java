package com.company.train.core;

import com.company.train.config.ContractNumSeqConfig;
import com.company.train.config.DefaultStageNameConfig;
import com.company.train.config.VatConfig;
import com.company.train.entity.Contract;
import com.company.train.entity.Stage;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;

@Component("train_ContractEntityListener")
public class ContractEntityListener implements BeforeInsertEntityListener<Contract> {
    @Inject
    private Persistence persistence;
    @Inject
    private Metadata metadata;
    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;
    @Inject
    private Configuration configuration;

    public ContractEntityListener() {
        super();
    }

    @Override
    public void onBeforeInsert(Contract contract, EntityManager entityManager) {
        contract.setNumber(((Long)(uniqueNumbersAPI.getNextNumber(configuration.getConfig(ContractNumSeqConfig.class).getSequenceName()))).intValue());
        populateSummary(contract);
        if (CollectionUtils.isEmpty(contract.getStages())) {
            Stage stage = getStageFromContract(contract);
            entityManager.persist(stage);
            contract.setStages(Arrays.asList(stage));
        }
        entityManager.persist(contract);
    }

    protected void populateSummary(Contract contract) {
        if (contract.getCustomer().getEscapeVat() != null && contract.getCustomer().getEscapeVat()) {
            contract.setVat(BigDecimal.ZERO);
            contract.setTotalAmount(contract.getAmount());
        } else {
            contract.setVat(contract.getAmount().multiply(BigDecimal.valueOf(configuration.getConfig(VatConfig.class).getVat())));
            contract.setTotalAmount(contract.getVat().add(contract.getAmount()));
        }
    }

    protected Stage getStageFromContract(Contract contract) {

        Stage stage = metadata.create(Stage.class);

        stage.setName(configuration.getConfig(DefaultStageNameConfig.class).getDefaultName());
        stage.setAmount(contract.getAmount());
        stage.setContract(contract);
        stage.setVat(contract.getVat());
        stage.setDateFrom(contract.getDateFrom());
        stage.setDateTo(contract.getDateTo());
        stage.setTotalAmount(contract.getTotalAmount());

        return stage;
    }




}
