package com.demo.ssm.service.interf.S_reliability;

import com.demo.ssm.po.S_reliability.jiangxi_ne_re;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface jiangxi_ne_reService {

    jiangxi_ne_re select(String OBJ_ID) throws IOException;
}
