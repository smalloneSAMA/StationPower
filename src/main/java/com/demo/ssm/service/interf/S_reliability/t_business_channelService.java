package com.demo.ssm.service.interf.S_reliability;

import com.demo.ssm.po.S_reliability.t_business_channel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface t_business_channelService {
    List<t_business_channel> selectByPrimaryKey(String buz_id) throws IOException;

    int count(String buz_id) throws IOException;
}
