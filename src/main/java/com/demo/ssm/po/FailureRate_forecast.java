package com.demo.ssm.po;

import org.omg.PortableInterceptor.INACTIVE;

public class FailureRate_forecast {
    private Integer id;

    private double useTime;



    public void setId(Integer id) {
        this.id = id;
    }

    public void setUseTime(double useTime) {
        this.useTime = useTime;
    }

    public Integer getId() {
        return id;
    }

    public double getUseTime() {
        return useTime;
    }


}
