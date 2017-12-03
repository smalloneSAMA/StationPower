package com.demo.ssm.service.Impl.S_net;

import com.demo.ssm.mapper.S_net.StationProperty_RouteMapper;
import com.demo.ssm.po.S_net.StationProperty_Route;
import com.demo.ssm.service.interf.S_net.StationProperty_RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StationProperty_RouteServiceImpl implements StationProperty_RouteService {

    @Autowired
    private StationProperty_RouteMapper stationProperty_RouteMapper;

    @Override
    public List<StationProperty_Route> selectByPrimaryKey() throws IOException{

        return stationProperty_RouteMapper.selectByPrimaryKey();


    }
    @Override
    public  int count() throws IOException{
        return stationProperty_RouteMapper.count();
    }


}
