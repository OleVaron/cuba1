alter table TRAIN_SERVICE_COMPLETION_CERTIFICATE_FILE_DESCRIPTOR_LINK add constraint FK_SERCOMCERFILDES_ON_SERVICE_COMPLETION_CERTIFICATE foreign key (SERVICE_COMPLETION_CERTIFICATE_ID) references TRAIN_SERVICE_COMPLETION_CERTIFICATE(ID);
alter table TRAIN_SERVICE_COMPLETION_CERTIFICATE_FILE_DESCRIPTOR_LINK add constraint FK_SERCOMCERFILDES_ON_FILE_DESCRIPTOR foreign key (FILE_DESCRIPTOR_ID) references SYS_FILE(ID);