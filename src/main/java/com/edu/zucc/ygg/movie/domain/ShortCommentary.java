package com.edu.zucc.ygg.movie.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "short_commentary")
public class ShortCommentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评论人id
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 被评论电影id
     */
    @Column(name = "movieId")
    private Integer movieid;

    /**
     * 短评内容 
     */
    private String content;

    /**
     * 点赞数量
     */
    private Integer star;

    /**
     * 电影评分
     */
    private Integer score;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 删除时间
     */
    @Column(name = "remove_date")
    private Date removeDate;

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
     * 获取评论人id
     *
     * @return userId - 评论人id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置评论人id
     *
     * @param userid 评论人id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取被评论电影id
     *
     * @return movieId - 被评论电影id
     */
    public Integer getMovieid() {
        return movieid;
    }

    /**
     * 设置被评论电影id
     *
     * @param movieid 被评论电影id
     */
    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    /**
     * 获取短评内容 
     *
     * @return content - 短评内容 
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置短评内容 
     *
     * @param content 短评内容 
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取点赞数量
     *
     * @return star - 点赞数量
     */
    public Integer getStar() {
        return star;
    }

    /**
     * 设置点赞数量
     *
     * @param star 点赞数量
     */
    public void setStar(Integer star) {
        this.star = star;
    }

    /**
     * 获取电影评分
     *
     * @return score - 电影评分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置电影评分
     *
     * @param score 电影评分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取删除时间
     *
     * @return remove_date - 删除时间
     */
    public Date getRemoveDate() {
        return removeDate;
    }

    /**
     * 设置删除时间
     *
     * @param removeDate 删除时间
     */
    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }
}