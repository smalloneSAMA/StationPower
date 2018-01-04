package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_ne;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface t_neMapper {

    t_ne select(String OBJ_ID) throws IOException;
}
