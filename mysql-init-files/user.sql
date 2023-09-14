create table user
(
    user_id   int auto_increment,
    user_name varchar(30)                   not null comment '이름',
    account    varchar(100)                  not null comment '계정',
    use_yn    enum ('y', 'n') default 'y'   not null comment '사용여부',
    create_at datetime        default NOW() not null,
    update_at datetime        default NOW() null on update NOW(),
    constraint payment_pk
        primary key (user_id)
);

create unique index user_acount_uindex
    on user (account);

