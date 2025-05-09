CREATE TABLE books
(
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(255),
    author VARCHAR(255),
    description VARCHAR(255),
    file_id    VARCHAR(255)
);

CREATE TABLE users
(
    ID          BIGSERIAL PRIMARY KEY,
    USERNAME    VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD    VARCHAR(255) NOT NULL,
    ROLE       VARCHAR(255)
)


