package com.demo.ssm.po.S_paint;

public class Province_Check {
    private Integer id;

    private String province;

    private double averageError;

    private double strobe12hours;

    private double strobe24hours;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setAverageError(double averageError) {
        this.averageError = averageError;
    }

    public void setStrobe12hours(double strobe12hours) {
        this.strobe12hours = strobe12hours;
    }

    public void setStrobe24hours(double strobe24hours) {
        this.strobe24hours = strobe24hours;
    }

    public void setStrobe48hours(double strobe48hours) {
        this.strobe48hours = strobe48hours;
    }

    private double strobe48hours;

    public Integer getId() {
        return id;
    }

    public String getProvince() {
        return province;
    }

    public double getAverageError() {
        return averageError;
    }

    public double getStrobe12hours() {
        return strobe12hours;
    }

    public double getStrobe24hours() {
        return strobe24hours;
    }

    public double getStrobe48hours() {
        return strobe48hours;
    }
}
