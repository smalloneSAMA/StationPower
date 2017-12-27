package com.demo.ssm.controller.S_reliability;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_reliability.*;
import com.demo.ssm.service.interf.S_reliability.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping("/tu")
    @ResponseBody
    public ArrayList<String> tu(String buz_type,String buz_id){
//        String buz_type = "1";
//        String buz_id = "A7464B0A-B0FB-41C5-951A-FE17E41B7263-00250";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        ArrayList<String> list = new ArrayList<String>();
        String aport="";
        String zport="";
        String type="";
        try {
            //通过id和type得到t_business_channel对象集合
            List<t_business_channel> list1 = t_business_channelService.selectByPrimaryKey(buz_id);
            int len = list1.size();
            //遍历集合
            for (int i=0;i<len;i++){
                String[] array1=new String[len];
                //得到通道id
                array1[i] = list1.get(i).getCHANNEL_ID();
                //通过通道id得到t_channel_base对象集合
                List<t_channel_base> list2 = t_channel_baseService.selectByPrimaryKey(array1[i]);
                //start:起始id    end:结束id
                String start = list2.get(0).getA_RES_ID();
                String end = list2.get(0).getZ_RES_ID();
//                if(!start.equals(end)){
                    //得到一个通道的几条
                    List<t_sdh_cc> list3 = t_sdh_ccService.select1(start,start);
                    int len1 = list3.size();
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
                                t_topology top = t_topologyService.select(start, start);
                                aport=top.getA_PORT();
                                zport=top.getZ_PORT();
                                if(start.equals(aport)){
                                    list.add(top.getA_NE());
                                    start = zport;
                                    t_sdh_cc t = t_sdh_ccService.select(type, start, type, start);
                                    ac=t.getA_CTP();
                                    ap=t.getA_PTP();
                                    zc=t.getZ_CTP();
                                    zp=t.getZ_PTP();
                                }else {
                                    list.add(top.getZ_NE());
                                    start = aport;
                                    t_sdh_cc t = t_sdh_ccService.select(type, start, type, start);
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
                                t_topology top = t_topologyService.select(start, start);
                                aport=top.getA_PORT();
                                zport=top.getZ_PORT();
                                if(start.equals(aport)){
                                    list.add(top.getA_NE());
                                    start = zport;
                                    t_sdh_cc t = t_sdh_ccService.select(type, start, type, start);
                                    ac = t.getA_CTP();
                                    zc = t.getZ_CTP();
                                    ap = t.getA_PTP();
                                    zp = t.getZ_PTP();
                                }else {
                                    list.add(top.getZ_NE());
                                    start = aport;
                                    t_sdh_cc t = t_sdh_ccService.select(type, start, type, start);
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
                            System.out.println(list);
                        }
                        System.out.println(111);


                    }
                }
                return list;
            } catch (Exception e) {

                return null;
            }
        }
//                        if (zp.equals(start)){
//                            t_topology top = t_topologyService.select(ap, ap);
//                            String aport=top.getA_PORT();
//                            String zport=top.getZ_PORT();
//
//                            while(!ap.equals(end)) {
//
//                                if (ap.equals(aport)) {
//                                    list.add(top.getA_NE());
//                                    t_sdh_cc t = t_sdh_ccService.select(ac, zport, ac, zport);
//                                    ac=t.getA_CTP();
//                                    ap=t.getA_PTP();
//                                    zc=t.getZ_CTP();
//                                    zp=t.getZ_PTP();
//                                    t_topology top1 = t_topologyService.select(ap,ap);
//                                    aport = top1.getA_PORT();
//                                    zport = top1.getZ_PORT();
//                                } else {
//                                    list.add(top.getZ_NE());
//                                    t_sdh_cc t = t_sdh_ccService.select(ac, ap, ac, ap);
//                                    ac=t.getA_CTP();
//                                    ap=t.getA_PTP();
//                                    zc=t.getZ_CTP();
//                                    zp=t.getZ_PTP();
//                                    t_topology top1 = t_topologyService.select(zp,zp);
//                                    aport = top1.getA_PORT();
//                                    zport = top1.getZ_PORT();
//                                }
//                            }
//
//                        }


    //查询异常对象数据
    @RequestMapping("/chaxun")
    @ResponseBody
    public JSONArray chaxun(String buz_type,String buz_id) {
//        placeholder="请输入业务ID"
//        ,String buz_type,String buz_id
//        String buz_type = "1";
//        String buz_id = "A7464B0A-B0FB-41C5-951A-FE17E41B7263-00270";
        t_buz t_buz = new t_buz();
        t_buz.setBUZ_TYPE(buz_type);
        t_buz.setOBJ_ID(buz_id);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        try{
            List<t_buz> list1 = t_buzService.selectByPrimaryKey(t_buz);
            JSONObject jsonObject1 = new JSONObject() ;
            jsonObject1.put("业务ID", (list1.get(0)).getOBJ_ID());
            jsonObject1.put("业务全名", (list1.get(0)).getFULL_NAME());
            jsonObject1.put("业务名称", (list1.get(0)).getNAME());
            jsonObject1.put("业务类型", (list1.get(0)).getBUZ_TYPE());
            jsonObject1.put("业务服务状态", (list1.get(0)).getSERVICE_STATE());
            jsonArray.add(jsonObject1);

            List<t_business_channel> list2 = t_business_channelService.selectByPrimaryKey(buz_id);
            int len = list2.size();
            for (int i=0;i<len;i++){
                String[] array=new String[len];
                array[i] = list2.get(i).getCHANNEL_ID();
                List<t_channel_base> list = t_channel_baseService.selectByPrimaryKey(array[i]);
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



}
