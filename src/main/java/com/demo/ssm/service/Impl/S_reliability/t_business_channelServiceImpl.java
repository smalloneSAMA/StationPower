package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.t_business_channelMapper;
import com.demo.ssm.po.S_reliability.t_business_channel;
import com.demo.ssm.service.interf.S_reliability.t_business_channelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class t_business_channelServiceImpl implements t_business_channelService {
    @Autowired
    private t_business_channelMapper t;

    @Override
    public List<t_business_channel> selectByPrimaryKey(String buz_id,String province) throws IOException {
        return t.selectByPrimaryKey(buz_id,province);
    }

    @Override
    public int count(String buz_id,String province) throws IOException {
        int i = t.count(buz_id,province);
        return i;
    }
}
