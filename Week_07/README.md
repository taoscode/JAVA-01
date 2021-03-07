### 第13节课作业实践  ###

------

1. （选做）用今天课上学习的知识，分析自己系统的 SQL 和表结构 

2. （必做）按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。

   [连接](https://github.com/taoscode/JAVA-01/tree/main/Week_07/InsertTest)

   测试结果：

   - 开启rewriteBatchedStatements：

   ```
   jdbc Statement单条循环插入10000条订单耗时：223489ms
   
   jdbc PreparedStatement批量插入10000条订单耗时：469ms
   jdbc PreparedStatement批量插入100000条订单耗时：1875ms
   jdbc PreparedStatement批量插入1000000条订单耗时：19972ms
   
   HikarPool 批量插入10000条订单耗时：702ms
   HikarPool 批量插入100000条订单耗时：2064ms
   HikarPool 批量插入1000000条订单耗时：20897ms
   ```

   - 未开启rewriteBatchedStatements:

   ```
   jdbc Statement单条循环插入10000条订单耗时：218667ms
   
   jdbc PreparedStatement批量插入10000条订单耗时：462ms
   jdbc PreparedStatement批量插入100000条订单耗时：60520ms
   jdbc PreparedStatement批量插入1000000条订单耗时：687314ms
   ```

   

3. （选做）按自己设计的表结构，插入1000万订单模拟数据，测试不同方式的插入效率。

4. （选做）使用不同的索引或组合，测试不同方式查询效率。

5. （选做）调整测试数据，使得数据尽量均匀，模拟1年时间内的交易，计算一年的销售报 表：销售总额，订单数，客单价，每月销售量，前十的商品等等（可以自己设计更多指标）。

6. （选做）尝试自己做一个 ID 生成器（可以模拟 Seq 或 Snowflake）。 

7. （选做）尝试实现或改造一个非精确分页的程序。



### 第14节课作业实践  

------

1. （选做）配置一遍异步复制，半同步复制、组复制。
2. （必做）读写分离-动态切换数据源版本1.0 
3. （必做）读写分离-数据库框架版本2.0 
4. （选做）读写分离-数据库中间件版本3.0 
5. （选做）配置 MHA，模拟 master 宕机 
6. （选做）配置 MGR，模拟 master 宕机
7. （选做）配置 Orchestrator，模拟 master 宕机，演练 UI 调整拓扑结构