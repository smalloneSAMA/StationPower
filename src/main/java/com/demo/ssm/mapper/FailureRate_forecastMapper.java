package com.demo.ssm.mapper;

import com.demo.ssm.po.FailureRate_forecast;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface FailureRate_forecastMapper {
    List<FailureRate_forecast> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
