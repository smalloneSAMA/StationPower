package com.demo.ssm.mapper.S_data;

import com.demo.ssm.po.S_data.TypeCodeName;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.io.IOException;


@Repository
public interface TypeCodeNameMapper {

       TypeCodeName queryName(@Param("code") String code,@Param("type") String type) throws IOException;


}
