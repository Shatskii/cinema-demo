create table seats (
id serial primary key,
row int not null,
number int not null,
price int not null,
is_free varchar(3) not null,
unique (row, number)
);

create table carts (
id serial primary key,
session_id varchar(256) not null unique
);

create table bookings (
id serial primary key,
cart_id int,
seat_id int unique,
constraint fk_carts_id foreign key (cart_id) references carts(id),
constraint fk_seats_id foreign key (seat_id) references seats(id)
);

insert into seats (row, number, price, is_free)
values (1, 1, 100, 'yes');
insert into seats (row, number, price, is_free)
values (1, 2, 100, 'no');
insert into seats (row, number, price, is_free)
values (1, 3, 100, 'yes');
insert into seats (row, number, price, is_free)
values (2, 1, 80, 'no');
insert into seats (row, number, price, is_free)
values (2, 2, 80, 'yes');
insert into seats (row, number, price, is_free)
values (2, 3, 80, 'no');
insert into seats (row, number, price, is_free)
values (3, 1, 50, 'yes');
insert into seats (row, number, price, is_free)
values (3, 2, 50, 'no');
insert into seats (row, number, price, is_free)
values (3, 3, 50, 'yes');

