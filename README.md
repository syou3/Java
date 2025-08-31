# 作ったもの
・BOOK
  (書籍を一覧で見れるサーブレットアプリ)※データ表示ができないエラーあり
[BOOK.sql](https://github.com/user-attachments/files/22065001/BOOK.sql)
create database bookmemoDB;
use bookmemoDB;

create table booklist(
id int not null auto_increment,
title varchar(256) default null,
name varchar(64) default null,
inputdate date default null,
evaluation int default null,
evadate date default null,
userid varchar(64) not null,
primary key(id)
);

create table users(
userid char(20) not null,
password varchar(20) not null,
primary key(userid)
);

create table roles(
userid char(20) not null,
role varchar(20) not null,
primary key(userid)
);

drop table if exists book_list;

insert into booklist (title,name,inputdate,evaluation,evadate,userid) values('Java入門第2版','柴田望洋','2025-02-27',5,'2025-08-21','myuu');
insert into booklist (title,name,inputdate,evaluation,evadate,userid) values('マスタリングTCP/IP','井上直也','2025-05-26',5,'2025-08-21','myuu');
insert into booklist (title,name,inputdate,evaluation,evadate,userid) values('Androidアプリ開発','山内直','2025-07-15',3,'2025-08-22','myuu');
insert into booklist (title,name,inputdate,evaluation,evadate,userid) values('猫でもわかる設計','ミケ','2025-08-15',3,'2025-08-22','tama');
insert into booklist (title,name,inputdate,evaluation,evadate,userid) values('猫の人間侵略の歴史','ポチ','2020-08-15',3,'2020-09-20','tama');

insert into users values('tama','Nibosi');
insert into users values('myuu','Katuobusi');
delete from roles;

insert into roles values('myuu','admin');
insert into roles values('tama','normal');

select b.id,b.title,b.name,b.inputdate,b.evaluation,b.evadate,b.userid,u.password,r.role
from booklist b
left join users u on b.userid=u.userid
left join roles r on u.userid=r.userid
where b.userid='myuu';
select b.id,b.title,b.name,b.inputdate,b.evaluation,b.evadate,b.userid,u.password,r.role
from booklist b
left join users u on b.userid=u.userid
left join roles r on u.userid=r.userid
where b.userid='tama';
