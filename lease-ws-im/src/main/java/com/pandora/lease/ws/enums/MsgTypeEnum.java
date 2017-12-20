package com.pandora.lease.ws.enums;


/**
 * 消息类型
 */
public enum MsgTypeEnum {
    通知,
    上线通知,
    离线通知,
    //之下所有内容不再转存Data
    // 而将Object转存到MsgContent
    消息,
    在线请求,
    在线应答,
    离线请求,
    离线应答,
    发送成功,
    接收成功;
}
