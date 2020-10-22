-- begin TRAIN_CONTRACT
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
    AMOUNT decimal(19, 2) not null,
    VAT decimal(19, 2),
    TOTAL_AMOUNT decimal(19, 2),
    CUSTOMER_SIGNER varchar(255),
    PERFORMER_SIGNER varchar(255),
    STATUS varchar(255),
    FILES_ID uuid,
    --
    primary key (ID)
)^
-- end TRAIN_CONTRACT
-- begin TRAIN_STATUS
create table TRAIN_STATUS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE integer,
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end TRAIN_STATUS
-- begin TRAIN_STAGE
create table TRAIN_STAGE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    DATE_FROM date,
    DATE_TO date,
    AMOUNT decimal(19, 2),
    VAT decimal(19, 2),
    TOTAL_AMOUNT decimal(19, 2),
    DESCRIPTION text,
    CONTRACT_ID uuid,
    --
    primary key (ID)
)^
-- end TRAIN_STAGE
-- begin TRAIN_ORGANIZATION
create table TRAIN_ORGANIZATION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    EMAIL varchar(255),
    TAX_NUMBER integer,
    REGISTRATION_NUMBER integer,
    ESCAPE_VAT boolean,
    --
    primary key (ID)
)^
-- end TRAIN_ORGANIZATION
-- begin TRAIN_INVOICE
create table TRAIN_INVOICE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ integer,
    DATE_ date,
    AMOUNT decimal(19, 2),
    STAGE_ID uuid,
    VAT decimal(19, 2),
    TOTAL_AMOUNT decimal(19, 2),
    DESCRIPTION varchar(255),
    CONTACT_ID uuid,
    --
    primary key (ID)
)^
-- end TRAIN_INVOICE
-- begin TRAIN_CONTRACT_TYPE
create table TRAIN_CONTRACT_TYPE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FIX_PRICE decimal(19, 2),
    TIME_AND_MATERIAL varchar(255),
    --
    primary key (ID)
)^
-- end TRAIN_CONTRACT_TYPE
-- begin TRAIN_SERVICE_COMPLETION_CERTIFICATE
create table TRAIN_SERVICE_COMPLETION_CERTIFICATE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ integer,
    DATE_ date,
    AMOUNT decimal(19, 2),
    VAT decimal(19, 2),
    TOTAL_AMOUNT decimal(19, 2),
    DESCRIPTION text,
    FILES_ID uuid,
    STAGE_ID uuid,
    --
    primary key (ID)
)^
-- end TRAIN_SERVICE_COMPLETION_CERTIFICATE
-- begin TRAIN_INVOICE_FILE_DESCRIPTOR_LINK
create table TRAIN_INVOICE_FILE_DESCRIPTOR_LINK (
    INVOICE_ID uuid,
    FILE_DESCRIPTOR_ID uuid,
    primary key (INVOICE_ID, FILE_DESCRIPTOR_ID)
)^
-- end TRAIN_INVOICE_FILE_DESCRIPTOR_LINK
