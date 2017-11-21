package com.demo.ssm.mapper;

import com.demo.ssm.po.StationLink_JiangXi;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface StationLink_JiangXiMapper {
    List<StationLink_JiangXi> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
