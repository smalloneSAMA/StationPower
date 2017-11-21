package com.demo.ssm.mapper;

import com.demo.ssm.po.User;
import org.springframework.stereotype.Repository;

@Repository//(value="UserMapper")
public interface UserMapper {

	public User findUser(String username);

	public void insertUser(User user);

}
