-- liquibase formatted sql

-- changeset ahmet:1713874305788-1
TRUNCATE TABLE "user_role" CASCADE;
TRUNCATE TABLE "role_privilege" CASCADE;
TRUNCATE TABLE "users" CASCADE;
TRUNCATE TABLE "role" CASCADE;
TRUNCATE TABLE "test" CASCADE;
TRUNCATE TABLE "privilege" CASCADE;

-- changeset ahmet:1713874305788-2
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (100000, TRUE, NULL, '2024-04-23 14:31:57.735221', 1, NULL, NULL, 'UM.*', 'Kullanıcı Yönetimi', NULL);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (100100, TRUE, NULL, '2024-04-23 14:31:57.737136', 1, NULL, NULL, 'UM.01.*', 'Kullanıcı Yönetimi - 1', 100000);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (100101, TRUE, NULL, '2024-04-23 14:31:57.738727', 1, NULL, NULL, 'UM.01.01', 'Kullanıcı Yönetimi - Listeleme', 100100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (100102, TRUE, NULL, '2024-04-23 14:31:57.740274', 1, NULL, NULL, 'UM.01.02', 'Kullanıcı Yönetimi - Okuma', 100100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (100103, TRUE, NULL, '2024-04-23 14:31:57.741787', 1, NULL, NULL, 'UM.01.03', 'Kullanıcı Yönetimi - Ekleme', 100100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (100104, TRUE, NULL, '2024-04-23 14:31:57.743587', 1, NULL, NULL, 'UM.01.04', 'Kullanıcı Yönetimi - Güncelleme', 100100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (100105, TRUE, NULL, '2024-04-23 14:31:57.745397', 1, NULL, NULL, 'UM.01.05', 'Kullanıcı Yönetimi - Silme', 100100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (100106, TRUE, NULL, '2024-04-23 14:31:57.746942', 1, NULL, NULL, 'UM.01.06', 'Kullanıcı Yönetimi - Dışa Aktar', 100100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (100107, TRUE, NULL, '2024-04-23 14:31:57.748426', 1, NULL, NULL, 'UM.01.07', 'Kullanıcı Yönetimi - Tarihçe', 100100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (200000, TRUE, NULL, '2024-04-23 14:31:57.749913', 1, NULL, NULL, 'RM.*', 'Rol Yönetimi', NULL);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (200100, TRUE, NULL, '2024-04-23 14:31:57.751506', 1, NULL, NULL, 'RM.01.*', 'Rol Yönetimi - 1', 200000);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (200101, TRUE, NULL, '2024-04-23 14:31:57.753106', 1, NULL, NULL, 'RM.01.01', 'Rol Yönetimi - Listeleme', 200100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (200102, TRUE, NULL, '2024-04-23 14:31:57.754649', 1, NULL, NULL, 'RM.01.02', 'Rol Yönetimi - Okuma', 200100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (200103, TRUE, NULL, '2024-04-23 14:31:57.756154', 1, NULL, NULL, 'RM.01.03', 'Rol Yönetimi - Ekleme', 200100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (200104, TRUE, NULL, '2024-04-23 14:31:57.757901', 1, NULL, NULL, 'RM.01.04', 'Rol Yönetimi - Güncelleme', 200100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (200105, TRUE, NULL, '2024-04-23 14:31:57.759509', 1, NULL, NULL, 'RM.01.05', 'Rol Yönetimi - Silme', 200100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (200106, TRUE, NULL, '2024-04-23 14:31:57.761153', 1, NULL, NULL, 'RM.01.06', 'Rol Yönetimi - Dışa Aktar', 200100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (200107, TRUE, NULL, '2024-04-23 14:31:57.762783', 1, NULL, NULL, 'RM.01.07', 'Rol Yönetimi - Tarihçe', 200100);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (300000, TRUE, NULL, '2024-04-23 14:31:57.76441', 1, NULL, NULL, 'AL.*', 'Erişim Logları', NULL);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (300100, TRUE, NULL, '2024-04-23 14:31:57.766056', 1, NULL, NULL, 'AL.01.*', 'Erişim Logları - 1', 300000);
INSERT INTO "privilege" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name", "title", "parent_id") VALUES (300101, TRUE, NULL, '2024-04-23 14:31:57.767754', 1, NULL, NULL, 'AL.01.01', 'Erişim Logları - Listeleme', 300100);

-- changeset ahmet:1713874305788-3
INSERT INTO "role" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name") VALUES (1, TRUE, NULL, '2024-04-23 14:31:57.727034', 1, NULL, NULL, 'ROLE1');
INSERT INTO "role" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name") VALUES (2, TRUE, NULL, '2024-04-23 14:31:57.728761', 1, NULL, NULL, 'ROLE2');
INSERT INTO "role" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name") VALUES (3, TRUE, NULL, '2024-04-23 14:31:57.730338', 1, NULL, NULL, 'ROLE3');

-- changeset ahmet:1713874305788-4
INSERT INTO "users" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "email", "password", "name", "surname", "address") VALUES (1, TRUE, NULL, '2024-04-23 14:31:57.731834', 1, NULL, NULL, 'ahmetokay14@gmail.com', '$2a$10$uTi7e43Cs4FsHLdc6UHC8.DiSs4C4eEUJY/20Mw2eLNXxkg.ktIP6', 'Ahmet', 'Okay', NULL);
INSERT INTO "users" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "email", "password", "name", "surname", "address") VALUES (2, TRUE, NULL, '2024-04-23 14:31:57.73359', 1, NULL, NULL, 'b@b.com', '$2a$10$uTi7e43Cs4FsHLdc6UHC8.DiSs4C4eEUJY/20Mw2eLNXxkg.ktIP6', 'Ahmet', 'Okay', NULL);

-- changeset ahmet:1713874305788-5
INSERT INTO "test" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name") VALUES (1, TRUE, NULL, '2024-04-23 14:31:57.718484', 1, NULL, NULL, 'test1');
INSERT INTO "test" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name") VALUES (2, TRUE, NULL, '2024-04-23 14:31:57.720722', 1, NULL, NULL, 'test2');
INSERT INTO "test" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name") VALUES (3, TRUE, NULL, '2024-04-23 14:31:57.722427', 1, NULL, NULL, 'test3');
INSERT INTO "test" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name") VALUES (4, TRUE, NULL, '2024-04-23 14:31:57.72396', 1, NULL, NULL, 'test4');
INSERT INTO "test" ("id", "is_active", "unique_id", "create_date", "create_user", "update_date", "update_user", "name") VALUES (5, TRUE, NULL, '2024-04-23 14:31:57.725498', 1, NULL, NULL, 'test5');

-- changeset ahmet:1713874305788-6
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 100000);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 100100);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 100101);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 100102);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 100103);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 100104);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 100105);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 100106);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 100107);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 200000);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 200100);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 200101);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 200102);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 200103);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 200104);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 200105);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 200106);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 200107);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 300000);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 300100);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (1, 300101);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (2, 100000);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (2, 100100);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (2, 100101);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (2, 100102);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (2, 100103);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (2, 100104);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (2, 100105);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (2, 100106);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (2, 100107);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (3, 200000);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (3, 200100);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (3, 200101);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (3, 200102);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (3, 200103);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (3, 200104);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (3, 200105);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (3, 200106);
INSERT INTO "role_privilege" ("fk_role_id", "fk_privilege_id") VALUES (3, 200107);

-- changeset ahmet:1713874305788-7
INSERT INTO "user_role" ("fk_user_id", "fk_role_id") VALUES (1, 1);
INSERT INTO "user_role" ("fk_user_id", "fk_role_id") VALUES (1, 2);
INSERT INTO "user_role" ("fk_user_id", "fk_role_id") VALUES (1, 3);

