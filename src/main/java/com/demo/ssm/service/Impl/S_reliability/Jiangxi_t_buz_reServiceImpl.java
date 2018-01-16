package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.jiangxi_t_buz_reMapper;
import com.demo.ssm.po.S_reliability.jiangxi_t_buz_re;
import com.demo.ssm.service.interf.S_reliability.Jiangxi_t_buz_reService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Jiangxi_t_buz_reServiceImpl implements Jiangxi_t_buz_reService {

    @Autowired
    private jiangxi_t_buz_reMapper j;

    @Override
    public jiangxi_t_buz_re select(String OBJ_ID, String province) throws IOException {
        return j.select(OBJ_ID,province);
    }

    @Override
    public List<jiangxi_t_buz_re> select1(String BUZ_TYPE, String province) throws IOException {
        return j.select1(BUZ_TYPE,province);
    }
}
