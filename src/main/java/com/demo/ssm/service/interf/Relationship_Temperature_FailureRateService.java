package com.demo.ssm.service.interf;

import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.Relationship_Temperature_FailureRate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface Relationship_Temperature_FailureRateService {

    List<Relationship_Temperature_FailureRate> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
