create table TRAIN_CONTRACT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TYPE_ENTITY_ID uuid,
    TYPE_STRING_ENTITY_ID varchar(255),
    TYPE_INT_ENTITY_ID integer,
    TYPE_LONG_ENTITY_ID bigint,
    --
    CUSTOMER_ID uuid,
    PERFORMER_ID uuid,
    NUMBER_ integer,
    SIGNED_DATE date,
    DATE_FROM date,
    DATE_TO date,
    AMOUNT decimal(19, 2),
    VAT decimal(19, 2),
    TOTAL_AMOUNT decimal(19, 2),
    CUSTOMER_SIGNER varchar(255),
    PERFORMER_SIGNER varchar(255),
    STATUS_ID uuid,
    FILES_ID uuid,
    --
    primary key (ID)
);