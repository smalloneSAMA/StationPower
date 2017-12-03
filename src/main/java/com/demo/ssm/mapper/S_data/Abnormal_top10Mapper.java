package com.demo.ssm.mapper.S_data;


import com.demo.ssm.po.S_data.Abnormal_top10;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface Abnormal_top10Mapper {

       List<Abnormal_top10> selectByPrimaryKey() throws IOException;

       int count() throws IOException;


}
