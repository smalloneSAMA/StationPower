package com.demo.ssm.mapper.S_reliability;


import com.demo.ssm.po.S_reliability.t_channel_base;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface t_channel_baseMapper {
    List<t_channel_base> selectByPrimaryKey(String CHANNEL_ID) throws IOException;

    int count(String CHANNEL_ID) throws IOException;

}
