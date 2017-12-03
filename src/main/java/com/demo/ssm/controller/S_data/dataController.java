package com.demo.ssm.controller.S_data;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_data.data;
import com.demo.ssm.service.interf.S_data.dataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;



@Controller
@RequestMapping("/S_data")
public class dataController {

    @Autowired
    dataService dataService;

    //查询异常channel_type
    @RequestMapping("/channel_type")
    @ResponseBody
    public JSONArray channel_type(HttpServletRequest request, String Province){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.channel_type_Count(Province);
            List<data> list = dataService.channel_typeQ(Province);
            if(s<10){
                k=s;
            }
            while (i < k) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", (list.get(i)).getObj_id());
                jsonObject.put("StationName", (list.get(i)).getVal());
                jsonObject.put("NumberRecords", (list.get(i)).getPredict());
                jsonObject.put("NumberForecast", (list.get(i)).getProbablity());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;
        }
        catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }
    }
    //查询异常channel_type_null
    @RequestMapping("/channel_type_null")
    @ResponseBody
    public JSONArray channel_type_null(HttpServletRequest request, String Province){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.channel_type_null_Count(Province);
            List<data> list = dataService.channel_type_null(Province);
            if(s<10){
                k=s;
            }
            while (i < k) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", (list.get(i)).getObj_id());
                jsonObject.put("StationName", (list.get(i)).getPredict());
                jsonObject.put("NumberForecast", (list.get(i)).getProbablity());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;
        }
        catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }
    }



    //查询异常channel_rate
    @RequestMapping("/channel_rate")
    @ResponseBody
    public JSONArray channel_rate(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.channel_rateQ_Count(Province);
            List<data> list = dataService.channel_rateQ(Province);
            if(s<10){
                k=s;
            }
            while (i < k) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", (list.get(i)).getObj_id());
                jsonObject.put("StationName", (list.get(i)).getVal());
                jsonObject.put("NumberRecords", (list.get(i)).getPredict());
                jsonObject.put("NumberForecast", (list.get(i)).getProbablity());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;
        }
    catch (IOException e) {
        e.printStackTrace();
        jsonObjecterror.put("result","result");
        jsonArray.add(jsonObjecterror);
        return jsonArray;

    }
    }
    //查询异常channel_rate_null
    @RequestMapping("/channel_rate_null")
    @ResponseBody
    public JSONArray channel_rate_null(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.channel_rate_null_Count(Province);
            List<data> list = dataService.channel_rate_null(Province);
            if (s < 10) {
                k = s;
            }
            while (i < k) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", (list.get(i)).getObj_id());
                jsonObject.put("StationName", (list.get(i)).getPredict());
                jsonObject.put("NumberForecast", (list.get(i)).getProbablity());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result", "result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }

    }



    //查询异常buz_type
    @RequestMapping("/buz_type")
    @ResponseBody
    public JSONArray buz_type(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.buz_typeQ_Count(Province);
            List<data> list = dataService.buz_typeQ(Province);
            if(s<10){
                k=s;
            }
            while (i < k) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", (list.get(i)).getObj_id());
                jsonObject.put("StationName", (list.get(i)).getVal());
                jsonObject.put("NumberRecords", (list.get(i)).getPredict());
                jsonObject.put("NumberForecast", (list.get(i)).getProbablity());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;
        }
        catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }
    }
    //查询异常buz_type_null
    @RequestMapping("/buz_type_null")
    @ResponseBody
    public JSONArray buz_type_null(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.buz_type_null_Count(Province);
            List<data> list = dataService.buz_type_null(Province);
            if (s < 10) {
                k = s;
            }
            while (i < k) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", (list.get(i)).getObj_id());
                jsonObject.put("StationName", (list.get(i)).getPredict());
                jsonObject.put("NumberForecast", (list.get(i)).getProbablity());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result", "result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }

    }



    //查询异常buz_rate
    @RequestMapping("/buz_rate")
    @ResponseBody
    public JSONArray buz_rate(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.buz_rateQ_Count(Province);
            List<data> list = dataService.buz_rateQ(Province);
            if(s<10){
                k=s;
            }
            while (i < k) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", (list.get(i)).getObj_id());
                jsonObject.put("StationName", (list.get(i)).getVal());
                jsonObject.put("NumberRecords", (list.get(i)).getPredict());
                jsonObject.put("NumberForecast", (list.get(i)).getProbablity());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;
        }
        catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }
    }
    //查询异常buz_rate_null
    @RequestMapping("/buz_rate_null")
    @ResponseBody
    public JSONArray buz_rate_null(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.buz_rate_null_Count(Province);
            List<data> list = dataService.buz_rate_null(Province);
            if (s < 10) {
                k = s;
            }
            while (i < k) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", (list.get(i)).getObj_id());
                jsonObject.put("StationName", (list.get(i)).getPredict());
                jsonObject.put("NumberForecast", (list.get(i)).getProbablity());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result", "result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }

    }



    //查询异常interface_rate
    @RequestMapping("/interface_type")
    @ResponseBody
    public JSONArray interface_type(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.interface_typeQ_Count(Province);
            List<data> list = dataService.interface_typeQ(Province);
            if(s<10){
                k=s;
            }
            while (i < k) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", (list.get(i)).getObj_id());
                jsonObject.put("StationName", (list.get(i)).getVal());
                jsonObject.put("NumberRecords", (list.get(i)).getPredict());
                jsonObject.put("NumberForecast", (list.get(i)).getProbablity());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;
        }
        catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }
    }
    //查询异常interface_type_null
    @RequestMapping("/interface_type_null")
    @ResponseBody
    public JSONArray interface_type_null(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.interface_type_null_Count(Province);
            List<data> list = dataService.interface_type_null(Province);
            if (s < 10) {
                k = s;
            }
            while (i < k) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", (list.get(i)).getObj_id());
                jsonObject.put("StationName", (list.get(i)).getPredict());
                jsonObject.put("NumberForecast", (list.get(i)).getProbablity());
                jsonArray.add(jsonObject);
                i++;
            }
            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result", "result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }

    }
}
