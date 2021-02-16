package io.github.vencent.springbootdemo.service;

import io.github.vencent.springbootdemo.entity.User;
import io.github.vencent.springbootdemo.util.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * jdbc
 *
 * @author vencent
 * @create 2021-02-16 15:30
 **/
@Service
public class JdbcPreStatementUserService implements IUserService{
    @Autowired
    private JdbcUtil jdbcUtil;
    @Override
    public List<User> findUsers() {
        String sql = "SELECT * FROM USER";
        Connection connection = null;
        List<User> users = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtil.getJdbcConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet != null){
                users = new ArrayList<>();
                User user = null;
               while (resultSet.next()){
                   user = new User(resultSet.getLong("id"),resultSet.getString("name"),resultSet.getInt("age"));
                    users.add(user);
               }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            jdbcUtil.close(resultSet,statement,connection);
        }
        return users;
    }

    @Override
    public boolean insertUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (user == null){
                return false;
            }
            connection = jdbcUtil.getJdbcConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO USER (name,age) value(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setInt(2,user.getAge());
            boolean result = statement.execute();
            connection.commit();
            return result;
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
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (user == null){
                return false;
            }
            connection = jdbcUtil.getJdbcConnection();
            String sql = "UPDATE USER SET name = ?,age = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getName());
            statement.setInt(2,user.getAge());
            statement.setLong(3,user.getId());
            connection.setAutoCommit(false);
            boolean result = statement.execute();
            connection.commit();
            return result;
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
        return false;
    }


    @Override
    public boolean deleteUser(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtil.getJdbcConnection();
            String sql = "DELETE FROM USER WHERE name = ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            connection.setAutoCommit(false);
            boolean result = statement.execute();
            connection.commit();
            return result;
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
        return false;
    }

    @Override
    public long saveBatch(int count) {
        List<User> users = initData(count);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long cast = 0;
        try {

            connection = jdbcUtil.getJdbcConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO USER (name,age) value(?,?)";
            statement = connection.prepareStatement(sql);
            for (User user: users){
                statement.setString(1, user.getName());
                statement.setInt(2,user.getAge());
                statement.addBatch();
            }
            long starTime = System.currentTimeMillis();
            int [] result = statement.executeBatch();
            cast = System.currentTimeMillis()-starTime;
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
        return cast;
    }
    public List<User> initData(int count){
        int num = 0;
        List<User> users = new ArrayList<>();
        User user = null;
        Random random =  new Random();
        while (num++ < count){
            user = new User("vencent"+num, random.nextInt(30));
            users.add(user);
        }
        return users;
    }
}
