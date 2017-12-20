package com.pandora.lease.ws.service;

import com.pandora.lease.ws.model.GroupUserModel;

public interface GroupUserService {
    GroupUserModel getGroupUserByUserId(Integer userId);
    GroupUserModel getGroupUserById(Integer id);
}
