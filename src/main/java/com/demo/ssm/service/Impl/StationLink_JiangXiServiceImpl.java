package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.Abnormal_top10Mapper;
import com.demo.ssm.mapper.StationLink_JiangXiMapper;
import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.StationLink_JiangXi;
import com.demo.ssm.service.interf.Abnormal_top10Service;
import com.demo.ssm.service.interf.StationLink_JiangXiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StationLink_JiangXiServiceImpl implements StationLink_JiangXiService {

    @Autowired
    private StationLink_JiangXiMapper stationLink_JiangXiMapper;

    @Override
    public List<StationLink_JiangXi> selectByPrimaryKey() throws IOException{

        return stationLink_JiangXiMapper.selectByPrimaryKey();


    }
    @Override
    public  int count() throws IOException{
        return stationLink_JiangXiMapper.count();
    }


}
