package com.demo.ssm.po;

public class StationType {
    private Integer id;

    private String type;

    private double averageBusinessNum;

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAverageBusinessNum() {
        return averageBusinessNum;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAverageBusinessNum(double averageBusinessNum) {
        this.averageBusinessNum = averageBusinessNum;
    }
}
