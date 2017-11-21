package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.StationTypeMapper;
import com.demo.ssm.po.StationType;
import com.demo.ssm.service.interf.StationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StationTypeServiceImpl implements StationTypeService{

    @Autowired
    private StationTypeMapper stationTypeMapper;

    @Override
    public List<StationType> selectByPrimaryKey() throws IOException {
        return stationTypeMapper.selectByPrimaryKey();
    }

    @Override
    public  int count() throws IOException{
        return stationTypeMapper.count();
    }
}
