
create table card
(
    card_id     bigint auto_increment
        primary key,
    card_amount int          null,
    card_number varchar(255) null
);
create table customer
(
    customer_id bigint auto_increment
        primary key,
    address     varchar(255) not null,
    name        varchar(255) not null
);
create table contract
(
    contract_id bigint auto_increment
        primary key,
    amount      decimal(38, 2) not null,
    comment     varchar(255)   not null,
    date        date           not null,
    name        varchar(255)   not null,
    subject     varchar(255)   not null,
    customer_id bigint         not null,
    foreign key (customer_id) references customer (customer_id)
);



create table payment
(
    payment_id      bigint auto_increment
        primary key,
    amount_of_money decimal(38, 2) null,
    times           datetime(6)    null,
    card_id         bigint         not null,
    contract_id     bigint         not null,
    foreign key (card_id) references card (card_id),
    foreign key (contract_id) references contract (contract_id)
);



