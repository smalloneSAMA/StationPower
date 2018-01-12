package com.demo.ssm.controller.S_paint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_paint.AlarmCause;
import com.demo.ssm.po.S_paint.AlarmCombine;
import com.demo.ssm.po.S_paint.AverageFailureRate;
import com.demo.ssm.po.S_paint.NeNameAlarm;
import com.demo.ssm.service.interf.S_paint.AverageFailureRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/AverageFailureRate")
public class AverageFailureRateController {
    @Autowired
    private AverageFailureRateService averageFailureRateService;

    //查询异常对象数据
    @RequestMapping("/select")
    @ResponseBody
    public JSONArray S_dataQuality(HttpServletRequest request,String user){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror=new JSONObject();


        try{

            int i=0;
            int k=averageFailureRateService.count();
            List<AverageFailureRate> list = averageFailureRateService.selectByPrimaryKey();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("Company", (list.get(i)).getCompany());
                jsonObject.put("FailureRate", (list.get(i)).getFailureRate());
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


//查询故障类型
    @RequestMapping("/alarm_cause")
    @ResponseBody
    public JSONArray alarm_cause(HttpServletRequest request,String producer){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror=new JSONObject();
        try{
            int i=0;
            List<AlarmCause> list = averageFailureRateService.findTypeAlarmCause(producer);
            int k=list.size();
            while (i<k) {
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("prodecer", (list.get(i)).getProducer());
                jsonObject.put("num", (list.get(i)).getNum());
                jsonObject.put("alarm_cause", (list.get(i)).getAlarm_cause());
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

    //查询故障详情
    @RequestMapping(value = "/alarm_combine",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String alarm_combine(HttpServletRequest request,String data){
        String data3[] = data.split(",");
        String producer = data3[0];
        String ne_name = data3[1];
        String province = data3[2];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror=new JSONObject();
        province = province.replaceAll("安徽","anhui").replaceAll("北京","beijing").replaceAll("重庆","chongqing");
        province = province.replaceAll("福建","fujian").replaceAll("甘肃","gansu").replaceAll("河北","hebei");
        province = province.replaceAll("黑龙江","heilongjiang").replaceAll("河南","henan").replaceAll("湖北","hubei");
        province = province.replaceAll("湖南","hunan").replaceAll("江苏","jiangsu").replaceAll("江西","jiangxi");
        province = province.replaceAll("吉林","jilin").replaceAll("辽宁","liaoning").replaceAll("蒙东","mengdong");
        province = province.replaceAll("宁夏","ningxia").replaceAll("青海","qinghai").replaceAll("山西","shan1xi");
        province = province.replaceAll("陕西","shan3xi").replaceAll("山东","shandong");
        province = province.replaceAll("天津","tianjin").replaceAll("新疆","xinjiang").replaceAll("西藏","xizang");
        province = province.replaceAll("浙江","zhejiang").replaceAll("四川","sicuan");
        try{
            int i=0;
            List<AlarmCombine> list = averageFailureRateService.findAlarmCombine(producer,ne_name,province);
            int k=list.size();
            while (i<k) {
                province = (list.get(i)).getPROVINCE();
                province = province.replaceAll("anhui","安徽").replaceAll("beijing","北京").replaceAll("chongqing","重庆");
                province = province.replaceAll("fujian","福建").replaceAll("gansu","甘肃").replaceAll("hebei","河北");
                province = province.replaceAll("heilongjiang","黑龙江").replaceAll("henan","河南").replaceAll("hubei","湖北");
                province = province.replaceAll("hunan","湖南").replaceAll("jiangsu","江苏").replaceAll("jiangxi","江西");
                province = province.replaceAll("jilin","吉林").replaceAll("liaoning","辽宁").replaceAll("mengdong","蒙东");
                province = province.replaceAll("ningxia","宁夏").replaceAll("qinghai","青海").replaceAll("shan1xi","山西");
                province = province.replaceAll("shan3xi","陕西").replaceAll("shandong","山东");
                province = province.replaceAll("tianjin","天津").replaceAll("xinjiang","新疆").replaceAll("xizang","西藏");
                province = province.replaceAll("zhejiang","浙江").replaceAll("sicuan","四川").replaceAll("sichuan","四川");
                JSONObject jsonObject = new JSONObject() ;
                long lt1 = new Long((list.get(i)).getALARM_EMS_TIME());
                long lt2 = new Long((list.get(i)).getALARM_EMS_RESUME_TIME());
                Date date1 = new Date(lt1);
                Date date2 = new Date(lt2);
                String res1 = simpleDateFormat.format(date1);
                String res2 = simpleDateFormat.format(date2);
                jsonObject.put("NE_OBJ_ID", (list.get(i)).getNE_OBJ_ID());
                jsonObject.put("ALARM_EMS_TIME_DAY", (list.get(i)).getALARM_EMS_TIME_DAY());
                jsonObject.put("ALARM_EMS_TIME", res1);
                jsonObject.put("ALARM_EMS_RESUME_TIME", res2);
                jsonObject.put("ALARM_CAUSE", (list.get(i)).getALARM_CAUSE());
                jsonObject.put("NE_NAME", (list.get(i)).getNE_NAME());
                jsonObject.put("DEV_TYPE_NAME", (list.get(i)).getDEV_TYPE_NAME());
                jsonObject.put("PRODUCER_NAME", (list.get(i)).getPRODUCER_NAME());
                //jsonObject.put("PRODUCER_ID", (list.get(i)).getPRODUCER_ID());
                jsonObject.put("WORK_YEAR", (list.get(i)).getWORK_YEAR());
                //jsonObject.put("DEV_TYPE", (list.get(i)).getDEV_TYPE());
                jsonObject.put("PROVINCE", province);
                jsonArray.add(jsonObject);
                i++;
            }
            String re="{\"data\":"+jsonArray.toJSONString()+"}";

            return re;
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result","error");
            jsonArray.add(jsonObjecterror);
            return jsonArray.toJSONString();
        }

    }

    //查询故障记录
    @RequestMapping(value = "/ne_name_alarm",produces = "application/json;charset=UTF-8")//解决中文乱码问题
    @ResponseBody
    public String ne_name_alarm(HttpServletRequest request,String producer){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObjecterror=new JSONObject();
        int i=0;
        int k=150;
        String province;
        String producer_name;
        String ne_name;
        String dev_type_name;
        String work_year;
        int num;
        String re;
        try{
            List<NeNameAlarm> list = averageFailureRateService.neNameAlarm(producer);
            int s=list.size();
            if (s<k)
                k=s;
            while (i<k) {
                province = (list.get(i)).getProvince();
                province = province.replaceAll("anhui","安徽").replaceAll("beijing","北京").replaceAll("chongqing","重庆");
                province = province.replaceAll("fujian","福建").replaceAll("gansu","甘肃").replaceAll("hebei","河北");
                province = province.replaceAll("heilongjiang","黑龙江").replaceAll("henan","河南").replaceAll("hubei","湖北");
                province = province.replaceAll("hunan","湖南").replaceAll("jiangsu","江苏").replaceAll("jiangxi","江西");
                province = province.replaceAll("jilin","吉林").replaceAll("liaoning","辽宁").replaceAll("mengdong","蒙东");
                province = province.replaceAll("ningxia","宁夏").replaceAll("qinghai","青海").replaceAll("shan1xi","山西");
                province = province.replaceAll("shan3xi","陕西").replaceAll("shandong","山东");
                province = province.replaceAll("tianjin","天津").replaceAll("xinjiang","新疆").replaceAll("xizang","西藏");
                province = province.replaceAll("zhejiang","浙江").replaceAll("sicuan","四川").replaceAll("sichuan","四川");
                JSONObject jsonObject = new JSONObject() ;
                producer_name = (list.get(i)).getProducer_name();
                ne_name = (list.get(i)).getNe_name();
                dev_type_name = (list.get(i)).getDev_type_name();
                num = (list.get(i)).getNum();
                if((list.get(i)).getWork_year() == null)
                    work_year = "";
                else
                    work_year = (list.get(i)).getWork_year().toString();
                if(producer_name == null) producer_name = "未知";
                if(ne_name == null) ne_name = "未知";
                if(dev_type_name == null) dev_type_name = "未知";
                if(province == null) province = "未知";
                jsonObject.put("producer_name", producer_name);
                jsonObject.put("ne_name", ne_name);
                jsonObject.put("dev_type_name", dev_type_name);
                jsonObject.put("work_year", work_year);
                jsonObject.put("num", num);
                jsonObject.put("province", province);
                jsonArray.add(jsonObject);
                i++;
            }
            re="{\"data\":"+jsonArray.toJSONString()+"}";
            return re;
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result","error");
            jsonArray.add(jsonObjecterror);
            return jsonArray.toJSONString();
        }

    }

}
