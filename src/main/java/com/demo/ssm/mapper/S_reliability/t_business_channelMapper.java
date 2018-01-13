package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_business_channel;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface t_business_channelMapper {
    List<t_business_channel> selectByPrimaryKey(String buz_id) throws IOException;

    int count(String buz_id) throws IOException;
}
