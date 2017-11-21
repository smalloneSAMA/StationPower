package com.demo.ssm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.Abnormal_top10;
import com.demo.ssm.po.StationLink_JiangXi;
import com.demo.ssm.service.interf.Abnormal_top10Service;
import com.demo.ssm.service.interf.StationLink_JiangXiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/StationLink_JiangXi")
public class StationLink_JiangXiController {
    @Autowired
    private StationLink_JiangXiService stationLink_JiangXiService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();


        try{
//            int k=stationLink_JiangXiService.count();
//            int i = 0;
//            while (k>0) {
//                int j = i;
//                while (1>0) {
//                    if(stationLink_JiangXiService.selectByPrimaryKey(j + 1) != null) {
//                        JSONObject jsonObject = new JSONObject();
//                        StationLink_JiangXi stationLink_JiangXiinfo = stationLink_JiangXiService.selectByPrimaryKey(j + 1);
//                        jsonObject.put("Name1", stationLink_JiangXiinfo.getName1());
//                        jsonObject.put("Name2", stationLink_JiangXiinfo.getName2());
//                        jsonObject.put("FiberOcc", stationLink_JiangXiinfo.getFiberOcc());
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
            int k=stationLink_JiangXiService.count();
            List<StationLink_JiangXi> list = stationLink_JiangXiService.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("Name1", (list.get(i)).getName1());
                jsonObject.put("Name2", (list.get(i)).getName2());
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
