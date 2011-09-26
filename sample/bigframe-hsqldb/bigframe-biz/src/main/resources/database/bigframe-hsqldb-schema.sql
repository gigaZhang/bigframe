create table sample(
	id int not null IDENTITY,
	name varchar(100),
	content varchar(255),
	createTime datetime,
	updateTime datetime,
	constraint pk_sample primary key (id)
);
