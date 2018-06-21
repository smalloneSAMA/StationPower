package com.demo.ssm.po.S_data;

public class data {
    private String obj_id;

    private String Val;

    private String predict;

    private Double probablity;

    private String province;

    private String name; //这是业务对应的编号的名字

    private String name_name;//这是业务的名称

    public String getName_name() {
        return name_name;
    }

    public void setName_name(String name_name) {
        this.name_name = name_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObj_id() {
        return obj_id;
    }

    public void setObj_id(String obj_id) {
        this.obj_id = obj_id;
    }

    public String getVal() {
        return Val;
    }

    public void setVal(String val) {
        Val = val;
    }

    public String getPredict() {
        return predict;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }

    public Double getProbablity() {
        return probablity;
    }

    public void setProbablity(Double probablity) {
        this.probablity = probablity;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
