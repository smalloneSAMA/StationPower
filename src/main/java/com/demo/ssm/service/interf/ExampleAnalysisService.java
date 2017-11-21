package com.demo.ssm.service.interf;

import com.demo.ssm.po.ExampleAnalysis;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ExampleAnalysisService {

    List<ExampleAnalysis> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
