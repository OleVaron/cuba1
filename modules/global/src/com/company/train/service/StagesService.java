package com.company.train.service;

import com.company.train.entity.Stage;

import java.util.List;

public interface StagesService {
    String NAME = "train_StagesService";

    public void generateInvoicesAndActsFromStages(List<Stage> stages);

}