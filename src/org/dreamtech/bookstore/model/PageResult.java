package org.dreamtech.bookstore.model;

import java.util.List;

/**
 * @Program: BookStore
 * @Description: 分页模型
 * @Author: YiQing Xu
 * @Create: 2019-03-04 15:12
 */
public class PageResult<T> {
    //封装内容
    private List<T> list;
    //总条数
    private long totalCount;
    //每一页显示条数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页
    private int currentPage;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                '}';
    }
}
