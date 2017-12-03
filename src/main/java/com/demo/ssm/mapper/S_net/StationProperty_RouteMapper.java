package com.demo.ssm.mapper.S_net;


import com.demo.ssm.po.S_net.StationProperty_Route;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface StationProperty_RouteMapper {
    List<StationProperty_Route> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
