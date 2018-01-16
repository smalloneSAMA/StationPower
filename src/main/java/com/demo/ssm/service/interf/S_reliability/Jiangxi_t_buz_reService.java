package com.demo.ssm.service.interf.S_reliability;

import com.demo.ssm.po.S_reliability.jiangxi_t_buz_re;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface Jiangxi_t_buz_reService {

    jiangxi_t_buz_re select(String OBJ_ID, String province) throws IOException;


    List<jiangxi_t_buz_re> select1(String BUZ_TYPE, String province) throws IOException;
}
