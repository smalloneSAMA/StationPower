package com.demo.ssm.service.interf.S_data;

import com.demo.ssm.po.S_data.Accurate_top10;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface Accurate_top10Service {

    List<Accurate_top10> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
