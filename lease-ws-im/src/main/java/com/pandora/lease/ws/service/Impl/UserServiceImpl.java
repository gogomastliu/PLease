package com.pandora.lease.ws.service.Impl;


import com.pandora.lease.model.manager.user.UserModel;
import com.pandora.lease.ws.dao.UserDAO;
import com.pandora.lease.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    public int getMaxVersionByCode(String usercode) {
        return userDAO.getMaxVersionByCode(usercode);
    }
    public UserModel getUserModelById(Integer id){
        return userDAO.selectByPrimaryKey(id);
    }
}
