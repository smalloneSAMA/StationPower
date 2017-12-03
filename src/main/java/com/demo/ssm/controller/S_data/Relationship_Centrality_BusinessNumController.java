package com.demo.ssm.controller.S_data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_data.Relationship_Centrality_BusinessNum;
import com.demo.ssm.service.interf.S_data.Relationship_Centrality_BusinessNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Relationship_Centrality_BusinessNum")
public class Relationship_Centrality_BusinessNumController {
    @Autowired
    private Relationship_Centrality_BusinessNumService relationship_Centrality_BusinessNumService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();


        try{
//            int k=relationship_Centrality_BusinessNumService.count();
//            int i = 0;
//            while (k>0) {
//                int j = i;
//                while (1>0) {
//                    if(relationship_Centrality_BusinessNumService.selectByPrimaryKey(j + 1) != null) {
//                        JSONObject jsonObject = new JSONObject();
//                        Relationship_Centrality_BusinessNum relationship_Centrality_BusinessNuminfo = relationship_Centrality_BusinessNumService.selectByPrimaryKey(j + 1);
//                        jsonObject.put("name", relationship_Centrality_BusinessNuminfo.getCentrality());
//                        jsonObject.put("value", relationship_Centrality_BusinessNuminfo.getAverageBusinessNum());
//                        jsonObject.put("xAxis", relationship_Centrality_BusinessNuminfo.getCentrality());
//                        jsonObject.put("yAxis", relationship_Centrality_BusinessNuminfo.getAverageBusinessNum());
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
            int k=relationship_Centrality_BusinessNumService.count();
            List<Relationship_Centrality_BusinessNum> list = relationship_Centrality_BusinessNumService.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("name", (list.get(i)).getCentrality());
                jsonObject.put("value", (list.get(i)).getAverageBusinessNum());
                jsonObject.put("xAxis", (list.get(i)).getCentrality());
                jsonObject.put("yAxis", (list.get(i)).getAverageBusinessNum());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;

        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result","error");
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
