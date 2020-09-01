package com.ego.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 6. 读写分离多数据源：操作注解
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DBSource {
    /**
     * 默认使用写库Write
     */
    DBEnum value() default DBEnum.WRITE;
}
