create database bigframe;
use bigframe;
create table sample(
 id int primary key auto_increment,
 name varchar(100),
 content varchar(255),
 createTime datetime,
 updateTime datetime
);
