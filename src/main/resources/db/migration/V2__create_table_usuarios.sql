CREATE TABLE T_UNHAENG_USERS (
  user_id     BIGINT IDENTITY(1,1) PRIMARY KEY,
  name        VARCHAR(150) NULL,
  email       VARCHAR(150) NULL,
  avatar_url  VARCHAR(255) NULL
);

CREATE UNIQUE INDEX un_unhaeng_users_email ON T_UNHAENG_USERS(email);

