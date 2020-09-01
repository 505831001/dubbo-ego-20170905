package com.ego.annotation;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * 4. 读写分离多数据源：获取路由Key
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
