--Tables
--address
DROP TABLE IF EXISTS addresses cascade;
CREATE TABLE addresses(
    address_id SERIAL PRIMARY KEY,
    street VARCHAR(100),
    postal VARCHAR(7),
    city VARCHAR(50),
    province VARCHAR(30),
    country VARCHAR(30)
);

--user
DROP TABLE IF EXISTS users cascade;
CREATE TABLE users(
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(75),
    password VARCHAR(80),
    email VARCHAR(100),
    phone VARCHAR(16),
    address_id int,
    is_admin BOOLEAN,
    FOREIGN KEY(address_id) REFERENCES addresses(address_id)

);

DROP TABLE IF EXISTS categories cascade;
CREATE TABLE categories(
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(50)
);

DROP TABLE IF EXISTS items cascade;
CREATE TABLE items (
    item_id SERIAL PRIMARY KEY,
    item_name VARCHAR(80) NOT NULL,
    description TEXT,
    category_id INT NOT NULL,
    stock NUMERIC(10, 2),
    price NUMERIC(10, 2),
    FOREIGN KEY(category_id) REFERENCES categories(category_id)

);

DROP TABLE IF EXISTS images cascade;
CREATE TABLE images(
    image_id SERIAL PRIMARY KEY,
    item_id INT,
    url VARCHAR(255),
    FOREIGN KEY(item_id) REFERENCES items(item_id)
);


--sale type e.g. bought 1 case, 3 pounds, 2 bags, etc
DROP TABLE IF EXISTS itemSaleTypes cascade;
CREATE TABLE itemSaleTypes(
    type_id serial PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS orders cascade;
CREATE TABLE orders(
    order_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    sale_date DATE NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

DROP TABLE IF EXISTS itemSales cascade;
CREATE TABLE itemSales(
    sale_id SERIAL PRIMARY KEY,
    item_id INT NOT NULL,
    quantity NUMERIC(10, 2) NOT NULL,
    type_id INT NOT NULL,
    sale_date DATE NOT NULL,
    item_cost NUMERIC(10,2),
    order_id INT,
    isOnSale BOOLEAN,
    FOREIGN KEY (item_id) REFERENCES items(item_id),
    FOREIGN KEY (type_id) REFERENCES itemSaleTypes(type_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

DROP TABLE IF EXISTS shoppingCarts cascade;
CREATE TABLE shoppingCarts(
    cart_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

DROP TABLE IF EXISTS cartItems cascade;
CREATE TABLE cartItems(
    cart_item_id SERIAL PRIMARY KEY,
    cart_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity NUMERIC(10, 2) NOT NULL,
    type_id INT NOT NULL,
    FOREIGN KEY(cart_id) REFERENCES shoppingCarts(cart_id),
    FOREIGN KEY(item_id) REFERENCES items(item_id),
    FOREIGN KEY(type_id) REFERENCES itemSaleTypes(type_id)
);







