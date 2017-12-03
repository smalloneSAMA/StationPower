package com.demo.ssm.controller.S_paint;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_paint.Relationship_Temperature_FailureRate;
import com.demo.ssm.service.interf.S_paint.Relationship_Temperature_FailureRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Relationship_Temperature_FailureRate")
public class Relationship_Temperature_FailureRateController {
    @Autowired
    private Relationship_Temperature_FailureRateService relationship_Temperature_FailureRateService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();


        try{
//            int k=relationship_Temperature_FailureRateService.count();
//            int i = 0;
//            while (k>0) {
//                int j = i;
//                while (1>0) {
//                    if(relationship_Temperature_FailureRateService.selectByPrimaryKey(j + 1) != null) {
//                        JSONObject jsonObject = new JSONObject();
//                        Relationship_Temperature_FailureRate relationship_Temperature_FailureRateinfo = relationship_Temperature_FailureRateService.selectByPrimaryKey(j + 1);
//                        jsonObject.put("Temperature", relationship_Temperature_FailureRateinfo.getTemperature());
//                        jsonObject.put("FailureRate", relationship_Temperature_FailureRateinfo.getFailureRate());
//
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
            int k=relationship_Temperature_FailureRateService.count();
            List<Relationship_Temperature_FailureRate> list = relationship_Temperature_FailureRateService.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("Temperature", (list.get(i)).getTemperature());
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

    //排序









    /*public ModelAndView S_dataQuality()throws Exception{
        //调用servic查找数据库
        Abnormal_top10 abnormal_top10 = abnormal_top10Service.selectByPrimaryKey(null);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("abnormal_top10",abnormal_top10);

        modelAndView.setViewName("S_dataQuality");

        return modelAndView;

    }*/


}
