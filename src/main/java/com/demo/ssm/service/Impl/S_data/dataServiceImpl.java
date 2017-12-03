package com.demo.ssm.service.Impl.S_data;

import com.demo.ssm.mapper.S_data.dataMapper;
import com.demo.ssm.po.S_data.data;
import com.demo.ssm.service.interf.S_data.dataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class dataServiceImpl implements dataService {
    @Autowired
    dataMapper dataS ;

    @Override
    public List<data> channel_typeQ(String Province) {
        return dataS.channel_typeQ(Province);
    }

    @Override
    public List<data> channel_type_null(String Province) {
        return dataS.channel_type_null(Province);
    }

    @Override
    public List<data> channel_rateQ(String Province) {
        return dataS.channel_rateQ(Province);
    }

    @Override
    public List<data> channel_rate_null(String Province) {
        return dataS.channel_rate_null(Province);
    }

    @Override
    public List<data> buz_typeQ(String Province) {
        return dataS.buz_typeQ(Province);
    }

    @Override
    public List<data> buz_type_null(String Province) {
        return dataS.buz_type_null(Province);
    }

    @Override
    public List<data> buz_rateQ(String Province) {
        return dataS.buz_rateQ(Province);
    }

    @Override
    public List<data> buz_rate_null(String Province) {
        return dataS.buz_rate_null(Province);
    }

    @Override
    public List<data> interface_typeQ(String Province) {
        return dataS.interface_typeQ(Province);
    }

    @Override
    public List<data> interface_type_null(String Province) {
        return dataS.interface_type_null(Province);
    }

    @Override
    public Integer channel_type_Count(String Province) throws IOException {
        return dataS.channel_type_Count(Province) ;
    }

    @Override
    public Integer channel_type_null_Count(String Province) throws IOException {
        return dataS.channel_type_null_Count(Province);
    }

    @Override
    public Integer channel_rateQ_Count(String Province) throws IOException {
        return dataS.channel_rateQ_Count(Province);
    }

    @Override
    public Integer channel_rate_null_Count(String Province) throws IOException {
        return dataS.channel_rate_null_Count(Province);
    }

    @Override
    public Integer buz_typeQ_Count(String Province) throws IOException {
        return dataS.buz_typeQ_Count(Province);
    }

    @Override
    public Integer buz_type_null_Count(String Province) throws IOException {
        return dataS.buz_type_null_Count(Province);
    }

    @Override
    public Integer buz_rateQ_Count(String Province) throws IOException {
        return dataS.buz_rateQ_Count(Province);
    }

    @Override
    public Integer buz_rate_null_Count(String Province) throws IOException {
        return dataS.buz_rate_null_Count(Province);
    }

    @Override
    public Integer interface_typeQ_Count(String Province) throws IOException {
        return dataS.interface_typeQ_Count(Province);
    }

    @Override
    public Integer interface_type_null_Count(String Province) throws IOException {
        return dataS.interface_type_null_Count(Province);
    }


}
