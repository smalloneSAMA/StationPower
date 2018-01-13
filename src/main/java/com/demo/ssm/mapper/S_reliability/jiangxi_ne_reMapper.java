package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.jiangxi_ne_re;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface jiangxi_ne_reMapper {

    jiangxi_ne_re select(String OBJ_ID) throws IOException;
}
