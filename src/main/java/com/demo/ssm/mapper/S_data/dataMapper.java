package com.demo.ssm.mapper.S_data;

import com.demo.ssm.po.S_data.data;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
@Repository
public interface dataMapper {

    Integer Count_pin_BT(Double prob);

    Integer Count_pin_BR(Double prob);

    Integer Count_pin_CT(Double prob);

    Integer Count_pin_CR(Double prob);

    Integer Count_pin_IT(Double prob);





    List<data> channel_typeQ(String Province);

    List<data> channel_type_null(String Province);

    List<data> channel_rateQ(String Province);

    List<data> channel_rate_null(String Province);

    List<data> buz_typeQ(String Province);

    List<data> buz_type_null(String Province);

    List<data> buz_rateQ(String Province);

    List<data> buz_rate_null(String Province);

    List<data> interface_typeQ(String Province);

    List<data> interface_type_null(String Province);

    Integer channel_type_Count(String Province) throws IOException;

    Integer channel_type_null_Count(String Province) throws IOException;

    Integer channel_rateQ_Count(String Province) throws IOException;

    Integer channel_rate_null_Count(String Province) throws IOException;

    Integer buz_typeQ_Count(String Province) throws IOException;

    Integer buz_type_null_Count(String Province) throws IOException;

    Integer buz_rateQ_Count(String Province) throws IOException;

    Integer buz_rate_null_Count(String Province) throws IOException;

    Integer interface_typeQ_Count(String Province) throws IOException;

    Integer interface_type_null_Count(String Province) throws IOException;






}
