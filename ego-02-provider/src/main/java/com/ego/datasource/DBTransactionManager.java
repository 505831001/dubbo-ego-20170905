package com.ego.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * 8. 自定义事务管理器
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
public class DBTransactionManager extends DataSourceTransactionManager {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DBTransactionManager.class);

    public DBTransactionManager(javax.sql.DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void doBegin(Object transaction, TransactionDefinition definition) {
        // 当遇到事务的时候 强制选择写数据库
        DataSourceHolder.putDataSource(DataSourceEnum.WRITE);
        super.doBegin(transaction, definition);
    }

    @Override
    public void doCommit(DefaultTransactionStatus status) {
        DataSourceHolder.clearDataSource();
        super.doCommit(status);
    }
}
