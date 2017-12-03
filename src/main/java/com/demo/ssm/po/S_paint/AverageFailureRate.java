package com.demo.ssm.po.S_paint;

import org.springframework.stereotype.Repository;

@Repository
public class AverageFailureRate {
    private Integer id;

    private String company;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setFailureRate(double failureRate) {
        this.failureRate = failureRate;
    }

    private double failureRate;

    public Integer getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public double getFailureRate() {
        return failureRate;
    }
}
