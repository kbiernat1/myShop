--liquibase formatted sql
--changeset kbiernat:3
alter table product add img varchar(128) after currency;