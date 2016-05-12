alter table t_admin drop foreign key FK9FF53FC4E65264BB
alter table t_attribute drop foreign key FK1FA9A49165FEBE01
alter table t_attribute drop foreign key FK1FA9A491FC762211
alter table t_card drop foreign key FKCB5B5BFBFDCC27FA
alter table t_card drop foreign key FKCB5B5BFB434CD93F
alter table t_catalogright drop foreign key FK97BC4F8EFC762211
alter table t_catalogright drop foreign key FK97BC4F8EE65264BB
alter table t_catalogue drop foreign key FK75A9C65EB2FDCE10
alter table t_navigate drop foreign key FK33FDF8DC894C622A
alter table t_nurl drop foreign key FKCB60A72C434CD93F
alter table t_permission drop foreign key FK9E830A7A1385EF31
alter table t_permission drop foreign key FK9E830A7AE65264BB
drop table if exists t_admin
drop table if exists t_attribute
drop table if exists t_blob
drop table if exists t_card
drop table if exists t_catalogright
drop table if exists t_catalogue
drop table if exists t_clob
drop table if exists t_config
drop table if exists t_hotel
drop table if exists t_navigate
drop table if exists t_nurl
drop table if exists t_permission
drop table if exists t_role
drop table if exists t_user
create table t_admin (p_id varchar(255) not null, p_name varchar(64), p_password varchar(255), p_role varchar(255), p_username varchar(255), p_position varchar(255), p_group varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
create table t_attribute (p_id varchar(255) not null, p_name varchar(255), p_catalogue varchar(255), p_column varchar(255), p_type varchar(255), p_length varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, p_catalog varchar(255), primary key (p_id))
create table t_blob (p_id varchar(255) not null, p_content blob, p_name varchar(255), p_type varchar(255), primary key (p_id))
create table t_card (p_id varchar(255) not null, p_hotel varchar(255), p_name varchar(255), p_no varchar(255), p_off float, p_desc varchar(255), p_user varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
create table t_catalogright (p_id varchar(255) not null, p_role varchar(255), p_checked varchar(255), p_catalogue varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
create table t_catalogue (p_id varchar(255) not null, p_name varchar(64), p_parent varchar(255), p_order varchar(255), p_tablename varchar(255), p_version varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
create table t_clob (p_id varchar(255) not null, p_content text, p_name varchar(255), primary key (p_id))
create table t_config (p_id varchar(255) not null, p_configkey varchar(255), p_configvalue varchar(255), p_desc varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
create table t_hotel (p_id varchar(255) not null, p_desc varchar(255), p_area varchar(255), p_name varchar(255), p_address varchar(255), p_mapx float, p_mapy float, p_prices varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
create table t_navigate (p_id varchar(255) not null, p_name varchar(64), p_parent varchar(255), p_order varchar(255), p_url varchar(255), p_icon varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
create table t_nurl (p_id varchar(255) not null, p_desc varchar(255), p_user varchar(255), p_nurl varchar(255), p_tags varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
create table t_permission (p_id varchar(255) not null, p_role varchar(255), p_checked varchar(255), p_navigate varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
create table t_role (p_id varchar(255) not null, p_name varchar(64), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
create table t_user (p_id varchar(255) not null, p_name varchar(255), p_username varchar(255), p_password varchar(255), p_position varchar(255), p_address varchar(255), p_company varchar(255), p_phonenumber varchar(255), p_sex varchar(255), p_email varchar(255), p_createTime datetime, p_modifyTime datetime, p_creator varchar(255), p_modifier varchar(255), p_remark varchar(255), p_deleted bit, primary key (p_id))
alter table t_admin add index FK9FF53FC4E65264BB (p_role), add constraint FK9FF53FC4E65264BB foreign key (p_role) references t_role (p_id)
alter table t_attribute add index FK1FA9A49165FEBE01 (p_catalog), add constraint FK1FA9A49165FEBE01 foreign key (p_catalog) references t_catalogue (p_id)
alter table t_attribute add index FK1FA9A491FC762211 (p_catalogue), add constraint FK1FA9A491FC762211 foreign key (p_catalogue) references t_catalogue (p_id)
alter table t_card add index FKCB5B5BFBFDCC27FA (p_hotel), add constraint FKCB5B5BFBFDCC27FA foreign key (p_hotel) references t_hotel (p_id)
alter table t_card add index FKCB5B5BFB434CD93F (p_user), add constraint FKCB5B5BFB434CD93F foreign key (p_user) references t_user (p_id)
alter table t_catalogright add index FK97BC4F8EFC762211 (p_catalogue), add constraint FK97BC4F8EFC762211 foreign key (p_catalogue) references t_catalogue (p_id)
alter table t_catalogright add index FK97BC4F8EE65264BB (p_role), add constraint FK97BC4F8EE65264BB foreign key (p_role) references t_role (p_id)
alter table t_catalogue add index FK75A9C65EB2FDCE10 (p_parent), add constraint FK75A9C65EB2FDCE10 foreign key (p_parent) references t_catalogue (p_id)
alter table t_navigate add index FK33FDF8DC894C622A (p_parent), add constraint FK33FDF8DC894C622A foreign key (p_parent) references t_navigate (p_id)
alter table t_nurl add index FKCB60A72C434CD93F (p_user), add constraint FKCB60A72C434CD93F foreign key (p_user) references t_user (p_id)
alter table t_permission add index FK9E830A7A1385EF31 (p_navigate), add constraint FK9E830A7A1385EF31 foreign key (p_navigate) references t_navigate (p_id)
alter table t_permission add index FK9E830A7AE65264BB (p_role), add constraint FK9E830A7AE65264BB foreign key (p_role) references t_role (p_id)
