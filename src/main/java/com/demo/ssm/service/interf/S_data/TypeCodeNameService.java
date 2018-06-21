package com.demo.ssm.service.interf.S_data;

import com.demo.ssm.po.S_data.TypeCodeName;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface TypeCodeNameService {

    TypeCodeName queryName(@Param("code") String code, @Param("type")String type) throws IOException;
}
