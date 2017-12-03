package com.demo.ssm.mapper.S_paint;

import com.demo.ssm.po.S_paint.Province_Check;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface Province_CheckMapper {
    List<Province_Check> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
