package com.demo.ssm.mapper.S_paint;

import com.demo.ssm.po.S_paint.AverageFailureRate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface AverageFailureRateMapper {

    List<AverageFailureRate> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
