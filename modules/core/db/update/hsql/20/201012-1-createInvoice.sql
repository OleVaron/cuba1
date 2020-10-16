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
    --
    primary key (ID)
);