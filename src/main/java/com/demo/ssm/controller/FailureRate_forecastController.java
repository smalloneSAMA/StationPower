package com.demo.ssm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.FailureRate_forecast;
import com.demo.ssm.service.interf.Abnormal_top10Service;
import com.demo.ssm.service.interf.FailureRate_forecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/FailureRate_forecast")
public class FailureRate_forecastController {
    @Autowired
    private FailureRate_forecastService failureRate_forecastService;
    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();



        try{
            int i=0;
            int k=failureRate_forecastService.count();
            List<FailureRate_forecast> list = failureRate_forecastService.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("UseTime", (list.get(i)).getUseTime());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;




//            System.out.println("result:"+ JSON.toJSONString(jsonArray));


//            return jsonArray;

        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }

    }






    /*public ModelAndView S_dataQuality()throws Exception{
        //调用servic查找数据库
        Abnormal_top10 abnormal_top10 = abnormal_top10Service.selectByPrimaryKey(null);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("abnormal_top10",abnormal_top10);

        modelAndView.setViewName("S_dataQuality");

        return modelAndView;

    }*/


}
