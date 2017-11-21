package com.demo.ssm.mapper;

import com.demo.ssm.po.Accurate_top10;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface Accurate_top10Mapper {

    List<Accurate_top10> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
