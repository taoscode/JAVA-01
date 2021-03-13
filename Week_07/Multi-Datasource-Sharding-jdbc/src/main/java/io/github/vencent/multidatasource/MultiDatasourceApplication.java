package io.github.vencent.multidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = JtaAutoConfiguration.class)
@EnableTransactionManagement
public class MultiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceApplication.class, args);
    }

}
