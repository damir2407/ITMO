CREATE TABLE users
(
    login             varchar(10) PRIMARY KEY,

    password          varchar(255) NOT NULL,

    status            varchar(32)  NOT NULL,
    last_login_date   timestamp,
    email             varchar(64)  NOT NULL,
    registration_date date         NOT NULL
);

CREATE TABLE groups
(
    id         SERIAL PRIMARY KEY,
    group_name varchar(64) NOT NULL,
    max_count  integer     NOT NULL,
    owner      varchar(10) REFERENCES users ON DELETE CASCADE ON UPDATE CASCADE
);