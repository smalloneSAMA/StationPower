package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_alarm_clean;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface t_alarm_cleanMapper {

    List<t_alarm_clean> select(String NE_OBJ_ID) throws IOException;
}
