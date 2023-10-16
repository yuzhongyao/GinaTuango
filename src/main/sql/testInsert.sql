-- Inserts for Addresses table
INSERT INTO addresses (street, postal, city, province, country)
VALUES
  ('123 Main St', '12345', 'City A', 'Province A', 'Country A'),
  ('456 Elm St', '54321', 'City B', 'Province B', 'Country B'),
  ('789 Oak St', '98765', 'City C', 'Province C', 'Country C'),
  -- Add more addresses as needed
  ('101 Pine St', '11111', 'City D', 'Province D', 'Country D'),
  ('202 Cedar St', '22222', 'City E', 'Province E', 'Country E');

-- Inserts for Users table
INSERT INTO users (user_name, password, email, phone, address_id, is_admin)
VALUES
  ('User1', 'Password1', 'user1@example.com', '111-111-1111', 1, true),
  ('User2', 'Password2', 'user2@example.com', '222-222-2222', 2, false),
  ('User3', 'Password3', 'user3@example.com', '333-333-3333', 3, false),
  -- Add more users as needed
  ('User4', 'Password4', 'user4@example.com', '444-444-4444', 4, true),
  ('User5', 'Password5', 'user5@example.com', '555-555-5555', 5, false);

-- Inserts for Categories table
INSERT INTO categories (category_name)
VALUES
  ('Category A'),
  ('Category B'),
  ('Category C'),
  -- Add more categories as needed
  ('Category D'),
  ('Category E');

-- Inserts for Items table
INSERT INTO items (item_name, description, category_id, stock, price)
VALUES
  ('Item 1', 'Description 1', 1, 100.00, 9.99),
  ('Item 2', 'Description 2', 2, 50.00, 19.99),
  ('Item 3', 'Description 3', 3, 75.00, 14.99),
  -- Add more items as needed
  ('Item 4', 'Description 4', 1, 120.00, 11.99),
  ('Item 5', 'Description 5', 2, 60.00, 24.99);

-- Inserts for Images table
INSERT INTO images (item_id, url)
VALUES
  (1, 'https://example.com/image1.jpg'),
  (2, 'https://example.com/image2.jpg'),
  (3, 'https://example.com/image3.jpg'),
  -- Add more images as needed
  (4, 'https://example.com/image4.jpg'),
  (5, 'https://example.com/image5.jpg');

-- Inserts for ItemSaleTypes table
INSERT INTO itemSaleTypes (type_name)
VALUES
  ('Type X'),
  ('Type Y'),
  ('Type Z'),
  -- Add more types as needed
  ('Type A'),
  ('Type B');

-- Inserts for Orders table
INSERT INTO orders (user_id, sale_date)
VALUES
  (1, '2023-09-01'),
  (2, '2023-09-02'),
  (3, '2023-09-03'),
  -- Add more orders as needed
  (4, '2023-09-04'),
  (5, '2023-09-05');

-- Inserts for ItemSales table
INSERT INTO itemSales (item_id, quantity, type_id, sale_date, item_cost, order_id, isOnSale)
VALUES
  (1, 5.0, 1, '2023-09-01', 49.95, 1, true),
  (2, 3.0, 2, '2023-09-02', 59.97, 2, false),
  (3, 2.5, 3, '2023-09-03', 37.48, 3, true),
  -- Add more item sales as needed
  (4, 4.0, 1, '2023-09-04', 47.96, 4, false),
  (5, 6.0, 2, '2023-09-05', 149.94, 5, true);
