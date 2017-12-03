package com.demo.ssm.mapper.S_data;


import com.demo.ssm.po.S_data.StationType;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface StationTypeMapper {
    List<StationType> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
