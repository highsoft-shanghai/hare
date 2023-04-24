create table persons (
    id      varchar(100) primary key,
    code    varchar(100)  not null,
    name    varchar(200)  not null,
    remarks varchar(2000) not null
);

create table tags (
    id      varchar(100) primary key,
    name    varchar(200)  not null,
    remarks varchar(2000) not null
);
