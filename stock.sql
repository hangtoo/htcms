create table t_stock(
	p_id int(8) not null AUTO_INCREMENT,
	p_name varchar(255),
	p_data DEC(20,2),
	p_date datetime, 
	p_add DEC(20,2),
	p_rate DEC(5,2),
	p_highdata DEC(20,2),
	p_highdate datetime, 
	p_creator varchar(255), 
	p_modifier varchar(255), 
	p_remark varchar(255), 
	p_deleted bit, 
	primary key (p_id)
);
insert into t_stock(p_name,p_data,p_add,p_rate,p_highdata,p_highdate)
values("股票总市值（元）","30123391863906.90","409664878265.48","1.38","30123391863906.90","2015-06-12");

##-------------------------
create table t_stock1(
	p_id int(8) not null AUTO_INCREMENT, 
	p_name varchar(255),
	p_data varchar(255), 
	p_date datetime, 
	p_add varchar(255), 
	p_rate varchar(255), 
	p_highdata varchar(255), 
	p_highdate datetime, 
	p_creator varchar(255), 
	p_modifier varchar(255), 
	p_remark varchar(255), 
	p_deleted bit, 
	primary key (p_id)
);
insert into t_stock1(p_name,p_data,p_add,p_rate,p_highdata,p_highdate)
value("股票总市值（元）","30,123,391,863,906.90","409,664,878,265.48","1.38","30,123,391,863,906.90","2015-06-12");
