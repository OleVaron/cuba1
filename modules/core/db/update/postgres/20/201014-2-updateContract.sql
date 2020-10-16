alter table TRAIN_CONTRACT rename column status_id to status_id__u95511 ;
alter table TRAIN_CONTRACT drop constraint FK_TRAIN_CONTRACT_ON_STATUS ;
drop index IDX_TRAIN_CONTRACT_ON_STATUS ;
alter table TRAIN_CONTRACT add column STATUS varchar(255) ;
