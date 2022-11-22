create table order_table(
    order_id bigserial,
    order_owner varchar(50),
    order_amount integer,
    shipping_ready boolean,
    PRIMARY KEY (order_id)
);