package com.demo.ssm.service.interf.S_reliability;

import com.demo.ssm.po.S_reliability.jiangxi_fiber_fault;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface Jiangxi_fiber_faultService {

    List<jiangxi_fiber_fault> select(String A_RESOBJID, String Z_RESOBJID) throws IOException;
}
