package com.demo.ssm.po.S_data;

public class Abnormal_top10 {
    private Integer id;

    private String stationName;

    private double numberRecords;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setNumberRecords(double numberRecords) {
        this.numberRecords = numberRecords;
    }

    public void setNumberForecast(double numberForecast) {
        this.numberForecast = numberForecast;
    }

    private double numberForecast;

    public Integer getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public double getNumberRecords() {
        return numberRecords;
    }

    public double getNumberForecast() {
        return numberForecast;
    }
}
