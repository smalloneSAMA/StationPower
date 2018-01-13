package com.demo.ssm.po.S_paint;

import org.springframework.stereotype.Repository;

@Repository
public class NeNameAlarm {
    private Integer num;

    private Integer work_year;

    private String province;

    private String producer_name;

    private String ne_name;

    private String dev_type_name;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getWork_year() {
        return work_year;
    }

    public void setWork_year(Integer work_year) {
        this.work_year = work_year;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProducer_name() {
        return producer_name;
    }

    public void setProducer_name(String producer_name) {
        this.producer_name = producer_name;
    }

    public String getNe_name() {
        return ne_name;
    }

    public void setNe_name(String ne_name) {
        this.ne_name = ne_name;
    }

    public String getDev_type_name() {
        return dev_type_name;
    }

    public void setDev_type_name(String dev_type_name) {
        this.dev_type_name = dev_type_name;
    }
}
