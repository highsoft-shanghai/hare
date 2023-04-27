insert into iam_tenants(id, name)
values ('a1fa8c64c75f4bc5b520bd6b8af5f6f9', '平台');

insert into iam_users(id, name, predefined)
values ('66c4a1329f0c4e5089c554b95d5ec15c', '超级管理员', true);

insert into iam_user_accounts(id, name, user_id, tenant_id, predefined)
values ('f354b3d03119475899de312f80c4c99e', '超级管理员', '66c4a1329f0c4e5089c554b95d5ec15c', 'a1fa8c64c75f4bc5b520bd6b8af5f6f9', true);

insert into iam_credentials(id, type, login_name, secret, user_account_id, user_id, tenant_id, predefined)
values ('bac21ab5f2174cf8ad3f21b17f5e4c97', 'username-and-password', 'admin', '$2a$10$g9yTQUIHrL4yF/k.1G3QDuuFB0RE0p8H4tdZCrLkSLr2s/WIdYcUK',
        'f354b3d03119475899de312f80c4c99e', '66c4a1329f0c4e5089c554b95d5ec15c', 'a1fa8c64c75f4bc5b520bd6b8af5f6f9', true);

insert into iam_roles(id, name, authorities, remarks, tenant_id, predefined) values ('admin', '管理员', '', '', '', true);
insert into iam_roles(id, name, authorities, remarks, tenant_id, predefined) values ('supervisor', '超级管理员', '', '', '', true);

insert into iam_user_account_roles (user_account_id, role_id) values ('f354b3d03119475899de312f80c4c99e', 'admin');
insert into iam_user_account_roles (user_account_id, role_id) values ('f354b3d03119475899de312f80c4c99e', 'supervisor');
