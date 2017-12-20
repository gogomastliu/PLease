package com.pandora.lease.ws.model;

import com.pandora.lease.model.enums.OsTypeEnum;
import com.pandora.lease.ws.enums.MsgTypeEnum;
import com.pandora.lease.ws.enums.MsgStateEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 时间轴消息
 */
@Table(name = "t_socket_msg")
public class MsgModel {
    @javax.persistence.Id
    @GeneratedValue(generator = "JDBC")
    private  Integer id;
    /**
     * 时间轴
     */
    private Long timeStamp;

    /**
     * 操作系统类型
     */
    private OsTypeEnum osType;
    /**
     * 消息类型
     */
    private MsgTypeEnum msgType;
    /**
     * 状态
     */
    private MsgStateEnum state;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 发送人ID
     */
    private Integer sendmanId;
    /**
     * 接收人ID
     */
    private Integer receivemanId;
    /**
     * 发送日期
     */
    private Date senddate;
    /**
     * 接收日期
     */
    private Date receivedate;
    /**
     * 存储在线用户信息Json
     */
    @Transient
    private Object data;

    public MsgModel() {
    }

    public MsgModel(Long timeStamp, MsgTypeEnum msgType, Object data) {
        this.timeStamp = timeStamp;
        this.msgType = msgType;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public OsTypeEnum getOsType() {
        return osType;
    }

    public void setOsType(OsTypeEnum osType) {
        this.osType = osType;
    }

    public MsgTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeEnum msgType) {
        this.msgType = msgType;
    }

    public MsgStateEnum getState() {
        return state;
    }

    public void setState(MsgStateEnum state) {
        this.state = state;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getSendmanId() {
        return sendmanId;
    }

    public void setSendmanId(Integer sendmanId) {
        this.sendmanId = sendmanId;
    }

    public Integer getReceivemanId() {
        return receivemanId;
    }

    public void setReceivemanId(Integer receivemanId) {
        this.receivemanId = receivemanId;
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    public Date getReceivedate() {
        return receivedate;
    }

    public void setReceivedate(Date receivedate) {
        this.receivedate = receivedate;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
