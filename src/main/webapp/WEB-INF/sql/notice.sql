drop table notice;
CREATE TABLE notice(
noticeno    number(10)  not null,
title varchar2(50)  not null,
content varchar2(500)   not null,
wname varchar2(20) not null,
passwd varchar2(20) not null,
viewcnt number(10)  default 0 not null,
ndate date not null,
primary key(noticeno)
);

insert into notice
values((select nvl(max(noticeno),0)+1 from notice),'spring 시즌 접수 안내 1', '10% 할인 실시!', '왕눈이', '1234', 0, sysdate);
insert into notice
values((select nvl(max(noticeno),0)+1 from notice), 'spring 시즌 접수 안내 2', '10% 할인 실시!', '아로미', '1234', 0, sysdate);
insert into notice
values((select nvl(max(noticeno),0)+1 from notice), 'spring 시즌 접수 안내 3', '10% 할인 실시!', '투투투', '1234', 0, sysdate);