package com.pandora.lease.model.manager.room;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_room_pic")
public class RoomPicModel {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 房源子表ID
     */
    @Column(name = "room_config_id")
    private Integer room_config_id;
    /**
     * 图片名称
     */
    private String pic_name;
    /**
     * 图片路径
     */
    private String pic_url;
    /**
     * 图片描述
     */
    private String pic_desc;
    /**
     * 图片类型 0：纯图片 1：全景图片
     */
    private String pic_type;
    /**
     * 排序
     */
    private Integer sorts;
    /**
     * 创建时间
     */
    private Date creatdate;
    /**
     * 修改时间
     */
    private Date updatedate;
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

    public Integer getRoom_config_id() {
        return room_config_id;
    }

    public void setRoom_config_id(Integer room_config_id) {
        this.room_config_id = room_config_id;
    }

    public String getPic_name() {
        return pic_name;
    }

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPic_desc() {
        return pic_desc;
    }

    public void setPic_desc(String pic_desc) {
        this.pic_desc = pic_desc;
    }

    public String getPic_type() {
        return pic_type;
    }

    public void setPic_type(String pic_type) {
        this.pic_type = pic_type;
    }

    public Integer getSorts() {
        return sorts;
    }

    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getOper() {
        return oper;
    }

    public void setOper(Integer oper) {
        this.oper = oper;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }
}
