DROP TABLE IF EXISTS "user_role";
DROP TABLE IF EXISTS "role_privilege";
DROP TABLE IF EXISTS "privilege";
DROP TABLE IF EXISTS "role";
DROP TABLE IF EXISTS "users";
DROP TABLE IF EXISTS "test";

CREATE TABLE "privilege"
(
    "id"          serial       NOT NULL,
    "is_active"   boolean      NOT NULL default true,
    "unique_id"   varchar(36),
    "create_date" TIMESTAMP,
    "create_user" numeric,
    "update_date" TIMESTAMP,
    "update_user" numeric,
    "name"        varchar(50)  NOT NULL,
    "title"       varchar(150) NOT NULL,
    "parent_id"   numeric,
    CONSTRAINT "privilege_pk" PRIMARY KEY ("id")
) WITH (OIDS = FALSE);

CREATE TABLE "role"
(
    "id"          serial  NOT NULL,
    "is_active"   boolean NOT NULL default true,
    "unique_id"   varchar(36),
    "create_date" TIMESTAMP,
    "create_user" numeric,
    "update_date" TIMESTAMP,
    "update_user" numeric,
    "name"        varchar(100),
    CONSTRAINT "role_pk" PRIMARY KEY ("id")
) WITH (OIDS = FALSE);

CREATE TABLE "users"
(
    "id"          serial       NOT NULL,
    "is_active"   boolean      NOT NULL default true,
    "unique_id"   varchar(36),
    "create_date" TIMESTAMP,
    "create_user" numeric,
    "update_date" TIMESTAMP,
    "update_user" numeric,
    "email"       varchar(150) NOT NULL,
    "password"    varchar(150) NOT NULL,
    "name"        varchar(100),
    "surname"     varchar(100),
    "address"     varchar(500),
    CONSTRAINT "users_pk" PRIMARY KEY ("id")
) WITH (OIDS = FALSE);

CREATE TABLE "role_privilege"
(
    "fk_role_id"      numeric NOT NULL,
    "fk_privilege_id" numeric NOT NULL
) WITH (OIDS = FALSE);
ALTER TABLE "role_privilege" ADD CONSTRAINT "role_privilege_uniq" UNIQUE (fk_role_id, fk_privilege_id);

CREATE TABLE "user_role"
(
    "fk_user_id" numeric NOT NULL,
    "fk_role_id" numeric NOT NULL
) WITH (OIDS = FALSE);
ALTER TABLE "user_role" ADD CONSTRAINT "user_role_uniq" UNIQUE (fk_user_id, fk_role_id);

CREATE TABLE "test"
(
    "id"          serial      NOT NULL,
    "is_active"   boolean     NOT NULL default true,
    "unique_id"   varchar(36),
    "create_date" TIMESTAMP,
    "create_user" numeric,
    "update_date" TIMESTAMP,
    "update_user" numeric,
    "name"        varchar(50) NOT NULL,
    CONSTRAINT "test_pk" PRIMARY KEY ("id")
) WITH (OIDS = FALSE);

INSERT INTO "test"(create_date, create_user, update_date, update_user, name)
VALUES (current_timestamp, 1, null, null, 'test1');
INSERT INTO "test"(create_date, create_user, update_date, update_user, name)
VALUES (current_timestamp, 1, null, null, 'test2');
INSERT INTO "test"(create_date, create_user, update_date, update_user, name)
VALUES (current_timestamp, 1, null, null, 'test3');
INSERT INTO "test"(create_date, create_user, update_date, update_user, name)
VALUES (current_timestamp, 1, null, null, 'test4');
INSERT INTO "test"(create_date, create_user, update_date, update_user, name)
VALUES (current_timestamp, 1, null, null, 'test5');

INSERT INTO "role"(create_date, create_user, update_date, update_user, name)
VALUES (current_timestamp, 1, null, null, 'ROLE1');
INSERT INTO "role"(create_date, create_user, update_date, update_user, name)
VALUES (current_timestamp, 1, null, null, 'ROLE2');
INSERT INTO "role"(create_date, create_user, update_date, update_user, name)
VALUES (current_timestamp, 1, null, null, 'ROLE3');

INSERT INTO "users"(is_active, create_date, create_user, update_date, update_user, email, password, name, surname,
                    address)
VALUES (true, current_timestamp, 1, null, null, 'ahmetokay14@gmail.com',
        '$2a$10$uTi7e43Cs4FsHLdc6UHC8.DiSs4C4eEUJY/20Mw2eLNXxkg.ktIP6',
        'Ahmet', 'Okay', null);
