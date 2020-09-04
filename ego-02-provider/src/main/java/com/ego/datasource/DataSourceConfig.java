package com.ego.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 6. 数据源配置
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
@Slf4j
@Configuration
public class DataSourceConfig {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * 主库
     */
    @Bean(name = "master", destroyMethod = "close", initMethod = "init")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public javax.sql.DataSource master() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    /**
     * 从库
     */
    @Bean(name = "slave1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave1")
    public javax.sql.DataSource slave1() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    /**
     * 动态数据源
     */
    @Bean("dynamicDataSource")
    public javax.sql.DataSource dynamicDataSource() {
        DataSourceRouting dynamicDataSource = new DataSourceRouting();
        dynamicDataSource.setWriteDataSource(master());
        List<Object> reads = new ArrayList<>();
        reads.add(slave1());
        dynamicDataSource.setReadDataSources(reads);
        return dynamicDataSource;
    }

    /**
     * 事务管理器配置
     *
     * @return 返回事务管理器
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DBTransactionManager(dynamicDataSource());
    }

    /**
     * mybatis配置
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/**Mapper.xml"));
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }
}
