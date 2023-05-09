update iam_roles
set code='0'
where code is null;

update iam_roles
set code = '1'
where id = 'supervisor';

update iam_roles
set code = '2'
where id = 'admin';

alter table iam_roles
    alter column code set not null;