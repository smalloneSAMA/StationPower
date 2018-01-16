package com.demo.ssm.controller.S_reliability;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_reliability.*;
import com.demo.ssm.service.interf.S_reliability.*;
import com.demo.ssm.tool.path_python;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;


@Controller
@RequestMapping("/t")
public class t_channel_baseController {

    @Autowired
    private t_channel_baseService t_channel_baseService;
    @Autowired
    private t_buzService t_buzService;
    @Autowired
    private t_business_channelService t_business_channelService;
    @Autowired
    private t_sdh_ccService t_sdh_ccService;
    @Autowired
    private t_topologyService t_topologyService;
    @Autowired
    private t_neService t_neService;
    @Autowired
    private t_alarm_cleanService t_alarm_cleanService;
    @Autowired
    private t_weibullService t_weibullService;
    @Autowired
    private Jiangxi_fiber_faultService jiangxi_fiber_faultService;
    @Autowired
    private jiangxi_ne_reService jiangxi_ne_reService;
    @Autowired
    private t_fiber_reService t_fiber_reService;
    @Autowired
    private Jiangxi_t_buz_reService jiangxi_t_buz_reService;

    //返回设备集合（1个）
    @RequestMapping("/tu")
    @ResponseBody
    public ArrayList<String> tu(String buz_id,String province){
//       String buz_id,String province
//        String buz_type = "2";
//        String buz_id = "A7464B0A-B0FB-41C5-951A-FE17E41B7263-00250";
//        String province = "江西";
        String table = "";
        if(province.equals("安徽")){
            table = "t_sdh_cc_anhui";
        }
        if(province.equals("北京")){
            table = "t_sdh_cc_beijing";
        }
        if(province.equals("福建")){
            table = "t_sdh_cc_fujian";
        }
        if(province.equals("甘肃")){
            table = "t_sdh_cc_gansu";
        }
        if(province.equals("河北")){
            table = "t_sdh_cc_hebei";
        }
        if(province.equals("河南")){
            table = "t_sdh_cc_henan";
        }
        if(province.equals("黑龙江")){
            table = "t_sdh_cc_heilongjiang";
        }
        if(province.equals("吉林")){
            table = "t_sdh_cc_jilin";
        }
        if(province.equals("江苏")){
            table = "t_sdh_cc_jiangsu";
        }
        if(province.equals("江西")){
            table = "t_sdh_cc_jiangxi";
        }
        if(province.equals("蒙东")){
            table = "t_sdh_cc_mengdong";
        }
        if(province.equals("宁夏")){
            table = "t_sdh_cc_ningxia";
        }
        if(province.equals("青海")){
            table = "t_sdh_cc_qinghai";
        }
        if(province.equals("陕西")){
            table = "t_sdh_cc_shan3xi";
        }
        if(province.equals("天津")){
            table = "t_sdh_cc_tianjin";
        }
        if(province.equals("西藏")){
            table = "t_sdh_cc_xizang";
        }
        if(province.equals("浙江")){
            table = "t_sdh_cc_zhejiang";
        }
        if(province.equals("重庆")){
            table = "t_sdh_cc_chongqing";
        }

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        ArrayList<String> list = new ArrayList<String>();
        String aport="";
        String zport="";
        String type="";
        try {
            //通过id和type得到t_business_channel对象集合
            List<t_business_channel> list1 = t_business_channelService.selectByPrimaryKey(buz_id,province);
            int len = list1.size();
//            int [] a = new int[10] ;

            //遍历集合
            for (int i=0;i<len;i++){
                String[] array1=new String[len];
                //得到通道id
                array1[i] = list1.get(i).getCHANNEL_ID();
                //通过通道id得到t_channel_base对象集合
                List<t_channel_base> list2 = t_channel_baseService.selectByPrimaryKey(array1[i],province);
                //start:起始id    end:结束id
                String start = list2.get(0).getA_RES_ID();
                String end = list2.get(0).getZ_RES_ID();
//                if(!start.equals(end)){
                //得到一个通道的几条
                List<t_sdh_cc> list3 = t_sdh_ccService.select1(start,start,table);
                int len1 = list3.size();
                List[] lists = new  ArrayList[len1];
                //对通道的每一条操作
                loop:for (int j=0;j<len1;j++){
                    String ac = list3.get(j).getA_CTP();
                    String ap = list3.get(j).getA_PTP();
                    String zc = list3.get(j).getZ_CTP();
                    String zp = list3.get(j).getZ_PTP();
                    while (!start.equals(end)){
                        if (zp.equals(start)){
                            start = ap;
                            type = ac;
                            t_topology top = t_topologyService.select(start, start,province);
                            aport=top.getA_PORT();
                            zport=top.getZ_PORT();
                            if(start.equals(aport)){
                                list.add(top.getA_NE());
                                start = zport;
                                t_sdh_cc t = t_sdh_ccService.select(type, start, type, start,table);
                                ac=t.getA_CTP();
                                ap=t.getA_PTP();
                                zc=t.getZ_CTP();
                                zp=t.getZ_PTP();
                            }else {
                                list.add(top.getZ_NE());
                                start = aport;
                                t_sdh_cc t = t_sdh_ccService.select(type, start, type, start,table);
                                ac=t.getA_CTP();
                                ap=t.getA_PTP();
                                zc=t.getZ_CTP();
                                zp=t.getZ_PTP();
                            }
                            if(zp.equals(end) || ap.equals(end)){
                                if(start.equals(aport)){
                                    list.add(top.getA_NE());
                                }else {
                                    list.add(top.getZ_NE());
                                }
                                ac = list3.get(j).getA_CTP();
                                ap = list3.get(j).getA_PTP();
                                zc = list3.get(j).getZ_CTP();
                                zp = list3.get(j).getZ_PTP();
                                start = list2.get(0).getA_RES_ID();
                                continue loop;
                            }

                        }else if(ap.equals(start)){ //ap = start
                            start = zp;
                            type = zc;
                            t_topology top = t_topologyService.select(start, start,province);
                            aport=top.getA_PORT();
                            zport=top.getZ_PORT();
                            if(start.equals(aport)){
                                list.add(top.getA_NE());
                                start = zport;
                                t_sdh_cc t = t_sdh_ccService.select(type, start, type, start,table);
                                ac = t.getA_CTP();
                                zc = t.getZ_CTP();
                                ap = t.getA_PTP();
                                zp = t.getZ_PTP();
                            }else {
                                list.add(top.getZ_NE());
                                start = aport;
                                t_sdh_cc t = t_sdh_ccService.select(type, start, type, start,table);
                                ac = t.getA_CTP();
                                zc = t.getZ_CTP();
                                ap = t.getA_PTP();
                                zp = t.getZ_PTP();
                            }
                            if(ap.equals(end)||zp.equals(end)){
                                if(start.equals(aport)){
                                    list.add(top.getA_NE());
                                }else {
                                    list.add(top.getZ_NE());
                                }
                                ac = list3.get(j).getA_CTP();
                                zc = list3.get(j).getZ_CTP();
                                ap = list3.get(j).getA_PTP();
                                zp = list3.get(j).getZ_PTP();
                                start = list2.get(0).getA_RES_ID();
                                continue loop;
                            }
                        }
//                            System.out.println(list);
                    }
//                        System.out.println(111);


                }
            }
            return list;
        } catch (Exception e) {

            return null;
        }
    }


