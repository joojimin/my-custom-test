create table member (
    `id` bigint not null AUTO_INCREMENT,
    `address` varchar(255) not null,
    `age` int not null,
    `name` varchar(255) not null,
    `create_time` datetime(6) NOT NULL,
    `update_time` datetime(6) NOT NULL,
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
