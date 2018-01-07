package com.demo.ssm.controller.S_net;

import com.demo.ssm.po.S_net.Site;
import com.demo.ssm.po.S_net.t_spc_zone;
import com.demo.ssm.po.S_net.zone_roly;
import com.demo.ssm.service.interf.S_net.DealAreaBorderService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/DealAreaBorder")
public class DealAreaBorderController {
    @Autowired
    private DealAreaBorderService dealAreaBorderService;


    /**
     * Created by GZY on 2017-09-29.
     * 加载所有区域名、id
     */
    @RequestMapping("/BorderCollection")
    @ResponseBody
    public Map<String,Object> BorderCollection(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();

        try{
            List<t_spc_zone> tSpcZones = dealAreaBorderService.getAllAreaCollection();
            map.put("tSpcZones",tSpcZones);
            map.put("total",tSpcZones.size());

        }catch (Exception e){
            e.printStackTrace();
            map.put("result","error");
        }
        return map;
    }

    /**
     * Created by GZY on 2017-09-29.
     * 新增边界
     */
    @RequestMapping("/insertBorderCollection")
    @ResponseBody
    public Map<String,Object> insertBorderCollection(HttpServletRequest request,
                                                     @RequestParam("province")String province,
                                                     @RequestParam("area")String area,
                                                     @RequestParam("station")String station,
                                                     @RequestParam("xPoint")String xPoint,
                                                     @RequestParam("yPoint")String yPoint,
                                                     @RequestParam("listSize")int listSize){
        Map<String,Object> map = new HashMap<>();
        List<zone_roly> zoneRolies = new ArrayList<>();
        List<Site> siteList = new ArrayList<>();


        if(listSize>0){
            String conditions = request.getParameter("siteList");
            JSONArray conditionList = JSONArray.fromObject(conditions);
            for(int i = 0;i<conditionList.size();i++){
                Site temp = new Site();
                temp.setxPoint(conditionList.getJSONObject(i).getString("xPoint"));
                temp.setyPoint(conditionList.getJSONObject(i).getString("yPoint"));
                siteList.add(temp);
            }

            for(Site item:siteList){
                zone_roly temp = new zone_roly();
                temp.setProvince(province);
                temp.setArea(area);
                temp.setStation(station);
                temp.setXpoint(xPoint);
                temp.setYpoint(yPoint);
                temp.setRoly_x(item.getxPoint());
                temp.setRoly_y(item.getyPoint());
                zoneRolies.add(temp);
            }
            try{
                Boolean insertStatics = dealAreaBorderService.insertAreaBorder(zoneRolies);
                map.put("reslut",insertStatics);

            }catch (Exception e){
                e.printStackTrace();
                map.put("result","error");
            }
        }


        return map;
    }
}
