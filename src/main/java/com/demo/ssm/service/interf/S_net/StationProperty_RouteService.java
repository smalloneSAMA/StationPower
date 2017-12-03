package com.demo.ssm.service.interf.S_net;

import com.demo.ssm.po.S_net.StationProperty_Route;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface StationProperty_RouteService {

    List<StationProperty_Route> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
