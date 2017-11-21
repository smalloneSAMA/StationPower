package com.demo.ssm.mapper;

import com.demo.ssm.po.ExampleAnalysis;
import com.demo.ssm.po.Province_Check;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface Province_CheckMapper {
    List<Province_Check> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
