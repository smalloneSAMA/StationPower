package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.t_topologyMapper;
import com.demo.ssm.po.S_reliability.t_topology;
import com.demo.ssm.service.interf.S_reliability.t_topologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class t_topologyServiceImpl implements t_topologyService {

    @Autowired
    private t_topologyMapper t;

    @Override
    public t_topology select(String A_PORT, String Z_PORT, String province) throws IOException {
        return t.select(A_PORT,Z_PORT,province);
    }

    @Override
    public int count(String A_PORT, String Z_PORT,String province) throws IOException {
        return t.count(A_PORT,Z_PORT,province);
    }
}
