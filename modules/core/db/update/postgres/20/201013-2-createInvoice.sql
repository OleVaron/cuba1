alter table TRAIN_INVOICE add constraint FK_TRAIN_INVOICE_ON_STAGE foreign key (STAGE_ID) references TRAIN_STAGE(ID);
alter table TRAIN_INVOICE add constraint FK_TRAIN_INVOICE_ON_CONTACT foreign key (CONTACT_ID) references TRAIN_CONTRACT(ID);
create index IDX_TRAIN_INVOICE_ON_STAGE on TRAIN_INVOICE (STAGE_ID);
create index IDX_TRAIN_INVOICE_ON_CONTACT on TRAIN_INVOICE (CONTACT_ID);