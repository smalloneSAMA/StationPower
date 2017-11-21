package com.demo.ssm.mapper;


import com.demo.ssm.po.StationType;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface StationTypeMapper {
    List<StationType> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
