package com.demo.ssm.service.Impl.S_paint;

import com.demo.ssm.mapper.S_paint.Relationship_Failure_YearMapper;
import com.demo.ssm.po.S_paint.Relationship_Failure_Year;
import com.demo.ssm.service.interf.S_paint.Relationship_Failure_YearService;
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
