package com.demo.ssm.mapper.S_net;

import com.demo.ssm.po.S_net.LowReliability;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LowReliabilityMapper {

    List<LowReliability> selectByProvince(@Param("province") String province);
}
