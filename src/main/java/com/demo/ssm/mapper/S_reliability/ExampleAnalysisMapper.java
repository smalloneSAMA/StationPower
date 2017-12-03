package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.ExampleAnalysis;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface ExampleAnalysisMapper {
    List<ExampleAnalysis> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
