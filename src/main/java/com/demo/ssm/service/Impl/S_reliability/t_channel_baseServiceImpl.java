package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.t_channel_baseMapper;
import com.demo.ssm.po.S_reliability.t_channel_base;
import com.demo.ssm.service.interf.S_reliability.t_channel_baseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class t_channel_baseServiceImpl implements t_channel_baseService {
    @Autowired
    private t_channel_baseMapper t ;

    @Override
    public List<t_channel_base> selectByPrimaryKey(String CHANNEL_ID, String province) throws IOException {
        return t.selectByPrimaryKey(CHANNEL_ID,province);
    }

    @Override
    public int count(String CHANNEL_ID,String province) throws IOException {
        return t.count(CHANNEL_ID,province);
    }
}
