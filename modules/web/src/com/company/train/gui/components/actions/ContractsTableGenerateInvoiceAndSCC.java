package com.company.train.gui.components.actions;

import com.company.train.entity.Contract;
import com.company.train.service.StagesService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.ActionType;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.cuba.gui.screen.compatibility.LegacyFrame;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;

import static com.company.train.web.screens.contract.ContractBrowse.CONTRACT_IS_CREATED;

@ActionType("generateInvoiceAndSCC")
@Scope("prototype")
public class ContractsTableGenerateInvoiceAndSCC extends ItemTrackingAction {

    @Inject
    private StagesService stagesService;
    @Inject
    private Messages messages;

    public ContractsTableGenerateInvoiceAndSCC(String id) {
        super(id);
    }

    @Override
    public void actionPerform(Component component) {
        final Contract contract = (Contract)target.getSingleSelected();
        stagesService.generateInvoicesAndActsFromStages(contract.getStages());
        target.getFrame().showNotification(messages.getMessage(this.getClass(), CONTRACT_IS_CREATED)); //Notification bean is not available.
    }

}
