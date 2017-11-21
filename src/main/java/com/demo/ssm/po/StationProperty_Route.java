package com.demo.ssm.po;

public class StationProperty_Route {
    private Integer id;

    private String name;

    private double xAxis;

    private double yAxis;

    private Integer businessNum;

    private double kuoRong;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getxAxis() {
        return xAxis;
    }

    public double getyAxis() {
        return yAxis;
    }

    public Integer getBusinessNum() {
        return businessNum;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setxAxis(double xAxis) {
        this.xAxis = xAxis;
    }

    public void setyAxis(double yAxis) {
        this.yAxis = yAxis;
    }

    public void setBusinessNum(Integer businessNum) {
        this.businessNum = businessNum;
    }

    public void setKuoRong(double kuoRong) {
        this.kuoRong = kuoRong;
    }

    public double getKuoRong() {
        return kuoRong;
    }
}
