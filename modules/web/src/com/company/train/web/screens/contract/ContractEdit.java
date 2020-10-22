package com.company.train.web.screens.contract;

import com.company.train.config.VatConfig;
import com.company.train.entity.Invoice;
import com.company.train.entity.Organization;
import com.company.train.entity.Status;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.app.PersistenceManagerService;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.Contract;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@UiController("train_Contract.edit")
@UiDescriptor("contract-edit.xml")
@EditedEntityContainer("contractDc")
@LoadDataBeforeShow
public class ContractEdit extends StandardEditor<Contract> {

    private static final String PROCESS_DEFINITION_KEY = "contract-process";
    private static final String CONTRACT_STATUS_NEW = "New";
    public static final String CONTRACT = "contract";
    public static final String ADMINISTRATOR = "administrator";
    public static final String AMOUNT_FIELD = "amountField";

    private boolean isNewVisit;

    @Inject
    private Metadata metadata;
    @Inject
    private UserSession userSession;
    @Inject
    private BprocRuntimeService bprocRuntimeService;
    @Inject
    private MetadataTools metadataTools;
    @Inject
    private TextField<BigDecimal> amountField;
    @Inject
    private TextField<BigDecimal> totalAmountField;
    @Inject
    private TextField<BigDecimal> vatField;
    @Inject
    private PickerField<Organization> customerField;
    @Inject
    private Configuration configuration;

    @Subscribe(AMOUNT_FIELD)
    public void onAmountFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (customerField.getValue() == null)
            return;
        if (customerField.getValue().getEscapeVat()) {
            BigDecimal vat = amountField.getValue().multiply(BigDecimal.valueOf(configuration.getConfig(VatConfig.class).getVat()));
            vatField.setValue(vat);
            totalAmountField.setValue(vat.add(amountField.getValue()));
        } else {
            vatField.setValue(BigDecimal.ZERO);
            totalAmountField.setValue(amountField.getValue());
        }
    }

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
            pV.put(CONTRACT, contract);
            pV.put(ADMINISTRATOR, userSession.getCurrentOrSubstitutedUser());

            bprocRuntimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY,metadataTools.getInstanceName(contract),  pV);
        }
    }
    
    
}