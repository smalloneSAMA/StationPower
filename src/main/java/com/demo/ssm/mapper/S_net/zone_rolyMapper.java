package com.demo.ssm.mapper.S_net;

import com.demo.ssm.po.S_net.t_spc_zone;
import com.demo.ssm.po.S_net.zone_roly;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface zone_rolyMapper {

    //插入单个边界
    int insertRoly(zone_roly zone_roly);

    //插入多个边界
    int insertRolyWithList(List<zone_roly> zone_rolys);


}
