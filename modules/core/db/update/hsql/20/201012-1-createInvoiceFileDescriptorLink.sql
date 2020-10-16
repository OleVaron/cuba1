create table TRAIN_INVOICE_FILE_DESCRIPTOR_LINK (
    INVOICE_ID varchar(36) not null,
    FILE_DESCRIPTOR_ID varchar(36) not null,
    primary key (INVOICE_ID, FILE_DESCRIPTOR_ID)
);
