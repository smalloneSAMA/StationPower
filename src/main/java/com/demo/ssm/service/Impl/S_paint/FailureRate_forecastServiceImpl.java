package com.demo.ssm.service.Impl.S_paint;

import com.demo.ssm.mapper.S_paint.FailureRate_forecastMapper;
import com.demo.ssm.po.S_paint.FailureRate_forecast;
import com.demo.ssm.service.interf.S_paint.FailureRate_forecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FailureRate_forecastServiceImpl implements FailureRate_forecastService {

    @Autowired
    private FailureRate_forecastMapper failureRate_forecastMapper;


    @Override
    public FailureRate_forecast Query(String id, String Province) throws IOException {
        return failureRate_forecastMapper.Query(id,Province);
    }

    @Override
    public int Count(String Province) throws IOException {
        return failureRate_forecastMapper.Count(Province);
    }
}
