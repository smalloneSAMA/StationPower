package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.Province_CheckMapper;
import com.demo.ssm.po.Province_Check;
import com.demo.ssm.service.interf.Province_CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Province_CheckServiceImpl implements Province_CheckService {

    @Autowired
    private Province_CheckMapper province_CheckMapper;

    @Override
    public List<Province_Check> selectByPrimaryKey() throws IOException{

        return province_CheckMapper.selectByPrimaryKey();


    }

    @Override
    public  int count() throws IOException{
        return province_CheckMapper.count();
    }


}
