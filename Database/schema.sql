create schema Asada;

SET SQL_REQUIRE_PRIMARY_KEY = false;

create table Asada.type
(
    id   bigint auto_increment,
    name varchar(50) not null,
    `group` varchar(50) not null,
    constraint type_pk
        primary key (id)
);

create table Asada.type_seq
(
    next_val bigint null
);

create table Asada.role
(
    id     bigint auto_increment,
    name   varchar(50) not null,
    status bigint      null,
    constraint role_pk
        primary key (id),
    constraint role_type_id_fk
        foreign key (status) references type (id)
);

create table Asada.role_seq
(
    next_val bigint null
);

create table Asada.privilege
(
    id      bigint auto_increment,
    name    varchar(255) not null,
    `group` varchar(255) not null,
    constraint privilege_pk
        primary key (id)
);

create table Asada.roles_privileges
(
    role_id      bigint not null,
    privilege_id bigint not null,
    constraint FK5yjwxw2gvfyu76j3rgqwo685u
        foreign key (privilege_id) references Asada.privilege (id),
    constraint FK9h2vewsqh8luhfq71xokh4who
        foreign key (role_id) references Asada.role (id)
);

create table Asada.user
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
        foreign key (status) references Asada.type (id)
);

create table Asada.user_seq
(
    next_val bigint null
);

create table Asada.users_roles
(
    role_id bigint not null,
    user_id bigint not null,
    constraint users_roles_pk
        primary key (role_id, user_id),
    constraint users_roles_role_id_fk
        foreign key (role_id) references role (id),
    constraint users_roles_user_id_fk
        foreign key (user_id) references user (id)
);



create table Asada.lot
(
    id    bigint auto_increment,
    name  varchar(50) not null unique,
    owner bigint      not null,
    status           bigint       null,
    last_month_paid DATE null,
    constraint lot_pk
        primary key (id),
    constraint lot_user_id_fk
        foreign key (owner) references user (id),
    constraint lot_status_id_fk
        foreign key (status) references Asada.type (id)
);

create table Asada.lot_seq
(
    next_val bigint null
);

-- auto-generated definition
create table Asada.lot_receipt
(
    id         bigint auto_increment
        primary key,
    lot_id     bigint      not null,
    receipt_id varchar(50) not null,
    month_paid date        not null,
    cost       double      not null,
    constraint lot_receipt_lot_id_fk
        foreign key (lot_id) references Asada.lot (id)
);

create table Asada.lot_receipt_seq
(
    next_val bigint null
);



