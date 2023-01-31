insert into colors (color)
values ('black'),
       ('red'),
       ('blue'),
       ('green');

insert into categories (category_name)
values ('hoodie'),
       ('t-short'),
       ('backpack');

insert into items (item_name, category_id, color_id, price)
values ('black hoodie', 1, 1, 1000),
       ('red hoodie', 1, 2, 1100),
       ('black t-short', 2, 1, 500);
