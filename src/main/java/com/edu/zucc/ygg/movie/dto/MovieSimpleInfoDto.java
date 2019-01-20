package com.edu.zucc.ygg.movie.dto;

import com.edu.zucc.ygg.movie.domain.Movie;

import java.io.Serializable;

public class MovieSimpleInfoDto implements Serializable {
    private static final long serialVersionUID = 4411507175501089025L;

    private int id;
    private String name;
    private String imgUrl;
    private String simpleInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSimpleInfo() {
        return simpleInfo;
    }

    public void setSimpleInfo(String simpleInfo) {
        this.simpleInfo = simpleInfo;
    }

    public MovieSimpleInfoDto(){}
    public MovieSimpleInfoDto(Movie movie){
        this.id = movie.getId();
        this.imgUrl = movie.getImgUrl();
        this.name = movie.getName();
    }
}
