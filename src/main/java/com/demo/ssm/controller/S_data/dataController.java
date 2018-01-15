package com.demo.ssm.controller.S_data;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.ssm.po.S_data.data;
import com.demo.ssm.service.interf.S_data.dataService;
import com.demo.ssm.tool.path_python;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/S_data")
public class dataController {

    @Autowired
    dataService dataService;

    //查询异常channel_type
    @RequestMapping("/channel_type")
    @ResponseBody
    public JSONArray channel_type(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.channel_type_Count(Province);
            List<data> list = dataService.channel_typeQ(Province);
            if (s < 10) {
                k = s;
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
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result", "result");
            jsonArray.add(jsonObjecterror);
            return jsonArray;

        }
    }

    //查询异常channel_type_null
    @RequestMapping("/channel_type_null")
    @ResponseBody
    public JSONArray channel_type_null(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.channel_type_null_Count(Province);
            List<data> list = dataService.channel_type_null(Province);
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
            if (s < 10) {
                k = s;
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
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result", "result");
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
            if (s < 10) {
                k = s;
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
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result", "result");
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
            if (s < 10) {
                k = s;
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
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result", "result");
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
    @RequestMapping("/interface_type_diff")
    @ResponseBody
    public JSONArray interface_type(HttpServletRequest request, String Province) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjecterror = new JSONObject();
        Integer i = 0;
        Integer k = 10;

        try {
            Integer s = dataService.interface_typeQ_Count(Province);
            List<data> list = dataService.interface_typeQ(Province);
            if (s < 10) {
                k = s;
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
        } catch (IOException e) {
            e.printStackTrace();
            jsonObjecterror.put("result", "result");
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




//    饼状图各概率区间记录个数
    //buz_type
    @RequestMapping("/buz_type_diff_pin")
    @ResponseBody
    public JSONObject buz_type_diff_pin(){
        JSONObject jsonObject = new JSONObject();

        Integer a = dataService.Count_pin_BT(0.6);
        Integer b = dataService.Count_pin_BT(0.55);
        Integer c = dataService.Count_pin_BT(0.5);
        Integer d = dataService.Count_pin_BT(-0.1);


        a=a;
        d=d-c;
        c=c-b;
        b=b-a;

        jsonObject.put("a",a);
        jsonObject.put("b",b);
        jsonObject.put("c",c);
        jsonObject.put("d",d);

        return jsonObject;


    }

    //buz_rate
    @RequestMapping("/buz_rate_diff_pin")
    @ResponseBody
    public JSONObject buz_rate_diff_pin(){
        JSONObject jsonObject = new JSONObject();

        Integer a = dataService.Count_pin_BR(0.5);
        Integer b = dataService.Count_pin_BR(0.4);
        Integer c = dataService.Count_pin_BR(0.3);
        Integer d = dataService.Count_pin_BR(-0.1);


        a=a;
        d=d-c;
        c=c-b;
        b=b-a;

        jsonObject.put("a",a);
        jsonObject.put("b",b);
        jsonObject.put("c",c);
        jsonObject.put("d",d);

        return jsonObject;


    }

    //channel_type
    @RequestMapping("/channel_type_diff_pin")
    @ResponseBody
    public JSONObject channel_type_diff_pin(){
        JSONObject jsonObject = new JSONObject();

        Integer a = dataService.Count_pin_CT(0.90);
        Integer b = dataService.Count_pin_CT(0.80);
        Integer c = dataService.Count_pin_CT(0.7);
        Integer d = dataService.Count_pin_CT(-0.1);


        a=a;
        d=d-c;
        c=c-b;
        b=b-a;

        jsonObject.put("a",a);
        jsonObject.put("b",b);
        jsonObject.put("c",c);
        jsonObject.put("d",d);

        return jsonObject;


    }

    //channel_rate
    @RequestMapping("/channel_rate_diff_pin")
    @ResponseBody
    public JSONObject channel_rate_diff_pin(){
        JSONObject jsonObject = new JSONObject();

        Integer a = dataService.Count_pin_CR(0.5);
        Integer b = dataService.Count_pin_CR(0.45);
        Integer c = dataService.Count_pin_CR(0.4);
        Integer d = dataService.Count_pin_CR(-0.1);


        a=a;
        d=d-c;
        c=c-b;
        b=b-a;

        jsonObject.put("a",a);
        jsonObject.put("b",b);
        jsonObject.put("c",c);
        jsonObject.put("d",d);

        return jsonObject;


    }

    //interface_type
    @RequestMapping("/interface_type_diff_pin")
    @ResponseBody
    public JSONObject interface_type_diff_pin(){
        JSONObject jsonObject = new JSONObject();

        Integer a = dataService.Count_pin_IT(0.95);
        Integer b = dataService.Count_pin_IT(0.90);
        Integer c = dataService.Count_pin_IT(0.8);
        Integer d = dataService.Count_pin_IT(-0.1);


        a=a;
        d=d-c;
        c=c-b;
        b=b-a;

        jsonObject.put("a",a);
        jsonObject.put("b",b);
        jsonObject.put("c",c);
        jsonObject.put("d",d);

        return jsonObject;


    }


    /**
     * 刷新数据
     */

    //Jy利用Java调用python程序
    //刷新//channel_type
    @RequestMapping("/Jpchanneltype")
    @ResponseBody
    public int Jpchanneltype(HttpServletRequest request, String Province) {
        //java调用python程序更新数据库数据
        //将select选中的省份传递给python
        System.out.printf(Province);
        List<String> processList = new ArrayList<>();
        String[] url = new String[]{ "python ", path_python.getCT(), Province};
        String line = "";
        try {
            System.out.printf("\npython Jpchanneltype程序准备执行\n");
            Process pr = Runtime.getRuntime().exec(url);
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
            System.out.printf("\npython end\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String out : processList) {
            System.out.println(out);
        }
        return 0;
    }

    //刷新buz_type
    @RequestMapping("/Jpbuztype")
    @ResponseBody
    public int Jpflash(HttpServletRequest request, String Province) {
        //java调用python程序更新数据库数据
        //将select选中的省份传递给python
        System.out.printf(Province);
        String line = "";
        List<String> processList = new ArrayList<>();
        String[] url =new String[]{ "python ", path_python.getBT(), Province};
        try {
            System.out.printf("\npython程序准备执行\n");
            Process pr = Runtime.getRuntime().exec(url);
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
            System.out.printf("\npython end\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String out : processList) {
            System.out.println(out);
        }
        return 0;
    }

    //刷新buz_rate
    @RequestMapping("/Jpbuzrate")
    @ResponseBody
    public int Jpbuzrate(HttpServletRequest request, String Province) {
        //java调用python程序更新数据库数据
        //将select选中的省份传递给python
        System.out.printf(Province);
        String line = "";
        List<String> processList = new ArrayList<>();
        String[] url =new String[]{ "python ",path_python.getBR(), Province};
        try {
            System.out.printf("\npython  buzrate程序准备执行\n");
            Process pr = Runtime.getRuntime().exec(url);
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
            System.out.printf("\npython end\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String out : processList) {
            System.out.println(out);
        }
        return 0;
    }

    //刷新channel_rate数据
    @RequestMapping("/re_channel_rate")
    @ResponseBody
    public JSONArray re_channel_rate(HttpServletRequest request, String Province) {
        //java调用python程序更新数据库数据
        //将select选中的省份传递给python
        List<String> processList = new ArrayList<>();
        String line = "";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        System.out.printf(Province);
        String[] arg = new String[] {"python", path_python.getCR(),Province};

        try {
            System.out.printf("\npython程序准备执行\n");
            Process pr = Runtime.getRuntime().exec(arg);
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
            System.out.printf("\npython end\n");
            jsonObject.put("success", "success");
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("success", "faile");
        }
        for (String out : processList) {
            System.out.println(out);
        }
        jsonArray.add(jsonObject);
        return jsonArray;
    }

    //刷新interface_type数据
    @RequestMapping("/re_interface_type")
    @ResponseBody
    public JSONArray re_interface_type(HttpServletRequest request, String Province) {
        //java调用python程序更新数据库数据
        //将select选中的省份传递给python
        List<String> processList = new ArrayList<>();
        String line = "";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        System.out.printf(Province);
        String[] url = new String[]{"python ",path_python.getIT(),Province};

        try {
            System.out.printf("\npython程序predictInterfaceType准备执行\n");
            Process pr = Runtime.getRuntime().exec(url);
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
            System.out.printf("\npython end\n");
            jsonObject.put("success", "success");
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("success", "faile");
        }
        for (String out : processList) {
            System.out.println(out);
        }
        jsonArray.add(jsonObject);
        return jsonArray;
    }



}

