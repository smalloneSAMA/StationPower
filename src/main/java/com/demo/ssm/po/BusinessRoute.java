package com.demo.ssm.po;

public class BusinessRoute {
    private Integer id ;

    private String stationName1;

    private String stationName2;

    private double fiberOcc;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStationName1(String stationName1) {
        this.stationName1 = stationName1;
    }

    public void setStationName2(String stationName2) {
        this.stationName2 = stationName2;
    }

    public void setFiberOcc(double fiberOcc) {
        this.fiberOcc = fiberOcc;
    }

    public Integer getId() {
        return id;
    }

    public String getStationName1() {
        return stationName1;
    }

    public String getStationName2() {
        return stationName2;
    }

    public double getFiberOcc() {
        return fiberOcc;
    }




}
