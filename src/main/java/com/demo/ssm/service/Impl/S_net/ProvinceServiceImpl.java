package com.demo.ssm.service.Impl.S_net;

import com.demo.ssm.mapper.S_net.ProvinceMapper;
import com.demo.ssm.service.interf.S_net.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public Boolean setProvince(String province){
        int ins = provinceMapper.setCurrentProvince(province);
        if(ins > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteProvinceData(String province) {
        int i = provinceMapper.deleteLink(province);
        int j = provinceMapper.deleteProperty(province);
        int k = provinceMapper.deleteRoute(province);
        if(i > 0 && k > 0 && j > 0) {
            return true;
        }
        return false;
    }
}
