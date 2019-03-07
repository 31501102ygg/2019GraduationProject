package com.edu.zucc.ygg.movie.domain;

import javax.persistence.*;

@Table(name = "collections")
public class Collections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 影评id
     */
    @Column(name = "commentary_id")
    private Integer commentaryId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取影评id
     *
     * @return commentary_id - 影评id
     */
    public Integer getCommentaryId() {
        return commentaryId;
    }

    /**
     * 设置影评id
     *
     * @param commentaryId 影评id
     */
    public void setCommentaryId(Integer commentaryId) {
        this.commentaryId = commentaryId;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}