package com.demo.ssm.service.interf.S_reliability;

import com.demo.ssm.po.S_reliability.t_fiber_re;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface t_fiber_reService {

    List<t_fiber_re> select(String province) throws IOException;
}
