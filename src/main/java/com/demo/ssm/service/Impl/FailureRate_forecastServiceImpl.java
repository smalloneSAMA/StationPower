package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.FailureRate_forecastMapper;
import com.demo.ssm.po.FailureRate_forecast;
import com.demo.ssm.service.interf.FailureRate_forecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FailureRate_forecastServiceImpl implements FailureRate_forecastService {

    @Autowired
    private FailureRate_forecastMapper failureRate_forecastMapper;

    @Override
    public List<FailureRate_forecast> selectByPrimaryKey() throws IOException{

        return failureRate_forecastMapper.selectByPrimaryKey();


    }

    @Override
    public  int count() throws IOException{
        return failureRate_forecastMapper.count();
    }


}
