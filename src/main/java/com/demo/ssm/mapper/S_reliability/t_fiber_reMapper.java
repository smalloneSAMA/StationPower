package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_fiber_re;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface t_fiber_reMapper {

    List<t_fiber_re> select(String province) throws IOException;
}
