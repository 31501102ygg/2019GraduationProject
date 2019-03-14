package com.edu.zucc.ygg.movie.dto;

import java.io.Serializable;

public class UpgradeProDto implements Serializable {
    private static final long serialVersionUID = 1064207285132049036L;
    private String content;
    private String userName;
    private int userId;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
