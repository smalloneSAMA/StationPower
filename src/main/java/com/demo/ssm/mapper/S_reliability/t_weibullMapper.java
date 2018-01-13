package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_weibull;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface t_weibullMapper {

    t_weibull select(String NE_OBJ_ID) throws IOException;
}
