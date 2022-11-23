create table shipping_table(
    shipping_id bigserial,
    shipping_address varchar(50),
    payed boolean,
    shipping_ready boolean,
       PRIMARY KEY (shipping_id)
);