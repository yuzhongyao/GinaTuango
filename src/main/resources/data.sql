-- Categories
INSERT INTO categories (category_name) VALUES
('Fruits'),
('Vegetables'),
('Grocery'),
('Frozen'),
('Other');

-- Addresses (Sample Addresses)
INSERT INTO addresses (street, postal, city, province, country) VALUES
('123 Apple St', '12345', 'Orangeville', 'Ontario', 'Canada'),
('456 Banana Ave', '67890', 'Grapetown', 'California', 'USA'),
('789 Cherry Blvd', '54321', 'Melon City', 'Florida', 'USA'),
('321 Pear Rd', '98765', 'Lemonville', 'Texas', 'USA'),
('876 Plum Lane', '13579', 'Peachville', 'Georgia', 'USA');

-- Users (Sample Users)
INSERT INTO users (user_name, password, email, phone, address_id, is_admin) VALUES
('1', '1', 'user1@example.com', '123-4567', 1, TRUE),
('user1', 'password1', 'user1@example.com', '123-4567', 1, FALSE),
('user2', 'password2', 'user2@example.com', '987-6543', 2, FALSE),
('admin', 'admin', 'admin@example.com', '111-2222', 3, TRUE),
('buyer', 'buypass', 'buyer@example.com', '333-4444', 4, FALSE),
('seller', 'sellpass', 'seller@example.com', '555-6666', 5, FALSE);

-- Items (Sample Items)
INSERT INTO items (item_name, description, category_id, stock, price) VALUES
('Apple', 'Fresh and juicy apples', 1, 100, 1.99),
('Carrot', 'Organic carrots', 2, 50, 2.49),
('Rice', 'Long-grain rice', 3, 200, 5.99),
('Frozen Pizza', 'Pepperoni pizza', 4, 30, 7.99),
('Toothpaste', 'Mint-flavored toothpaste', 5, 100, 3.49);

-- Images (Sample Images - Linking to Items)
INSERT INTO images (item_id, url) VALUES
(1, 'https://example.com/apple_image.jpg'),
(2, 'https://example.com/carrot_image.jpg'),
(3, 'https://example.com/rice_image.jpg'),
(4, 'https://example.com/pizza_image.jpg'),
(5, 'https://example.com/toothpaste_image.jpg');

-- Sale Types (Sample Sale Types)
INSERT INTO itemSaleTypes (type_name) VALUES
('Bulk'),
('Per Pound'),
('Per Unit'),
('Bundle'),
('Promotion');

-- Orders (Sample Orders)
INSERT INTO orders (user_id, sale_date) VALUES
(1, '2024-01-15'),
(2, '2024-01-16'),
(3, '2024-01-17'),
(4, '2024-01-18'),
(5, '2024-01-19');

-- Item Sales (Sample Item Sales)
INSERT INTO itemSales (item_id, quantity, type_id, sale_date, item_cost, order_id, isOnSale) VALUES
(1, 5, 3, '2024-01-15', 9.95, 1, TRUE),
(2, 3, 2, '2024-01-16', 7.47, 2, FALSE),
(3, 10, 1, '2024-01-17', 59.90, 3, TRUE),
(4, 2, 4, '2024-01-18', 15.98, 4, FALSE),
(5, 1, 5, '2024-01-19', 3.49, 5, TRUE),
(2, 3, 3, '2024-01-15', 5.97, 1, FALSE),
(2, 6, 2, '2024-01-21', 14.94, 2, TRUE),
(3, 8, 1, '2024-01-22', 47.92, 3, FALSE),
(4, 1, 4, '2024-01-23', 7.99, 4, TRUE),
(5, 4, 5, '2024-01-24', 13.96, 5, FALSE),
(3, 2, 3, '2024-01-15', 3.98, 1, TRUE),
(2, 5, 2, '2024-01-26', 12.45, 2, FALSE),
(3, 7, 1, '2024-01-27', 41.93, 3, TRUE),
(4, 3, 4, '2024-01-28', 23.97, 4, FALSE),
(5, 9, 5, '2024-01-29', 31.41, 5, TRUE);
