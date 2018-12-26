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

    private Integer state;

    private Date createDate;

    private Date updateDate;

    private String createTimeString;

    private String updateTimeString;

    private Integer pageSize;

    private Integer pageNum;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
        if (this.createDate!=null)
            this.createTimeString = DateUtil.convertToDateString(this.createDate);
        if (this.updateDate!=null)
            this.updateTimeString = DateUtil.convertToDateString(this.updateDate);
    }

    public Slide toSlide(){
        Slide slide = new Slide();
        slide.setId(this.id);
        slide.setTitle(this.title);
        slide.setContent(this.content);
        slide.setState(this.state);
        slide.setImg(this.img);
        return slide;
    }
    public SlideDto(){}
    public SlideDto(Slide slide){
        this.id = slide.getId();
        this.title = slide.getTitle();
        this.content = slide.getContent();
        this.img = slide.getImg();
        this.state = slide.getState();
        this.createDate = slide.getCreateDate();
        this.updateDate = slide.getUpdateDate();
    }
}
