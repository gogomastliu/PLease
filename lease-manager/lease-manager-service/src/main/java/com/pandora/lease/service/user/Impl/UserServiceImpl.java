package com.pandora.lease.service.user.Impl;

import com.pandora.lease.dao.user.UserDAO;
import com.pandora.lease.model.manager.user.UserModel;
import com.pandora.lease.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    /**
     * 注册用户
     *
     * @param userModel 注册用户信息
     * @return
     */
    public int registerUser(UserModel userModel) {
        return userDAO.insert(userModel);
    }

    public int updateUser(UserModel userModel) {
        return userDAO.updateByPrimaryKeySelective(userModel);
    }
}
