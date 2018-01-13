package com.demo.ssm.service.interf.S_reliability;


import com.demo.ssm.po.S_reliability.t_weibull;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public interface t_weibullService {

    t_weibull select(String id,String province) throws IOException;
}