    //返回设备集合（n个）
    @RequestMapping("/ntu")
    @ResponseBody
    public List<List<String>> ntu(String buz_id,String province){
//        String buz_id,String province
//        String buz_type = "1";
//        String buz_id = "A7464B0A-B0FB-41C5-951A-FE17E41B7263-00250";
//        String province = "江西";
        String table = "";
        if(province.equals("安徽")){
            table = "t_sdh_cc_anhui";
        }
        if(province.equals("北京")){
            table = "t_sdh_cc_beijing";
        }
        if(province.equals("福建")){
            table = "t_sdh_cc_fujian";
        }
        if(province.equals("甘肃")){
            table = "t_sdh_cc_gansu";
        }
        if(province.equals("河北")){
            table = "t_sdh_cc_hebei";
        }
        if(province.equals("河南")){
            table = "t_sdh_cc_henan";
        }
        if(province.equals("黑龙江")){
            table = "t_sdh_cc_heilongjiang";
        }
        if(province.equals("吉林")){
            table = "t_sdh_cc_jilin";
        }
        if(province.equals("江苏")){
            table = "t_sdh_cc_jiangsu";
        }
        if(province.equals("江西")){
            table = "t_sdh_cc_jiangxi";
        }
        if(province.equals("蒙东")){
            table = "t_sdh_cc_mengdong";
        }
        if(province.equals("宁夏")){
            table = "t_sdh_cc_ningxia";
        }
        if(province.equals("青海")){
            table = "t_sdh_cc_qinghai";
        }
        if(province.equals("陕西")){
            table = "t_sdh_cc_shan3xi";
        }
        if(province.equals("天津")){
            table = "t_sdh_cc_tianjin";
        }
        if(province.equals("西藏")){
            table = "t_sdh_cc_xizang";
        }
        if(province.equals("浙江")){
            table = "t_sdh_cc_zhejiang";
        }
        if(province.equals("重庆")){
            table = "t_sdh_cc_chongqing";
        }
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        List<List<String>> nelist = new ArrayList<List<String>>();
//        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        String aport="";
        String zport="";
        String type="";
        try {
            //通过id和type得到t_business_channel对象集合
            List<t_business_channel> list1 = t_business_channelService.selectByPrimaryKey(buz_id,province);
            int len = list1.size();
//            int [] a = new int[10] ;

            //遍历集合
            for (int i=0;i<len;i++){
                String[] array1=new String[len];
                //得到通道id
                array1[i] = list1.get(i).getCHANNEL_ID();
                //通过通道id得到t_channel_base对象集合
                List<t_channel_base> list2 = t_channel_baseService.selectByPrimaryKey(array1[i],province);
                //start:起始id    end:结束id
                String start = list2.get(0).getA_RES_ID();
                String end = list2.get(0).getZ_RES_ID();
//                if(!start.equals(end)){
                //得到一个通道的几条
                List<t_sdh_cc> list3 = t_sdh_ccService.select1(start,start,table);
                int len1 = list3.size();
                List[] lists = new  ArrayList[len1];
                //对通道的每一条操作
                loop:for (int j=0;j<len1;j++){
                    List<String> listtt = new ArrayList<String>();
                    String ac = list3.get(j).getA_CTP();
                    String ap = list3.get(j).getA_PTP();
                    String zc = list3.get(j).getZ_CTP();
                    String zp = list3.get(j).getZ_PTP();
                    while (!start.equals(end)){
                        if (zp.equals(start)){
                            start = ap;
                            type = ac;
                            t_topology top = t_topologyService.select(start, start,province);
                            aport=top.getA_PORT();
                            zport=top.getZ_PORT();
                            if(start.equals(aport)){
                                listtt.add(top.getA_NE());
                                start = zport;
                                t_sdh_cc t = t_sdh_ccService.select(type, start, type, start,table);
                                ac=t.getA_CTP();
                                ap=t.getA_PTP();
                                zc=t.getZ_CTP();
                                zp=t.getZ_PTP();
                            }else {
                                listtt.add(top.getZ_NE());
                                start = aport;
                                t_sdh_cc t = t_sdh_ccService.select(type, start, type, start,table);
                                ac=t.getA_CTP();
                                ap=t.getA_PTP();
                                zc=t.getZ_CTP();
                                zp=t.getZ_PTP();
                            }
                            if(zp.equals(end) || ap.equals(end)){
                                if(start.equals(aport)){
                                    listtt.add(top.getA_NE());
                                }else {
                                    listtt.add(top.getZ_NE());
                                }
                                ac = list3.get(j).getA_CTP();
                                ap = list3.get(j).getA_PTP();
                                zc = list3.get(j).getZ_CTP();
                                zp = list3.get(j).getZ_PTP();
                                start = list2.get(0).getA_RES_ID();
                                System.out.println(nelist);
                                System.out.println(listtt);
                                nelist.add(listtt);
                                System.out.println(nelist);
                                continue loop;
                            }

                        }else if(ap.equals(start)){ //ap = start
                            start = zp;
                            type = zc;
                            t_topology top = t_topologyService.select(start, start,province);
                            aport=top.getA_PORT();
                            zport=top.getZ_PORT();
                            if(start.equals(aport)){
                                listtt.add(top.getA_NE());
                                start = zport;
                                t_sdh_cc t = t_sdh_ccService.select(type, start, type, start,table);
                                ac = t.getA_CTP();
                                zc = t.getZ_CTP();
                                ap = t.getA_PTP();
                                zp = t.getZ_PTP();
                            }else {
                                listtt.add(top.getZ_NE());
                                start = aport;
                                t_sdh_cc t = t_sdh_ccService.select(type, start, type, start,table);
                                ac = t.getA_CTP();
                                zc = t.getZ_CTP();
                                ap = t.getA_PTP();
                                zp = t.getZ_PTP();
                            }
                            if(ap.equals(end)||zp.equals(end)){
                                if(start.equals(aport)){
                                    listtt.add(top.getA_NE());
                                }else {
                                    listtt.add(top.getZ_NE());
                                }
                                ac = list3.get(j).getA_CTP();
                                zc = list3.get(j).getZ_CTP();
                                ap = list3.get(j).getA_PTP();
                                zp = list3.get(j).getZ_PTP();
                                start = list2.get(0).getA_RES_ID();
                                System.out.println(nelist);
                                System.out.println(listtt);
                                nelist.add(listtt);
                                System.out.println(nelist);
                                continue loop;
                            }
                        }
//                            System.out.println(list);

                    }
                    nelist.add(listtt);
                    System.out.println(nelist);
                }
            }
            return nelist;
        } catch (Exception e) {

            return null;
        }
    }


