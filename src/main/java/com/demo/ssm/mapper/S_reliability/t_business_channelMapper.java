package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_business_channel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface t_business_channelMapper {
    List<t_business_channel> selectByPrimaryKey(@Param("buz_id")String buz_id,@Param("province")String province) throws IOException;

    int count(@Param("buz_id")String buz_id,@Param("province")String province) throws IOException;
}
