create table type
(
    id   bigint ,
    name varchar(50) not null,
    "group" varchar(50) not null,
    constraint type_pk
        primary key (id)
);

create sequence type_seq as integer start with 6;

create table role
(
    id     bigint ,
    name   varchar(50) not null,
    status bigint      null,
    constraint role_pk
        primary key (id),
    constraint role_type_id_fk
        foreign key (status) references type(id)
);

create sequence role_sq as integer start with 4;

create table privilege
(
    id      bigint ,
    name    varchar(255) not null,
    "group" varchar(255) not null,
    constraint privilege_pk
        primary key (id)
);

create table roles_privileges
(
    role_id      bigint not null,
    privilege_id bigint not null,
    constraint FK5yjwxw2gvfyu76j3rgqwo685u
        foreign key (privilege_id) references privilege (id),
    constraint FK9h2vewsqh8luhfq71xokh4who
        foreign key (role_id) references role (id)
);

create table "user"
(
    id               bigint       not null
        primary key,
    username         varchar(50) not null,
    email            varchar(255) null,
    first_name       varchar(50) not null,
    last_name        varchar(50) not null,
    mother_last_name varchar(50) not null,
    password         varchar(255) not null,
    phone_number     varchar(10) not null,
    status           bigint       not null,
    constraint FKtn8gwnenl0giyhdhy5xw62v31
        foreign key (status) references type (id)
);

create sequence user_seq as integer start with 2;

create table users_roles
(
    role_id bigint not null,
    user_id bigint not null,
    constraint users_roles_pk
        primary key (role_id, user_id),
    constraint users_roles_role_id_fk
        foreign key (role_id) references role (id),
    constraint users_roles_user_id_fk
        foreign key (user_id) references "user" (id)
);

create table lot
(
    id    bigint ,
    name  varchar(50) not null unique,
    owner bigint      not null,
    status           bigint       null,
    last_month_paid DATE null,
    constraint lot_pk
        primary key (id),
    constraint lot_user_id_fk
        foreign key (owner) references "user" (id),
    constraint lot_status_id_fk
        foreign key (status) references type (id)
);

create sequence lot_seq as integer start with 1;

-- auto-generated definition
create table lot_receipt
(
    id         bigint
        primary key,
    lot_id     bigint      not null,
    receipt_id varchar(50) not null,
    month_paid date        not null,
    cost       DOUBLE PRECISION      not null,
    created_date timestamp  not null,
    constraint lot_receipt_lot_id_fk
        foreign key (lot_id) references lot (id)
);

create sequence lot_receipt_seq as integer start with 1;