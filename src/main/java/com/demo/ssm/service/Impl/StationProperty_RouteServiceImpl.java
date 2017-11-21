package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.StationPropertyMapper;
import com.demo.ssm.mapper.StationProperty_RouteMapper;
import com.demo.ssm.po.StationProperty;
import com.demo.ssm.po.StationProperty_Route;
import com.demo.ssm.service.interf.StationPropertyService;
import com.demo.ssm.service.interf.StationProperty_RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StationProperty_RouteServiceImpl implements StationProperty_RouteService {

    @Autowired
    private StationProperty_RouteMapper stationProperty_RouteMapper;

    @Override
    public List<StationProperty_Route> selectByPrimaryKey() throws IOException{

        return stationProperty_RouteMapper.selectByPrimaryKey();


    }
    @Override
    public  int count() throws IOException{
        return stationProperty_RouteMapper.count();
    }


}
