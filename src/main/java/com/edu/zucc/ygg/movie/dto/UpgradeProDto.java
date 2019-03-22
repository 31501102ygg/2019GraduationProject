package com.edu.zucc.ygg.movie.dto;

import com.edu.zucc.ygg.movie.domain.UpgradePro;
import com.edu.zucc.ygg.movie.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class UpgradeProDto implements Serializable {
    private static final long serialVersionUID = 1064207285132049036L;
    private Integer id;
    private Integer userId;
    private String username;
    private String content;
    private Integer state;
    private Date createtime;
    private Date auditingtime;
    private String createtimeString;
    private String auditingtimeString;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getAuditingtime() {
        return auditingtime;
    }

    public void setAuditingtime(Date auditingtime) {
        this.auditingtime = auditingtime;
    }

    public String getCreatetimeString() {
        return createtimeString;
    }

    public void setCreatetimeString(String createtimeString) {
        this.createtimeString = createtimeString;
    }

    public String getAuditingtimeString() {
        return auditingtimeString;
    }

    public void setAuditingtimeString(String auditingtimeString) {
        this.auditingtimeString = auditingtimeString;
    }
    public void transformDateToString(){
        this.createtimeString = DateUtil.convertToDateString(this.createtime);
        if (auditingtime!=null)
            this.auditingtimeString = DateUtil.convertToDateString(this.auditingtime);
    }
    public UpgradeProDto(){}
    public UpgradeProDto(UpgradePro upgradePro){
        this.id = upgradePro.getId();
        this.userId = upgradePro.getUserId();
        this.username = upgradePro.getUsername();
        this.content = upgradePro.getContent();
        this.state = upgradePro.getState();
        this.createtime = upgradePro.getCreatetime();
        this.auditingtime = upgradePro.getAuditingtime();
        this.transformDateToString();
    }
}
