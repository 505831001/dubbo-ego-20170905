package com.ego.entity;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuweiwei
 * @since 2020-08-14
 */
@Data
@ToString
public class PageResult<T> implements Serializable {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 当前页记录数
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private long count;
    /**
     * 总记录数
     */
    protected long total;
    /**
     * 结果集
     */
    protected List<T> list;

    /**
     * 带参构造方法
     *
     * @param pageInfo
     */
    public PageResult(PageInfo pageInfo) {
        this.pageNum  = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.count = pageInfo.getTotal();
        this.total = pageInfo.getPages();
        this.list  = pageInfo.getList();
    }

    /**
     * 无参构造方法
     */
    public PageResult() {
    }

    /**
     * 全参构造方法
     *
     * @param count
     * @param total
     * @param pageNum
     * @param pageSize
     * @param list
     */
    public PageResult(long count, int total, int pageNum, int pageSize, List<T> list) {
        this.count = count;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
    }
}
