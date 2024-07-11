CREATE TABLE records
(
    id          BIGSERIAL PRIMARY KEY,
    task_id     BIGINT REFERENCES tasks,
    user_id     BIGINT REFERENCES users,
    start_time  TIMESTAMP WITHOUT TIME ZONE,
    finish_time TIMESTAMP WITHOUT TIME ZONE,
    time        BIGINT
);