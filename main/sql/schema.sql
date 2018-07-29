-- 数据库初始化脚本


-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
use seckill;
-- 创建秒杀表
CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` varchar(120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存数量',
`start_time` DATETIME NOT NULL COMMENT '秒杀开始时间',
`end_time` DATETIME NOT NULL COMMENT '秒杀结束时间',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';


-- 初始化数据
insert into
  seckill(name,number,start_time,end_time)
values
  ('1000元秒杀iPhone X',100,'2018-08-08 00:00:00','2018-08-09 00:00:00' ),
  ('500元秒杀iPd',200,'2018-08-08 00:00:00','2018-08-09 00:00:00' ),
  ('300元秒杀小米8',300,'2018-08-08 00:00:00','2018-08-09 00:00:00' ),
  ('200元秒杀OPPO Find X',400,'2018-08-08 00:00:00','2018-08-09 00:00:00' ),
  ('100元秒杀xps',500,'2018-07-27 00:00:00','2018-07-28 00:00:00' );


-- 秒杀成功明细表
-- 用户登录认证相关信息
CREATE TABLE success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT 0 COMMENT '状态表示：1：表示秒杀成功 0：秒杀结束 -1：重复秒杀 -2：系统异常 -3：数据篡改 ',
`create_time` TIMESTAMP  NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id,user_phone), /*联合主键*/
KEY idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';


-- 链接数据库控制台