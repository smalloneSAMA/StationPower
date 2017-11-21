package com.demo.ssm.service.interf;


import com.demo.ssm.po.StationType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface StationTypeService {

    List<StationType> selectByPrimaryKey() throws IOException;

    int count() throws IOException;

}
