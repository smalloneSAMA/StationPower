package com.demo.ssm.controller.S_net;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_net.BusinessRoute;
import com.demo.ssm.service.interf.S_net.BusinessRouteService;
import com.demo.ssm.service.interf.S_net.StationLink_JiangXiService;
import com.demo.ssm.service.interf.S_net.StationPropertyService;
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
    @Autowired
    private StationLink_JiangXiService stationLink_JiangXiService;
    @Autowired
    private StationPropertyService stationPropertyService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String Province,String buz_id){
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
//            int k=businessRouteService.count(Province);
//            查出量站点路由
            List<BusinessRoute> list = businessRouteService.selectByPrimaryKey(Province,buz_id);

//            while (i<k) {
                String route = (list.get(i)).getRoute();
//                分割路由
                String []ids = route.split(",");
                int len= ids.length;
//                ids[0]="0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00456";
//                ids[1]="0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00469";
//            传入json
                for(int a=0,b=1;b<len;a++,b++){
                    JSONObject jsonObject = new JSONObject() ;
                    jsonObject.put("name1", stationPropertyService.selectByID(Province,ids[a]));
                    jsonObject.put("name2", stationPropertyService.selectByID(Province,ids[b]));
                    if(stationLink_JiangXiService.selectByID(ids[a],ids[b],Province)==null){

                        jsonObject.put("FiberOcc", stationLink_JiangXiService.selectByID(ids[b],ids[a],Province));
                    }else {
                        jsonObject.put("FiberOcc", stationLink_JiangXiService.selectByID(ids[a],ids[b],Province));

                    }
                    jsonArray.add(jsonObject);
                }

//                i++;
//            }
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
