create table `user`
(
    id   int auto_increment
        primary key,
    user_name varchar(100)                                  not null comment '유저 이름',
    account   varchar(100)                                  not null unique comment '아이디',
    password  varchar(100)                                  not null comment '비밀번호',
    use_yn    enum ('y', 'n') default 'y'                   not null,
    create_at timestamp       default current_timestamp     not null,
    update_at timestamp       default null
);