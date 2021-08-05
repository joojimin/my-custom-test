create table member (
    id bigint not null AUTO_INCREMENT,
    address varchar(255) not null,
    age integer not null,
    name varchar(255) not null,
    primary key (id),
    create_time timestamp,
    update_time timestamp
);
