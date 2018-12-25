package com.edu.zucc.ygg.movie.dto;

import com.edu.zucc.ygg.movie.domain.Slide;
import com.edu.zucc.ygg.movie.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class SlideDto implements Serializable {
    private static final long serialVersionUID = 5644333983776135931L;

    private Integer id;

    private String title;

    private String content;

    private String img;

    private Date createDate;

    private Date updateDate;

    private String createTimeString;

    private String updateTimeString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateTimeString() {
        return createTimeString;
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
    }

    public String getUpdateTimeString() {
        return updateTimeString;
    }

    public void setUpdateTimeString(String updateTimeString) {
        this.updateTimeString = updateTimeString;
    }

    public void transformDateToString(){
        this.createTimeString = DateUtil.convertToDateString(this.createDate);
        this.updateTimeString = DateUtil.convertToDateString(this.updateDate);
    }

    public Slide toSlide(){
        Slide slide = new Slide();
        slide.setId(this.id);
        slide.setTitle(this.title);
        slide.setContent(this.content);
        slide.setImg(this.img);
        return slide;
    }
}
