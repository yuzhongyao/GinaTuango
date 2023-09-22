--Tables
--user
DROP TABLE IF EXISTS users;
CREATE TABLE users(
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(75),
    email VARCHAR(100),
    phone VARCHAR(16),
    address VARCHAR(150),
    isAdmin BIT
);

DROP TABLE IF EXISTS items;
CREATE TABLE items (
    item_id SERIAL PRIMARY KEY,
    item_name VARCHAR(80) NOT NULL,
    description TEXT,
    category_id INT NOT NULL,
    stock NUMERIC(10, 2),
    price NUMERIC(10, 2)
);

--sale type e.g. bought 1 case, 3 pounds, 2 bags, etc
DROP TABLE IF EXISTS itemType;
CREATE TABLE itemType(
    type_id serial PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS itemSales;
CREATE TABLE itemSales(
    sale_id SERIAL PRIMARY KEY,
    item_id INT NOT NULL,
    quantity NUMERIC(10, 2) NOT NULL,
    type_id INT NOT NULL,
    sale_date DATE NOT NULL,
    FOREIGN KEY (item_id) REFERENCES items(item_id),
    FOREIGN KEY (type_id) REFERENCES itemType(type_id)
);




