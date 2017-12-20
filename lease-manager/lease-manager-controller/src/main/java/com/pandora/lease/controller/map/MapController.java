package com.pandora.lease.controller.map;

import com.pandora.lease.controller.room.RoomListController;
import com.pandora.lease.model.common.ResultModel;
import com.pandora.lease.model.manager.user.UserModel;
import com.pandora.lease.model.map.MapModel;
import com.pandora.lease.service.map.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 *
 */
@Controller
@RequestMapping("/map")
public class MapController {
    private Logger logger = LoggerFactory.getLogger(RoomListController.class);
    @Autowired
    private MapService mapService;

    /**
     * 获取当前地市所有区的名称与总数
     *
     * @param cityName
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDistrict")
    public ResultModel getDistrict(String cityName) {
        List<MapModel> result = mapService.getmap();
        ResultModel resultModel;
        if (result != null) {
            resultModel = new ResultModel(true, "map所有区查询成功!", result);
        } else {
            resultModel = new ResultModel(false, "map所有区查询失败!", null);
        }
        return resultModel;
    }
}
