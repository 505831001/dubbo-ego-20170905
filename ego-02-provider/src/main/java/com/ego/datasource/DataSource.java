package com.ego.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 3. 数据源注解
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
    /**
     * 默认使用写库
     */
    DataSourceEnum value() default DataSourceEnum.WRITE;
}
