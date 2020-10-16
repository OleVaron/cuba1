-- begin TRAIN_CONTRACT
alter table TRAIN_CONTRACT add constraint FK_TRAIN_CONTRACT_ON_CUSTOMER foreign key (CUSTOMER_ID) references TRAIN_ORGANIZATION(ID)^
alter table TRAIN_CONTRACT add constraint FK_TRAIN_CONTRACT_ON_PERFORMER foreign key (PERFORMER_ID) references TRAIN_ORGANIZATION(ID)^
alter table TRAIN_CONTRACT add constraint FK_TRAIN_CONTRACT_ON_FILES foreign key (FILES_ID) references SYS_FILE(ID)^
create index IDX_TRAIN_CONTRACT_ON_CUSTOMER on TRAIN_CONTRACT (CUSTOMER_ID)^
create index IDX_TRAIN_CONTRACT_ON_PERFORMER on TRAIN_CONTRACT (PERFORMER_ID)^
create index IDX_TRAIN_CONTRACT_ON_FILES on TRAIN_CONTRACT (FILES_ID)^
-- end TRAIN_CONTRACT
-- begin TRAIN_STAGE
alter table TRAIN_STAGE add constraint FK_TRAIN_STAGE_ON_CONTRACT foreign key (CONTRACT_ID) references TRAIN_CONTRACT(ID)^
create index IDX_TRAIN_STAGE_ON_CONTRACT on TRAIN_STAGE (CONTRACT_ID)^
-- end TRAIN_STAGE
-- begin TRAIN_INVOICE
alter table TRAIN_INVOICE add constraint FK_TRAIN_INVOICE_ON_STAGE foreign key (STAGE_ID) references TRAIN_STAGE(ID)^
alter table TRAIN_INVOICE add constraint FK_TRAIN_INVOICE_ON_CONTACT foreign key (CONTACT_ID) references TRAIN_CONTRACT(ID)^
create index IDX_TRAIN_INVOICE_ON_STAGE on TRAIN_INVOICE (STAGE_ID)^
create index IDX_TRAIN_INVOICE_ON_CONTACT on TRAIN_INVOICE (CONTACT_ID)^
-- end TRAIN_INVOICE
-- begin TRAIN_SERVICE_COMPLETION_CERTIFICATE
alter table TRAIN_SERVICE_COMPLETION_CERTIFICATE add constraint FK_TRAIN_SERVICE_COMPLETION_CERTIFICATE_ON_FILES foreign key (FILES_ID) references SYS_FILE(ID)^
alter table TRAIN_SERVICE_COMPLETION_CERTIFICATE add constraint FK_TRAIN_SERVICE_COMPLETION_CERTIFICATE_ON_STAGE foreign key (STAGE_ID) references TRAIN_STAGE(ID)^
create index IDX_TRAIN_SERVICE_COMPLETION_CERTIFICATE_ON_FILES on TRAIN_SERVICE_COMPLETION_CERTIFICATE (FILES_ID)^
create index IDX_TRAIN_SERVICE_COMPLETION_CERTIFICATE_ON_STAGE on TRAIN_SERVICE_COMPLETION_CERTIFICATE (STAGE_ID)^
-- end TRAIN_SERVICE_COMPLETION_CERTIFICATE
-- begin TRAIN_INVOICE_FILE_DESCRIPTOR_LINK
alter table TRAIN_INVOICE_FILE_DESCRIPTOR_LINK add constraint FK_INVFILDES_ON_INVOICE foreign key (INVOICE_ID) references TRAIN_INVOICE(ID)^
alter table TRAIN_INVOICE_FILE_DESCRIPTOR_LINK add constraint FK_INVFILDES_ON_FILE_DESCRIPTOR foreign key (FILE_DESCRIPTOR_ID) references SYS_FILE(ID)^
-- end TRAIN_INVOICE_FILE_DESCRIPTOR_LINK