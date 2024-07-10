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
    project_id BIGINT
);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_PROJECT FOREIGN KEY (project_id) REFERENCES projects (id);

CREATE TABLE tasks
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255),
    project_id BIGINT REFERENCES projects
);

CREATE TABLE projects_tasks
(
    project_id BIGINT NOT NULL REFERENCES projects,
    tasks_id   BIGINT NOT NULL REFERENCES tasks
);

CREATE TABLE projects_users
(
    project_id BIGINT NOT NULL,
    users_id   BIGINT NOT NULL
);

ALTER TABLE projects_users
    ADD CONSTRAINT uc_projects_users_users UNIQUE (users_id);

ALTER TABLE projects_users
    ADD CONSTRAINT fk_prouse_on_project FOREIGN KEY (project_id) REFERENCES projects (id);

ALTER TABLE projects_users
    ADD CONSTRAINT fk_prouse_on_user FOREIGN KEY (users_id) REFERENCES users (id);

CREATE TABLE records
(
    id          BIGSERIAL PRIMARY KEY,
    task_id     BIGINT REFERENCES tasks,
    user_id     BIGINT REFERENCES users,
    start_time  TIMESTAMP WITHOUT TIME ZONE,
    finish_time TIMESTAMP WITHOUT TIME ZONE,
    time        BIGINT
);

CREATE TABLE tasks_records
(
    task_id    BIGINT REFERENCES tasks,
    records_id BIGINT REFERENCES records
);

CREATE TABLE users_records
(
    user_id    BIGINT NOT NULL REFERENCES users,
    records_id BIGINT NOT NULL REFERENCES records
);