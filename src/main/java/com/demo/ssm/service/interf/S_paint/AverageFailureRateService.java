package com.demo.ssm.service.interf.S_paint;


import com.demo.ssm.po.S_paint.AlarmCause;
import com.demo.ssm.po.S_paint.AlarmCombine;
import com.demo.ssm.po.S_paint.AverageFailureRate;
import com.demo.ssm.po.S_paint.NeNameAlarm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface AverageFailureRateService {

    List<AverageFailureRate> selectByPrimaryKey() throws IOException;

    int count() throws IOException;

    List<AlarmCause> findTypeAlarmCause(@Param("producer") String producer) throws IOException;

    List<AlarmCombine> findAlarmCombine(@Param("producer") String producer,@Param("ne_name")String ne_name,@Param("province")String province) throws IOException;

    List<NeNameAlarm> neNameAlarm(@Param("producer") String producer) throws IOException;
}
