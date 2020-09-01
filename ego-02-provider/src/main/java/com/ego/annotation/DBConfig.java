package com.ego.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 读写分离多数据源：配置文档
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
@Configuration
public class DBConfig {
    /**
     * 读写分离多数据源：主库
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 读写分离多数据源：从库
     */
    @Bean(name = "slave0")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.slave0")
    public DataSource slave0() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 读写分离多数据源：路由配置
     */
    @Bean
    public DataSource dataSource(@Qualifier("master") DataSource master, @Qualifier("slave0") DataSource slave0) {
        // 1.3 映射集
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBEnum.WRITE, master);
        targetDataSources.put(DBEnum.READ, slave0);
        // 1. 配置路由
        DBRouting dbRouting = new DBRouting();
        // 1.1 指定默认的目标数据源。
        dbRouting.setDefaultTargetDataSource(master);
        // 1.2 指定目标数据源的映射。
        dbRouting.setTargetDataSources(targetDataSources);
        return dbRouting;
    }
}
