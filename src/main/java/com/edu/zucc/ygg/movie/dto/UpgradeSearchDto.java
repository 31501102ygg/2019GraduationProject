package com.edu.zucc.ygg.movie.dto;

import java.io.Serializable;

public class UpgradeSearchDto implements Serializable {
    private static final long serialVersionUID = 3359412469212062075L;

    private String username;
    private int state;
    private int pageNum;
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
