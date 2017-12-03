package com.demo.ssm.mapper.S_paint;

import com.demo.ssm.po.S_paint.FailureRate_forecast;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface FailureRate_forecastMapper {

//    List<FailureRate_forecast> selectByPrimaryKey(String id) throws IOException;
    FailureRate_forecast Query(@Param("id") String id,@Param("Province") String Province) throws IOException;

//    int count() throws IOException;
    int Count(String Province) throws IOException;

}
