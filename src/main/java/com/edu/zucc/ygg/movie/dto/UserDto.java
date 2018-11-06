package com.edu.zucc.ygg.movie.dto;

public class UserDto {
    /**
     * user_id
     */
    private Integer id;
    private Integer userId;//userInfo使用
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private Integer ban;
    /**
     * 性别:男 0;女 1;
     */
    private Integer sex;
    /**
     * 个性签名
     */
    private String personalitySignature;
    /**
     * 生日
     */
    private String birthDay;
    /**
     * 职业
     */
    private String occupation;
    /**
     * 所在地
     */
    private Integer location;
    /**
     * 分页查询，页码和页数据条数
     */
    private Integer pageNum;
    private Integer pageSize;
    private String createDate;
    private String nickname;

    public Integer getBan() {
        return ban;
    }

    public void setBan(Integer ban) {
        this.ban = ban;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPersonalitySignature() {
        return personalitySignature;
    }

    public void setPersonalitySignature(String personalitySignature) {
        this.personalitySignature = personalitySignature;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }
}
