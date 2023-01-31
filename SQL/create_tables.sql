CREATE TABLE categories (
	category_id serial PRIMARY KEY,
	category_name VARCHAR (50) UNIQUE NOT NULL
);

CREATE TABLE colors (
	color_id serial PRIMARY KEY,
	color VARCHAR(20) NOT NULL
);

CREATE TABLE sizes (
	size_id serial PRIMARY KEY,
	size VARCHAR(20) NOT NULL
);

CREATE TABLE picture_sets (
	picture_set_id serial PRIMARY KEY,
	description VARCHAR(500) NOT NULL DEFAULT ''
);

CREATE TABLE pictures (
	picture_id serial PRIMARY KEY,
	picture_set_id INT,
	FOREIGN KEY (picture_set_id) REFERENCES picture_sets(picture_set_id),
	link VARCHAR(100) UNIQUE NOT NULL DEFAULT 'default link to picture'
);

CREATE TABLE items (
	item_id serial PRIMARY KEY,
	category_id INT,
	FOREIGN KEY (category_id) REFERENCES categories(category_id),
	size_id INT,
	FOREIGN KEY (size_id) REFERENCES sizes(size_id),
	picture_id INT,
	FOREIGN KEY (picture_id) REFERENCES pictures(picture_id),
	color_id INT,
	FOREIGN KEY (color_id) REFERENCES colors(color_id),
	item_name VARCHAR (100) UNIQUE NOT NULL,
	price INT NOT NULL
);

CREATE TABLE users (
	user_id serial PRIMARY KEY,
	first_name VARCHAR (50),
	last_name VARCHAR (50),
	password VARCHAR (50),
	email VARCHAR (255) UNIQUE,
	address VARCHAR (255),
	created_on TIMESTAMP,
	last_login TIMESTAMP
);

CREATE TABLE orders (
	order_id serial PRIMARY KEY,
	user_id INT,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	condition VARCHAR(20) NOT NULL DEFAULT 'draft',
	order_date TIMESTAMP NOT NULL,
	price INT NOT NULL
);

CREATE TABLE order_items (
	order_id INT,
	item_id INT,
	PRIMARY KEY (order_id, item_id),
	FOREIGN KEY (order_id) REFERENCES orders(order_id),
	FOREIGN KEY (item_id) REFERENCES items(item_id)
);

SELECT * FROM order_items;