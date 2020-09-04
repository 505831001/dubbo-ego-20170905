package com.ego.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 4. 动态数据库切面
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
@Component
@Aspect
@Slf4j
public class DataSourceAspect {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    /**
     * 切点
     */
    @Pointcut("@annotation(com.ego.datasource.DataSource)")
    public void pointCut() {

    }

    /**
     * mapper 层执行前 根据mapper层方法设置的数据源 设置当前线程的数据源
     */
    @Before("pointCut()")
    public void before(JoinPoint point) {
        Object target = point.getTarget();
        String methodName = point.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method method = clazz[0].getMethod(methodName, parameterTypes);
            if (method != null && method.isAnnotationPresent(DataSource.class)) {
                DataSource data = method.getAnnotation(DataSource.class);
                DataSourceHolder.putDataSource(data.value());
            }
        } catch (Exception e) {
            LOGGER.error(String.format("Choose DataSource error, method:%s, msg:%s", methodName, e.getMessage()));
        }
    }

    /**
     * mapper层执行结束和清理当前线程
     */
    @After("pointCut()")
    public void after() {
        DataSourceHolder.clearDataSource();
    }
}
