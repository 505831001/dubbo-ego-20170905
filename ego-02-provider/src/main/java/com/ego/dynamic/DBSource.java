package com.ego.dynamic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 3. 读写分离多数据源：自定义多数据源注解
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
