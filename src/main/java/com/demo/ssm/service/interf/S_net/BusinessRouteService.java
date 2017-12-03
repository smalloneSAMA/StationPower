package com.demo.ssm.service.interf.S_net;

import com.demo.ssm.po.S_net.BusinessRoute;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface BusinessRouteService {
//    查询两站点间路由
    List<BusinessRoute> selectByPrimaryKey(@Param("Province")String Province,
                                           @Param("buz_id")String buz_id) throws IOException;

    int count(String Province) throws IOException;
}
