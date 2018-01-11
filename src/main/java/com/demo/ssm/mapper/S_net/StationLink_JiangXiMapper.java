package com.demo.ssm.mapper.S_net;

import com.demo.ssm.po.S_net.StationLink_JiangXi;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface StationLink_JiangXiMapper {
    List<StationLink_JiangXi> selectByPrimaryKey(String Province) throws IOException;

    List<StationLink_JiangXi> selectByID(@Param("Province")String Province,@Param("obj_id")String obj_id1) throws IOException;

    int count(String Province) throws IOException;

    int countByID(@Param("Province")String Province,@Param("obj_id")String obj_id)throws IOException;

    Double selectBy2ID(@Param("obj_id1")String obj_id1,@Param("obj_id2")String obj_id2,@Param("Province")String Province)throws IOException;

    List<String> selectDistinctObj_1(@Param("Province")String Province);

    List<String> selectDistinctObj_2(@Param("Province")String Province);
}
