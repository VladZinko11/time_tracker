CREATE TABLE projects
(
    id              BIGSERIAL PRIMARY KEY,
    user_creator_id BIGINT REFERENCES users,
    name            VARCHAR(255)
);