package com.company.train.service;

import com.company.train.entity.Contract;

public interface ContractStatusService {
    String NAME = "train_ContractStatusService";

    void setStatus(Contract contract, String status);
}