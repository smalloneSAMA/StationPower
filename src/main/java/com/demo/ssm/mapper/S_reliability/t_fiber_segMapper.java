package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_fiber_seg;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface t_fiber_segMapper {

    t_fiber_seg select(String OBJ_ID) throws IOException;
}
