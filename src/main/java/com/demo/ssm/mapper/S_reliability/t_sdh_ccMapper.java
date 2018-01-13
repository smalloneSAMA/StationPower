package com.demo.ssm.mapper.S_reliability;

import com.demo.ssm.po.S_reliability.t_sdh_cc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface t_sdh_ccMapper {

    List<t_sdh_cc> select1(@Param("A_PTP")String A_PTP, @Param("Z_PTP")String Z_PTP) throws IOException;

    t_sdh_cc select(@Param("A_CTP")String A_CTP, @Param("A_PTP")String A_PTP,
                    @Param("Z_CTP")String Z_CTP, @Param("Z_PTP")String Z_PTP) throws IOException;

    int count(@Param("A_CTP")String A_CTP, @Param("A_PTP")String A_PTP,
              @Param("Z_CTP")String Z_CTP, @Param("Z_PTP")String Z_PTP) throws IOException;
}
