package com.demo.ssm.mapper.S_net;

import com.demo.ssm.po.S_net.BusinessRoute;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface BusinessRouteMapper {
//    查询两站点间路由
    List<BusinessRoute> selectByPrimaryKey(@Param("Province")String Province,
                                           @Param("buz_id")String buz_id) throws IOException;

    int count(String Province) throws IOException;
}
