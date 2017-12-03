package com.demo.ssm.service.Impl.S_net;

import com.demo.ssm.mapper.S_net.StationPropertyMapper;
import com.demo.ssm.po.S_net.StationProperty;
import com.demo.ssm.service.interf.S_net.StationPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StationPropertyServiceImpl implements StationPropertyService {

    @Autowired
    private StationPropertyMapper stationPropertyMapper;

    @Override
    public List<StationProperty> selectByPrimaryKey(String Province) throws IOException{

        return stationPropertyMapper.selectByPrimaryKey(Province);


    }

    @Override
    public  int count(String Province) throws IOException{
        return stationPropertyMapper.count(Province);
    }

    @Override
    public String selectByID(String Province, String obj_id) {
        return stationPropertyMapper.selectByID(Province,obj_id);
    }


}
