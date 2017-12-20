package com.pandora.lease.ws.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.pandora.lease.ws.enums.UserStateEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * 群内人信息类
 */
@Table(name = "t_im_groupuser")
public class GroupUserModel {
    @javax.persistence.Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Integer userId;
    private String username;
    private String avatar;//用户头像
    private String sign;//用户签名
    private UserStateEnum status;//若值为offline代表离线，online或者不填为在线
    @JSONField(serialize=false)
    private Integer groupId;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public UserStateEnum getStatus() {
        return status;
    }

    public void setStatus(UserStateEnum status) {
        this.status = status;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
