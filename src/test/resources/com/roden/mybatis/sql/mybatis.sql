create database study;
create table study.user
(
    id  int auto_increment primary key,
    user_name   varchar(32) null,
    birth_day   date        null,
    create_time timestamp   null
) comment '用户表';

create unique index uni_user_name on user (user_name);

insert into user(user_name,birth_day,create_time)values ('roden','1991-04-23',now());

create table id_card
(
    id int auto_increment,
    id_card_no varchar(32) not null,
    id_card_name varchar(32) not null,
    status int null,
    create_time timestamp null,
    update_time timestamp null,
    constraint id_card_pk
        primary key (id)
)
    comment '身份证信息';

create unique index uni_id_card_no on id_card (id_card_no);
