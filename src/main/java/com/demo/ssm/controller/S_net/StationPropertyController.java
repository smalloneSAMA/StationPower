package com.demo.ssm.controller.S_net;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_net.StationProperty;
import com.demo.ssm.service.interf.S_net.StationPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/StationProperty")
public class StationPropertyController {
    @Autowired
    private StationPropertyService stationPropertyService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String Province){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();



        try{
//            int k=stationPropertyService.count();
//            int i = 0;
//            while (k>0) {
//                int j = i;
//
//                while (1>0) {
//                    if(stationPropertyService.selectByPrimaryKey(j + 1) != null) {
//                        JSONObject jsonObject = new JSONObject();
//                        StationProperty stationPropertyinfo = stationPropertyService.selectByPrimaryKey(j + 1);
//                        jsonObject.put("Name", stationPropertyinfo.getName());
//                        jsonObject.put("xAxis", stationPropertyinfo.getxAxis());
//                        jsonObject.put("yAxis", stationPropertyinfo.getyAxis());
//                        jsonObject.put("BusinessNum", stationPropertyinfo.getBusinessNum());
//                        jsonObject.put("KuoRong", stationPropertyinfo.getKuoRong());
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
            int k=stationPropertyService.count(Province);
            List<StationProperty> list = stationPropertyService.selectByPrimaryKey(Province);
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
//                jsonObject.put("Province",(list.get(i)).getProvince());
                jsonObject.put("Name", (list.get(i)).getName());
                jsonObject.put("xAxis", (list.get(i)).getXaxis());
                jsonObject.put("yAxis", (list.get(i)).getYaxis());
                jsonObject.put("BusinessNum", (list.get(i)).getBusinessNum());
                jsonObject.put("KuoRong", (list.get(i)).getKuorong());
                jsonObject.put("OBJ_ID", (list.get(i)).getObj_id());
                jsonObject.put("portOcc", (list.get(i)).getPortOcc());
                jsonObject.put("buzNumRate", (list.get(i)).getBuzNumRate());
                jsonObject.put("increaseRate", (list.get(i)).getIncreaseRate());
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
