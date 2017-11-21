package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.Abnormal_top10Mapper;
import com.demo.ssm.mapper.StationPropertyMapper;
import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.StationProperty;
import com.demo.ssm.service.interf.Abnormal_top10Service;
import com.demo.ssm.service.interf.StationPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StationPropertyServiceImpl implements StationPropertyService {

    @Autowired
    private StationPropertyMapper stationPropertyMapper;

    @Override
    public List<StationProperty> selectByPrimaryKey() throws IOException{

        return stationPropertyMapper.selectByPrimaryKey();


    }

    @Override
    public  int count() throws IOException{
        return stationPropertyMapper.count();
    }


}
