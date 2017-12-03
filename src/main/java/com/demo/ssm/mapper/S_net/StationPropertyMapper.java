package com.demo.ssm.mapper.S_net;


import com.demo.ssm.po.S_net.StationProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface StationPropertyMapper {
    List<StationProperty> selectByPrimaryKey(String Province) throws IOException;

    int count(String Proince) throws IOException;

    String selectByID(@Param("Province")String Province,@Param("obj_id")String obj_id);
}
