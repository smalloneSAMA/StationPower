package com.demo.ssm.service.interf.S_net;

import com.demo.ssm.po.S_net.StationProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface StationPropertyService {

    List<StationProperty> selectByPrimaryKey(String Province) throws IOException;

    int count(String Province) throws IOException;

    String selectByID(@Param("Province")String Province, @Param("obj_id")String obj_id);

}
