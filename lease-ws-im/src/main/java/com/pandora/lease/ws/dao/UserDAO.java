package com.pandora.lease.ws.dao;


import com.pandora.lease.model.manager.user.UserModel;
import tk.mybatis.mapper.common.Mapper;

public interface UserDAO extends Mapper<UserModel>{
    /**
     * 测试
     * @param usercode  用户编码
     * @return
     */
    int getMaxVersionByCode(String usercode);
}
