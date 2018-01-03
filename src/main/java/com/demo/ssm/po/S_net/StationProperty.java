package com.demo.ssm.po.S_net;

public class StationProperty {
    private String obj_id;

    private String Province;

    private String name;

    private double Xaxis;

    private double Yaxis;

    private Integer businessNum;

    private double kuorong;

    private String portOcc;

    private String buzNumRate;

    private String increaseRate;

    public String getObj_id() {
        return obj_id;
    }

    public void setObj_id(String obj_id) {
        this.obj_id = obj_id;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getXaxis() {
        return Xaxis;
    }

    public void setXaxis(double xaxis) {
        Xaxis = xaxis;
    }

    public double getYaxis() {
        return Yaxis;
    }

    public void setYaxis(double yaxis) {
        Yaxis = yaxis;
    }

    public Integer getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(Integer businessNum) {
        this.businessNum = businessNum;
    }

    public double getKuorong() {
        return kuorong;
    }

    public void setKuorong(double kuorong) {
        this.kuorong = kuorong;
    }

    public String getPortOcc() {
        return portOcc;
    }

    public void setPortOcc(String portOcc) {
        this.portOcc = portOcc;
    }

    public String getBuzNumRate() {
        return buzNumRate;
    }

    public void setBuzNumRate(String buzNumRate) {
        this.buzNumRate = buzNumRate;
    }

    public String getIncreaseRate() {
        return increaseRate;
    }

    public void setIncreaseRate(String increaseRate) {
        this.increaseRate = increaseRate;
    }
}
