package com.demo.ssm.service.Impl.S_data;

import com.demo.ssm.mapper.S_data.TypeCodeNameMapper;
import com.demo.ssm.po.S_data.TypeCodeName;
import com.demo.ssm.service.interf.S_data.TypeCodeNameService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TypeCodeNameServiceImpl implements TypeCodeNameService{
    @Autowired
    TypeCodeNameMapper typeCodeNameMapper;
    @Override
    public TypeCodeName queryName(@Param("code") String code,@Param("type") String type) throws IOException {
        return typeCodeNameMapper.queryName(code,type);
    }
}
