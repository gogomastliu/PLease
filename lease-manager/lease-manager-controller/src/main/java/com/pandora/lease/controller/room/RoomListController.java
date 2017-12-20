package com.pandora.lease.controller.room;

import com.alibaba.fastjson.JSON;
import com.pandora.lease.model.common.ResultModel;
import com.pandora.lease.model.manager.room.RoomModel;
import com.pandora.lease.model.manager.user.UserModel;
import com.pandora.lease.service.room.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 房源列表操作
 */
@Controller
@RequestMapping("/room")
public class RoomListController {
    private Logger logger = LoggerFactory.getLogger(RoomListController.class);
    /**
     * 用户服务
     */
    @Autowired
    private RoomService roomListService;

    /**
     * 注册用户
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRoomList")
    public ResultModel getRoomList(UserModel userModel) {
        List<RoomModel> result = roomListService.getRoomList(null);
        String json=JSON.toJSONString(result);
        logger.info("注册用户：" + userModel + "\n" + json);
        ResultModel resultModel;
        if (result != null) {
            resultModel = new ResultModel(true, "注册成功!", result);
        } else {
            resultModel = new ResultModel(false, "注册失败!", null);
        }
        return resultModel;
    }
}
