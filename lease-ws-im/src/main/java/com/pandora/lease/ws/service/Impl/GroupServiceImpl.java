package com.pandora.lease.ws.service.Impl;

import com.pandora.lease.ws.dao.GroupDAO;
import com.pandora.lease.ws.model.GroupModel;
import com.pandora.lease.ws.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDAO groupDAO;
    public List<GroupModel> getGroups(Integer userid){
        return groupDAO.QueryGroupByUser(userid);
    }
}
