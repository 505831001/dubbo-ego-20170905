package com.ego.dynamic;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 4. 读写分离多数据源：多数据源切面处理类，实现数据库的选择
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
@Component
@Aspect
public class DBAspect {
    /**
     * SLF4J 骚人日志必备
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DBAspect.class);

    @Pointcut("!@annotation(com.ego.dynamic.DBSource) " +
            "&& (execution(* com.ego.service..*.select*(..)) " +
            "|| execution(* com.ego.service..*.get*(..)))")
    public void readAspect() {

    }

    @Before("readAspect()")
    public void read() {
        DBHolder.slave0();
    }

    @Pointcut("@annotation(com.ego.dynamic.DBSource) " +
            "|| execution(* com.ego.service..*.insert*(..)) " +
            "|| execution(* com.ego.service..*.add*(..)) " +
            "|| execution(* com.ego.service..*.update*(..)) " +
            "|| execution(* com.ego.service..*.edit*(..)) " +
            "|| execution(* com.ego.service..*.delete*(..)) " +
            "|| execution(* com.ego.service..*.remove*(..))")
    public void writeAspect() {

    }

    @Before("writeAspect()")
    public void write() {
        DBHolder.master();
    }
}
