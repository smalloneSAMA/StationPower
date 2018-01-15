package com.demo.ssm.po.S_net;

public class LowReliability {
    private String province;
    private String OBJ_ID;
    private String NAME;
    private long BUZ_TYPE;
    private double BuzRe;


    public String getOBJ_ID() {
        return OBJ_ID;
    }

    public void setOBJ_ID(String OBJ_ID) {
        this.OBJ_ID = OBJ_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public long getBUZ_TYPE() {
        return BUZ_TYPE;
    }

    public void setBUZ_TYPE(long BUZ_TYPE) {
        this.BUZ_TYPE = BUZ_TYPE;
    }

    public double getBuzRe() {
        return BuzRe;
    }

    public void setBuzRe(double buzRe) {
        BuzRe = buzRe;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
