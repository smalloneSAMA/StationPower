package com.demo.ssm.po.S_data;

public class Accurate_top10 {
    private Integer id;

    private String stationName;

    private double numRecords;

    private double numForecast;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getNumRecords() {
        return numRecords;
    }

    public void setNumRecords(double numRecords) {
        this.numRecords = numRecords;
    }

    public double getNumForecast() {
        return numForecast;
    }

    public void setNumForecast(double numForecast) {
        this.numForecast = numForecast;
    }
}
