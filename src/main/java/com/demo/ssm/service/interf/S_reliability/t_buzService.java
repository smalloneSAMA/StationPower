package com.demo.ssm.service.interf.S_reliability;

import com.demo.ssm.po.S_reliability.t_buz;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface t_buzService {
    List<t_buz> selectByPrimaryKey(t_buz t_buz) throws IOException;

    int count(t_buz t_buz) throws IOException;
}
