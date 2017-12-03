package com.demo.ssm.service.Impl.S_paint;

import com.demo.ssm.mapper.S_paint.Relationship_Temperature_FailureRateMapper;
import com.demo.ssm.po.S_paint.Relationship_Temperature_FailureRate;
import com.demo.ssm.service.interf.S_paint.Relationship_Temperature_FailureRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Relationship_Temperature_FailureRateServiceImpl implements Relationship_Temperature_FailureRateService {

    @Autowired
    private Relationship_Temperature_FailureRateMapper relationship_Temperature_FailureRate;

    @Override
    public List<Relationship_Temperature_FailureRate> selectByPrimaryKey() throws IOException{

        return relationship_Temperature_FailureRate.selectByPrimaryKey();


    }

    @Override
    public  int count() throws IOException{
        return relationship_Temperature_FailureRate.count();
    }


}
