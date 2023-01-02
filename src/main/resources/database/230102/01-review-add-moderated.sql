--liquibase formatted sql
--changeset kbiernat:10
alter table review add moderated boolean default false;