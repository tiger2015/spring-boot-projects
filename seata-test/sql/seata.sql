

create table if not exists t_account(
  id serial not null,
  user_id varchar(255),
  amount numeric(14, 4),
  primary key(id)
)



create table if not exists t_order(
  id serial not null,
  order_no varchar(255),
  user_id varchar(255),
  commodity_code varchar(255),
  "count" int4 default 0,
  ammount numeric(14,2) default 0,
  primary key (id)
)


create table if not exists t_stock(
  id serial not null,
  commodity_code varchar(255),
  name varchar(255),
  "count" int4 default 0,
  primary key(id),
  unique (commodity_code)
)