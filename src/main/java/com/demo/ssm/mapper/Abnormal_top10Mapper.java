package com.demo.ssm.mapper;


import com.demo.ssm.po.Abnormal_top10;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface Abnormal_top10Mapper {

       List<Abnormal_top10> selectByPrimaryKey() throws IOException;

       int count() throws IOException;


}
