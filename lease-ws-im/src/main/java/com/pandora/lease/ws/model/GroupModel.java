package com.pandora.lease.ws.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 群组类
 * 主要试用聊天
 * 当与客服聊天时默认组ID为0
 */
@Table(name = "t_im_group")
public class GroupModel {
    @javax.persistence.Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;//群组ID-默认我的好友组
    @JSONField(name = "groupname")
    private String groupName;//群组名称
    private String groupDesc;//组描述
    private String groupNotice;//组内最后一次通知公告
    @Transient
    private List<GroupUserModel> list;//当前组内成员

    public List<GroupUserModel> getList() {
        return list;
    }

    public void setList(List<GroupUserModel> list) {
        this.list = list;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getGroupNotice() {
        return groupNotice;
    }

    public void setGroupNotice(String groupNotice) {
        this.groupNotice = groupNotice;
    }
}