INSERT INTO "users"(is_active, create_date, create_user, update_date, update_user, email, password, name, surname,
                    address)
VALUES (true, current_timestamp, 1, null, null, 'b@b.com',
        '$2a$10$uTi7e43Cs4FsHLdc6UHC8.DiSs4C4eEUJY/20Mw2eLNXxkg.ktIP6',
        'Ahmet', 'Okay', null);

INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (100000, true, current_timestamp, 1, null, null, 'Kullanıcı Yönetimi', 'UM.*', null);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (100100, true, current_timestamp, 1, null, null, 'Kullanıcı Yönetimi - 1', 'UM.01.*', 100000);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (100101, true, current_timestamp, 1, null, null, 'Kullanıcı Yönetimi - Listeleme', 'UM.01.01', 100100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (100102, true, current_timestamp, 1, null, null, 'Kullanıcı Yönetimi - Okuma', 'UM.01.02', 100100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (100103, true, current_timestamp, 1, null, null, 'Kullanıcı Yönetimi - Ekleme', 'UM.01.03', 100100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (100104, true, current_timestamp, 1, null, null, 'Kullanıcı Yönetimi - Güncelleme', 'UM.01.04', 100100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (100105, true, current_timestamp, 1, null, null, 'Kullanıcı Yönetimi - Silme', 'UM.01.05', 100100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (100106, true, current_timestamp, 1, null, null, 'Kullanıcı Yönetimi - Dışa Aktar', 'UM.01.06', 100100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (100107, true, current_timestamp, 1, null, null, 'Kullanıcı Yönetimi - Tarihçe', 'UM.01.07', 100100);

INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (200000, true, current_timestamp, 1, null, null, 'Rol Yönetimi', 'RM.*', null);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (200100, true, current_timestamp, 1, null, null, 'Rol Yönetimi - 1', 'RM.01.*', 200000);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (200101, true, current_timestamp, 1, null, null, 'Rol Yönetimi - Listeleme', 'RM.01.01', 200100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (200102, true, current_timestamp, 1, null, null, 'Rol Yönetimi - Okuma', 'RM.01.02', 200100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (200103, true, current_timestamp, 1, null, null, 'Rol Yönetimi - Ekleme', 'RM.01.03', 200100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (200104, true, current_timestamp, 1, null, null, 'Rol Yönetimi - Güncelleme', 'RM.01.04', 200100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (200105, true, current_timestamp, 1, null, null, 'Rol Yönetimi - Silme', 'RM.01.05', 200100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (200106, true, current_timestamp, 1, null, null, 'Rol Yönetimi - Dışa Aktar', 'RM.01.06', 200100);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (200107, true, current_timestamp, 1, null, null, 'Rol Yönetimi - Tarihçe', 'RM.01.07', 200100);

INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (300000, true, current_timestamp, 1, null, null, 'Erişim Logları', 'AL.*', null);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (300100, true, current_timestamp, 1, null, null, 'Erişim Logları - 1', 'AL.01.*', 300000);
INSERT INTO "privilege"(id, is_active, create_date, create_user, update_date, update_user, title, name, parent_id)
VALUES (300101, true, current_timestamp, 1, null, null, 'Erişim Logları - Listeleme', 'AL.01.01', 300100);

-- tum yetkiler
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 100000);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 100100);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 100101);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 100102);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 100103);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 100104);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 100105);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 100106);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 100107);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 200000);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 200100);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 200101);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 200102);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 200103);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 200104);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 200105);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 200106);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 200107);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 300000);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 300100);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (1, 300101);

INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (2, 100000);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (2, 100100);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (2, 100101);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (2, 100102);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (2, 100103);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (2, 100104);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (2, 100105);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (2, 100106);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (2, 100107);

INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (3, 200000);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (3, 200100);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (3, 200101);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (3, 200102);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (3, 200103);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (3, 200104);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (3, 200105);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (3, 200106);
INSERT INTO "role_privilege"(fk_role_id, fk_privilege_id) VALUES (3, 200107);

INSERT INTO "user_role"(fk_user_id, fk_role_id) VALUES (1, 1);
INSERT INTO "user_role"(fk_user_id, fk_role_id) VALUES (1, 2);
INSERT INTO "user_role"(fk_user_id, fk_role_id) VALUES (1, 3);