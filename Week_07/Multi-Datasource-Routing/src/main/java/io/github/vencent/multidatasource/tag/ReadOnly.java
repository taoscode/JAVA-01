package io.github.vencent.multidatasource.tag;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ReadOnly
 *
 * @author vencent
 * @create 2021-03-08 21:17
 **/
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReadOnly {
}
