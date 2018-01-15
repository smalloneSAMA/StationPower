package com.demo.ssm.service.Impl.S_net;

import com.demo.ssm.mapper.S_net.LowReliabilityMapper;
import com.demo.ssm.po.S_net.LowReliability;
import com.demo.ssm.service.interf.S_net.LowReliabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LowReliabilityServiceImpl implements LowReliabilityService{
    @Autowired
    private LowReliabilityMapper lowReliabilityMapper;

    @Override
    public List<LowReliability> lowReliabilityByProvince(String province) {
        return lowReliabilityMapper.selectByProvince(province);
    }
}
