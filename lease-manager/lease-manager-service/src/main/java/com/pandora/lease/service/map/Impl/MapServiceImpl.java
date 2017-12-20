package com.pandora.lease.service.map.Impl;

import com.pandora.lease.dao.map.MapDAO;
import com.pandora.lease.model.map.MapModel;
import com.pandora.lease.service.map.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MapServiceImpl implements MapService{
    @Autowired
    private MapDAO mapDAO;
    public List<MapModel> getmap(){
        return mapDAO.getDistrict();
    }
}
