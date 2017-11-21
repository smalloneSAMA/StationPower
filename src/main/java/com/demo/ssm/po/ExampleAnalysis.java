package com.demo.ssm.po;

public class ExampleAnalysis {
    private Integer id;

    private String stationName;

    private double useTime;

    private double beta;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setUseTime(double useTime) {
        this.useTime = useTime;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public void setEta(double eta) {
        this.eta = eta;
    }

    private double eta;

    public Integer getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public double getUseTime() {
        return useTime;
    }

    public double getBeta() {
        return beta;
    }

    public double getEta() {
        return eta;
    }



}
