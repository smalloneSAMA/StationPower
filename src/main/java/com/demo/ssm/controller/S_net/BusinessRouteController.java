package com.demo.ssm.controller.S_net;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_net.BusinessRoute;
import com.demo.ssm.po.S_net.LowReliability;
import com.demo.ssm.service.interf.S_net.BusinessRouteService;
import com.demo.ssm.service.interf.S_net.LowReliabilityService;
import com.demo.ssm.service.interf.S_net.StationLink_JiangXiService;
import com.demo.ssm.service.interf.S_net.StationPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/BusinessRoute")
public class BusinessRouteController {
    @Autowired
    private BusinessRouteService businessRouteService;
    @Autowired
    private StationLink_JiangXiService stationLink_JiangXiService;
    @Autowired
    private StationPropertyService stationPropertyService;
    @Autowired
    private LowReliabilityService lowReliabilityService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String Province,String buz_id){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();

        try{

            int i=0;
//            int k=businessRouteService.count(Province);
//            查出量站点路由
            List<BusinessRoute> list = businessRouteService.selectByPrimaryKey(Province,buz_id);

//            while (i<k) {
            if(list.size()==0){
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("result", false);
                jsonObject.put("name1", "1");
                jsonObject.put("name2", "2");
                jsonObject.put("FiberOcc", 1);
                jsonArray.add(jsonObject);

            }else {
                String route = (list.get(i)).getRoute();
                route = route.replaceAll("'", "").replaceAll(" ", "");
//                分割路由
                String[] ids = route.split(",");
                Integer len = ids.length;
//                ids[0]="0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00456";
//                ids[1]="0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00469";
//            传入json

                for(int a=0,b=1;b<len;a++,b++){
                    JSONObject jsonObject = new JSONObject() ;
                    jsonObject.put("result", true);
                    jsonObject.put("name1", stationPropertyService.selectByID(Province,ids[a]));
                    jsonObject.put("name2", stationPropertyService.selectByID(Province,ids[b]));
//                    jsonObject.put("result", "success");
                    if(stationLink_JiangXiService.selectBy2ID(ids[a],ids[b],Province)==null){

                        jsonObject.put("FiberOcc", stationLink_JiangXiService.selectBy2ID(ids[b],ids[a],Province));
                    }else {
                        jsonObject.put("FiberOcc", stationLink_JiangXiService.selectBy2ID(ids[a],ids[b],Province));

                    }
                    jsonObject.put("route", 1);
                    jsonObject.put("start", stationPropertyService.selectByID(Province,ids[0]));
                    jsonObject.put("end", stationPropertyService.selectByID(Province,ids[len-1]));
                    jsonArray.add(jsonObject);
                }


                String route_2 = (list.get(i)).getRoute_2();
                if(!"none".equals(route_2)){
                    route_2 = route_2.replaceAll("'", "").replaceAll(" ", "");
                    //分割路由
                    String[] ids_2 = route_2.split(",");
                    Integer len_2 = ids_2.length;

                    for(int a=0,b=1;b<len_2;a++,b++){
                        JSONObject jsonObject = new JSONObject() ;
                        jsonObject.put("result", true);
                        jsonObject.put("name1", stationPropertyService.selectByID(Province,ids_2[a]));
                        jsonObject.put("name2", stationPropertyService.selectByID(Province,ids_2[b]));
                        if(stationLink_JiangXiService.selectBy2ID(ids_2[a],ids_2[b],Province)==null){

                            jsonObject.put("FiberOcc", stationLink_JiangXiService.selectBy2ID(ids_2[b],ids_2[a],Province));
                        }else {
                            jsonObject.put("FiberOcc", stationLink_JiangXiService.selectBy2ID(ids_2[a],ids_2[b],Province));

                        }
                        jsonObject.put("route", 2);
                        jsonArray.add(jsonObject);
                    }
                }
            }


//                i++;
//            }
            return jsonArray;

        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result",false);
            jsonArray.add(jsonObjecterror);
            return jsonArray;
        }

    }

    @RequestMapping("/LowReliability")
    @ResponseBody
    public Map<String, Object> LowReliability(HttpServletRequest request, String Province){
        Map<String, Object> map = new HashMap<>();

        try {

            List<LowReliability> lowReliabilityList = lowReliabilityService.lowReliabilityByProvince(Province);
            map.put("LowReliability",lowReliabilityList);
            map.put("result",true);

        }catch (Exception e){
            map.put("result",false);
        }

        return map;

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
