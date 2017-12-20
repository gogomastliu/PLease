package com.pandora.lease.service.room;

import com.pandora.lease.model.manager.room.RoomModel;

import java.util.List;

public interface RoomService {
    /**
     * 查询所以房源列表
     * @return 房源列表
     */
    List<RoomModel> getAllRoomList();

    /**
     * 按照某种类型进行查询
     * @param roomModel 实体内赋值的作为筛选条件
     * @return  通过条件查询的房源列表
     */
    List<RoomModel> getRoomList(RoomModel roomModel);
}
