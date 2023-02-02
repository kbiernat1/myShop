--liquibase formatted sql
--changeset kbiernat:16
alter table `order` add payment_id bigint;
update `order` set payment_id=1;
alter table `order` modify payment_id bigint not null;