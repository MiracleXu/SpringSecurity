CREATE TABLE IF NOT EXISTS `user`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `username` VARCHAR(40) NOT NULL,
   `password` VARCHAR(40) NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `role`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(40) NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `role_user`(
  `user_id` INT UNSIGNED,
  `role_id` INT UNSIGNED
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `permission`(
  `id` INT UNSIGNED AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  `description` VARCHAR (20) NOT NULL ,
  `url` VARCHAR (20) NOT NULL ,
  `pid` INT ,
  PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `permission_role`(
 `id` INT UNSIGNED AUTO_INCREMENT,
  `role_id` INT UNSIGNED,
  `permission_id` INT UNSIGNED,
  PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into `user` (id,username, password) values ('dzc', '123');
insert into `user` (id,username, password) values ('zyw', '123');

insert into `role` (id,name) values('ROLE_VIP1');
insert into `role`(id,name) values('ROLE_VIP2');
insert into `role`(id,name) values('ROLE_VIP3');

insert into `role_user`(user_id,role_id) values(1,1);
insert into `role_user`(user_id,role_id) values(2,2);
insert into `role_user`(user_id,role_id) values(1,3);

INSERT INTO `permission` VALUES (null ,'ROLE_HOME', 'home', '/', null), ('ROLE_ADMIN', 'ABel', '/admin', null);

INSERT INTO `permission_role` VALUES (null,'1', '1'), (null,'1', '2'), (null,'2', '1');
