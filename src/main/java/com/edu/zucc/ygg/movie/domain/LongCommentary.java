package com.edu.zucc.ygg.movie.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "long_commentary")
public class LongCommentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "movie_id")
    private Integer movieId;

    /**
     * 影评人id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 影评标题
     */
    private String title;

    /**
     * 影评内容
     */
    private String content;

    /**
     * p标签纯文本内容
     */
    @Column(name = "filter_content")
    private String filterContent;

    /**
     * 提取内容中纯文本内容
     */
    @Column(name = "pure_text")
    private String pureText;

    /**
     * 影评种类：
0为普通影评；
1为专业影评
     */
    private Integer type;

    /**
     * 点赞数
     */
    private Integer like;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * @return movie_id
     */
    public Integer getMovieId() {
        return movieId;
    }

    /**
     * @param movieId
     */
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    /**
     * 获取影评人id
     *
     * @return user_id - 影评人id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置影评人id
     *
     * @param userId 影评人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取影评标题
     *
     * @return title - 影评标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置影评标题
     *
     * @param title 影评标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取影评内容
     *
     * @return content - 影评内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置影评内容
     *
     * @param content 影评内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取p标签纯文本内容
     *
     * @return filter_content - p标签纯文本内容
     */
    public String getFilterContent() {
        return filterContent;
    }

    /**
     * 设置p标签纯文本内容
     *
     * @param filterContent p标签纯文本内容
     */
    public void setFilterContent(String filterContent) {
        this.filterContent = filterContent == null ? null : filterContent.trim();
    }

    /**
     * 获取提取内容中纯文本内容
     *
     * @return pure_text - 提取内容中纯文本内容
     */
    public String getPureText() {
        return pureText;
    }

    /**
     * 设置提取内容中纯文本内容
     *
     * @param pureText 提取内容中纯文本内容
     */
    public void setPureText(String pureText) {
        this.pureText = pureText == null ? null : pureText.trim();
    }

    /**
     * 获取影评种类：
0为普通影评；
1为专业影评
     *
     * @return type - 影评种类：
0为普通影评；
1为专业影评
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置影评种类：
0为普通影评；
1为专业影评
     *
     * @param type 影评种类：
0为普通影评；
1为专业影评
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取点赞数
     *
     * @return like - 点赞数
     */
    public Integer getLike() {
        return like;
    }

    /**
     * 设置点赞数
     *
     * @param like 点赞数
     */
    public void setLike(Integer like) {
        this.like = like;
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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}