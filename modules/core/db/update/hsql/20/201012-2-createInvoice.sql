alter table TRAIN_INVOICE add constraint FK_TRAIN_INVOICE_ON_STAGE foreign key (STAGE_ID) references TRAIN_STAGE(ID);
create index IDX_TRAIN_INVOICE_ON_STAGE on TRAIN_INVOICE (STAGE_ID);
