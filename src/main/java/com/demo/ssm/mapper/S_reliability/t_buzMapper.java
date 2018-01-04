package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_buz;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface t_buzMapper {
    List<t_buz> selectByPrimaryKey(t_buz t_buz) throws IOException;

    int count(t_buz t_buz) throws IOException;
}
