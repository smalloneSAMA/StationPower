package com.demo.ssm.service.Impl.S_net;

import com.demo.ssm.mapper.S_net.StationLink_JiangXiMapper;
import com.demo.ssm.po.S_net.StationLink_JiangXi;
import com.demo.ssm.service.interf.S_net.StationLink_JiangXiService;
import com.sun.jimi.core.util.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StationLink_JiangXiServiceImpl implements StationLink_JiangXiService {

    @Autowired
    private StationLink_JiangXiMapper stationLink_JiangXiMapper;

    @Override
    public List<StationLink_JiangXi> selectByPrimaryKey(String Province) throws IOException{

        return stationLink_JiangXiMapper.selectByPrimaryKey(Province);
    }
    @Override
    public  int count(String Province) throws IOException{
        return stationLink_JiangXiMapper.count(Province);
    }

    @Override
    public int countByID(String Province, String obj_id) throws IOException {
        return stationLink_JiangXiMapper.countByID(Province,obj_id);
    }

    @Override
    public Double selectBy2ID(String obj_id1, String obj_id2, String Province) throws IOException {
        return stationLink_JiangXiMapper.selectBy2ID(obj_id1,obj_id2,Province);
    }

    @Override
    public List<String> selectDistinctStation(String Province) {

        List<String> obj_1 = stationLink_JiangXiMapper.selectDistinctObj_1(Province);
        List<String> obj_2 = stationLink_JiangXiMapper.selectDistinctObj_2(Province);
        for(String item:obj_1){
            if(!obj_2.contains(item)){
                obj_2.add(item);
            }
        }
        return obj_2;
    }

    @Override
    public List<StationLink_JiangXi> selectByID(String Province, String obj_id) throws IOException {
        return stationLink_JiangXiMapper.selectByID(Province,obj_id);
    }


}
