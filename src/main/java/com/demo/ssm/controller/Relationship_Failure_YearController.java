package com.demo.ssm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.Relationship_Failure_Year;
import com.demo.ssm.service.interf.Abnormal_top10Service;
import com.demo.ssm.service.interf.Relationship_Failure_YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Relationship_Failure_Year")
public class Relationship_Failure_YearController {
    @Autowired
    private Relationship_Failure_YearService relationship_Failure_YearService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();


        try{
//            int k=relationship_Failure_YearService.count();
//            int i = 0;
//            while (k>0) {
//                int j = i;
//
//                while (1>0) {
//                    if(relationship_Failure_YearService.selectByPrimaryKey(j + 1) != null) {
//                        JSONObject jsonObject = new JSONObject();
//                        Relationship_Failure_Year relationship_Failure_Yearinfo = relationship_Failure_YearService.selectByPrimaryKey(j + 1);
//                        jsonObject.put("Company", relationship_Failure_Yearinfo.getCompany());
//                        jsonObject.put("UseTime", relationship_Failure_Yearinfo.getUseTime());
//                        jsonObject.put("FailureRate", relationship_Failure_Yearinfo.getFailureRate());
//                        jsonArray.add(jsonObject);
//                        k--;
//                        break;
//                    }
//                    j++;
//                }
//                i=j+1;
//            }
////            System.out.println("result:"+ JSON.toJSONString(jsonArray));
//
//
//            return jsonArray;
            int i=0;
            int k=relationship_Failure_YearService.count();
            List<Relationship_Failure_Year> list = relationship_Failure_YearService.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("Company", (list.get(i)).getCompany());
                jsonObject.put("UseTime", (list.get(i)).getUseTime());
                jsonObject.put("FailureRate", (list.get(i)).getFailureRate());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;

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
