package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.t_fuber_faultMapper;
import com.demo.ssm.po.S_reliability.t_fuber_fault;
import com.demo.ssm.service.interf.S_reliability.t_fuber_faultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class t_fuber_faultServiceImpl implements t_fuber_faultService {

    @Autowired
    private t_fuber_faultMapper  t;

    @Override
    public t_fuber_fault select(String A_RESOBJID, String Z_RESOBJID) throws IOException {
        return t.select(A_RESOBJID,Z_RESOBJID);
    }
}
