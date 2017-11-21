package com.demo.ssm.service.interf;

import com.demo.ssm.po.User;

public interface UserService {
    public boolean login(String username, String password);
    public boolean register(User user);


}
