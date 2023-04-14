--liquibase formatted sql
--changeset kbiernat:22
alter table `order` add user_id bigint;
--changeset kbiernat:23
alter table `order` add constraint fk_order_user_id foreign key (user_id) references users(id);