package com.pandora.lease.service.user;

import com.pandora.lease.model.manager.user.UserModel;

public interface UserService {

    /**
     * 注册用户
     * @param UserModel 注册用户信息
     * @return
     */
    int registerUser(UserModel userModel);

    /**
     * 修改用户
     * @param userModel
     * @return
     */
    int updateUser(UserModel userModel);
}
