package com.edu.zucc.ygg.movie.dto;

import org.springframework.web.multipart.MultipartFile;

public class MovieDto {

    private int id;
    private String name;
    private String foreignName;
    private String imgUrl;
    private String releaseTime;
    private Integer sheetLength;
    private String makeState;
    private String type;
    private String directors;
    private String screenwriter;
    private String actors;
    private String introduction;
    private String language;

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

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

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getSheetLength() {
        return sheetLength;
    }

    public void setSheetLength(Integer sheetLength) {
        this.sheetLength = sheetLength;
    }

    public String getMakeState() {
        return makeState;
    }

    public void setMakeState(String makeState) {
        this.makeState = makeState;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
}
