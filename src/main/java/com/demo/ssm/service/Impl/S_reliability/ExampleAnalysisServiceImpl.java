package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.ExampleAnalysisMapper;
import com.demo.ssm.po.S_reliability.ExampleAnalysis;
import com.demo.ssm.service.interf.S_reliability.ExampleAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExampleAnalysisServiceImpl implements ExampleAnalysisService {

    @Autowired
    private ExampleAnalysisMapper exampleAnalysisMapper;

    @Override
    public List<ExampleAnalysis> selectByPrimaryKey() throws IOException{

        return exampleAnalysisMapper.selectByPrimaryKey();


    }

    @Override
    public  int count() throws IOException{
        return exampleAnalysisMapper.count();
    }


}
