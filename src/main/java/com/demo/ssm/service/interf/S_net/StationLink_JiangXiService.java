package com.demo.ssm.service.interf.S_net;

import com.demo.ssm.po.S_net.StationLink_JiangXi;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface StationLink_JiangXiService {

    List<StationLink_JiangXi> selectByPrimaryKey(String Province) throws IOException;

    int count(String Province) throws IOException;

    Double selectByID(@Param("obj_id1")String obj_id1,@Param("obj_id2")String obj_id2,@Param("Province")String Province) throws IOException;

    
}
