package com.demo.ssm.service.interf;

import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.Relationship_Centrality_BusinessNum;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface Relationship_Centrality_BusinessNumService {

    List<Relationship_Centrality_BusinessNum> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
