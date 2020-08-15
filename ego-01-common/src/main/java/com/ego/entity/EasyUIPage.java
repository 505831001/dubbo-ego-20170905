package com.ego.entity;

import java.io.Serializable;
import java.util.List;

public class EasyUIPage implements Serializable {
    private List<?> rows;
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
