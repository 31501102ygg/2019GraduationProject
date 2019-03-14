package com.edu.zucc.ygg.movie.dto;

import java.io.Serializable;

public class LongCommentarySearchDto implements Serializable {
    private static final long serialVersionUID = 5080997549273318559L;
    private String commentaryName;
    private String movieName;
    private String userName;
    private String keyword;
    private int type;
    private int pageNum;
    private int pageSize;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public String getCommentaryName() {
        return commentaryName;
    }

    public void setCommentaryName(String commentaryName) {
        this.commentaryName = commentaryName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
