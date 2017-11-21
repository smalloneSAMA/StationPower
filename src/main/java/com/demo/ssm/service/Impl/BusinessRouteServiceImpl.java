package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.Abnormal_top10Mapper;
import com.demo.ssm.mapper.BusinessRouteMapper;
import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.BusinessRoute;
import com.demo.ssm.service.interf.Abnormal_top10Service;
import com.demo.ssm.service.interf.BusinessRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BusinessRouteServiceImpl implements BusinessRouteService {

    @Autowired
    private BusinessRouteMapper BusinessRouteMapper;

    @Override
    public List<BusinessRoute> selectByPrimaryKey() throws IOException{

        return BusinessRouteMapper.selectByPrimaryKey();


    }

    @Override
    public  int count() throws IOException{
        return BusinessRouteMapper.count();
    }


}
