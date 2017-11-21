package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.Abnormal_top10Mapper;
import com.demo.ssm.mapper.Relationship_Temperature_FailureRateMapper;
import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.Relationship_Temperature_FailureRate;
import com.demo.ssm.service.interf.Abnormal_top10Service;
import com.demo.ssm.service.interf.Relationship_Temperature_FailureRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Relationship_Temperature_FailureRateServiceImpl implements Relationship_Temperature_FailureRateService {

    @Autowired
    private Relationship_Temperature_FailureRateMapper relationship_Temperature_FailureRate;

    @Override
    public List<Relationship_Temperature_FailureRate> selectByPrimaryKey() throws IOException{

        return relationship_Temperature_FailureRate.selectByPrimaryKey();


    }

    @Override
    public  int count() throws IOException{
        return relationship_Temperature_FailureRate.count();
    }


}
