package com.demo.ssm.service.Impl;

import com.demo.ssm.mapper.UserMapper;
import com.demo.ssm.po.User;
import com.demo.ssm.service.interf.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserService")
public class UserServiceImpl implements UserService {
//    @Autowired(required = false)
    @Resource
    private UserMapper userMapper;
    public boolean login(String username, String password){
        User user = userMapper.findUser(username);
        if (user != null) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean register(User user){
        userMapper.insertUser(user);
        return true;

    }




}
