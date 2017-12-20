package com.pandora.lease.model.manager.room;

import com.alibaba.fastjson.annotation.JSONField;
import com.pandora.lease.model.enums.RoomTypeEnum;

import javax.persistence.*;
import java.util.List;

/**
 * 房源信息类
 */
@Table(name = "t_room")
public class RoomModel {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 房源编码
     */
    private String room_code;
    /**
     * 房间标题
     */
    private String room_title;
    /**
     * 发布时间
     */
    private String release_time;
    /**
     * 发布类型
     */
    private Integer release_type;
    /**
     * 房源类型
     * 如公寓、民宅、商铺等
     */
    private RoomTypeEnum rtype;
    /**
     * 户型
     */
    private Integer room_type;
    /**
     * 装修类型
     */
    private Integer decoration_type;
    /**
     * 楼层
     */
    private Integer storey;
    /**
     * 房屋描述
     */
    private String room_desc;
    /**
     * 房源地址
     */
    private String address;
    /**
     * 坐标
     */
    @Column(name = "CGPoint")
    private String cgPoint;
    /**
     * 排序
     */
    private String sorts;
    /**
     * 创建时间
     */
    private String creatdate;
    /**
     * 修改时间
     */
    private String updatedate;
    /**
     * 子房源
     */
    @Transient
    private List<RoomConfigModel> childrenList;
    /**
     * 操作人id
     */
    private Integer oper;
    /**
     * 操作人名称
     */
    @Transient
    private String operName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoom_code() {
        return room_code;
    }

    public void setRoom_code(String room_code) {
        this.room_code = room_code;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public Integer getRelease_type() {
        return release_type;
    }

    public void setRelease_type(Integer release_type) {
        this.release_type = release_type;
    }

    public Integer getRoom_type() {
        return room_type;
    }

    public void setRoom_type(Integer room_type) {
        this.room_type = room_type;
    }

    public Integer getDecoration_type() {
        return decoration_type;
    }

    public void setDecoration_type(Integer decoration_type) {
        this.decoration_type = decoration_type;
    }

    public Integer getStorey() {
        return storey;
    }

    public void setStorey(Integer storey) {
        this.storey = storey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCgPoint() {
        return cgPoint;
    }

    public void setCgPoint(String cgPoint) {
        this.cgPoint = cgPoint;
    }

    public String getSorts() {
        return sorts;
    }

    public void setSorts(String sorts) {
        this.sorts = sorts;
    }

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(String creatdate) {
        this.creatdate = creatdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getOper() {
        return oper;
    }

    public void setOper(Integer oper) {
        this.oper = oper;
    }

    public String getRoom_title() {
        return room_title;
    }

    public void setRoom_title(String room_title) {
        this.room_title = room_title;
    }

    public String getRoom_desc() {
        return room_desc;
    }

    public void setRoom_desc(String room_desc) {
        this.room_desc = room_desc;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public List<RoomConfigModel> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<RoomConfigModel> childrenList) {
        this.childrenList = childrenList;
    }

    public RoomTypeEnum getRtype() {
        return rtype;
    }

    public void setRtype(RoomTypeEnum rtype) {
        this.rtype = rtype;
    }
}
