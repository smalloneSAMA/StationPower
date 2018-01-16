package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.jiangxi_t_buz_re;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface jiangxi_t_buz_reMapper {

    jiangxi_t_buz_re select(@Param("OBJ_ID") String OBJ_ID, @Param("province") String province) throws IOException;

    List<jiangxi_t_buz_re> select1(@Param("BUZ_TYPE") String BUZ_TYPE, @Param("province") String province) throws IOException;
}
