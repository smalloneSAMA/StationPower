package com.demo.ssm.service.Impl.Login;


import com.demo.ssm.mapper.Login.UserMapper;
import com.demo.ssm.po.Login.User;
import com.demo.ssm.service.interf.Login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(String username) {
        return userMapper.userLogin(username);
    }

    @Override
    public void userRegist(String username, String password) {
        userMapper.userRegist(username,password);
    }
}
