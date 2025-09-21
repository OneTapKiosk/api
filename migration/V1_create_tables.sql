create table cart
(
    id          bigint                      not null
        primary key,
    created_at  timestamp(6) with time zone not null,
    kiosk_id    bigint                      not null,
    total_price integer                     not null,
    updated_at  timestamp(6) with time zone not null
);

alter table cart
    owner to postgres;

create table cart_item
(
    id         bigint       not null
        primary key,
    image_url  varchar(255),
    name       varchar(255) not null,
    price      integer      not null,
    product_id bigint       not null,
    quantity   integer      not null,
    cart_id    bigint
        constraint fk1uobyhgl1wvgt1jpccia8xxs3
            references cart
);

alter table cart_item
    owner to postgres;

create table inventory
(
    id           bigint                      not null
        primary key,
    created_at   timestamp(6) with time zone not null,
    deleted_at   timestamp(6) with time zone,
    is_available boolean                     not null,
    is_deleted   boolean                     not null,
    kiosk_id     bigint                      not null,
    product_id   bigint                      not null,
    quantity     integer                     not null,
    updated_at   timestamp(6) with time zone not null
);

alter table inventory
    owner to postgres;

create table kiosk
(
    id         bigint       not null
        primary key,
    company_id bigint       not null,
    deleted_at timestamp(6) with time zone,
    is_deleted boolean      not null,
    location   varchar(255) not null,
    name       varchar(255) not null,
    status     varchar(255) not null,
    version    varchar(255) not null
);

alter table kiosk
    owner to postgres;

create table device
(
    device_id bigint       not null
        constraint fkf4edhaprqnj1n5p0cq5txf6m9
            references kiosk,
    devices   varchar(255) not null,
    primary key (device_id, devices)
);

alter table device
    owner to postgres;

create table orders
(
    id          bigint                      not null
        primary key,
    created_at  timestamp(6) with time zone not null,
    kiosk_id    bigint                      not null,
    status      varchar(255)                not null,
    total_price integer                     not null,
    updated_at  timestamp(6) with time zone not null
);

alter table orders
    owner to postgres;

create table order_item
(
    id       bigint       not null
        primary key,
    name     varchar(255) not null,
    price    integer      not null,
    quantity integer      not null,
    order_id bigint
        constraint fkt4dc2r9nbvbujrljv3e23iibt
            references orders
);

alter table order_item
    owner to postgres;

create table product
(
    id            bigint       not null
        primary key,
    barcode       varchar(255) not null,
    category_id   bigint,
    deleted_at    timestamp(6) with time zone,
    description   varchar(255),
    display_order integer      not null,
    image_url     varchar(255) not null,
    is_deleted    boolean      not null,
    kiosk_id      bigint       not null,
    name          varchar(255) not null,
    price         integer      not null
);

alter table product
    owner to postgres;

create table sale
(
    id          bigint                      not null
        primary key,
    created_at  timestamp(6) with time zone not null,
    kiosk_id    bigint                      not null,
    total_price integer                     not null
);

alter table sale
    owner to postgres;

create table sale_item
(
    id           bigint       not null
        primary key,
    name         varchar(255) not null,
    price        integer      not null,
    quantity     integer      not null,
    sale_item_id bigint
        constraint fkjaqg532vde0w3ic5g6f9ps1gx
            references sale
);

alter table sale_item
    owner to postgres;

create materialized view vw_orders as
SELECT o.id,
       o.status,
       o.total_price,
       o.kiosk_id,
       o.created_at,
       o.updated_at,
       json_agg(json_build_object('id', oi.id, 'name', oi.name, 'quantity', oi.quantity, 'price',
                                  oi.price)) AS order_items
FROM orders o
         JOIN order_item oi ON o.id = oi.order_id
GROUP BY o.id, o.status, o.total_price, o.kiosk_id, o.created_at, o.updated_at;

alter materialized view vw_orders owner to postgres;

create unique index idx_unique_vw_orders
    on vw_orders (id);

