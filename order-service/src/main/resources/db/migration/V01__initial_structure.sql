create table order_table(
    order_id bigserial primary key,
    order_owner varchar(50),
    order_amount integer,
    shipping_ready boolean
);