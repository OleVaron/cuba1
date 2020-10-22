package com.company.train.web.screens;

import com.company.train.entity.Contract;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@UiController("train_Chart")
@UiDescriptor("chart.xml")
public class Chart extends Screen {

    public static final String CONTRACT_VIEW = "contract-view";
    @Inject
    private CollectionContainer<Contract> contractDC;
    @Inject
    private DataManager dataManager;

    @Subscribe
    private void onInit(InitEvent event) {
        List<Contract> train_contract = dataManager.load(Contract.class)
                .query("select c from train_Contract c").view(CONTRACT_VIEW).list();
        contractDC.setItems(train_contract);
    }

}