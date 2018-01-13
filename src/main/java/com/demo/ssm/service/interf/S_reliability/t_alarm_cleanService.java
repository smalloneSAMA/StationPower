package com.demo.ssm.service.interf.S_reliability;


import com.demo.ssm.po.S_reliability.t_alarm_clean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public interface t_alarm_cleanService {

    List<t_alarm_clean> select(String OBJ_ID,String province) throws IOException;
}
