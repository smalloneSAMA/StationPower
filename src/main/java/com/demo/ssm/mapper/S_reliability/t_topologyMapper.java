package com.demo.ssm.mapper.S_reliability;


import com.demo.ssm.po.S_reliability.t_topology;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface t_topologyMapper {

    t_topology select(@Param("A_PORT") String A_PORT, @Param("Z_PORT") String Z_PORT) throws IOException;

    int count(@Param("A_PORT") String A_PORT, @Param("Z_PORT") String Z_PORT) throws IOException;
}
