package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.t_fiber_reMapper;
import com.demo.ssm.po.S_reliability.t_fiber_re;
import com.demo.ssm.service.interf.S_reliability.t_fiber_reService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class t_fiber_reServiceImpl implements t_fiber_reService {

    @Autowired
    private t_fiber_reMapper t;

    @Override
    public  List<t_fiber_re> select(String province) throws IOException{
        return t.select(province);
    }
}
