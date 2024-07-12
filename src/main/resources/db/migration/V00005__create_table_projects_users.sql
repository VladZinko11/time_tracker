CREATE TABLE projects_users
(
    project_id bigint references projects,
    user_id    bigint references users
);