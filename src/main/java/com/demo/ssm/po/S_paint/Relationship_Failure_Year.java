package com.demo.ssm.po.S_paint;

public class Relationship_Failure_Year {
    private Integer id;

    private String company;

    private String useTime;

    private Double failureRate;

    public Integer getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getUseTime() {
        return useTime;
    }

    public Double getFailureRate() {
        return failureRate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public void setFailureRate(Double failureRate) {
        this.failureRate = failureRate;
    }
}
