package io.github.vencent.multidatasource.aop;

import io.github.vencent.multidatasource.multids.DataSourceContextHolder;
import io.github.vencent.multidatasource.multids.DsEnum;
import io.github.vencent.multidatasource.tag.ReadOnly;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * MulituDataSourceAop
 *
 * @author vencent
 * @create 2021-03-09 21:43
 **/
@Aspect
@Component
public class MulituDataSourceAop implements Ordered {
    private static final Logger logger = LoggerFactory.getLogger(MulituDataSourceAop.class);

    @Pointcut(value = "execution(* io.github.vencent.multidatasource.service..*.*(..))")
    private void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("Annotion are made with errors～～～");
        }
        methodSignature = (MethodSignature) signature;

        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        ReadOnly readOnly = currentMethod.getAnnotation(ReadOnly.class);
        if (readOnly != null) {
            DataSourceContextHolder.setDataSourceType(DsEnum.DS_READONLY);
            logger.debug("Set the data source as:" + DsEnum.DS_READONLY.name());
        } else {
            //默认设置为mysql数据源
            DataSourceContextHolder.setDataSourceType(DsEnum.DS_DEFAULT);
            logger.debug("Set the data source as:{}", DsEnum.DS_DEFAULT.name());
        }
        try {
            return point.proceed();
        } finally {
            logger.debug("Clean the data source ~~~");
            DataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 应为要切数据源，需早于Spring事务执行
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}