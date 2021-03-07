package io.github.vencent.datasourcetest.service;

import io.github.vencent.datasourcetest.util.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 订单Service
 *
 * @author vencent
 * @create 2021-03-07 13:50
 **/
@Service
public class OrderServiceImpl implements IOrderService{
    @Autowired
    private JdbcUtil jdbcUtil;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void insertOrder(int num) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < num; i++) {
            try {
                connection = jdbcUtil.getJdbcConnection();
                connection.setAutoCommit(false);
                String orderNo = UUID.randomUUID().toString();
                Long nowDate = System.currentTimeMillis();
                String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime)" +
                        " VALUES ('"+orderNo+"',1,8900,1,"+nowDate+","+nowDate+","+nowDate+")";
//                String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime)" +
//                        " VALUES ('"+orderNo+"',1,8900,1,"+nowDate+","+nowDate+","+nowDate+");INSERT INTO T_ORDER_ITEM (orderno,userid,productid,amount,createtime,updatetime) VALUES" +
//                        " ('"+orderNo+"',1,1,"+8900+","+nowDate+","+nowDate+")";
                statement = connection.createStatement();
                boolean result = statement.execute(sql);
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }finally {
                jdbcUtil.close(resultSet,statement,connection);
            }
        }
        long  cast = System.currentTimeMillis()-startTime;
        System.out.println("jdbc Statement单条循环插入"+num+"条订单耗时："+cast+"ms");
    }

    @Override
    public void insertOrderBatch(int num) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        long cast = 0;
        long starTime = System.currentTimeMillis();
        try {
            connection = jdbcUtil.getJdbcConnection();
            statement = connection.createStatement();
            for (int i = 0; i < num; i++) {
                String orderNo = UUID.randomUUID().toString();
                Long nowDate = System.currentTimeMillis();
                String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime)" +
                        " VALUES ('"+orderNo+"',1,8900,1,"+nowDate+","+nowDate+","+nowDate+")";
//                String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime)" +
//                        " VALUES ('"+orderNo+"',1,8900,1,"+nowDate+","+nowDate+","+nowDate+");INSERT INTO T_ORDER_ITEM (orderno,userid,productid,amount,createtime,updatetime) VALUES" +
//                        " ('"+orderNo+"',1,1,"+8900+","+nowDate+","+nowDate+")";
                statement.addBatch(sql);
                if(i%10000==0){
                    statement.executeBatch();
                }
            }
            if((num-1)%10000 !=0){
                statement.executeBatch();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            jdbcUtil.close(resultSet,statement,connection);
        }
        cast = System.currentTimeMillis()-starTime;
        System.out.println("jdbc Statement批量插入"+num+"条订单耗时："+cast+"ms");
    }

    @Override
    public void insertOrderPre(int num) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {

            try {
                connection = jdbcUtil.getJdbcConnection();
                String orderNo = UUID.randomUUID().toString();
                Long nowDate = System.currentTimeMillis();
                String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime) VALUES (?,1,8900,1,?,?,?)";
//                String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime) VALUES (?,1,8900,1,?,?,?);INSERT INTO T_ORDER_ITEM (orderno,userid,productid,amount,createtime,updatetime) VALUES (?,1,1,?,?,?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, orderNo);
                statement.setLong(2, nowDate);
                statement.setLong(3, nowDate);
                statement.setLong(4, nowDate);
//                statement.setString(5, orderNo);
//                statement.setBigDecimal(6, BigDecimal.valueOf(8900));
//                statement.setLong(7, nowDate);
//                statement.setLong(8, nowDate);
                boolean result = statement.execute();
//                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                jdbcUtil.close(resultSet, statement, connection);
            }
        }
        long  cast = System.currentTimeMillis()-startTime;
        System.out.println("jdbc PreparedStatement单条循环插入"+num+"条订单耗时："+cast+"ms");
    }

    @Override
    public void insertOrderPreBatch(int num) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long cast = 0;
        long starTime = System.currentTimeMillis();
        try {
            connection = jdbcUtil.getJdbcConnection();
            String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime) VALUES (?,1,8900,1,?,?,?)";
//            String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime) VALUES (?,1,8900,1,?,?,?);INSERT INTO T_ORDER_ITEM (orderno,userid,productid,amount,createtime,updatetime) VALUES (?,1,1,?,?,?)";
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < num; i++) {
                String orderNo = UUID.randomUUID().toString();
                Long nowDate = System.currentTimeMillis();
                statement.setString(1, orderNo);
                statement.setLong(2,nowDate);
                statement.setLong(3,nowDate);
                statement.setLong(4,nowDate);
//                statement.setString(5, orderNo);
//                statement.setBigDecimal(6, BigDecimal.valueOf(8900));
//                statement.setLong(7,nowDate);
//                statement.setLong(8,nowDate);
                statement.addBatch();
                if(i%10000==0){
                    statement.executeBatch();
                }
            }
            if((num-1)%10000 !=0){
                statement.executeBatch();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            jdbcUtil.close(resultSet,statement,connection);
        }
        cast = System.currentTimeMillis()-starTime;
        System.out.println("jdbc PreparedStatement批量插入"+num+"条订单耗时："+cast+"ms");
    }

    @Override
    public void insertOrderPool(int num) {
        long startTime = System.currentTimeMillis();
        try {
            List list = new ArrayList();
            for (int i = 0; i < num; i++) {
                list.clear();
                String orderNo = UUID.randomUUID().toString();
                Long nowDate = System.currentTimeMillis();
                String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime) VALUES (?,1,8900,1,?,?,?)";
                list.add(Arrays.asList(orderNo,nowDate,nowDate,nowDate).toArray());
                jdbcTemplate.batchUpdate(sql,list);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
        }
        long  cast = System.currentTimeMillis()-startTime;
        System.out.println("HikarPool 单条循环插入"+num+"条订单耗时："+cast+"ms");
    }

    @Override
    public void insertOrderPoolBatch(int num) {
        long cast = 0;
        long starTime = System.currentTimeMillis();
        List<Object []> param = new ArrayList<>();
        try {
            String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime) VALUES (?,1,8900,1,?,?,?)";
//            String sql = "INSERT INTO T_ORDER (orderno,userid,totalamount,orderstatus,paytime,createtime,updatetime) VALUES (?,1,8900,1,?,?,?);INSERT INTO T_ORDER_ITEM (orderno,userid,productid,amount,createtime,updatetime) VALUES (?,1,1,?,?,?)";
            for (int i = 0; i < num; i++) {
                String orderNo = UUID.randomUUID().toString();
                Long nowDate = System.currentTimeMillis();
                param.add(Arrays.asList(orderNo,nowDate,nowDate,nowDate).toArray());
                if(i%10000==0){
                    jdbcTemplate.batchUpdate(sql,param);
                    param.clear();
                }
            }
            if(!param.isEmpty()){
                jdbcTemplate.batchUpdate(sql,param);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
        }
        cast = System.currentTimeMillis()-starTime;
        System.out.println("HikarPool 批量插入"+num+"条订单耗时："+cast+"ms");
    }

}
