package com.demo.ssm.controller.Login;

import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.Login.User;
import com.demo.ssm.service.interf.Login.UserService;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public JSONObject userLogin(String username,String password){
//        String username = "123456@123.com";
//        String password = "123456";
        JSONObject jsonObject = new JSONObject() ;
        try {
            User user = userService.userLogin(username);
            if (user.getPassword().equals(password))
                jsonObject.put("result", true);
            else
                jsonObject.put("result", false);
            return jsonObject;
        }catch (Exception e) {
            jsonObject.put("result",false);
            return jsonObject;
        }
    }

    @RequestMapping("/regist")
    @ResponseBody
    public JSONObject userRegist(String username,String password){
//        String username = "123456123@123.com";
//        String password = "123456";
        JSONObject jsonObject = new JSONObject() ;
        try {
            userService.userRegist(username,password);
            User user = userService.userLogin(username);
            if (user.getPassword().equals(password))
                jsonObject.put("result", true);
            else
                jsonObject.put("result", false);
            return jsonObject;
        }catch (Exception e) {
            jsonObject.put("result",false);
            return jsonObject;
        }
    }

}
