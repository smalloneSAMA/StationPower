package com.demo.ssm.mapper;

import com.demo.ssm.po.BusinessRoute;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface BusinessRouteMapper {
    List<BusinessRoute> selectByPrimaryKey() throws IOException;

    int count() throws IOException;
}
