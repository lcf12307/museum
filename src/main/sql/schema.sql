-- 数据库初始化脚本
 drop DATABASE museum;
 CREATE DATABASE museum;
  use museum;
create TABLE member(
    id int(11) primary key not null auto_increment comment '用户id',
    name VARCHAR(200) not null DEFAULT '' COMMENT '用户名',
    age int(4) not null DEFAULT 0 comment '年龄',
    email VARCHAR(200) not null DEFAULT '' comment '邮箱',
    phone VARCHAR (11) not null ,
    password VARCHAR(200) not null DEFAULT '' comment '密码',
    role int(4) not null DEFAULT 0 comment '角色'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


create TABLE role(
    id int(11) primary key not null auto_increment comment '角色id',
    name VARCHAR(200) not null DEFAULT '' COMMENT '类别名',
    description VARCHAR(200) not null DEFAULT '' comment '角色简介',
    authority int(4) not null DEFAULT 0 comment '权限'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


create TABLE quota(
    id int(11) primary key not null auto_increment,
    name VARCHAR(200) not null DEFAULT '' COMMENT '类别名',
    quota_id int(11)  not null DEFAULT 0 comment '指标id',
    description VARCHAR(200) not null DEFAULT '' comment '专家简介',
    year int(11) not null DEFAULT 0  comment '年份'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


create TABLE museum(
    id int(11) primary key not null auto_increment comment '博物馆id',
    name VARCHAR(200) not null DEFAULT '' COMMENT '类别名',
    category VARCHAR(200) not null DEFAULT 0 comment '种类',
    level VARCHAR(200) not null DEFAULT 0 comment '等级',
    year VARCHAR(200) not null DEFAULT '' comment '年份',
    description VARCHAR(200) not null DEFAULT '' comment '博物馆简介'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

create TABLE attachment(
    id int(11) primary key not null auto_increment comment '附件id',
    name VARCHAR(200) not null DEFAULT '' COMMENT '附件名',
    file VARCHAR(200) not null DEFAULT '' comment '文件路径',
    type int(4) not null DEFAULT 0 comment '附件类型',
    addtime varchar(15) not null default '' comment '附件类型',
    year int(4) not null default 0 comment '年份'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

drop table point;
create TABLE point(
    id int(11) primary key not null auto_increment comment 'id',
    name VARCHAR(200) not null DEFAULT '' COMMENT '专家名',
    mid int(4) not null DEFAULT 0 comment '博物馆id',
    year int(11) not null DEFAULT 0  comment '年份',
    point DECIMAL(5,2) not null DEFAULT 0 comment '分数',
    type int(4) not null DEFAULT 0 comment '分数类型'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

INSERT into role values(1,'超级管理员','超级管理员',255);
/*密码为1*/
insert into member values(1,'admin',21,'admin@admin.com','13000000000','39bfd36f6e86817d55b10aea02fcb9e7',1);

