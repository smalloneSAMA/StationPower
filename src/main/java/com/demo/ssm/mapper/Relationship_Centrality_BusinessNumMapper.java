package com.demo.ssm.mapper;

import com.demo.ssm.po.Province_Check;
import com.demo.ssm.po.Relationship_Centrality_BusinessNum;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface Relationship_Centrality_BusinessNumMapper {
    List<Relationship_Centrality_BusinessNum> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
