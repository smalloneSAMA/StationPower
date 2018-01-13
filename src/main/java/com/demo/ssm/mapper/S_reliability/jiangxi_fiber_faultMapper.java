package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.jiangxi_fiber_fault;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface jiangxi_fiber_faultMapper {

    List<jiangxi_fiber_fault> select(@Param("A_RESOBJID") String A_RESOBJID, @Param("Z_RESOBJID") String Z_RESOBJID)throws IOException;
}

