--liquibase formatted sql
--changeset kbiernat:18
create table users
(
    id       bigint       not null auto_increment PRIMARY KEY,
    username varchar(50)  not null unique,
    password varchar(500) not null,
    enabled  boolean      not null
);
--changeset kbiernat:19
create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
--changeset kbiernat:20
create unique index ix_auth_username on authorities (username, authority);
--changeset kbiernat:21
insert into users (id, username, password, enabled)
values (1, 'admin', '{bcrypt}$2a$10$AyGNt.m6wM76yin7k0nmUefqy874ynmsgUF0sfBfBDZvFZrL5uQLe', true);
insert into authorities (username, authority)
values ('admin', 'ROLE_ADMIN');