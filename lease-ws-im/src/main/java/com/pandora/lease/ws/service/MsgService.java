package com.pandora.lease.ws.service;

import com.pandora.lease.model.manager.user.UserModel;
import com.pandora.lease.ws.enums.DbReturn;
import com.pandora.lease.ws.model.MsgModel;

import java.util.List;

public interface MsgService {

    /**
     * 根据用户查询离线消息
     * @param userModel 用户实体类
     * @return  所有离线消息
     */
    List<MsgModel> QueryOffLineMsgByUser(UserModel userModel,Integer id);
    /**
     * 添加一个离线消息
     * @param msgModel 需添加的消息实体类
     * @return
     */
    DbReturn InsertMsg(MsgModel msgModel);

    DbReturn UpdateMsgByTimeStamp(MsgModel msgModel);
    /**
     * 更新消息主体与明细状态
     *
     * @param msgModel 需更新的消息实体类
     * @return
     */
    DbReturn UpdateMsgState(MsgModel msgModel);
}
