#create database heh;
#use heh;

charset gbk;

insert into t_role(p_id,p_name) values('1','管理员');
insert into t_admin(p_id,p_role,p_name,p_username,p_password) values('1','1','admin','admin','21232F297A57A5A743894A0E4A801FC3');
####################
insert into t_navigate(p_id,p_name,p_url) values('9','系统菜单','#');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('9999','Google','9','http://www.google.cn','google.png');
insert into t_navigate(p_id,p_name,p_parent,p_url) values('9009','配置管理','9','#');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('90091','新增参数','9009','system/config_newOne.action','config.png');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('90092','参数列表','9009','system/config_search.action','configlist.png');

insert into t_navigate(p_id,p_name,p_parent,p_url) values('9000','权限管理','9','#');

insert into t_navigate(p_id,p_name,p_parent,p_url) values('9001','用户管理','9000','#');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('90011','新增用户','9001','permission/admin_newOne.action','admin.png');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('90010','用户列表','9001','permission/admin_search.action','adminlist.png');

insert into t_navigate(p_id,p_name,p_parent,p_url) values('9002','角色管理','9000','#');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('90021','新增角色','9002','permission/role_newOne.action','role.png');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('90020','角色列表','9002','permission/role_search.action','rolelist.png');

insert into t_navigate(p_id,p_name,p_parent,p_url) values('9003','模块管理','9000','#');
#insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('90031','新增模块','9003','permission/navigate_newOne.action','navigatelist.png');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('90030','模块列表','9003','permission/navigate_search.action','navigate.png');

insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('9004','权限管理','9000','permission/permission_search.action','permission.png');

insert into t_navigate(p_id,p_name,p_parent,p_url) values('9100','内容管理','9','#');
insert into t_navigate(p_id,p_name,p_parent,p_url) values('9101','栏目管理','9100','#');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('91010','栏目列表','9101','cms/catalogue_search.action','catalogue.png');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('91011','栏目权限管理','9101','cms/catalogright_search.action','permission.png');
insert into t_navigate(p_id,p_name,p_parent,p_url) values('9102','属性管理','9100','#');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('91020','属性列表','9102','cms/attribute_search.action','attribute.png');
insert into t_navigate(p_id,p_name,p_parent,p_url) values('9103','信息管理','9100','#');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('91030','内容发布','9103','cms/content_search.action','content.png');
#insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('91031','信息Excel发布','9103','cms/excel_search.action','');
insert into t_navigate(p_id,p_name,p_parent,p_url) values('9200','信息系统','9','#');
insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('9201','票据录入','9200','cms/http_search.action','http.png');
#insert into t_navigate(p_id,p_name,p_parent,p_url,p_icon) values('9202','数据统计','9200','cms/http_search.action','');

insert into t_config(p_id,p_configkey,p_configvalue) values('1','show.top','1');
insert into t_config(p_id,p_configkey,p_configvalue) values('2','show.left','1');

insert into t_config(p_id,p_configkey,p_configvalue) values('3','err_product','没有该产品');
insert into t_config(p_id,p_configkey,p_configvalue) values('4','err_supplier','没有该供应商');

insert into t_config(p_id,p_configkey,p_configvalue,p_desc) values('5','sys_tablemonth','1','按月建表 0表示no,1表示yes');



####################
update t_permission set p_checked=1;
####################
#insert into t_catalogue(p_id,p_name,p_tablename) values('1','栏目列表','cms_info');
#insert into t_catalogue(p_id,p_name,p_parent,p_tablename) values('11','新闻','1','cms_news');
#insert into t_catalogue(p_id,p_name,p_parent,p_tablename) values('12','出货单','1','in_sale');
####################
#insert into t_catalogright(p_id,p_role,p_catalogue) values('11','1','1');
#insert into t_catalogright(p_id,p_role,p_catalogue) values('111','1','11');
#insert into t_catalogright(p_id,p_role,p_catalogue) values('112','1','12');
####################
#insert into t_attribute(p_id,p_name,p_catalogue,p_column,p_type,p_length) values('1101','标题','11','p_title','12','50');
#insert into t_attribute(p_id,p_name,p_catalogue,p_column,p_type,p_length) values('1102','内容','11','p_content','2005','');
#insert into t_attribute(p_id,p_name,p_catalogue,p_column,p_type,p_length) values('1103','附件','11','p_file','2004','');

#insert into t_attribute(p_id,p_name,p_catalogue,p_column,p_type,p_length) values('1201','出货单编号','12','no','12','10');
#insert into t_attribute(p_id,p_name,p_catalogue,p_column,p_type,p_length) values('1202','产品编号','12','productno','12','10');
#insert into t_attribute(p_id,p_name,p_catalogue,p_column,p_type,p_length) values('1203','产品名称','12','productname','12','10');
#insert into t_attribute(p_id,p_name,p_catalogue,p_column,p_type,p_length) values('1204','产品数量','12','productnum','12','10');
#insert into t_attribute(p_id,p_name,p_catalogue,p_column,p_type,p_length) values('1205','产品单位','12','unit','12','10');
####################
#drop table if exists cms_news;

#create table cms_news(
#	p_id varchar(255) not null, 
#	p_title varchar(255),
#	p_author varchar(100),
#	p_content varchar(255),
#	p_file varchar(255),
#	p_createTime datetime, 
#	p_modifyTime datetime, 
#	p_creator varchar(255), 
#	p_modifier varchar(255), 
#	p_remark varchar(255), 
#	p_deleted bit, 
#	primary key (p_id)
#);
####################





