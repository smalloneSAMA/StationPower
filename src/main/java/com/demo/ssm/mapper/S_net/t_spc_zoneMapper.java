package com.demo.ssm.mapper.S_net;

import com.demo.ssm.po.S_net.t_spc_zone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface t_spc_zoneMapper {

    //获得所有区域
    List<t_spc_zone> queryArea();


}
