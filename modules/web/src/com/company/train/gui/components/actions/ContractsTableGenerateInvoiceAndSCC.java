package com.company.train.gui.components.actions;

import com.company.train.entity.Contract;
import com.company.train.service.StagesService;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.ActionType;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction;
import com.haulmont.cuba.gui.screen.LookupComponent;

import javax.inject.Inject;

@ActionType("generateInvoiceAndSCC")

public class ContractsTableGenerateInvoiceAndSCC extends ItemTrackingAction {

    @Inject
    private MetadataTools metadataTools;
//    @Inject
//    private GroupTable<Contract> contractsTable;
    @Inject
    private StagesService stagesService;
//    @Inject
//    private Notifications notifications;

    public ContractsTableGenerateInvoiceAndSCC(String id) {
        super(id);
    }

//    @Override
//    public void actionPerform(Component component) {
//        Contract contract = ((GroupTable<Contract>)component.getParent().getParent()).getSingleSelected();
//        setCaption("Generate invoice and scc");
//        if (contract!=null) {
//            stagesService.generateInvoicesAndActsFromStages(contract.getStages());
//        } else {

//            notifications.create().withCaption("contract not selected").show();//
//        }
//    }

}
