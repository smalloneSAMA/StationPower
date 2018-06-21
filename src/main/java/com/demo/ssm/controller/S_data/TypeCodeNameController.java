package com.demo.ssm.controller.S_data;

import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_data.TypeCodeName;
import com.demo.ssm.service.interf.S_data.TypeCodeNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/TCN")
public class TypeCodeNameController {
    @Autowired
    TypeCodeNameService typeCodeNameService;

    @RequestMapping("/select")
    @ResponseBody
    public JSONObject getName(HttpServletRequest request,String code,String type){
        JSONObject jsonObject = new JSONObject();
//        String code = "5";
//        String type = "RATE";

        TypeCodeName typeCodeName;
        try {
            typeCodeName = typeCodeNameService.queryName(code,type);
            jsonObject.put("type",typeCodeName.getType());
            jsonObject.put("code",typeCodeName.getCode());
            jsonObject.put("name",typeCodeName.getName());
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("result","error");
        }

        return jsonObject;

    }

}
