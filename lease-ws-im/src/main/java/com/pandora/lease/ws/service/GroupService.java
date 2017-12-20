package com.pandora.lease.ws.service;

import com.pandora.lease.ws.model.GroupModel;

import java.util.List;

public interface GroupService {
    List<GroupModel> getGroups(Integer userid);
}
