package com.demo.ssm.service.interf.S_reliability;

import com.demo.ssm.po.S_reliability.t_fuber_fault;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface t_fuber_faultService {

    t_fuber_fault select(String A_RESOBJID, String Z_RESOBJID) throws IOException;
}
