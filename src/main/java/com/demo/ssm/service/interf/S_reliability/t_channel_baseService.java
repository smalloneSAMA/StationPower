package com.demo.ssm.service.interf.S_reliability;

import com.demo.ssm.po.S_reliability.t_channel_base;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public interface t_channel_baseService {

    List<t_channel_base> selectByPrimaryKey(String CHANNEL_ID, String province) throws IOException;

    int count(String CHANNEL_ID, String province) throws IOException;

}
