package com.ego.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * 5. 读写分离多数据源：DynamicDataSource申明其作为工程全局的使用
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
public class DBRouting extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        DBEnum dbEnum = DBHolder.get();
        return dbEnum;
    }
}
