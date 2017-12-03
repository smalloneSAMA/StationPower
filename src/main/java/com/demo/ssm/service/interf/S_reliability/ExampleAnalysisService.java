package com.demo.ssm.service.interf.S_reliability;

import com.demo.ssm.po.S_reliability.ExampleAnalysis;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ExampleAnalysisService {

    List<ExampleAnalysis> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
