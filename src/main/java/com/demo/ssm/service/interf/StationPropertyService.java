package com.demo.ssm.service.interf;

import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.StationProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface StationPropertyService {

    List<StationProperty> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
