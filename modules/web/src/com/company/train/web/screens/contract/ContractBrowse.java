package com.company.train.web.screens.contract;

import com.company.train.entity.Invoice;
import com.company.train.entity.Stage;
import com.company.train.service.StagesService;
import com.haulmont.charts.gui.components.charts.PieChart;
import com.haulmont.charts.gui.data.MapDataItem;
import com.haulmont.cuba.core.app.PersistenceManagerService;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.Contract;

import javax.inject.Inject;
import java.util.Date;

@UiController("train_Contract.browse")
@UiDescriptor("contract-browse.xml")
@LookupComponent("contractsTable")
@LoadDataBeforeShow
public class ContractBrowse extends StandardLookup<Contract> {

    @Inject
    private GroupTable<Contract> contractsTable;
    @Inject
    private StagesService stagesService;
    @Inject
    private Notifications notifications;
    @Inject
    private PieChart pieChart;

    @Subscribe("contractsTable.generateInvoiceAndSCC")
    public void onContractsTableGenerateInvoiceAndSCC(Action.ActionPerformedEvent event) {
        Contract contract = contractsTable.getSingleSelected();
        if (contract!=null) {
            stagesService.generateInvoicesAndActsFromStages(contract.getStages());
        } else {
            notifications.create().withCaption("contract not selected").show();//todo msg to bundle
        }
    }

}