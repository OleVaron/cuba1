package com.company.train.web.screens.contract;

import com.company.train.entity.Invoice;
import com.company.train.entity.Status;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.app.PersistenceManagerService;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.Contract;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@UiController("train_Contract.edit")
@UiDescriptor("contract-edit.xml")
@EditedEntityContainer("contractDc")
@LoadDataBeforeShow
public class ContractEdit extends StandardEditor<Contract> {

    private static final String PROCESS_DEFINITION_KEY = "contract-process";
    private static final String CONTRACT_STATUS_NEW = "New";

    private boolean isNewVisit;

    @Inject
    private Metadata metadata;
    @Inject
    private UserSession userSession;
    @Inject
    private BprocRuntimeService bprocRuntimeService;
    @Inject
    private MetadataTools metadataTools;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Contract> event) {
        event.getEntity().setStatus(CONTRACT_STATUS_NEW);
        isNewVisit = true;
    }

    @Subscribe
    public void onAfterCommitChanges(AfterCommitChangesEvent event) {
        if (isNewVisit) {
            Contract contract = getEditedEntity();
            Map<String, Object> pV = new HashMap<>();
            pV.put("contract", contract);
            pV.put("administrator", userSession.getCurrentOrSubstitutedUser());

            bprocRuntimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY,metadataTools.getInstanceName(contract),  pV);
        }
    }
    
    
}