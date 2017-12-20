package com.pandora.lease.model.manager.room;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 房源子表
 * 1、整租房源-只记录各个房间信息-一条
 * 2、合租房源-记录多个房间信息---N条
 * 父子关系
 */
@Table(name = "t_room_config")
public class RoomConfigModel {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 房源主表ID
     */
    @Column(name = "room_id")
    private Integer room_id;
    /**
     * 父ID
     */
    @Column(name = "pid")
    private Integer pid;
    /**
     * 房间编码
     */
    private String room_config_code;

    /**
     * 押金方式
     */
    private Integer cash_pledge;
    /**
     * 房间或卧室名称
     */
    private String chamber;
    /**
     * 房间面积
     */
    private double room_size;
    /**
     * 是否有-卫生间
     */
    private Boolean toilet;
    /**
     * 是否有-床
     */
    private Boolean bed;
    /**
     * 是否有-桌子
     */
    private Boolean desk;
    /**
     * 是否有-衣柜
     */
    private Boolean armoire;
    /**
     * 是否有-沙发
     */
    private Boolean sofa;
    /**
     * 是否有-空调
     */
    private Boolean air_condition;
    /**
     * 是否有-热水器
     */
    private Boolean water_heater;
    /**
     * 是否有-微波炉
     */
    private Boolean micro_wave_oven;
    /**
     * 是否有-电磁炉
     */
    private Boolean induction_cooker;
    /**
     * 是否有-抽油烟机
     */
    private Boolean cooker_hood;
    /**
     * 是否有-阳台
     */
    private Boolean balcony;
    /**
     * 是否有-网络
     */
    private Boolean wifi;
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
    private String oper;
    /**
     * 操作人名称
     */
    @Transient
    private String operName;
    /**
     * 租住状态列表
     */
    @Transient
    private RoomRentModel roomRentModel;
    /**
     * 房间图片情况
     */
    @Transient
    private List<RoomPicModel> roomPicModels;
    /**
     * 房间联系历史
     */
    @Transient
    private List<RoomContactModel> roomContactModels;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public String getRoom_config_code() {
        return room_config_code;
    }

    public void setRoom_config_code(String room_config_code) {
        this.room_config_code = room_config_code;
    }

    public Integer getCash_pledge() {
        return cash_pledge;
    }

    public void setCash_pledge(Integer cash_pledge) {
        this.cash_pledge = cash_pledge;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public double getRoom_size() {
        return room_size;
    }

    public void setRoom_size(double room_size) {
        this.room_size = room_size;
    }

    public Boolean getToilet() {
        return toilet;
    }

    public void setToilet(Boolean toilet) {
        this.toilet = toilet;
    }

    public Boolean getBed() {
        return bed;
    }

    public void setBed(Boolean bed) {
        this.bed = bed;
    }

    public Boolean getDesk() {
        return desk;
    }

    public void setDesk(Boolean desk) {
        this.desk = desk;
    }

    public Boolean getArmoire() {
        return armoire;
    }

    public void setArmoire(Boolean armoire) {
        this.armoire = armoire;
    }

    public Boolean getSofa() {
        return sofa;
    }

    public void setSofa(Boolean sofa) {
        this.sofa = sofa;
    }

    public Boolean getAir_condition() {
        return air_condition;
    }

    public void setAir_condition(Boolean air_condition) {
        this.air_condition = air_condition;
    }

    public Boolean getWater_heater() {
        return water_heater;
    }

    public void setWater_heater(Boolean water_heater) {
        this.water_heater = water_heater;
    }

    public Boolean getMicro_wave_oven() {
        return micro_wave_oven;
    }

    public void setMicro_wave_oven(Boolean micro_wave_oven) {
        this.micro_wave_oven = micro_wave_oven;
    }

    public Boolean getInduction_cooker() {
        return induction_cooker;
    }

    public void setInduction_cooker(Boolean induction_cooker) {
        this.induction_cooker = induction_cooker;
    }

    public Boolean getCooker_hood() {
        return cooker_hood;
    }

    public void setCooker_hood(Boolean cooker_hood) {
        this.cooker_hood = cooker_hood;
    }

    public Boolean getBalcony() {
        return balcony;
    }

    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
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

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Integer getPid() { return pid; }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public RoomRentModel getRoomRentModel() {
        return roomRentModel;
    }

    public void setRoomRentModel(RoomRentModel roomRentModel) {
        this.roomRentModel = roomRentModel;
    }

    public List<RoomPicModel> getRoomPicModels() {
        return roomPicModels;
    }

    public void setRoomPicModels(List<RoomPicModel> roomPicModels) {
        this.roomPicModels = roomPicModels;
    }

    public List<RoomContactModel> getRoomContactModels() {
        return roomContactModels;
    }

    public void setRoomContactModels(List<RoomContactModel> roomContactModels) {
        this.roomContactModels = roomContactModels;
    }
}
