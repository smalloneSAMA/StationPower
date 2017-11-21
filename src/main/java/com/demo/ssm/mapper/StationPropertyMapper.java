package com.demo.ssm.mapper;


import com.demo.ssm.po.StationProperty;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface StationPropertyMapper {
    List<StationProperty> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
