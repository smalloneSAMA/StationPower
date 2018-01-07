package com.demo.ssm.service.Impl.S_net;

import com.demo.ssm.mapper.S_net.t_spc_zoneMapper;
import com.demo.ssm.mapper.S_net.zone_rolyMapper;
import com.demo.ssm.po.S_net.t_spc_zone;
import com.demo.ssm.po.S_net.zone_roly;
import com.demo.ssm.service.interf.S_net.DealAreaBorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DealAreaBorderImpl implements DealAreaBorderService {

    @Autowired
    private t_spc_zoneMapper tSpcZoneMapper;

    @Autowired
    private zone_rolyMapper zoneRolyMapper;

    @Override
    public List<t_spc_zone> getAllAreaCollection() {
        return tSpcZoneMapper.queryArea();
    }

    @Override
    public Boolean insertAreaBorder(List<zone_roly> zone_rolies) {
        int ins =  zoneRolyMapper.insertRolyWithList(zone_rolies);
        if(ins>=0){
            return true;
        }
        else {
            return false;
        }
    }
}
