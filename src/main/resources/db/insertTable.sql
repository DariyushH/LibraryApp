CREATE TABLE authors
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    birth_date DATE,
    country    VARCHAR(100)
);

CREATE TABLE genres
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
CREATE TABLE books
(
    id        SERIAL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    author_id INT          REFERENCES authors (id) ON DELETE SET NULL,
    genre_id  INT          REFERENCES genres (id) ON DELETE SET NULL,

);