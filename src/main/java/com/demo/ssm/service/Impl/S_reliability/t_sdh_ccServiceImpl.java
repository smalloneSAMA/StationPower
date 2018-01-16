package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.t_sdh_ccMapper;
import com.demo.ssm.po.S_reliability.t_sdh_cc;
import com.demo.ssm.service.interf.S_reliability.t_sdh_ccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class t_sdh_ccServiceImpl implements t_sdh_ccService {

    @Autowired
    private t_sdh_ccMapper t;

    public List<t_sdh_cc> select1(String A_PTP, String Z_PTP, String province) throws IOException{
        return t.select1(A_PTP, Z_PTP,province);
    }

    public t_sdh_cc select(String A_CTP, String A_PTP, String Z_CTP, String Z_PTP, String province) throws IOException{
        return t.select(A_CTP,A_PTP,Z_CTP,Z_PTP,province);
    }


}
