CREATE TABLE tasks
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255),
    project_id BIGINT REFERENCES projects
);