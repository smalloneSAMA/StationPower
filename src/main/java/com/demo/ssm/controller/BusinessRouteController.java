package com.demo.ssm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.BusinessRoute;
import com.demo.ssm.service.interf.Abnormal_top10Service;
import com.demo.ssm.service.interf.BusinessRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/BusinessRoute")
public class BusinessRouteController {
    @Autowired
    private BusinessRouteService businessRouteService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();


        try{
//            int k=businessRouteService.count();
//            int i = 0;
//            while (k>0) {
//                int j = i;
//
//                while (1>0) {
//                    if(businessRouteService.selectByPrimaryKey(j + 1) != null) {
//                        JSONObject jsonObject = new JSONObject();
//                        BusinessRoute businessRouteinfo = businessRouteService.selectByPrimaryKey(j + 1);
//                        jsonObject.put("StationName1", businessRouteinfo.getStationName1());
//                        jsonObject.put("StationName2", businessRouteinfo.getStationName2());
//                        jsonObject.put("FiberOcc", businessRouteinfo.getFiberOcc());
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
            int k=businessRouteService.count();
            List<BusinessRoute> list = businessRouteService.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("StationName1", (list.get(i)).getStationName1());
                jsonObject.put("StationName2", (list.get(i)).getStationName2());
                jsonObject.put("FiberOcc", (list.get(i)).getFiberOcc());
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
