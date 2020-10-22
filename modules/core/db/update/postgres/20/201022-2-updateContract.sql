update TRAIN_CONTRACT set AMOUNT = 0 where AMOUNT is null ;
alter table TRAIN_CONTRACT alter column AMOUNT set not null ;
