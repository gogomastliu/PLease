package com.pandora.lease.ws.dao;

import com.pandora.lease.ws.model.GroupModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface GroupDAO  extends Mapper<GroupModel> {
    List<GroupModel> QueryGroupByUser(Integer userid);
}
