package com.ego.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 5. 读写分离多数据源：AOP切面编程
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
@Aspect
@Component
public class DBAspect {
    @Pointcut("!@annotation(com.ego.annotation.DBSource) " +
            "&& (execution(* com.ego.service..*.select*(..)) " +
            "|| execution(* com.ego.service..*.get*(..)))")
    public void readAspect() {

    }

    @Before("readAspect()")
    public void read() {
        DBHolder.slave0();
    }

    @Pointcut("@annotation(com.ego.annotation.DBSource) " +
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
