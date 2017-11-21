package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.Abnormal_top10Mapper;
import com.demo.ssm.mapper.ExampleAnalysisMapper;
import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.ExampleAnalysis;
import com.demo.ssm.service.interf.Abnormal_top10Service;
import com.demo.ssm.service.interf.ExampleAnalysisService;
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
