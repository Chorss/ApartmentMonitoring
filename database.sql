create table accounts
(
    uuid     uuid         not null,
    email    varchar(255) not null,
    name     varchar(250),
    password varchar(250) not null,
    primary key (uuid)
);
create unique index EMAIL_ACCOUNTS_UDX on accounts (email);

create table roles
(
    id          bigserial not null,
    description varchar(255),
    role        varchar(255),
    primary key (id)
);

create table accounts_roles
(
    account_entity_uuid uuid not null,
    roles_id            int8 not null
);
alter table accounts_roles
    add constraint ACCOUNTS_ROLES_ROLES_ID_FKEY foreign key (roles_id) references roles;
alter table accounts_roles
    add constraint ACCOUNTS_ROLES_ACCOUNT_ID_FKEY foreign key (account_entity_uuid) references accounts;

create table devices
(
    uuid    uuid         not null,
    created timestamp    not null,
    name    varchar(255) not null,
    account uuid,
    primary key (uuid)
);
alter table devices
    add constraint DEVICES_ACCOUNT_ID_FKEY foreign key (account) references accounts;

create table measurement
(
    uuid        uuid           not null,
    created     timestamp      not null,
    humidity    numeric(19, 2) not null check (humidity <= 100 AND humidity >= 0),
    temperature numeric(19, 2) not null check (temperature <= 100 AND temperature >= -100),
    device_id   uuid           not null,
    primary key (uuid)
);
alter table measurement
    add constraint MEASUREMENT_DEVICE_ID_FKEY foreign key (device_id) references devices;
create index DEVICE_ID_MEASUREMENT_IDX on measurement (device_id);
create index CREATED_MEASUREMENT_IDX on measurement (created);