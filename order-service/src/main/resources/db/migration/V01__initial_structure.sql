create table order_table(
    order_id bigserial,
    order_owner varchar(50),
    order_amount integer,
    shipping_ready boolean,
    PRIMARY KEY (order_id)
);

create table payment_table(
    payment_id bigserial,
    order_id bigint,
    payed boolean,
    PRIMARY KEY (payment_id)
);

create table shipping_table(
    shipping_id bigserial,
    shipping_address varchar(50),
    payed boolean,
    shipping_ready boolean,
    PRIMARY KEY (shipping_id)
    );

create table order_table_payment_table(
    order_id bigint,
    payment_id bigint
)