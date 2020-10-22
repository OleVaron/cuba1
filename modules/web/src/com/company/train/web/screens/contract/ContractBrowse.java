package com.company.train.web.screens.contract;

import com.company.train.entity.Invoice;
import com.company.train.entity.Stage;
import com.company.train.service.StagesService;
import com.haulmont.charts.gui.components.charts.PieChart;
import com.haulmont.charts.gui.data.MapDataItem;
import com.haulmont.cuba.core.app.PersistenceManagerService;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.Messages;
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

    public static final String CONTRACT_NOT_SELECTED = "Contract.notSelected";
    public static final String CONTRACT_IS_CREATED = "Contract.invoiceAndSCCCreated";
    public static final String CONTRACTS_TABLE_GENERATE_INVOICE_AND_SCC = "contractsTable.generateInvoiceAndSCC";
    @Inject
    private GroupTable<Contract> contractsTable;
    @Inject
    private StagesService stagesService;
    @Inject
    private Notifications notifications;
    @Inject
    private Messages messages;

    @Subscribe(CONTRACTS_TABLE_GENERATE_INVOICE_AND_SCC)
    public void onContractsTableGenerateInvoiceAndSCC(Action.ActionPerformedEvent event) {
        Contract contract = contractsTable.getSingleSelected();
        if (contract!=null) {
            stagesService.generateInvoicesAndActsFromStages(contract.getStages());
            notifications.create().withCaption(messages.getMessage(this.getClass(), CONTRACT_IS_CREATED)).show();
        } else {
            notifications.create().withCaption(messages.getMessage(this.getClass(), CONTRACT_NOT_SELECTED)).show();
        }
    }

}