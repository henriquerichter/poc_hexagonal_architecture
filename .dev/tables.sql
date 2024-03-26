CREATE TABLE game (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    release_date DATE NOT NULL,
    price DECIMAL(5, 2) NOT NULL
);

INSERT INTO game (name, release_date, price) VALUES
    ('The Witcher 3: Wild Hunt', '2015-05-19', 29.99),
    ('Cyberpunk 2077', '2020-12-10', 59.99),
    ('The Elder Scrolls V: Skyrim', '2011-11-11', 39.99),
    ('Fallout 4', '2015-11-10', 29.99),
    ('The Legend of Zelda: Breath of the Wild', '2017-03-03', 59.99);
