package com.pandora.lease.model.manager.room;

import javax.persistence.*;
import java.util.Date;

/**
 * 出租明细表
 */
@Table(name="t_room_rent")
public class RoomRentModel {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 房源子表ID
     */
    @Column(name = "room_config_id")
    private Integer room_config_id;
    /**
     * 租金
     */
    private double rent;
    /**
     * 浏览数
     * 累计数-独立action更新
     */
    private Integer browse_num;
    /**
     * 排序
     */
    private Integer sorts;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 创建时间
     */
    private Date creatdate;
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

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public Integer getBrowse_num() {
        return browse_num;
    }

    public void setBrowse_num(Integer browse_num) {
        this.browse_num = browse_num;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
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

    public Integer getSorts() {
        return sorts;
    }

    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }
}
