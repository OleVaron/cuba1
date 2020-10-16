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

    @Inject
    private CollectionContainer<Contract> contractDC;
    @Inject
    private DataManager dataManager;

    @Subscribe
    private void onInit(InitEvent event) {
        LoadContext<Contract> contractLoadContext = new LoadContext<Contract>(Contract.class);
        List<Contract> items = new ArrayList<>();
        List<Contract> train_contract = dataManager.load(Contract.class)
                .query("select c from train_Contract c").view("contract-view").list();
//        items.add(countryGrowth("UK", 1.7, 3.1));
        contractDC.setItems(train_contract);
    }

//    private CountryGrowth countryGrowth(String country, double year2014, double year2015) {
//        CountryGrowth cg = new CountryGrowth();
//        cg.setCountry(country);
//        cg.setYear2014(year2014);
//        cg.setYear2015(year2015);
//        return cg;
//    }

}