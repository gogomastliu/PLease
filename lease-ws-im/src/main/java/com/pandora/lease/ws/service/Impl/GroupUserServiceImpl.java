package com.pandora.lease.ws.service.Impl;

import com.pandora.lease.ws.dao.GroupUserDAO;
import com.pandora.lease.ws.enums.MsgStateEnum;
import com.pandora.lease.ws.model.GroupUserModel;
import com.pandora.lease.ws.model.MsgModel;
import com.pandora.lease.ws.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class GroupUserServiceImpl implements GroupUserService {
    @Autowired
    private GroupUserDAO groupUserDAO;

    public GroupUserModel getGroupUserByUserId(Integer userId){
        GroupUserModel gum=new GroupUserModel();
        gum.setUserId(userId);
        return groupUserDAO.selectOne(gum);
    }

    public GroupUserModel getGroupUserById(Integer id){
        GroupUserModel gum=new GroupUserModel();
        gum.setId(id);
        return groupUserDAO.selectOne(gum);
    }
}
