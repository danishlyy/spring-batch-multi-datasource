create table product
(
    id                varchar(32) not null
        constraint product_pkey
            primary key,
    product_code      varchar(128),
    product_name      varchar(1024),
    product_type      varchar(128),
    fund_manager_code varchar(128),
    fund_trustee_code varchar(128)
);

comment on table product is '产品表';

comment on column product.id is '产品id';

comment on column product.product_code is '基金代码';

comment on column product.product_name is '基金简称';

comment on column product.product_type is '基金类型';

comment on column product.fund_manager_code is '基金管理人code';

comment on column product.fund_trustee_code is '基金托管人code';

alter table product
    owner to product_dev3;

