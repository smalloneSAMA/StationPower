package com.demo.ssm.service.interf.S_reliability;

import com.demo.ssm.po.S_reliability.t_sdh_cc;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface t_sdh_ccService {

    List<t_sdh_cc> select1(String A_PTP, String Z_PTP, String province) throws IOException;

    t_sdh_cc select(String A_CTP, String A_PTP,
                    String Z_CTP, String Z_PTP, String province) throws IOException;


}
