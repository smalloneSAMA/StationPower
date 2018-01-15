package com.demo.ssm.service.interf.S_net;

import com.demo.ssm.po.S_net.LowReliability;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LowReliabilityService {

    List<LowReliability> lowReliabilityByProvince(String province);
}