    @RequestMapping("/list")
    @ResponseBody
    public List<String> list(String buz_id, String province){
        try{
            List<String> list = new ArrayList<String>();
            jiangxi_t_buz_re jiangxi_t_buz_re = jiangxi_t_buz_reService.select(buz_id,province);
            String nestr = jiangxi_t_buz_re.getNE_LIST();
            String[] arr = nestr.split("],");


            for (int i=0;i<arr.length;i++){
                String onestr = "";
                if(i == arr.length-1){
                    onestr = arr[i].substring(2,arr[i].length()-2);
                }else{
                    onestr = arr[i].substring(2,arr[i].length());
                }
                String[] arr1 = onestr.split(",");
                for (int j = 0;j<arr1.length;j++){
                    String ne = "";
                    if (j == 0){
                        ne = arr1[j].substring(1,arr1[j].length()-1);
                    }else{
                        ne = arr1[j].substring(2,arr1[j].length()-1);
                    }
                    list.add(ne);
                }

            }
            return list;

        }catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }


    @RequestMapping("/nlist")
    @ResponseBody
    public List<List<String>> nlist(String buz_id, String province){
        try{
            List<List<String>> list = new ArrayList<List<String>>();
            jiangxi_t_buz_re jiangxi_t_buz_re = jiangxi_t_buz_reService.select(buz_id,province);
            String nestr = jiangxi_t_buz_re.getNE_LIST();
            String[] arr = nestr.split("],");


            for (int i=0;i<arr.length;i++){
                String onestr = "";
                if(i == arr.length-1){
                    onestr = arr[i].substring(2,arr[i].length()-2);
                }else{
                    onestr = arr[i].substring(2,arr[i].length());
                }
                String[] arr1 = onestr.split(",");
                List<String> onelist = new ArrayList();
                for (int j = 0;j<arr1.length;j++){
                    String ne = "";
                    if (j == 0){
                        ne = arr1[j].substring(1,arr1[j].length()-1);
                    }else{
                        ne = arr1[j].substring(2,arr1[j].length()-1);
                    }
                    onelist.add(ne);
                }
                list.add(onelist);

            }
            return list;

        }catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

    //设备那个页面请求的
    @RequestMapping("/tt")
    @ResponseBody
    public JSONArray tt(HttpServletRequest request){
//        @ModelAttribute("Buz_id") String buz_id,ModelMap mmp
//        String buz_id = "A7464B0A-B0FB-41C5-951A-FE17E41B7263-00250";
//        String province = "江西";

        //取出session里的值
        String buz_id = request.getSession().getAttribute("buz_id").toString();
        String province = request.getSession().getAttribute("province").toString();

//        t_buz t_buz = new t_buz();
//        t_buz.setOBJ_ID(buz_id);
        //调用上面的方法获得设备id集合
        List<String> list = new ArrayList<>();
        if (province.equals("江西")){
            list = list(buz_id,province);
        }else{
            list = tu(buz_id,province);
        }
         //buz_id,province

        //集合去重复
        LinkedHashSet<String> lhs = new LinkedHashSet<>();
        lhs.addAll(list);
        list.clear();
        list.addAll(lhs);

        //根据设备id查询各种信息展示到前台页面
        JSONObject jsonObjecterror = new JSONObject();
        JSONArray jsonArray = new JSONArray();


            int len = list.size();

        for(int i=0;i<len;i++){
            try {
                List<t_alarm_clean> list1 = t_alarm_cleanService.select(list.get(i), province);
                t_weibull t_weibull = t_weibullService.select(list.get(i), province);
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("index", (i + 1));
                jsonObject1.put("id", (list1.get(0).getNE_OBJ_ID()));
                jsonObject1.put("name", (list1.get(0).getNE_NAME()));
                jsonObject1.put("type_name", (list1.get(0).getDEV_TYPE_NAME()));
                jsonObject1.put("proname", (list1.get(0).getPRODUCER_NAME()));
                jsonObject1.put("workyear", (list1.get(0).getWORK_YEAR()));
                jsonObject1.put("devtype", (list1.get(0).getDEV_TYPE()));
                jsonObject1.put("alarmTimeLst", (t_weibull.getALARM_TIME_D()));
                jsonObject1.put("beta", (t_weibull.getBETA()));
                jsonObject1.put("eta", (t_weibull.getETA()));
                jsonArray.add(jsonObject1);
            }catch (Exception e) {
                continue;
            }
        }
            return jsonArray;

    }


    //一条业务的可靠性，和下面的方法是一个页面的请求
    @RequestMapping("/buz_re")
    @ResponseBody
    public JSONArray buz_re(HttpServletRequest request) {
        //取出session里的值
        String buz_id = request.getSession().getAttribute("buz_id").toString();
        String buz_type = request.getSession().getAttribute("buz_type").toString();
        String province = request.getSession().getAttribute("province").toString();
//        String province = "江西";
//        String buz_type="2";
//        String buz_id = "A7464B0A-B0FB-41C5-951A-FE17E41B7263-00250";
        List<String> processList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        try {
            //查一条业务
            jiangxi_t_buz_re jiangxi_t_buz_re = jiangxi_t_buz_reService.select(buz_id,province);

//            double buz_re = jiangxi_t_buz_re.getBuzRe();
            JSONObject jsonObject = new JSONObject() ;
            jsonObject.put("业务id", jiangxi_t_buz_re.getOBJ_ID());
            jsonObject.put("业务名称", jiangxi_t_buz_re.getNAME());
            jsonObject.put("业务可靠性", jiangxi_t_buz_re.getBuzRe());
            jsonArray.add(jsonObject);

            return jsonArray;

        } catch (Exception e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;
        }
    }


    //和上一个方法都是buz_pre页面发的请求
    @RequestMapping("/buz_pre")
    @ResponseBody
    public JSONArray buz_pre(HttpServletRequest request){

//        ,String buz_type,String buz_id,String province
//        String buz_id = "A7464B0A-B0FB-41C5-951A-FE17E41B7263-00250";
//        String buz_type = "2";
//        String province = "江西";
        //调用上面方法获得设备id集合
        String buz_id = request.getSession().getAttribute("buz_id").toString();
        String province = request.getSession().getAttribute("province").toString();
        List<List<String>> list = new ArrayList<List<String>>();
        if(province.equals("江西")){
            list = nlist(buz_id,province);
        }else {
            list = ntu(buz_id,province);
        }

        JSONArray jsonArrayAall = new JSONArray();

        JSONObject jsonObjecterror = new JSONObject();

        //大集合长度
        int len = list.size();


        for(int i=0;i<len;i++){

            //小集合长度
            int len1 = list.get(i).size();
            JSONArray jsonArray = new JSONArray();
            for(int j=0;j<len1;j++){
                try {
                    JSONObject jsonObject = new JSONObject();

                    //查询设备信息
                    List<t_alarm_clean> list1 = t_alarm_cleanService.select(list.get(i).get(j), province);
                    t_weibull t_weibull = t_weibullService.select(list.get(i).get(j), province);

                    //设备可靠性
                    String ne_re = "";
                    String a = list.get(i).get(j);

                    jiangxi_ne_re jnr = jiangxi_ne_reService.select(a);
                    System.out.println(jnr);
                    ne_re = jnr.getReliability();

                    //光缆可靠性
                    Double fiber_re = 1.0;
                    if (j != len1 - 1) {
                        t_ne t_ne1 = t_neService.select(list.get(i).get(j));
                        t_ne t_ne2 = t_neService.select(list.get(i).get(j + 1));
                        String PAR_STATION1 = t_ne1.getPAR_STATION();
                        String PAR_STATION2 = t_ne2.getPAR_STATION();

                        List<jiangxi_fiber_fault> list_fiber = jiangxi_fiber_faultService.select(PAR_STATION1, PAR_STATION2);
                        String type = list_fiber.get(0).getFIBER_TYPE();
                        String length = list_fiber.get(0).getLENGTH();
                        if (type == "1") {
                            fiber_re = Math.pow(Double.parseDouble("0.999998"), Double.parseDouble(length));
                        } else if (type == "2") {
                            fiber_re = Math.pow(Double.parseDouble("0.999999"), Double.parseDouble(length));
                        } else {
                            fiber_re = Math.pow(Double.parseDouble("0.998390"), Double.parseDouble(length));
                        }
                    }

                    //把每个小列表的每个设备信息信息添加到jsonObject对象，然后整个小列表存到jsonArray数据
                    jsonObject.put("id", list1.get(0).getNE_OBJ_ID());
                    jsonObject.put("name", (list1.get(0).getNE_NAME()));
                    jsonObject.put("type_name", (list1.get(0).getDEV_TYPE_NAME()));
                    jsonObject.put("proname", (list1.get(0).getPRODUCER_NAME()));
                    jsonObject.put("workyear", (list1.get(0).getWORK_YEAR()));
                    jsonObject.put("devtype", (list1.get(0).getDEV_TYPE()));
                    jsonObject.put("alarmTimeLst", (t_weibull.getALARM_TIME_D()));
                    jsonObject.put("beta", (t_weibull.getBETA()));
                    jsonObject.put("eta", (t_weibull.getETA()));

                    //设备可靠性和光缆可靠性添加到对象
                    jsonObject.put("ne_re", ne_re);

                    jsonObject.put("fiber_re", String.valueOf(fiber_re));  //Double.toString(fiber_re))

                    jsonArray.add(jsonObject);
                }catch (Exception e){
                    continue;
                }

            }
            jsonArrayAall.add(jsonArray);
                //把每个小列表添加到大列表然后返回到前台页面

        }
        return jsonArrayAall;



    }


    //调python代码做图再展示出来
    @RequestMapping("/history")
    @ResponseBody
    public JSONArray history(HttpServletRequest request) {
//        String province = "江西";
//        String buz_type="2";
//        String buz_id = "A7464B0A-B0FB-41C5-951A-FE17E41B7263-00250";

        String buz_type = request.getSession().getAttribute("buz_type").toString();
        String buz_id = request.getSession().getAttribute("buz_id").toString();
        String province = request.getSession().getAttribute("province").toString();

//        String buz_id = request.getSession().getAttribute("buz_id").toString();
//        String buz_type = request.getSession().getAttribute("buz_type").toString();

        List<String> processList = new ArrayList<>();
        String line = "";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();

        try {
            //python脚本文件和命令
            String[] arg = new String[] {"python", path_python.getLS(),buz_id,buz_type};  //,province

            //执行python脚本
            Process proc = Runtime.getRuntime().exec(arg);

            //proc.waitFor();
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();

            for (String out : processList) {
                System.out.println(out);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("业务id", buz_id);
            jsonArray.add(jsonObject);



        } catch (Exception e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }
        return jsonArray;
    }


    //调python代码做图再展示出来
    @RequestMapping("/topo")
    @ResponseBody
    public Map<Object,Boolean> topo(HttpServletRequest request) {
        String buz_type = request.getSession().getAttribute("buz_type").toString();
        String buz_id = request.getSession().getAttribute("buz_id").toString();
        String province = request.getSession().getAttribute("province").toString();
        List<String> processList = new ArrayList<>();
        String line = "";
        Map<Object, Boolean> map = new HashMap<>();
        try {
            //python脚本文件和命令
            String[] arg = new String[] {"python", path_python.getTP(),buz_id,buz_type};  //,province

            //执行python脚本
            Process proc = Runtime.getRuntime().exec(arg);

            //proc.waitFor();
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();

            for (String out : processList) {
                System.out.println(out);
            }
            map.put("result", true);


        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);

        }
        return map;
    }


    //低可靠性的业务
    @RequestMapping("/buz_low")
    @ResponseBody
    public JSONArray buz_low(HttpServletRequest request,String buz_type,String province) {
//        ,String buz_type,String province
//        String province = "江西";
//        String buz_type="2";
        List<String> processList = new ArrayList<>();
        String line = "";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        try {
            //查省份五个业务可靠性最低的业务
            List<jiangxi_t_buz_re> list = jiangxi_t_buz_reService.select1(buz_type,province);

            int len = list.size();

            for (int i=0;i<len;i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("序号", i+1);
                jsonObject.put("业务id", list.get(i).getOBJ_ID());
                jsonObject.put("业务名称", (list.get(i)).getNAME());
                jsonObject.put("业务可靠性", (list.get(i)).getBuzRe());
                jsonArray.add(jsonObject);
            }
            return jsonArray;

        } catch (Exception e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;
        }
    }


    //查询业务相关数据
    @RequestMapping("/chaxun")
    @ResponseBody
    public JSONArray chaxun(HttpServletRequest request,ModelMap mmp,String buz_type,String buz_id,String province) {
//        placeholder="请输入业务ID"
//        ,String buz_type,String buz_id
//        String buz_type = "1";
//        String buz_id = "A7464B0A-B0FB-41C5-951A-FE17E41B7263-00270";
        t_buz t_buz = new t_buz();
        t_buz.setBUZ_TYPE(buz_type);
        t_buz.setOBJ_ID(buz_id);
        t_buz.setProvince(province);
        //把buz_id和省份存到session
        request.getSession().setAttribute("buz_id",buz_id);
        request.getSession().setAttribute("buz_type",buz_type);
        request.getSession().setAttribute("province",province);

//        mmp.addAttribute("Buz_id",buz_id);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        try{
            List<t_buz> list1 = t_buzService.selectByPrimaryKey(t_buz);
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("业务ID", (list1.get(0)).getOBJ_ID());
            jsonObject1.put("业务全名", (list1.get(0)).getFULL_NAME());
            jsonObject1.put("业务名称", (list1.get(0)).getNAME());
            jsonObject1.put("业务类型", (list1.get(0)).getBUZ_TYPE());
            jsonObject1.put("业务服务状态", (list1.get(0)).getSERVICE_STATE());
            jsonArray.add(jsonObject1);

            List<t_business_channel> list2 = t_business_channelService.selectByPrimaryKey(buz_id,province);
            int len = list2.size();

            for (int i=0;i<len;i++){
                String[] array=new String[len];
                array[i] = list2.get(i).getCHANNEL_ID();
                List<t_channel_base> list = t_channel_baseService.selectByPrimaryKey(array[i],province);
                JSONObject jsonObject2 = new JSONObject() ;
                jsonObject2.put("通道ID", (list.get(0)).getOBJ_ID());
                jsonObject2.put("通道名称", (list.get(0)).getNAME());
                jsonObject2.put("通道服务状态", (list.get(0)).getSERVICE_STATE());
                jsonObject2.put("通道类型", (list.get(0)).getCHANNEL_TYPE());
                jsonObject2.put("通道是否删除", (list.get(0)).getDELETE_FLAG());

                jsonArray.add(jsonObject2);
            }

            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;
        }
    }


