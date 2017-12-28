package com.demo.ssm.service.interf.S_net;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ProvinceService {

    Boolean setProvince(String province);

    Boolean deleteProvinceData(String province);

}
