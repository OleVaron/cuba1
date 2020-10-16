-- begin TRAIN_CONTRACT
create table TRAIN_CONTRACT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TYPE_ENTITY_ID varchar(36),
    TYPE_STRING_ENTITY_ID varchar(255),
    TYPE_INT_ENTITY_ID integer,
    TYPE_LONG_ENTITY_ID bigint,
    --
    CUSTOMER_ID varchar(36),
    PERFORMER_ID varchar(36),
    NUMBER_ integer,
    SIGNED_DATE date,
    DATE_FROM date,
    DATE_TO date,
    AMOUNT decimal(19, 2),
    VAT decimal(19, 2),
    TOTAL_AMOUNT decimal(19, 2),
    CUSTOMER_SIGNER varchar(255),
    PERFORMER_SIGNER varchar(255),
    STATUS_ID varchar(36),
    FILES_ID varchar(36),
    --
    primary key (ID)
)^
-- end TRAIN_CONTRACT
-- begin TRAIN_STATUS
create table TRAIN_STATUS (
    ID varchar(36) not null,
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
    ID varchar(36) not null,
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
    DESCRIPTION longvarchar,
    CONTRACT_ID varchar(36),
    --
    primary key (ID)
)^
-- end TRAIN_STAGE
-- begin TRAIN_ORGANIZATION
create table TRAIN_ORGANIZATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    TAX_NUMBER integer,
    REGISTRATION_NUMBER integer,
    ESCAPE_VAT boolean,
    --
    primary key (ID)
)^
-- end TRAIN_ORGANIZATION
-- begin TRAIN_CONTRACT_TYPE
create table TRAIN_CONTRACT_TYPE (
    ID varchar(36) not null,
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
-- begin TRAIN_INVOICE
create table TRAIN_INVOICE (
    ID varchar(36) not null,
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
    STAGE_ID varchar(36),
    VAT decimal(19, 2),
    TOTAL_AMOUNT decimal(19, 2),
    DESCRIPTION varchar(255),
    CONTACT_ID varchar(36),
    --
    primary key (ID)
)^
-- end TRAIN_INVOICE
-- begin TRAIN_SERVICE_COMPLETION_CERTIFICATE
create table TRAIN_SERVICE_COMPLETION_CERTIFICATE (
    ID varchar(36) not null,
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
    DESCRIPTION longvarchar,
    FILES_ID varchar(36),
    STAGE_ID varchar(36),
    --
    primary key (ID)
)^
-- end TRAIN_SERVICE_COMPLETION_CERTIFICATE
-- begin TRAIN_INVOICE_FILE_DESCRIPTOR_LINK
create table TRAIN_INVOICE_FILE_DESCRIPTOR_LINK (
    INVOICE_ID varchar(36) not null,
    FILE_DESCRIPTOR_ID varchar(36) not null,
    primary key (INVOICE_ID, FILE_DESCRIPTOR_ID)
)^
-- end TRAIN_INVOICE_FILE_DESCRIPTOR_LINK
