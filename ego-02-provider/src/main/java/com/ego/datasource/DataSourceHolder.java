package com.ego.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 5.1 本地线程设置和获取数据源信息
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
public class DataSourceHolder {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceHolder.class);

    private static final ThreadLocal<DataSourceEnum> holder = new ThreadLocal<>();

    public static void putDataSource(DataSourceEnum dataSource) {
        holder.set(dataSource);
    }

    public static DataSourceEnum getDataSource() {
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }
}
