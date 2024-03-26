USE test_db;

CREATE TABLE game (
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    release_date DATE NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO game (id, name, release_date, price) VALUES
    (1, 'Game 1', '2023-03-21', '1.01');
