package com.demo.ssm.mapper;

import com.demo.ssm.po.AverageFailureRate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface AverageFailureRateMapper {

    List<AverageFailureRate> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
