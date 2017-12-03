package com.demo.ssm.service.interf.S_paint;

import com.demo.ssm.po.S_paint.Province_Check;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface Province_CheckService {

    List<Province_Check> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
