INSERT INTO role (id, role_name)
VALUES
    (11, 'ADMIN'),
    (1, 'CUSTOMER'),
    (2, 'SELLER')
ON CONFLICT DO NOTHING;

ALTER TABLE role ALTER COLUMN id RESTART WITH 12;

-- Password untuk semua akun dummy: "indocyber" (BCrypt).
INSERT INTO users (username, password, role)
VALUES ('admin01', '$2a$10$ExyQjxAGK.qRJruTkxvGle.vyOcuiM3iq8I4XPQmRsFgYuyb.ILVm', 11)
ON CONFLICT (username) DO UPDATE
SET password = EXCLUDED.password, role = EXCLUDED.role;

INSERT INTO users (username, password, role)
VALUES ('customer', '$2a$10$ExyQjxAGK.qRJruTkxvGle.vyOcuiM3iq8I4XPQmRsFgYuyb.ILVm', 1)
ON CONFLICT (username) DO UPDATE
SET password = EXCLUDED.password, role = EXCLUDED.role;

INSERT INTO users (username, password, role)
VALUES ('seller', '$2a$10$ExyQjxAGK.qRJruTkxvGle.vyOcuiM3iq8I4XPQmRsFgYuyb.ILVm', 2)
ON CONFLICT (username) DO UPDATE
SET password = EXCLUDED.password, role = EXCLUDED.role;
