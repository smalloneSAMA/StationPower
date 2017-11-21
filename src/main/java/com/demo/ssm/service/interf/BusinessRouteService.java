package com.demo.ssm.service.interf;

import com.demo.ssm.po.BusinessRoute;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface BusinessRouteService {

    List<BusinessRoute> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
