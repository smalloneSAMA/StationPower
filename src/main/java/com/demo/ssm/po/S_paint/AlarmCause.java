package com.demo.ssm.po.S_paint;

import org.springframework.stereotype.Repository;

@Repository
public class AlarmCause {
    private Integer num;

    private String producer;

    private String alarm_cause;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getAlarm_cause() {
        return alarm_cause;
    }

    public void setAlarm_cause(String alarm_cause) {
        this.alarm_cause = alarm_cause;
    }
}
