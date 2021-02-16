package io.github.vencent.springbootdemo.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hikari
 *
 * @author vencent
 * @create 2021-02-16 18:27
 **/
@Component
public class HikariUtil {
    @Value("${jdbc.mysql.url}")
    private  String url;
    @Value("${jdbc.mysql.username}")
    private  String userName;
    @Value("${jdbc.mysql.password}")
    private  String passWord;
    private HikariDataSource hikariDataSource;
    @PostConstruct
    public void initJdbcDriver(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(passWord);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(50000);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setConnectionTimeout(20000);
        hikariConfig.setPoolName("MyHikariPool");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }
    public  Connection getJdbcConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
    public  void close(ResultSet resultSet, Statement statement, Connection connection){
        closeResult(resultSet);
        closeSeesion(statement);
        closeConnection(connection);
    }
    public  void closeResult(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public  void closeSeesion(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public  void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
