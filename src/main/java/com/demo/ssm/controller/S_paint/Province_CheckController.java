package com.demo.ssm.controller.S_paint;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_paint.Province_Check;
import com.demo.ssm.service.interf.S_paint.Province_CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Province_Check")
public class Province_CheckController {
    @Autowired
    private Province_CheckService province_CheckService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();


        try{
//            int k=province_CheckService.count();
//            int i = 0;
//            while (k>0) {
//                int j = i;
//                while (1>0) {
//                    if(province_CheckService.selectByPrimaryKey(j + 1) != null) {
//                        JSONObject jsonObject = new JSONObject();
//                        Province_Check province_Checkinfo = province_CheckService.selectByPrimaryKey(j + 1);
//                        jsonObject.put("Province", province_Checkinfo.getProvince());
//                        jsonObject.put("AverageError", province_Checkinfo.getAverageError());
//                        jsonObject.put("Strobe12hours", province_Checkinfo.getStrobe12hours());
//                        jsonObject.put("Strobe24hours", province_Checkinfo.getStrobe24hours());
//                        jsonObject.put("Strobe48hours", province_Checkinfo.getStrobe48hours());
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
            int k=province_CheckService.count();
            List<Province_Check> list = province_CheckService.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("Province", (list.get(i)).getProvince());
                jsonObject.put("AverageError", (list.get(i)).getAverageError());
                jsonObject.put("Strobe12hours", (list.get(i)).getStrobe12hours());
                jsonObject.put("Strobe24hours", (list.get(i)).getStrobe24hours());
                jsonObject.put("Strobe48hours", (list.get(i)).getStrobe48hours());
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
