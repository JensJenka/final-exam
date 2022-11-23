insert into order_table
values (nextval('order_table_order_id_seq'), 'Jens Jenka', 249, false);

insert into order_table
values (nextval('order_table_order_id_seq'), 'Jason Wiliams', 2499, true);

insert into payment_table
values (nextval('payment_table_payment_id_seq'), 1, false);

insert into order_table_payment_table
values (1, 2);