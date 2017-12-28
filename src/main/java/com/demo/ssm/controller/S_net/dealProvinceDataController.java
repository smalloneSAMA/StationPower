package com.demo.ssm.controller.S_net;

import com.demo.ssm.service.interf.S_net.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dealData")
public class dealProvinceDataController {
    @Autowired
    private ProvinceService provinceService;

    /*
    * 设置需要重新生成数据的省份 并且调用python脚本重新生成route link property数据
    */
    @RequestMapping("/dealProvinceData")
    @ResponseBody
    public Map<Object,Boolean> dealProvinceData(HttpServletRequest request, String Province) {
        Map<Object, Boolean> map = new HashMap<>();
        List<String> processList = new ArrayList<>();
        String line = "";
        try {
            //设置省份
            //Boolean setStatics = provinceService.setProvince(Province);
            //删除当前省份三个表的数据库记录
            Boolean deleteStatics = provinceService.deleteProvinceData(Province);
            //python脚本文件及其命令
            String[] arg = new String[] {"python3", "/Users/gubaidan/PycharmProjects/ShortPath/shortPath.py",Province};
            //执行python脚本
            Process proc = Runtime.getRuntime().exec(arg);
            //proc.waitFor();
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();

            if (deleteStatics){

                map.put("result", true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        for (String out : processList) {
            System.out.println(out);
        }

        return map;
    }
}
