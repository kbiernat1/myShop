--liquibase formatted sql
--changeset kbiernat:1
--validCheckSum: 8:cd2cfd8472be45e54973d8f3ad4b23f8
create table product(
    id bigint not null auto_increment PRIMARY KEY,
    name varchar(255) not null,
    category varchar(255) not null,
    description text not null,
--     img varchar(255) not null,
    price decimal(9,2) not null,
    currency varchar(3) not null
);