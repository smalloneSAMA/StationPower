package com.demo.ssm.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.StationType;
import com.demo.ssm.service.interf.StationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/StationType")
public class StationTypeController {
    @Autowired
    private StationTypeService stationTypeService;

    @RequestMapping("/data")
    public String loadHtml(){
        return "S_dataQuality_0";
    }

    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request, String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror=new JSONObject();

        try{
//            int k=stationTypeService.count();
//            int i = 0;
//            while (k>0) {
//                int j = i;
//                while (1>0) {
//                    if(stationTypeService.selectByPrimaryKey(j + 1) != null) {
//                        JSONObject jsonObject = new JSONObject();
//                        StationType stationTypeInfo = stationTypeService.selectByPrimaryKey(j + 1);
//                        jsonObject.put("name", stationTypeInfo.getType());
//                        jsonObject.put("value", stationTypeInfo.getAverageBusinessNum());
//                        jsonObject.put("xAxis", (stationTypeInfo.getId() - 1));
//                        jsonObject.put("yAxis", stationTypeInfo.getAverageBusinessNum());
//
//                        jsonArray.add(jsonObject);
//                        k--;
//                        break;
//                    }
//                    j++;
//                }
//                i=j+1;
//            }
////            System.out.println("jsonArray:"+ JSON.toJSONString(jsonArray));
//            return jsonArray;
            int i=0;
            int k=stationTypeService.count();
            List<StationType> list = stationTypeService.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("name", (list.get(i)).getType());
                jsonObject.put("value", (list.get(i)).getAverageBusinessNum());
                jsonObject.put("xAxis", (list.get(i)).getId()-1);
                jsonObject.put("yAxis", (list.get(i)).getAverageBusinessNum());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;

        } catch (IOException e) {
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;
        }

    }




}
