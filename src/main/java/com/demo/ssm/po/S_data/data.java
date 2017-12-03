package com.demo.ssm.po.S_data;

public class data {
    private String obj_id;

    private String Val;

    private String predict;

    private Double probablity;

    private String province;

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
