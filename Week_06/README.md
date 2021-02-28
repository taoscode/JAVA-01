### 第12节课作业实践

------

1. （选做）：基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统 进行数据库设计或是数据库服务器方面的优化。

2. （必做）：基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面2周的作业依然要是用到这个表结构）。

   - 用户信息表：

     与订单表的关系：1-->N

     索引：手机号

     ~~~mysql
     CREATE TABLE `T_USER` (
       `id` bigint(20) NOT NULL AUTO_INCREMENT,
       `username` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '账号名称',
       `nickname` varchar(255) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '昵称',
       `password` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '密码',
       `gender` tinyint(1) DEFAULT NULL COMMENT '性别：0-女，1-男',
       `phone` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '手机号',
       `createtime` bigint(20) DEFAULT NULL COMMENT '创建时间，时间戳',
       `updatetime` bigint(20) DEFAULT NULL COMMENT '修改时间',
       PRIMARY KEY (`id`),
       UNIQUE KEY `IDX_USER_PHONE` (`phone`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
     ~~~

     

   - 商品信息表：

     ~~~mysql
     CREATE TABLE `T_PRODUCT` (
       `id` bigint(20) NOT NULL AUTO_INCREMENT,
       `productname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '商品名称',
       `price` decimal(12,2) DEFAULT NULL COMMENT '商品价格',
       `productpic` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '商品图片',
       `createtime` bigint(20) DEFAULT NULL COMMENT '创建时间，时间戳',
       `updatetime` bigint(20) DEFAULT NULL COMMENT '修改时间',
       PRIMARY KEY (`id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';
     ~~~

     

   - 订单信息表：

     与用户信息表的关系：N-->1

     与订单明细表的关系：1-->N

     ~~~mysql
     CREATE TABLE `T_ORDER` (
       `id` bigint(20) NOT NULL AUTO_INCREMENT,
       `orderno` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '订单编号',
       `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
       `totalamount` decimal(12,2) DEFAULT NULL COMMENT '总金额',
       `payamount` decimal(12,2) DEFAULT NULL COMMENT '实际支付金额',
       `orderstatus` tinyint(1) DEFAULT NULL COMMENT '订单状态：0-未支付，1-已支付',
       `paytime` bigint(20) DEFAULT NULL COMMENT '支付时间',
       `createtime` bigint(20) DEFAULT NULL COMMENT '创建时间，时间戳',
       `updatetime` bigint(20) DEFAULT NULL COMMENT '修改时间',
       PRIMARY KEY (`id`),
       UNIQUE KEY `IDX_ORDER_ORDERNO` (`orderno`),
       KEY `IDX_ORDER_USERID` (`userid`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';
     ~~~

     

   - 订单明细表：

     与订单表关系：N-->1

     与用户表关系：N-->1

     ~~~mysql
     CREATE TABLE `T_ORDER ITEM` (
       `id` bigint(20) NOT NULL AUTO_INCREMENT,
       `orderno` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '订单编号',
       `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
       `productid` bigint(20) DEFAULT NULL COMMENT '商品id',
       `amount` decimal(12,2) DEFAULT NULL COMMENT '订单明细金额',
       `createtime` bigint(20) DEFAULT NULL COMMENT '创建时间，时间戳',
       `updatetime` bigint(20) DEFAULT NULL COMMENT '修改时间',
       PRIMARY KEY (`id`),
       KEY `IDX_ORDER_ITME_ORDERNO` (`orderno`),
       KEY `IDX_ORDER_ITEM_USERID` (`userid`),
       KEY `IDX_ORDER_ITEM_PRODUCTID` (`productid`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单明细表';
     ~~~

     

3. （选做）：尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的 SQL 测试简单的增删改查。

4. （选做）：基于上一题，尝试对各个数据库测试100万订单数据的增删改查性能。

5. （选做）：尝试对 MySQL 不同引擎下测试100万订单数据的增删改查性能。

6. （选做）：模拟1000万订单数据，测试不同方式下导入导出（数据备份还原） MySQL 的速度，包括 jdbc 程序处理和命令行处理。思考和实践，如何提升处理效率。

7. （选做）：对 MySQL 配置不同的数据库连接池（DBCP、C3P0、Druid、Hikari）， 测试增删改查100万次，对比性能，生成报告。

