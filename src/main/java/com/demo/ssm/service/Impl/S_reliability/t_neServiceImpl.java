package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.t_neMapper;
import com.demo.ssm.po.S_reliability.t_ne;
import com.demo.ssm.service.interf.S_reliability.t_neService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class t_neServiceImpl implements t_neService {

    @Autowired
    private t_neMapper t;

    @Override
    public t_ne select(String OBJ_ID) throws IOException {
        return t.select(OBJ_ID);
    }
}
