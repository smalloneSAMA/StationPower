package com.demo.ssm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.StationProperty;
import com.demo.ssm.po.StationProperty_Route;
import com.demo.ssm.service.interf.StationPropertyService;
import com.demo.ssm.service.interf.StationProperty_RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/StationProperty_Route")
public class StationProperty_RouteController {
    @Autowired
    private StationProperty_RouteService stationProperty_RouteService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();


        try{
//            int k=stationProperty_RouteService.count();
//            int i = 0;
//            while (k>0) {
//                int j = i;
//                while (1>0) {
//                    if(stationProperty_RouteService.selectByPrimaryKey(j + 1) != null) {
//                        JSONObject jsonObject = new JSONObject();
//                        StationProperty_Route stationProperty_Routeinfo = stationProperty_RouteService.selectByPrimaryKey(j + 1);
//                        jsonObject.put("Name", stationProperty_Routeinfo.getName());
//                        jsonObject.put("xAxis", stationProperty_Routeinfo.getxAxis());
//                        jsonObject.put("yAxis", stationProperty_Routeinfo.getyAxis());
//                        jsonObject.put("BusinessNum", stationProperty_Routeinfo.getBusinessNum());
//                        jsonObject.put("KuoRong", stationProperty_Routeinfo.getKuoRong());
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
            int k=stationProperty_RouteService.count();
            List<StationProperty_Route> list = stationProperty_RouteService.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("Name", (list.get(i)).getName());
                jsonObject.put("xAxis", (list.get(i)).getxAxis());
                jsonObject.put("yAxis", (list.get(i)).getyAxis());
                jsonObject.put("BusinessNum", (list.get(i)).getBusinessNum());
                jsonObject.put("KuoRong", (list.get(i)).getKuoRong());
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
