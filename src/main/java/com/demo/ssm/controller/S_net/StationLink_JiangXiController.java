package com.demo.ssm.controller.S_net;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_net.StationLink_JiangXi;
import com.demo.ssm.service.interf.S_net.StationLink_JiangXiService;
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
    public JSONArray S_dataQuality(HttpServletRequest request,String Province){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();

        try{
            int i=0;
            int k=stationLink_JiangXiService.count(Province);
            List<StationLink_JiangXi> list = stationLink_JiangXiService.selectByPrimaryKey(Province);
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

    //查询异常对象数据
    @RequestMapping("/S_dataQualityLink")
    @ResponseBody
    public JSONArray S_dataQualityLink(HttpServletRequest request,String Province){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();

        try{
            int i=0;
            int k=stationLink_JiangXiService.count(Province);
            List<StationLink_JiangXi> list = stationLink_JiangXiService.selectByPrimaryKey(Province);
            while (i<k) {
                String name1 = list.get(i).getName1();
                String name2 = list.get(i).getName2();
                if (!name1.contains("35kV") && !name1.contains("110kV")&&!name2.contains("35kV") && !name2.contains("110kV")) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Name1", (list.get(i)).getName1());
                    jsonObject.put("Name2", (list.get(i)).getName2());
                    jsonObject.put("FiberOcc", (list.get(i)).getFiberOcc());
                    jsonArray.add(jsonObject);
                }
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

//    按照ID查询
    @RequestMapping("/selectByID")
    @ResponseBody
    public JSONArray S_net(HttpServletRequest request,String Province,String obj_id){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        try{
            int i=0;
            int k=stationLink_JiangXiService.countByID(Province,obj_id);
            List<StationLink_JiangXi> list = stationLink_JiangXiService.selectByID(Province,obj_id);
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


}
