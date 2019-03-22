package com.edu.zucc.ygg.movie.dto;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;
import sun.swing.StringUIClientPropertyKey;
import tk.mybatis.mapper.util.StringUtil;

import java.text.ParseException;

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
    private String keyword;

    private boolean shortCommentary;    //某个用户是否已经对这个电影有短评
    private boolean longCommentary;     //某个用户是否已经对这个电影有长评

    private Integer pageSize;
    private Integer pageNum;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public boolean isShortCommentary() {
        return shortCommentary;
    }

    public void setShortCommentary(boolean shortCommentary) {
        this.shortCommentary = shortCommentary;
    }

    public boolean isLongCommentary() {
        return longCommentary;
    }

    public void setLongCommentary(boolean longCommentary) {
        this.longCommentary = longCommentary;
    }

    public Movie convertToMovie() throws ParseException {
        Movie movie = new Movie();
        movie.setId(this.id);
        movie.setActors(this.actors);
        movie.setDirector(this.directors);
        movie.setForeignName(this.foreignName);
        movie.setImgUrl(this.imgUrl);
        movie.setScreenwriter(this.screenwriter);
        movie.setLanguage(this.language);
        movie.setIntroduction(this.introduction);
        movie.setSheetLength(this.sheetLength);
        movie.setType(this.type);
        movie.setMakeState(this.makeState);
        movie.setName(this.name);
        if (!StringUtil.isEmpty(this.releaseTime))
            movie.setReleaseTime(DateUtil.convertToDate(this.releaseTime));
        return movie;
    }

    public MovieDto(){}
    public MovieDto(Movie movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.foreignName = movie.getForeignName();
        this.imgUrl = movie.getImgUrl();
        if (StringUtil.isNotEmpty(movie.getReleaseTimeString()))
            this.releaseTime = DateUtil.convertToDateString(movie.getReleaseTime());
        this.releaseTime = movie.getReleaseTimeString();
        this.sheetLength = movie.getSheetLength();
        this.makeState = movie.getMakeState();
        this.type = movie.getType();
        this.directors = movie.getDirector();
        this.screenwriter = movie.getScreenwriter();
        this.actors = movie.getActors();
        this.introduction = movie.getIntroduction();
        this.language = movie.getLanguage();
        this.longCommentary = true;
        this.shortCommentary = true;
    }
}
