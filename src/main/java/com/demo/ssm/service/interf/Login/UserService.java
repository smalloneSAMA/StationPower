package com.demo.ssm.service.interf.Login;

import com.demo.ssm.po.Login.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

        User userLogin(@Param("username") String username);

        void userRegist(@Param("username") String username, @Param("password") String password);


}
