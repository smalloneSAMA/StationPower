package com.demo.ssm.po;

public class Relationship_Temperature_FailureRate {
    private Integer id;

    private double temperature;

    private double failureRate;

    private double useTime;

    public double getUseTime() {
        return useTime;
    }

    public Integer getId() {
        return id;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFailureRate() {
        return failureRate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setFailureRate(double failureRate) {
        this.failureRate = failureRate;
    }

    public void setUseTime(double useTime) {
        this.useTime = useTime;
    }
}
