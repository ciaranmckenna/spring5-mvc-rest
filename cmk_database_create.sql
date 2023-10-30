create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table customer (id bigint not null auto_increment, firstname varchar(255), lastname varchar(255), primary key (id)) engine=InnoDB;
create table vendor (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
