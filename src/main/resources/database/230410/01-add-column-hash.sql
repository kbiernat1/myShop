--liquibase formatted sql
--changeset kbiernat:24
alter table users add hash varchar(120);
--changeset kbiernat:25
alter table users add hash_date datetime;
