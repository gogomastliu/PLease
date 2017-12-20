package com.pandora.lease.ws.service;


import com.pandora.lease.model.manager.user.UserModel;

public interface UserService {

    int getMaxVersionByCode(String usercode);
    UserModel getUserModelById(Integer id);
}
