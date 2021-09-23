drop database if exists shopshare;

show create database shopshare;
CREATE DATABASE `shopshare` /*!40100 DEFAULT CHARACTER SET utf8 */

use shopshare;


select *from user;

delete from user where email = 'bob@163.com';

drop table if exists user;

CREATE TABLE `user` (
  `id` int(11) AUTO_INCREMENT,
  `username` varchar(100) NOT NULL COMMENT 'username',
  `password` varchar(100) NOT NULL COMMENT 'password',
  `email` varchar(100) NOT NULL COMMENT 'email',
  `is_active` tinyint(1) DEFAULT '1' COMMENT '1 active 0 not',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp default current_timestamp on update current_timestamp COMMENT 'update time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_email` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user_information_table'

insert into user values(null, 'admin', '123456', 'admin@163.com', 1, null, null);
insert into user values(null, 'bob', '123456', 'bob@163.com', 1, null, null);
insert into user values(null, 'darren', '123456', 'darren@163.com', 1, null, null);
insert into user values(null, 'darren1', '123456', 'darren1@163.com', 1, null, null);
insert into user values(null, 'darren2', '123456', 'darren2@163.com', 1, null, null);
insert into user values(null, 'darren3', '123456', 'darren3@163.com', 1, null, null);
insert into user values(null, 'darren4', '123456', 'darren4@163.com', 1, null, null);
insert into user values(null, 'darren5', '123456', 'darren5@163.com', 1, null, null);
insert into user values(null, 'darren6', '123456', 'darren6@163.com', 1, null, null);


DROP table  IF EXISTS shared_order;

CREATE TABLE `shared_order` (
  `id` int(11) AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT 'title',
  `description` varchar(65535) NOT NULL COMMENT 'details and needs',
  `photo` varchar(255) DEFAULT '/images/login_cart.jpg' COMMENT 'photo link',
  `tags` varchar(255) COMMENT 'tags for classication',
  `deadline` VARCHAR(255) DEFAULT 'Anytime' COMMENT 'expired after the deadline',
  `initiator_id` int(11) NOT NULL COMMENT 'link to user table',
  `view_count` int(11) DEFAULT 0 COMMENT 'count the view number by user',
  `comment_count` int(11) DEFAULT 0 COMMENT 'count the comment number by user',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp DEFAULT current_timestamp on update current_timestamp COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='shared_order_information_table'

select *from shared_order;

alter table shared_order add COLUMN `comment_count` int(11) DEFAULT 0 COMMENT 'count the comment number by user';

update shared_order set photo = '/images/login_cart.jpg';

ALTER TABLE shared_order drop `comment_count`;

alter table shared_order modify photo varchar(255) DEFAULT '/images/login_cart.jpg' COMMENT 'photo link';


insert into shared_order(id, title, description, tags, deadline, initiator_id) 
value (null, '中超', 'need students to buy things', 'supermarket' , '2021-09-01', 2); 


update shared_order set photo = '/images/waitrose.jpg' where id = 1;


alter table shared_order change user_id  initiator_id int(11);


#comment table


drop table if exists comment;


CREATE TABLE `comment` (
  `id` int(11) AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT 'link to parent comment',
  `type` int(11) NOT NULL COMMENT 'type to classify the parent and child comment',
  `commenter_id` int(11) NOT NULL COMMENT 'link to user id',
  `like_count` int(11) DEFAULT 0 COMMENT 'count of like',
  `content` VARCHAR(1024) NOT NULL COMMENT 'the content of one comment',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp DEFAULT current_timestamp on update current_timestamp COMMENT 'update time',
  PRIMARY KEY (`id`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT= 'comment information table'


select * from comment;

alter table comment change commenter commenter_id int(11);
alter table comment change likeCount like_count int(11);

alter table comment modify type int(11);


insert into comment(parent_id, type, commenter_id,content) values(1, 1, 1,'I want to join');


#order_id --> user_id --> joiner_id


CREATE TABLE `joiner` (
  `id` int(11) AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT 'the order id',
  `joiner_id` int(11) NOT NULL COMMENT 'joiners who join one order',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp DEFAULT current_timestamp on update current_timestamp COMMENT 'update time',
  PRIMARY KEY (`id`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT= 'comment information table'
 
 select * from joiner;
 

