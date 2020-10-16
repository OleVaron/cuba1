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
);