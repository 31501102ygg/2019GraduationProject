package com.edu.zucc.ygg.movie.domain;

import javax.persistence.*;

@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "phoneNumber")
    private String phonenumber;

    private String email;

    /**
     * 性别：男 0 ; 女 1
     */
    private Integer sex;

    @Column(name = "personality_signature")
    private String personalitySignature;

    @Column(name = "birth_day")
    private String birthDay;

    private String occupation;

    private String location;

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
     * @return phoneNumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取性别：男 0 ; 女 1
     *
     * @return sex - 性别：男 0 ; 女 1
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别：男 0 ; 女 1
     *
     * @param sex 性别：男 0 ; 女 1
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return personality_signature
     */
    public String getPersonalitySignature() {
        return personalitySignature;
    }

    /**
     * @param personalitySignature
     */
    public void setPersonalitySignature(String personalitySignature) {
        this.personalitySignature = personalitySignature == null ? null : personalitySignature.trim();
    }

    /**
     * @return birth_day
     */
    public String getBirthDay() {
        return birthDay;
    }

    /**
     * @param birthDay
     */
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay == null ? null : birthDay.trim();
    }

    /**
     * @return occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}