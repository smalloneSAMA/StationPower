package com.demo.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.User;
import com.demo.ssm.service.interf.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
//@RequestMapping("/user")用于标定访问时对url位置
@RequestMapping("/user")
//在默认情况下spring-mvc的实例都是单例模式,所以使用scope域将其注解为每次都创建一个新的实例
@Scope("prototype")
public class logincontroller {
    //自动注入业务层的userService类
//    @Autowired
    @Resource
    private UserService userService;
    JSONObject jsonObject = new JSONObject();


    //login业务的访问位置为/user/login
    @RequestMapping("/loginAction")
    @ResponseBody
    public JSONObject login(User user) {
        //调用login方法来验证是否是注册用户
        boolean loginType = userService.login(user.getUsername(),user.getPassword());
        if(loginType){
            jsonObject.put("result",true);
        }
        else jsonObject.put("result",false);

        return jsonObject;
//            //跳转到index.html页面

        /*boolean loginType = userService.login(user.getUsername(),user.getPassword());
        if(loginType){
            System.out.println("user");
            return "redirect:/view/index.html";
        }else{
        //跳转到login.html页面
            return "redirect:/view/login.html";
        }*/
    }

    @RequestMapping("/register")
    @ResponseBody
    public JSONObject register(User user) {
        boolean registerType= userService.register(user);
        if(registerType){
            jsonObject.put("result",true);
        }
        else jsonObject.put("result",false);

        return jsonObject;
    }

}