package com.demo.ssm.service.interf.S_paint;

import com.demo.ssm.po.S_paint.Relationship_Failure_Year;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface Relationship_Failure_YearService {

    List<Relationship_Failure_Year> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