    //三种类型光缆的可靠性  （用不到了）
    @RequestMapping("/fiber")
    @ResponseBody
    public JSONArray fiber(HttpServletRequest request) {

        //取出session里的值
        String province = request.getSession().getAttribute("province").toString();
//        String province = "江西";
        List<String> processList = new ArrayList<>();
        String line = "";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        try {
            //设置省份
            //Boolean setStatics = provinceService.setProvince(Province);

            //python脚本文件及其命令
            String[] arg = new String[] {"python", path_python.getRe(),province};  //,province

            //执行python脚本
            Process proc = Runtime.getRuntime().exec(arg);

            //proc.waitFor();
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();

            for (String out : processList) {
                System.out.println(out);
            }

            //查询当前省份光缆可靠性表的数据库记录
            List<t_fiber_re> list = t_fiber_reService.select(province);
            int len = list.size();

            for (int i=0;i<len;i++){
                JSONObject jsonObject = new JSONObject() ;
                jsonObject.put("光缆类型", (list.get(i)).getType());
                jsonObject.put("总长度", (list.get(i)).getLength());
                jsonObject.put("单位长度可靠性", (list.get(i)).getReliability());
                jsonArray.add(jsonObject);
            }
            return jsonArray;

        } catch (Exception e) {
            e.printStackTrace();
            jsonObjecterror.put("result","result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;
        }



    }
}
