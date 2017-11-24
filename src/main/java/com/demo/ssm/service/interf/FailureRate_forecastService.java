package com.demo.ssm.service.interf;

import com.demo.ssm.po.FailureRate_forecast;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface FailureRate_forecastService {

    FailureRate_forecast Query(@Param("id") String id, @Param("Province") String Province) throws IOException;

    int Count(String Province) throws IOException;

}
