package com.demo.ssm.service.Impl.S_reliability;

import com.demo.ssm.mapper.S_reliability.t_buzMapper;
import com.demo.ssm.po.S_reliability.t_buz;
import com.demo.ssm.service.interf.S_reliability.t_buzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class t_buzServiceImpl implements t_buzService {
    @Autowired
    private t_buzMapper t;

    @Override
    public List<t_buz> selectByPrimaryKey(t_buz t_buz) throws IOException {
        List<t_buz> list=  t.selectByPrimaryKey(t_buz);
        return list;
    }

    @Override
    public int count(t_buz t_buz) throws IOException {
        int i = t.count(t_buz);
        return i;
    }
}
