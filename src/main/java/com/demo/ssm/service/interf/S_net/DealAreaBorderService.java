package com.demo.ssm.service.interf.S_net;

import com.demo.ssm.po.S_net.t_spc_zone;
import com.demo.ssm.po.S_net.zone_roly;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DealAreaBorderService {

    List<t_spc_zone> getAllAreaCollection();

    Boolean insertAreaBorder(List<zone_roly> zone_rolies);

}
