CREATE TABLE projects
(
    id   BIGSERIAL PRIMARY KEY ,
    name VARCHAR(255)
);

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY ,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255),
    password   VARCHAR(255),
    role       VARCHAR(255),
    project_id BIGINT REFERENCES projects
);

CREATE TABLE tasks
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255),
    project_id BIGINT REFERENCES projects
);

CREATE TABLE records
(
    id          BIGSERIAL PRIMARY KEY,
    task_id     BIGINT REFERENCES tasks,
    user_id     BIGINT REFERENCES users,
    start_time  TIMESTAMP WITHOUT TIME ZONE,
    finish_time TIMESTAMP WITHOUT TIME ZONE,
    time        BIGINT
);
