package com.demo.ssm.po.S_reliability;

public class t_weibull {

    private String NE_OBJ_ID;

    private String ALARM_TIME_D;

    private String BETA;

    private String ETA;

    private String R;

    private String FA;

    private String P;

    private String ERROR;
    private String province;
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getNE_OBJ_ID() {
        return NE_OBJ_ID;
    }

    public void setNE_OBJ_ID(String NE_OBJ_ID) {
        this.NE_OBJ_ID = NE_OBJ_ID;
    }

    public String getALARM_TIME_D() {
        return ALARM_TIME_D;
    }

    public void setALARM_TIME_D(String ALARM_TIME_D) {
        this.ALARM_TIME_D = ALARM_TIME_D;
    }

    public String getBETA() {
        return BETA;
    }

    public void setBETA(String BETA) {
        this.BETA = BETA;
    }

    public String getETA() {
        return ETA;
    }

    public void setETA(String ETA) {
        this.ETA = ETA;
    }

    public String getR() {
        return R;
    }

    public void setR(String r) {
        R = r;
    }

    public String getFA() {
        return FA;
    }

    public void setFA(String FA) {
        this.FA = FA;
    }

    public String getP() {
        return P;
    }

    public void setP(String p) {
        P = p;
    }

    public String getERROR() {
        return ERROR;
    }

    public void setERROR(String ERROR) {
        this.ERROR = ERROR;
    }
}
