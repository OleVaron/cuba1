alter table TRAIN_INVOICE_FILE_DESCRIPTOR_LINK add constraint FK_INVFILDES_ON_INVOICE foreign key (INVOICE_ID) references TRAIN_INVOICE(ID);
alter table TRAIN_INVOICE_FILE_DESCRIPTOR_LINK add constraint FK_INVFILDES_ON_FILE_DESCRIPTOR foreign key (FILE_DESCRIPTOR_ID) references SYS_FILE(ID);