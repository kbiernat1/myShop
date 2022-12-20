--liquibase formatted sql
--changeset kbiernat:4
alter table product add slug varchar(255) after img;
alter table product add constraint ui_product_slug unique key(slug);