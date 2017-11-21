package com.demo.ssm.mapper;

import com.demo.ssm.po.Relationship_Failure_Year;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface Relationship_Failure_YearMapper {

    List<Relationship_Failure_Year> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
