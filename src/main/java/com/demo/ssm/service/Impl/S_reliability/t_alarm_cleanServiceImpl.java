package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.t_alarm_cleanMapper;
import com.demo.ssm.po.S_reliability.t_alarm_clean;
import com.demo.ssm.service.interf.S_reliability.t_alarm_cleanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class t_alarm_cleanServiceImpl implements t_alarm_cleanService {
    @Autowired
    private t_alarm_cleanMapper t;

    @Override
    public List<t_alarm_clean> select(String OBJ_ID, String province) throws IOException {
        return t.select(OBJ_ID,province);
    }
}
