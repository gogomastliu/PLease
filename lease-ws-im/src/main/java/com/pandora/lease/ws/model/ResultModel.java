package com.pandora.lease.ws.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.pandora.lease.ws.enums.MsgTypeEnum;

public class ResultModel {

	private boolean success;//是否成功
	private MsgTypeEnum msgType;
	private String msg;//消息
	private Object data;//数据
	private int status;//状态

	public ResultModel() {

	}
	public ResultModel(boolean success, MsgTypeEnum msgType, String msg, Object data, int status) {
		this.success = success;
		this.msgType = msgType;
		this.msg = msg;
		this.data = data;
		this.status = status;
	}


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public MsgTypeEnum getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgTypeEnum msgType) {
		this.msgType = msgType;
	}
}
