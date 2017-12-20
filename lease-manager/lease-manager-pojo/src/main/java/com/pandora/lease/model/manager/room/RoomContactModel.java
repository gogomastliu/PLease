package com.pandora.lease.model.manager.room;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 房间联系历史
 */
@Table(name="t_room_contact")
public class RoomContactModel {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 房源子表ID
     */
    @Column(name = "room_config_id")
    private Integer room_config_id;
    /**
     * 联系时间
     * 前台计算时间 【XX小时前】
     */
    private Date contact_time;
    /**
     * 联系电话号码
     * 除房东外中间4位加密 ****
     */
    private String contact_number;
    /**
     * 联系类型方式
     * 联系房东、联系客服等
     */
    private String contact_type;
    /**
     * 排序
     */
    private Integer sorts;
    /**
     * 创建时间
     */
    private Date creatdate;

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

    public Date getContact_time() {
        return contact_time;
    }

    public void setContact_time(Date contact_time) {
        this.contact_time = contact_time;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getContact_type() {
        return contact_type;
    }

    public void setContact_type(String contact_type) {
        this.contact_type = contact_type;
    }

    public Integer getSorts() {
        return sorts;
    }

    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }

    public Date getCreatdate() { return creatdate; }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }
}
