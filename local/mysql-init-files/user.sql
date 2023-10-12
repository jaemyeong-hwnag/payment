CREATE TABLE user
(
    user_id   int AUTO_INCREMENT
        PRIMARY KEY,
    user_name varchar(100)                                  NOT NULL COMMENT '유저 이름',
    account   varchar(100)                                  NOT NULL unique COMMENT '아이디',
    password  varchar(100)                                  NOT NULL COMMENT '비밀번호',
    use_yn    enum ('y', 'n') DEFAULT 'y'                   NOT NULL,
    create_at timestamp       DEFAULT CURRENT_TIMESTAMP     NOT NULL,
    update_at timestamp       DEFAULT '0000-00-00 00:00:00' NOT NULL
);

