package com.demo.ssm.service.Impl.S_paint;

import com.demo.ssm.mapper.S_paint.AverageFailureRateMapper;
import com.demo.ssm.po.S_paint.AlarmCause;
import com.demo.ssm.po.S_paint.AlarmCombine;
import com.demo.ssm.po.S_paint.AverageFailureRate;
import com.demo.ssm.po.S_paint.NeNameAlarm;
import com.demo.ssm.service.interf.S_paint.AverageFailureRateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AverageFailureRateServiceImpl implements AverageFailureRateService {

    @Autowired
    private AverageFailureRateMapper averageFailureRateMapper;

    @Override
    public List<AverageFailureRate> selectByPrimaryKey() throws IOException{
        return averageFailureRateMapper.selectByPrimaryKey();
    }

    @Override
    public  int count() throws IOException{
        return averageFailureRateMapper.count();
    }

    @Override
    public List<AlarmCause> findTypeAlarmCause(@Param("producer") String producer) throws IOException{
        return averageFailureRateMapper.findTypeAlarmCause(producer);
    }

    @Override
    public List<AlarmCombine> findAlarmCombine(@Param("producer") String producer,@Param("ne_name")String ne_name,@Param("province")String province) throws IOException{
        return averageFailureRateMapper.findAlarmCombine(producer,ne_name,province);
    }

    @Override
    public List<NeNameAlarm> neNameAlarm(@Param("producer") String producer) throws IOException{
        return averageFailureRateMapper.neNameAlarm(producer);
    }
}
