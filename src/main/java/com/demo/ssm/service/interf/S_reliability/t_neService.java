package com.demo.ssm.service.interf.S_reliability;


import com.demo.ssm.po.S_reliability.t_ne;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface t_neService {

    t_ne select(String OBJ_ID) throws IOException;
}
