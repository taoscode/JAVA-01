package io.github.vencent;

import io.github.vencent.entity.ISchool;
import io.github.vencent.entity.Klass;
import io.github.vencent.entity.School;
import io.github.vencent.entity.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ConditionalOnProperty(prefix = "custom.demo", name = "enabled", havingValue = "true")
public class CustomAutoConfiguration {
    @Bean(name = "zhangsan")
    public Student genStudent() {
        return new Student(100, "zhangsan", null, null);
    }

    @Bean("klass")
    public Klass genKlass() {
        return new Klass(Arrays.asList(genStudent()));
    }

    @Bean
    public ISchool genSchool() {
        return new School(genKlass(), genStudent());
    }
}
