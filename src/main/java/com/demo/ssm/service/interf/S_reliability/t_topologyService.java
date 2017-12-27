package com.demo.ssm.service.interf.S_reliability;



import com.demo.ssm.po.S_reliability.t_topology;

import java.io.IOException;

public interface t_topologyService {

    t_topology select(String A_PORT, String Z_PORT) throws IOException;

    int count(String A_PORT, String Z_PORT) throws IOException;
}
