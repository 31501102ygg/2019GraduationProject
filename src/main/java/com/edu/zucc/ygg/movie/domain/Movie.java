package com.edu.zucc.ygg.movie.domain;

import com.edu.zucc.ygg.movie.dto.MovieDto;
import com.edu.zucc.ygg.movie.util.DateUtil;

import java.text.ParseException;
import java.util.Date;
import javax.persistence.*;

@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 电影名称
     */
    private String name;

    /**
     * 外文名
     */
    @Column(name = "foreign_name")
    private String foreignName;

    /**
     * 电影图片链接地址
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 上映时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 片长
     */
    @Column(name = "sheet_length")
    private Integer sheetLength;

    /**
     * 制片国家
     */
    @Column(name = "make_state")
    private String makeState;

    /**
     * 电影类型
     */
    private String type;

    /**
     * 导演
     */
    private String director;

    /**
     * 编剧
     */
    private String screenwriter;

    private String actors;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private String language;

    private String introduction;

    private double score;

    private String createTimeString;

    private String releaseTimeString;

    public String getReleaseTimeString() {
        return releaseTimeString;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setReleaseTimeString(String releaseTimeString) {
        this.releaseTimeString = releaseTimeString;
    }

    public String getCreateTimeString() {
        return createTimeString;
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
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

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取电影名称
     *
     * @return name - 电影名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置电影名称
     *
     * @param name 电影名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取外文名
     *
     * @return foreign_name - 外文名
     */
    public String getForeignName() {
        return foreignName;
    }

    /**
     * 设置外文名
     *
     * @param foreignName 外文名
     */
    public void setForeignName(String foreignName) {
        this.foreignName = foreignName == null ? null : foreignName.trim();
    }

    /**
     * 获取电影图片链接地址
     *
     * @return img_url - 电影图片链接地址
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置电影图片链接地址
     *
     * @param imgUrl 电影图片链接地址
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 获取上映时间
     *
     * @return release_time - 上映时间
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 设置上映时间
     *
     * @param releaseTime 上映时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * 获取片长
     *
     * @return sheet_length - 片长
     */
    public Integer getSheetLength() {
        return sheetLength;
    }

    /**
     * 设置片长
     *
     * @param sheetLength 片长
     */
    public void setSheetLength(Integer sheetLength) {
        this.sheetLength = sheetLength;
    }

    /**
     * 获取制片国家
     *
     * @return make_state - 制片国家
     */
    public String getMakeState() {
        return makeState;
    }

    /**
     * 设置制片国家
     *
     * @param makeState 制片国家
     */
    public void setMakeState(String makeState) {
        this.makeState = makeState == null ? null : makeState.trim();
    }

    /**
     * 获取电影类型
     *
     * @return type - 电影类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置电影类型
     *
     * @param type 电影类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取导演
     *
     * @return director - 导演
     */
    public String getDirector() {
        return director;
    }

    /**
     * 设置导演
     *
     * @param director 导演
     */
    public void setDirector(String director) {
        this.director = director == null ? null : director.trim();
    }

    /**
     * 获取编剧
     *
     * @return screenwriter - 编剧
     */
    public String getScreenwriter() {
        return screenwriter;
    }

    /**
     * 设置编剧
     *
     * @param screenwriter 编剧
     */
    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter == null ? null : screenwriter.trim();
    }

    /**
     * @return actors
     */
    public String getActors() {
        return actors;
    }

    /**
     * @param actors
     */
    public void setActors(String actors) {
        this.actors = actors == null ? null : actors.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void transformDateToString(){
        this.createTimeString = DateUtil.convertToDateString(this.createTime);
        if (this.releaseTime!=null)
        this.releaseTimeString = DateUtil.convertToDateString(this.releaseTime);
    }
    public Movie(){}

    public Movie(MovieDto movieDto) {
        this.name = movieDto.getName();
        this.foreignName = movieDto.getForeignName();
        this.actors = movieDto.getActors();
        this.director = movieDto.getDirectors();
        this.screenwriter = movieDto.getScreenwriter();
        this.makeState = movieDto.getMakeState();
        this.sheetLength = movieDto.getSheetLength();
        this.type = movieDto.getType();
        this.imgUrl = movieDto.getImgUrl();
        this.introduction = movieDto.getIntroduction();
        this.language = movieDto.getLanguage();
    }
}