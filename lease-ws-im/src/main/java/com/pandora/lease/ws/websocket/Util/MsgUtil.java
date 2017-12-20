package com.pandora.lease.ws.websocket.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.pandora.lease.model.manager.user.UserModel;
import com.pandora.lease.ws.enums.MsgTypeEnum;
import com.pandora.lease.ws.enums.UserStateEnum;
import com.pandora.lease.ws.job.TestJob;
import com.pandora.lease.ws.model.GroupModel;
import com.pandora.lease.ws.model.GroupUserModel;
import com.pandora.lease.ws.model.MsgModel;
import com.pandora.lease.ws.service.UserService;
import com.pandora.lease.ws.websocket.ChatTextHandler;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 发送消息通用类
 */
@Component
public class MsgUtil {
    private static Logger logger = LoggerFactory.getLogger(MsgUtil.class);
    @Autowired
    private UserService userService;
    private static MsgUtil msgUtil;
    @PostConstruct
    public void init(){
        msgUtil=this;
        msgUtil.userService=this.userService;
    }
    /**
     * 发送消息时监控是否收到
     * 未收到消息调用Job
     *
     * @throws SchedulerException
     * @throws InterruptedException
     */
    private static void StartJob() throws SchedulerException, InterruptedException {
        JobDetail job = newJob()
                .ofType(TestJob.class) //引用Job Class
                .withIdentity("job1", "group1") //设置name/group
                .withDescription("this is a test job") //设置描述
                .usingJobData("age", 18) //加入属性到ageJobDataMap
                .build();

        job.getJobDataMap().put("name", "quertz"); //加入属性name到JobDataMap

        //定义一个每秒执行一次的SimpleTrigger
        Trigger trigger = newTrigger()
                .startNow()
                .withIdentity("trigger1")
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        Scheduler sche = StdSchedulerFactory.getDefaultScheduler();
        sche.scheduleJob(job, trigger);

        sche.start();
    }

    /**
     * 上线连接需发送消息-在线用户列表
     *
     * @param session     用户信息
     * @param groupModels 用户所有组信息
     * @throws IOException
     */
    public static void SetMyOnLineUsers(WebSocketSession session, List<GroupModel> groupModels) throws IOException {
        //发送当前除自己外所以在线用户列表
        //只需要用户名及ID
        UserModel currentum = getUserModel(session);
        ChatTextHandler.users.stream().filter(f ->
                getUserModel(f).getOstype().equals(currentum.getOstype())&&!getUserModel(f).getId().equals(currentum.getId())&&f.isOpen()
        ).forEach(user -> {
            //同一个操作系统且不是当前登陆用户
                groupModels.stream().filter(f ->
                        f.getList() != null && f.getList().size() > 0
                ).forEach(groupModel -> groupModel.getList().forEach(groupuser -> {
                    if (groupuser.getUserId().equals(getUserModel(user).getId())) {
                        groupuser.setStatus(UserStateEnum.online);
                    } else {
                        groupuser.setStatus(UserStateEnum.offline);
                    }
                }));
                MsgModel currenttsm = new MsgModel(System.currentTimeMillis(), MsgTypeEnum.上线通知, session.getAttributes().get("groupuser"));
                try {
                    sendMsg(user, currenttsm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
        //通知当前用户所有在线用户列表
        Map<String, Object> maps = new HashMap<>();
        maps.put("currentum", session.getAttributes().get("groupuser"));
        maps.put("groups", groupModels);
        MsgModel tsm = new MsgModel(System.currentTimeMillis(), MsgTypeEnum.通知, maps);
        sendMsg(session, tsm);
    }

    /**
     * 通知所有在线用户离线情况-对应该操作系统
     *
     * @param session
     */
    public static void setMyOffToAllUsers(WebSocketSession session) throws IOException {
        MsgModel tsm = new MsgModel(System.currentTimeMillis(), MsgTypeEnum.离线通知, session.getAttributes().get("groupuser"));
         UserModel onLineUser = MsgUtil.getUserModel(session);
        ChatTextHandler.users.stream().filter(f -> getUserModel(f).getOstype().equals(onLineUser.getOstype())).forEach(ou -> {
            UserModel forUser = getUserModel(ou);
            if (!forUser.getId().equals(onLineUser.getId()) && ou.isOpen()) {
                try {
                    sendMsg(ou, tsm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 发消息给某一个用户
     *
     * @param session session用户
     * @param msg     消息内容
     * @throws IOException
     */
    public static void sendMsg(WebSocketSession session, MsgModel msg) throws IOException {
        if (session.isOpen()) {
            session.sendMessage(new TextMessage(JSON.toJSONString(msg)));
        }
    }


    /**
     * 当大于消息枚举时为消息
     *
     * @param message
     * @return
     */
    public static MsgModel setMsgContent(TextMessage message) {
        MsgModel msgModel = JSON.parseObject(message.getPayload(), new TypeReference<MsgModel>() {
        });
        //将Object转存到MsgContent
        if (msgModel.getMsgType().ordinal() > MsgTypeEnum.消息.ordinal() && msgModel.getData() != null) {
            msgModel.setMsgContent(msgModel.getData().toString());
        }
        return msgModel;
    }



    public static WebSocketSession getsession(Integer userid) {

        return ChatTextHandler.users.stream().filter(user -> ((GroupUserModel)user.getAttributes().get("groupuser")).getId().equals(userid)).findFirst().get();
    }

    /**
     * 获取一个用户
     *
     * @param session
     * @return
     */
    public static UserModel getUserModel(WebSocketSession session) {
        return (UserModel)session.getAttributes().get("userModel");
    }
}
