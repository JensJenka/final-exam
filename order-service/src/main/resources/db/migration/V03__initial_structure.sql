create table payment_table(
  payment_id bigserial,
  order_id bigint,
  payed boolean,
  PRIMARY KEY (payment_id),
  CONSTRAINT fk_payment_table_payment_id
      FOREIGN KEY (order_id)
          REFERENCES order_table (order_id)

);