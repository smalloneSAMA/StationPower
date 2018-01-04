package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_fuber_fault;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface t_fuber_faultMapper {

    t_fuber_fault select(@Param("A_RESOBJID")String A_RESOBJID, @Param("Z_RESOBJID")String Z_RESOBJID)throws IOException;
}
