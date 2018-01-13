package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_channel_base;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface t_channel_baseMapper {
    List<t_channel_base> selectByPrimaryKey(@Param("CHANNEL_ID") String CHANNEL_ID, @Param("province") String province) throws IOException;

    int count(@Param("CHANNEL_ID") String CHANNEL_ID, @Param("province") String province) throws IOException;

}
