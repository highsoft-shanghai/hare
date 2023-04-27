create table iam_access_tokens (
    id                  varchar(64) primary key not null,
    user_account_id     varchar(64)             not null,
    user_account_name   varchar(256)            not null,
    user_id             varchar(64),
    user_name           varchar(256),
    tenant_id           varchar(64),
    tenant_name         varchar(256),
    token_group         varchar(256)            not null,
    granted_authorities text,
    unique (tenant_id, id)
);

create table iam_tenants (
    id   varchar(64) primary key not null,
    name varchar(256)            not null
);

create table iam_users (
    id         varchar(64) primary key not null,
    name       varchar(256)            not null,
    predefined bool                    not null
);

create table iam_user_accounts (
    id         varchar(64) primary key not null,
    name       varchar(256)            not null,
    user_id    varchar(64),
    predefined bool                    not null,
    tenant_id  varchar(64)             not null,
    unique (tenant_id, id)
);

create table iam_credentials (
    id              varchar(64) primary key not null,
    type            varchar(100)            not null,
    login_name      varchar(256)            not null,
    secret          varchar(256),
    user_account_id varchar(64)             not null,
    user_id         varchar(64),
    tenant_id       varchar(64)             not null,
    predefined      bool                    not null,
    unique (tenant_id, id)
);

create table iam_roles (
    id          varchar(64) primary key not null,
    name        varchar(256)            not null,
    authorities varchar(2560000),
    remarks     varchar(2048),
    tenant_id   varchar(64)             not null,
    predefined  bool                    not null,
    unique (tenant_id, id)
);

create table iam_user_account_roles (
    user_account_id varchar(64) not null,
    role_id         varchar(64) not null,
    primary key (user_account_id, role_id)
);

create table iam_authorities (
    id        varchar(64) primary key not null,
    parent_id varchar(64),
    name      varchar(256)            not null,
    remarks   varchar(2048)
);
