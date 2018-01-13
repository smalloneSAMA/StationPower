package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.jiangxi_fiber_faultMapper;
import com.demo.ssm.po.S_reliability.jiangxi_fiber_fault;
import com.demo.ssm.service.interf.S_reliability.Jiangxi_fiber_faultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Jiangxi_fiber_faultServiceImpl implements Jiangxi_fiber_faultService {

    @Autowired
    private jiangxi_fiber_faultMapper t;

    @Override
    public List<jiangxi_fiber_fault> select(String A_RESOBJID, String Z_RESOBJID) throws IOException {
        return t.select(A_RESOBJID,Z_RESOBJID);
    }
}
