package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.Abnormal_top10Mapper;
import com.demo.ssm.mapper.Relationship_Failure_YearMapper;
import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.Relationship_Failure_Year;
import com.demo.ssm.service.interf.Abnormal_top10Service;
import com.demo.ssm.service.interf.Relationship_Failure_YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Relationship_Failure_YearServiceImpl implements Relationship_Failure_YearService {

    @Autowired
    private Relationship_Failure_YearMapper relationship_Failure_YearMapper;

    @Override
    public List<Relationship_Failure_Year> selectByPrimaryKey() throws IOException{

        return relationship_Failure_YearMapper.selectByPrimaryKey();


    }

    @Override
    public  int count() throws IOException{
        return relationship_Failure_YearMapper.count();
    }


}
