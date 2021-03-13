package io.github.vencent.multidatasource.config;

import com.zaxxer.hikari.HikariDataSource;
import io.github.vencent.multidatasource.multids.DsEnum;
import io.github.vencent.multidatasource.multids.DynamicDataSourceAdaptive;
import io.github.vencent.multidatasource.tag.ReadOnly;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * configBean
 *
 * @author vencent
 * @create 2021-03-07 22:52
 **/
@Configuration
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = "io.github.vencent.multidatasource.mapper")
public class ConfigBean {

    /**
     * 配置SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDb") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
        ssf.setDataSource(dataSource);
        ssf.setTypeAliasesPackage("io.github.vencent.multidatasource.mapper");
        return ssf.getObject();
    }
    @Bean
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDb") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
        ssf.setDataSource(dataSource);
        ssf.setTypeAliasesPackage("io.github.vencent.multidatasource.mapper");
        return ssf.getObject();
    }

    /**
     * 配置数据源
     * @return
     */
    @Bean(name = "masterDb")
    @ConfigurationProperties(prefix = "multi.datasource.master")
    public HikariDataSource masterDataSource(){
        HikariDataSource hikariDataSource = new HikariDataSource();

        return hikariDataSource;
    }
    @Bean(name = "slaveDb")
    @ConfigurationProperties(prefix = "multi.datasource.slave")
    public HikariDataSource slaveDataSource(){
        HikariDataSource hikariDataSource = new HikariDataSource();
        return hikariDataSource;
    }


    /**
     * 多数据源连接池配置
     */
    @Bean
    public DynamicDataSourceAdaptive mutiDataSource() {
        DynamicDataSourceAdaptive dynamicDataSourceAdaptive = new DynamicDataSourceAdaptive();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(DsEnum.DS_DEFAULT, masterDataSource());
        hashMap.put(DsEnum.DS_READONLY, slaveDataSource());
        dynamicDataSourceAdaptive.setTargetDataSources(hashMap);
        dynamicDataSourceAdaptive.setDefaultTargetDataSource(masterDataSource());
        return dynamicDataSourceAdaptive;
    }
    /**
     * 配置事务管理器
     */
    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDb") DataSource masterDataSource){
        DataSourceTransactionManager masterTransactionManager = new DataSourceTransactionManager();
        masterTransactionManager.setDataSource(masterDataSource);
        return masterTransactionManager;
    }
    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager slaveTransactionManager(@Qualifier("slaveDb") DataSource slaveDataSource){
        DataSourceTransactionManager slaveTransactionManager = new DataSourceTransactionManager();
        slaveTransactionManager.setDataSource(slaveDataSource);
        return slaveTransactionManager;
    }
}
