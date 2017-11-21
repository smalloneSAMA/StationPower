package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.AverageFailureRateMapper;
import com.demo.ssm.po.AverageFailureRate;
import com.demo.ssm.service.interf.AverageFailureRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AverageFailureRateServiceImpl implements AverageFailureRateService {

    @Autowired
    private AverageFailureRateMapper averageFailureRateMapper;

    @Override
    public List<AverageFailureRate> selectByPrimaryKey() throws IOException{

        return averageFailureRateMapper.selectByPrimaryKey();


    }

    @Override
    public  int count() throws IOException{
        return averageFailureRateMapper.count();
    }


}
