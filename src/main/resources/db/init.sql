
CREATE DATABASE test_db;

-- Table user
CREATE TABLE IF NOT EXISTS tbl_users (
id SERIAL,
username VARCHAR(255),
name VARCHAR(255),
password VARCHAR(255),
email VARCHAR(255),
enabled BOOLEAN,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(id)
);

INSERT INTO tbl_users (username,"name","password",email,enabled,created_date,updated_date) VALUES
	 ('hoangnhat','Hoang Van Nhat','$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6','hoangnhat102003@gmail.com',true,'2022-09-13 11:56:45.132735','2022-09-13 11:56:45.132735'),
	 ('vannam','Nguyen Van Nam','$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6','vannam@gmail.com',true,'2022-09-13 11:57:32.79039','2022-09-13 11:57:32.79039');

CREATE TYPE E_ROLES AS ENUM ('USER','ADMIN');
-- Table roles
CREATE TABLE IF NOT EXISTS tbl_roles  (
id SERIAL,
name E_ROLES,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(id)
);

INSERT INTO tbl_roles (name)
VALUES ('USER');
INSERT INTO tbl_roles (name)
VALUES ('ADMIN');

-- Table user_roles
CREATE TABLE IF NOT EXISTS tbl_user_roles (
user_id INT,
role_id INT,
CONSTRAINT user_roles_fk1 FOREIGN KEY (user_id) REFERENCES tbl_users(id),
CONSTRAINT user_roles_fk2 FOREIGN KEY (role_id) REFERENCES tbl_roles(id)
);
INSERT INTO tbl_user_roles (user_id,role_id) VALUES
	 (1,3),
	 (2,4);

-- Table refresh_token
CREATE TABLE IF NOT EXISTS tbl_refresh_token (
id SERIAL,
user_id INT,
token VARCHAR(255),
expiry_date TIMESTAMP,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT refresh_token_fk1 FOREIGN KEY (user_id) REFERENCES tbl_users(id),
PRIMARY KEY(id)
);