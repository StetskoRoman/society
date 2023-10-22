
create sequence usr_seq start with 1 increment by 1;
create table message
(
    id       int8 not null,
    user_id  int8,
    filename varchar(255),
    tag      varchar(255),
    text     varchar(2048) not null,
    primary key (id)
);
create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);
create table usr
(
    active          boolean not null,
    id              int8  not null,
    activation_code varchar(255),
    email           varchar(255),
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (id)
);
-- maybe not necessary
alter table if exists message
    add constraint message_user_fk foreign key (user_id) references usr;
alter table if exists user_role
    add constraint user_role_user_fk foreign key (user_id) references usr

