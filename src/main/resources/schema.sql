create table customers (
                           id serial primary key,
                           name text varchar(255) not null,
                           surname text varchar(255) not null,
                           age int,
                           phone_number varchar(20)

);

create table orders (
                        id serial primary key,
                        date date,
                        customer_id int,
                        product_name text varchar(255),
                        amount int,
                        foreign key (customer_id) references customers(id)
);

