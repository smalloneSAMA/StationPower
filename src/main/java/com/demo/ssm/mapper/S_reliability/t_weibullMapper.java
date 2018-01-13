package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_weibull;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface t_weibullMapper {

    t_weibull select(@Param("NE_OBJ_ID")String NE_OBJ_ID,@Param("province")String province) throws IOException;
}
