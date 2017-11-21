package com.demo.ssm.service.interf;

import com.demo.ssm.po.FailureRate_forecast;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface FailureRate_forecastService {

    List<FailureRate_forecast> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
