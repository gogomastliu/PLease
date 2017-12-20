package com.pandora.lease.ws.service.Impl;

import com.pandora.lease.model.manager.user.UserModel;
import com.pandora.lease.ws.dao.MsgDAO;
import com.pandora.lease.ws.enums.MsgStateEnum;
import com.pandora.lease.ws.enums.DbReturn;
import com.pandora.lease.ws.model.MsgDetailModel;
import com.pandora.lease.ws.model.MsgModel;
import com.pandora.lease.ws.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    private MsgDAO msgDAO;

    /**
     * 根据用户查询离线消息
     * @param userModel 用户实体类
     * @return  所有离线消息
     */
    public List<MsgModel> QueryOffLineMsgByUser(UserModel userModel,Integer id) {
        Example example = new Example(MsgModel.class);//通用Example查询-字段名称与属性名称对应时区分大小写
        example.createCriteria().andEqualTo("state", MsgStateEnum.未读)
                .andEqualTo("osType", userModel.getOstype())
                .andEqualTo("receivemanId",id);
        return msgDAO.selectByExample(example);
    }

    /**
     * 添加一个离线消息
     * @param msgModel 需添加的消息实体类
     * @return
     */
    @Transient
    public DbReturn InsertMsg(MsgModel msgModel){
        Example example=new Example(MsgModel.class);
        example.createCriteria().andEqualTo("timeStamp",msgModel.getTimeStamp())
                                .andEqualTo("osType",msgModel.getOsType())
                                .andEqualTo("msgType",msgModel.getMsgType())
                                .andEqualTo("msgContent",msgModel.getMsgContent())
                                .andEqualTo("sendmanId",msgModel.getSendmanId())
                                .andEqualTo("receivemanId",msgModel.getReceivemanId());
        //判断该离线消息是否存在
        if(msgDAO.selectCountByExample(example)>0){
            return DbReturn.已存在;
        }
        msgModel.setState(MsgStateEnum.未读);
        return msgDAO.insertSelective(msgModel)>0? DbReturn.成功: DbReturn.失败;
    }

    /**
     * 根据时间戳更新消息
     * @param msgModel
     * @return
     */
    @Override
    public DbReturn UpdateMsgByTimeStamp(MsgModel msgModel) {
        return msgDAO.updateByPrimaryKeySelective(msgModel)>0?DbReturn.成功:DbReturn.失败;
    }


    /**
     * 更新消息主体与明细状态
     *
     * @param msgModel 需更新的消息实体类
     * @return
     */
    public DbReturn UpdateMsgState(MsgModel msgModel) {
        DbReturn bret = DbReturn.成功;
        int ret = 0;
        msgModel.setMsgContent(null);
        msgModel.setOsType(null);
        msgModel.setSenddate(null);
        msgModel.setReceivemanId(null);
        msgModel.setSendmanId(null);
        msgModel.setData(null);
        msgModel.setState(MsgStateEnum.已读);
        msgModel.setReceivedate(new Date());
        msgModel.setTimeStamp(null);
        ret = msgDAO.updateByPrimaryKeySelective(msgModel);
        if (ret <= 0) {
            bret = DbReturn.失败;
        }
        return bret;
    }

}
