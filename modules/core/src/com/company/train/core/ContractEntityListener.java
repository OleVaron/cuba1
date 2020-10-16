package com.company.train.core;

import com.company.train.config.VatConfig;
import com.company.train.entity.Contract;
import com.company.train.entity.Stage;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.reports.app.service.ReportService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.math.BigDecimal;

@Component("train_ContractEntityListener")
public class ContractEntityListener implements BeforeInsertEntityListener<Contract> {
    @Inject
    private Persistence persistence;
    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;
    @Inject
    private Configuration configuration;
    @Inject
    private ReportService reportService;

    public ContractEntityListener() {
        super();
    }

    @Override
    public void onBeforeInsert(Contract contract, EntityManager entityManager) {
        contract.setNumber(((Long)(uniqueNumbersAPI.getNextNumber("CONTRACT_NUM"))).intValue());//todo to CONG
        populateSummary(contract);
        if (CollectionUtils.isEmpty(contract.getStages())) {
            persistence.getEntityManager().persist(getStageFromContract(contract));
        }
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

        stage.setName("Default stage");//todo to CONG
        stage.setAmount(contract.getAmount());
        stage.setContract(contract);
        stage.setVat(contract.getVat());
        stage.setDateFrom(contract.getDateFrom());
        stage.setDateTo(contract.getDateTo());
        stage.setTotalAmount(contract.getTotalAmount());

        return stage;
    }




}
