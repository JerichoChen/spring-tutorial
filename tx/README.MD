# 事务
##1. 创建库表,实体
```
create table product(
    id int default 0 not null comment '商品id' primary key,
   	name varchar(80) null comment '商品名称',
   	amount int null comment '库存量',
   	price decimal(8,2) null comment '单价'
) comment '产品信息表' charset=utf8;
```
```
create table sale_record(
   	id int auto_increment comment '记录id' primary key,
   	gid int null comment '商品id',
   	quantity int null comment '销售数量'
) comment '销售记录表' charset=utf8;
```
```
INSERT INTO mybatis.product (id, name, amount, price) VALUES (1001, '笔记本电脑', 100, 5000.00);
INSERT INTO mybatis.product (id, name, amount, price) VALUES (1002, '苹果手机', 50, 3000.00);
```