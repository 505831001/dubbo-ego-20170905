package com.ego.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuweiwei
 * @since 2020-08-14
 */
public class EasyUIPage implements Serializable {
    /**
     * 数据集
     */
    private List<?> rows;
    /**
     * 总条数
     */
    private long total;

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
