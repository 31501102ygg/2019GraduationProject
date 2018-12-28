package com.edu.zucc.ygg.movie.dto;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.domain.ShortCommentary;
import com.edu.zucc.ygg.movie.domain.UserInfo;
import com.edu.zucc.ygg.movie.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class ShortCommentaryDto implements Serializable {
    private static final long serialVersionUID = 4958558713393843992L;

    private Integer id;
    private Integer userId;
    private String username;
    private String userHeaderImg;
    private Integer movieId;
    private String movieName;
    private String movieImg;
    private String content;
    private Integer star;
    private Integer score;
    private Date createDate;
    private Date removeDate;
    private String createDateTimeString;
    private Integer pageSize;
    private Integer pageNum;

    public ShortCommentaryDto(){};
    public ShortCommentaryDto(ShortCommentary shortCommentary){
        this.id = shortCommentary.getId();
        this.userId = shortCommentary.getUserid();
        this.movieId = shortCommentary.getMovieid();
        this.content = shortCommentary.getContent();
        this.score = shortCommentary.getScore();
        this.star = shortCommentary.getStar();
        this.createDate = shortCommentary.getCreateDate();
        this.createDateTimeString = DateUtil.convertToTimeString(createDate);
    }
    public String getCreateDateTimeString() {
        return createDateTimeString;
    }

    public void setCreateDateTimeString(String createDateTimeString) {
        this.createDateTimeString = createDateTimeString;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImg() {
        return movieImg;
    }

    public void setMovieImg(String movieImg) {
        this.movieImg = movieImg;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserHeaderImg() {
        return userHeaderImg;
    }

    public void setUserHeaderImg(String userHeaderImg) {
        this.userHeaderImg = userHeaderImg;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }

    public ShortCommentary toShortCommentary(){
        ShortCommentary shortCommentary = new ShortCommentary();
        shortCommentary.setId(this.id);
        shortCommentary.setUserid(this.userId);
        shortCommentary.setMovieid(this.movieId);
        shortCommentary.setContent(this.content);
        shortCommentary.setScore(this.score);
        shortCommentary.setStar(this.star);
        return shortCommentary;
    }

    public void addUserInfo(UserDto userDto){
        this.username = userDto.getNickname();
        this.userHeaderImg = userDto.getHeaderImgUrl();
    }

    public void addMovieInfo(Movie movie){
        this.movieName = movie.getName();
        this.movieImg = movie.getImgUrl();
    }
}
