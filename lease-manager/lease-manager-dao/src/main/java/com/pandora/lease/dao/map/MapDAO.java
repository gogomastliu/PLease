package com.pandora.lease.dao.map;

import com.pandora.lease.model.map.MapModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MapDAO extends Mapper<MapModel> {
    List<MapModel> getDistrict();
}
