package com.pandora.lease.dao.room;

import com.pandora.lease.model.manager.room.RoomModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoomDAO extends Mapper<RoomModel> {
    List<RoomModel> queryForList();
}
