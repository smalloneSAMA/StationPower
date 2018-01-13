package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.t_weibullMapper;
import com.demo.ssm.po.S_reliability.t_weibull;
import com.demo.ssm.service.interf.S_reliability.t_weibullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class t_weibullServiceImpl implements t_weibullService {

    @Autowired
    private t_weibullMapper t;

    @Override
    public t_weibull select(String id, String province) throws IOException{
        return t.select(id,province);

    }
}
