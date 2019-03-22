package com.edu.zucc.ygg.movie.domain;

import com.edu.zucc.ygg.movie.util.DateUtil;

import java.util.Date;
import javax.persistence.*;

@Table(name = "upgrade_pro")
public class UpgradePro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String username;

    /**
     * 申请理由
     */
    private String content;

    /**
     * 审核结果
0 未审核
1 审核通过
2 审核不通过
     */
    private Integer state;

    @Column(name = "createTime")
    private Date createtime;

    @Column(name = "auditingTime")
    private Date auditingtime;

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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取申请理由
     *
     * @return content - 申请理由
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置申请理由
     *
     * @param content 申请理由
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取审核结果
0 未审核
1 审核通过
2 审核不通过
     *
     * @return state - 审核结果
0 未审核
1 审核通过
2 审核不通过
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置审核结果
0 未审核
1 审核通过
2 审核不通过
     *
     * @param state 审核结果
0 未审核
1 审核通过
2 审核不通过
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return createTime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return auditingTime
     */
    public Date getAuditingtime() {
        return auditingtime;
    }

    /**
     * @param auditingtime
     */
    public void setAuditingtime(Date auditingtime) {
        this.auditingtime = auditingtime;
    }

}