package com.demo.ssm.mapper.S_data;

import com.demo.ssm.po.S_data.Accurate_top10;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface Accurate_top10Mapper {

    List<Accurate_top10> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
