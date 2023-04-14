--liquibase formatted sql
--changeset kbiernat:3
--validCheckSum: 8:c26df93da243f3816107b3afa77aad87
delete img from product;
alter table product add img varchar(128) after currency;