package com.ego.dynamic;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * 8. 读写分离多数据源：自定义事务管理器
 *
 * @author liuweiwei
 * @since 2020-09-04
 */
public class DBTransaction extends DataSourceTransactionManager {

    public DBTransaction(javax.sql.DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        super.doCommit(status);
    }
}
