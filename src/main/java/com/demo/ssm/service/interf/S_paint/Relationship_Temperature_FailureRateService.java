package com.demo.ssm.service.interf.S_paint;

import com.demo.ssm.po.S_paint.Relationship_Temperature_FailureRate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface Relationship_Temperature_FailureRateService {

    List<Relationship_Temperature_FailureRate> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
