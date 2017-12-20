package com.pandora.lease.service.room.Impl;

import com.alibaba.fastjson.JSON;
import com.pandora.lease.dao.room.RoomDAO;
import com.pandora.lease.model.manager.room.RoomModel;
import com.pandora.lease.service.room.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 房源列表操作列
 */
@Service
public class RoomServiceImpl implements RoomService {
    private Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Autowired
    private RoomDAO roomListDAO;

    /**
     * 查询所有房源列表
     *
     * @return
     */
    public List<RoomModel> getAllRoomList() {
        return roomListDAO.selectAll();
    }

    /**
     * 按照某种类型进行查询
     *
     * @param roomModel 实体内赋值的作为筛选条件
     * @return 通过条件查询的房源列表
     */
    public List<RoomModel> getRoomList(RoomModel roomModel) {
        //查询房源列表
        List<RoomModel> roomModels = roomListDAO.queryForList();
        logger.info(JSON.toJSONString(roomModels));
        return roomModels;
    }
}
