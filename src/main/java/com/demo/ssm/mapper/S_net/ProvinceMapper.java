package com.demo.ssm.mapper.S_net;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface ProvinceMapper {


    int setCurrentProvince(@Param("province") String province);

    int deleteRoute(@Param("province") String province);

    int deleteLink(@Param("province") String province);

    int deleteProperty(@Param("province") String province);

}
