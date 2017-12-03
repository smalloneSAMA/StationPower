package com.demo.ssm.controller.S_data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_data.Accurate_top10;
import com.demo.ssm.service.interf.S_data.Accurate_top10Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Accurate_top10")
public class Accurate_top10Controller {
    @Autowired
    private Accurate_top10Service accurate_top10Service;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();


        try{
//            int k=accurate_top10Service.count();
//            int i = 0;
//            while (k>0) {
//                int j = i;
//                while (1>0) {
//                    if(accurate_top10Service.selectByPrimaryKey(j + 1) != null) {
//                        JSONObject jsonObject = new JSONObject();
//                        Accurate_top10 accurate_top10info = accurate_top10Service.selectByPrimaryKey(j + 1);
//                        jsonObject.put("ID", accurate_top10info.getId());
//                        jsonObject.put("StationName", accurate_top10info.getStationName());
//                        jsonObject.put("NumberRecords", accurate_top10info.getNumRecords());
//                        jsonObject.put("NumberForecast", accurate_top10info.getNumForecast());
//                        jsonArray.add(jsonObject);
//                        k--;
//                        break;
//                    }
//                    j++;
//                }
//                i=j+1;
//
//            }
////            System.out.println("result:"+ JSON.toJSONString(jsonArray));
//
//
//            return jsonArray;
            int i=0;
            int k=accurate_top10Service.count();
            List<Accurate_top10> list = accurate_top10Service.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("ID", (list.get(i)).getId());
                jsonObject.put("StationName", (list.get(i)).getStationName());
                jsonObject.put("NumberRecords", (list.get(i)).getNumRecords());
                jsonObject.put("NumberForecast", (list.get(i)).getNumForecast());
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
