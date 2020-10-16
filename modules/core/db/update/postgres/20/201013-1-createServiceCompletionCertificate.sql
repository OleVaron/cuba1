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
);