package com.demo.ssm.mapper.Login;

import com.demo.ssm.po.Login.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User userLogin(@Param("username") String username);

    void userRegist(@Param("username") String username, @Param("password") String password);

}
