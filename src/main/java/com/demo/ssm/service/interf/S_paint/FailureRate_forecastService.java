package com.demo.ssm.service.interf.S_paint;

import com.demo.ssm.po.S_paint.FailureRate_forecast;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface FailureRate_forecastService {

    FailureRate_forecast Query(@Param("id") String id, @Param("Province") String Province) throws IOException;

    int Count(String Province) throws IOException;

}
