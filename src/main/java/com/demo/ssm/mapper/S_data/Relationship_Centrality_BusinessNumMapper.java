package com.demo.ssm.mapper.S_data;

import com.demo.ssm.po.S_data.Relationship_Centrality_BusinessNum;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface Relationship_Centrality_BusinessNumMapper {
    List<Relationship_Centrality_BusinessNum> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
