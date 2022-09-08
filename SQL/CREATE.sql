CREATE TABLE users (
	user_id serial PRIMARY KEY,
	first_name VARCHAR (50) NOT NULL,
	last_name VARCHAR (50) NOT NULL,
	password VARCHAR (50) NOT NULL,
	email VARCHAR (255) UNIQUE NOT NULL,
	address VARCHAR (255) NOT NULL,
	created_on TIMESTAMP NOT NULL,
	last_login TIMESTAMP 
);

CREATE TABLE categories (
	category_id serial PRIMARY KEY,
	category_name VARCHAR (50) UNIQUE NOT NULL,
	main_picture VARCHAR (100) UNIQUE NOT NULL
);

CREATE TABLE items (
	item_id serial PRIMARY KEY,
	category_id INT,
	FOREIGN KEY (category_id) REFERENCES categories(category_id),
	item_name VARCHAR (100) UNIQUE NOT NULL,
	description VARCHAR (500) UNIQUE NOT NULL,
	picture VARCHAR (100) UNIQUE NOT NULL,
	price INT NOT NULL
);

CREATE TABLE orders (
	order_id serial PRIMARY KEY,
	user_id INT,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	item_id INT,
	FOREIGN KEY (item_id) REFERENCES items(item_id),
	order_date TIMESTAMP NOT NULL,
	price INT NOT NULL
);

