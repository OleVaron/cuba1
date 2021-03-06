alter table TRAIN_CONTRACT add constraint FK_TRAIN_CONTRACT_ON_CUSTOMER foreign key (CUSTOMER_ID) references TRAIN_ORGANIZATION(ID);
alter table TRAIN_CONTRACT add constraint FK_TRAIN_CONTRACT_ON_PERFORMER foreign key (PERFORMER_ID) references TRAIN_ORGANIZATION(ID);
alter table TRAIN_CONTRACT add constraint FK_TRAIN_CONTRACT_ON_STATUS foreign key (STATUS_ID) references TRAIN_STATUS(ID);
alter table TRAIN_CONTRACT add constraint FK_TRAIN_CONTRACT_ON_FILES foreign key (FILES_ID) references SYS_FILE(ID);
create index IDX_TRAIN_CONTRACT_ON_CUSTOMER on TRAIN_CONTRACT (CUSTOMER_ID);
create index IDX_TRAIN_CONTRACT_ON_PERFORMER on TRAIN_CONTRACT (PERFORMER_ID);
create index IDX_TRAIN_CONTRACT_ON_STATUS on TRAIN_CONTRACT (STATUS_ID);
create index IDX_TRAIN_CONTRACT_ON_FILES on TRAIN_CONTRACT (FILES_ID);
