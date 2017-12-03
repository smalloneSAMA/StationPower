package com.demo.ssm.service.interf.S_paint;


import com.demo.ssm.po.S_paint.AverageFailureRate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface AverageFailureRateService {

    List<AverageFailureRate> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
