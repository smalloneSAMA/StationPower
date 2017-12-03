package com.demo.ssm.service.Impl.S_net;

import com.demo.ssm.mapper.S_net.BusinessRouteMapper;
import com.demo.ssm.po.S_net.BusinessRoute;
import com.demo.ssm.service.interf.S_net.BusinessRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BusinessRouteServiceImpl implements BusinessRouteService {

    @Autowired
    private BusinessRouteMapper BusinessRouteMapper;


    @Override
    public List<BusinessRoute> selectByPrimaryKey(String Province, String buz_id) throws IOException {
        return BusinessRouteMapper.selectByPrimaryKey(Province,buz_id);
    }

    @Override
    public  int count(String Province) throws IOException{
        return BusinessRouteMapper.count(Province);
    }


}
