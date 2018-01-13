package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.jiangxi_ne_reMapper;
import com.demo.ssm.po.S_reliability.jiangxi_ne_re;
import com.demo.ssm.service.interf.S_reliability.jiangxi_ne_reService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class jiangxi_ne_reServiceImpl implements jiangxi_ne_reService {

    @Autowired
    private jiangxi_ne_reMapper j;

    @Override
    public jiangxi_ne_re select(String OBJ_ID) throws IOException {
        return j.select(OBJ_ID);
    }
}
