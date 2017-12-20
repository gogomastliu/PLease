package com.pandora.lease.ws.websocket;


import com.pandora.lease.model.manager.user.UserModel;
import com.pandora.lease.ws.enums.DbReturn;
import com.pandora.lease.ws.enums.MsgTypeEnum;
import com.pandora.lease.ws.model.GroupModel;
import com.pandora.lease.ws.model.GroupUserModel;
import com.pandora.lease.ws.model.MsgDetailModel;
import com.pandora.lease.ws.model.MsgModel;
import com.pandora.lease.ws.service.GroupService;
import com.pandora.lease.ws.service.GroupUserService;
import com.pandora.lease.ws.service.MsgService;
import com.pandora.lease.ws.service.UserService;
import com.pandora.lease.ws.websocket.Util.MsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChatTextHandler extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(ChatTextHandler.class);
    @Autowired
    private MsgService msgService;
    @Autowired
    private GroupUserService guService;
    @Autowired
    private GroupService groupService;
    //已建立连接的用户
    public static final ArrayList<WebSocketSession> users = new ArrayList<>();

    public static final ArrayList<MsgModel> msgs = new ArrayList<>();

    /**
     * 处理前端发送的文本信息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        // 获取提交过来的消息详情
        logger.info("==> handleMessage： " + message.getPayload());
        MsgModel msgModel = MsgUtil.setMsgContent(message);
        DbReturn dbReturn = null;//数据库操作返回类型
        //消息库查询结果
        Optional<MsgModel> omsg = msgs.stream().filter(msg -> msg.getTimeStamp().equals(msgModel.getTimeStamp())).findFirst();
        //查询服务器消息队列中是否存在该消息
        switch (msgModel.getMsgType()) {
            case 离线应答:
                if (omsg.isPresent()) {
                    dbReturn = msgService.UpdateMsgState(omsg.get());
                    if (dbReturn.equals(DbReturn.成功)) {
                        logger.info(dbReturn.toString());
                        if (msgs.contains(omsg.get())) {
                            msgs.remove(omsg.get());
                        }
                    }
                } else {
                    //没有对应的时间戳--表示已经删除
                }
                break;
            case 在线应答:
                if (omsg.isPresent()) {
                    //更新接收时间
                    msgModel.setReceivedate(new Date());
                    msgModel.setId(omsg.get().getId());
                    switch (msgService.UpdateMsgByTimeStamp(msgModel)) {
                        case 成功:
                            //确认收到目标用户消息-删除消息池消息
                            if (msgs.contains(omsg.get())) {
                                msgModel.setMsgType(MsgTypeEnum.接收成功);
                                MsgUtil.sendMsg(session, msgModel);
                                msgModel.setMsgType(MsgTypeEnum.发送成功);
                                MsgUtil.sendMsg(MsgUtil.getsession(omsg.get().getSendmanId()), msgModel);
                                msgs.remove(omsg.get());
                            }
                            break;
                        case 失败:
                            logger.error("未知原因，导致一个在线应答的消息，未能更新成功。");
                            break;
                    }
                }

                break;
            case 在线请求:
                msgModel.setSenddate(new Date());
                Integer guid= ((GroupUserModel)session.getAttributes().get("groupuser")).getId();
                msgModel.setSendmanId(guid);
                MsgDetailModel zsdetailModel=setDetailModel(((GroupUserModel)session.getAttributes().get("groupuser")).getUsername(),
                        ((GroupUserModel)session.getAttributes().get("groupuser")).getAvatar(),
                        ((GroupUserModel)session.getAttributes().get("groupuser")).getId(),msgModel);
                msgModel.setData(zsdetailModel);
                //发给目标用户
                //从Model中找到接收人id对应Session
                Optional<WebSocketSession> simWeb = ChatTextHandler.users.stream().filter(f -> ((GroupUserModel)f.getAttributes().get("groupuser")).getId().equals(msgModel.getReceivemanId())).findFirst();
                if (simWeb.isPresent()) {
                    //添加到消息池
                    if (!msgs.contains(msgModel) && msgModel.getMsgType().equals(MsgTypeEnum.在线请求)) {
                        msgs.add(msgModel);
                    }
                    setMsg(msgModel);
                    MsgUtil.sendMsg(simWeb.get(), msgModel);
                } else {
                    //保存离线数据
                    msgModel.setMsgType(MsgTypeEnum.离线请求);
                    setMsg(msgModel);
                }
                break;
            case 离线请求:
                UserModel userModel = MsgUtil.getUserModel(session);
                //生成新的时间戳-批量推送该设备系统离线消息
                List<MsgModel> msgModels = msgService.QueryOffLineMsgByUser(userModel,((GroupUserModel)session.getAttributes().get("groupuser")).getId());
                if (msgModels.size() > 0) {
                    for (MsgModel mm: msgModels) {
                        GroupUserModel groupUserModel=guService.getGroupUserById(mm.getSendmanId());
                        MsgDetailModel lsdetailModel=setDetailModel(groupUserModel.getUsername(),groupUserModel.getAvatar(),groupUserModel.getId(),mm);
                        mm.setData(lsdetailModel);
                        MsgUtil.sendMsg(session, mm);
                        msgs.add(mm);
                    }
                }
                break;
        }
        //----------保存时间戳状态，5秒内为接到回执的再发
        //
        //1、接收消息，判断时间戳列表中是否存在不存在则保存，并返回对应值
        //2、周期5秒内未接到回执的再发，发送10次后记录数据库不再发，下次上线再发
        //   如果目标用户离线，则直接存数据库，并回执发送方。再循环第2项
        //3、接到回执后删除该时间戳，


        //回复一条信息，
        //转发给特定用户-如客服
        //1、当GroupModel中的groupType类型大于1时查询群内人员填充pmLists
        //2、当GroupModel中的groupType类型等于1时直接查询空闲客服人员填充pmLists
        //3、当GroupModel中的groupType类型等于1或2时发送一对一的消息
        //session.sendMessage(new TextMessage("reply msg:" +message.getPayload()));
        //发送图片
        //session.sendMessage(new TextMessage(ImageUtil.im));

     }

    /**
     * 当新连接建立的时候，被调用;
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("Connection Established");
        GroupUserModel ugm = guService.getGroupUserByUserId(MsgUtil.getUserModel(session).getId());
        session.getAttributes().put("groupuser", ugm);
        users.add(session);
        //更新用户列表只更新当前用户
        MsgUtil.SetMyOnLineUsers(session, groupService.getGroups(MsgUtil.getUserModel(session).getId()));
        super.afterConnectionEstablished(session);
    }

    /**
     * 当连接关闭时被调用；
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        MsgUtil.setMyOffToAllUsers(session);
        logger.debug("Connection closed. Status: " + status);
        users.remove(session);
        super.afterConnectionClosed(session, status);
    }


    /**
     * 在线请求插入一个新的消息
     *
     * @param msgModel
     */
    private void setMsg(MsgModel msgModel) {

        switch (msgService.InsertMsg(msgModel)) {
            case 成功:
                logger.info(msgModel.getMsgType().name() + " : 插入一个新的消息");
                break;
            case 已存在:
                logger.info(msgModel.getMsgType().name() + " : 已存在一个新的消息，未能插入成功。");
                break;
            case 失败:
                logger.info(msgModel.getMsgType().name() + " : 未知原因，导致一个新的消息，未能插入成功。");
                break;
        }
    }

    /**
     * 设置消息明细
     * @param username
     * @param avatar
     * @param id
     * @param msgModel
     * @return
     */
    private MsgDetailModel setDetailModel(String username,String avatar,Integer id,MsgModel msgModel) {
        MsgDetailModel detailModel= new MsgDetailModel();
        detailModel.setUsername(username);
        detailModel.setAvatar(avatar);
        detailModel.setId(id);//如果是私聊，则是用户id，如果是群聊，则是群组id
        detailModel.setType("friend");//聊天类型，一般分friend和group两种，group即群聊
        detailModel.setContent(msgModel.getMsgContent().toString());
        //detailModel.setCid(msgModel.getId());
        //detailModel.setMine(true);
        //detailModel.setFromid(msgModel.getReceivemanId());
        //detailModel.setTimestamp(System.currentTimeMillis());
        return detailModel;
    }

}