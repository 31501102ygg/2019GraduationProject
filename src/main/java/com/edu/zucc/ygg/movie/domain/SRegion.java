package com.edu.zucc.ygg.movie.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "s_region")
public class SRegion implements Serializable {

    private static final long serialVersionUID = -4794473947526483279L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 父节点
     */
    @Column(name = "PARENT_ID")
    private Integer parentId;

    /**
     * 短名称
     */
    @Column(name = "SHORT_NAME")
    private String shortName;

    /**
     * 层级
     */
    @Column(name = "LEVEL_TYPE")
    private Integer levelType;

    /**
     * 区号
     */
    @Column(name = "CITY_CODE")
    private String cityCode;

    /**
     * 邮编
     */
    @Column(name = "ZIP_CODE")
    private String zipCode;

    /**
     * 全名
     */
    @Column(name = "MERGER_NAME")
    private String mergerName;

    /**
     * 拼音
     */
    @Column(name = "PINYIN")
    private String pinyin;

    @Column(name = "LNG")
    private String lng;

    @Column(name = "LAT")
    private String lat;

    /**
     * 开放城市，只有市级有此属性 0未开放 1开放
     */
    @Column(name = "OPEN_FLAG")
    private String openFlag;

    @Column(name = "TREE_PATH")
    private String treePath;

    /**
     * 登录城市，只有省级或直辖市有此属性  0-不是登录城市，1--是登录城市
     */
    @Column(name = "LOGIN_FLAG")
    private String loginFlag;

    /**
     * 热门城市，只有是在登录城市下的才能是热门城市，0--不是热门城市，1--是热门城市
     */
    @Column(name = "HOT_FLAG")
    private String hotFlag;

    @Transient
    private List<SRegion> childSRegion;

    public List<SRegion> getChildSRegion() {
        return childSRegion;
    }

    public void setChildSRegion(List<SRegion> childSRegion) {
        this.childSRegion = childSRegion;
    }
    /**
     * @return ID
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
     * 获取名称
     *
     * @return NAME - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取父节点
     *
     * @return PARENT_ID - 父节点
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父节点
     *
     * @param parentId 父节点
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取短名称
     *
     * @return SHORT_NAME - 短名称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置短名称
     *
     * @param shortName 短名称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    /**
     * 获取层级
     *
     * @return LEVEL_TYPE - 层级
     */
    public Integer getLevelType() {
        return levelType;
    }

    /**
     * 设置层级
     *
     * @param levelType 层级
     */
    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }

    /**
     * 获取区号
     *
     * @return CITY_CODE - 区号
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置区号
     *
     * @param cityCode 区号
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * 获取邮编
     *
     * @return ZIP_CODE - 邮编
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 设置邮编
     *
     * @param zipCode 邮编
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    /**
     * 获取全名
     *
     * @return MERGER_NAME - 全名
     */
    public String getMergerName() {
        return mergerName;
    }

    /**
     * 设置全名
     *
     * @param mergerName 全名
     */
    public void setMergerName(String mergerName) {
        this.mergerName = mergerName == null ? null : mergerName.trim();
    }

    /**
     * 获取拼音
     *
     * @return PINYIN - 拼音
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 设置拼音
     *
     * @param pinyin 拼音
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     * @return LNG
     */
    public String getLng() {
        return lng;
    }

    /**
     * @param lng
     */
    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    /**
     * @return LAT
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat
     */
    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    /**
     * 获取开放城市，只有市级有此属性 0未开放 1开放
     *
     * @return OPEN_FLAG - 开放城市，只有市级有此属性 0未开放 1开放
     */
    public String getOpenFlag() {
        return openFlag;
    }

    /**
     * 设置开放城市，只有市级有此属性 0未开放 1开放
     *
     * @param openFlag 开放城市，只有市级有此属性 0未开放 1开放
     */
    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag == null ? null : openFlag.trim();
    }

    /**
     * @return TREE_PATH
     */
    public String getTreePath() {
        return treePath;
    }

    /**
     * @param treePath
     */
    public void setTreePath(String treePath) {
        this.treePath = treePath == null ? null : treePath.trim();
    }

    /**
     * 获取登录城市，只有省级或直辖市有此属性  0-不是登录城市，1--是登录城市
     *
     * @return LOGIN_FLAG - 登录城市，只有省级或直辖市有此属性  0-不是登录城市，1--是登录城市
     */
    public String getLoginFlag() {
        return loginFlag;
    }

    /**
     * 设置登录城市，只有省级或直辖市有此属性  0-不是登录城市，1--是登录城市
     *
     * @param loginFlag 登录城市，只有省级或直辖市有此属性  0-不是登录城市，1--是登录城市
     */
    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag == null ? null : loginFlag.trim();
    }

    /**
     * 获取热门城市，只有是在登录城市下的才能是热门城市，0--不是热门城市，1--是热门城市
     *
     * @return HOT_FLAG - 热门城市，只有是在登录城市下的才能是热门城市，0--不是热门城市，1--是热门城市
     */
    public String getHotFlag() {
        return hotFlag;
    }

    /**
     * 设置热门城市，只有是在登录城市下的才能是热门城市，0--不是热门城市，1--是热门城市
     *
     * @param hotFlag 热门城市，只有是在登录城市下的才能是热门城市，0--不是热门城市，1--是热门城市
     */
    public void setHotFlag(String hotFlag) {
        this.hotFlag = hotFlag == null ? null : hotFlag.trim();
    }
}