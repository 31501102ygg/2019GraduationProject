package com.edu.zucc.ygg.movie.dto;

import java.io.Serializable;

public class PageHelperDto<T> implements Serializable {
    private static final long serialVersionUID = 1566623160726521932L;

    private T data;
    private Integer pageSize;
    private Integer pageNum;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
