package com.demo.ssm.po.S_net;

public class t_spc_zone {

    private String Province;

    private String OBJ_ID;

    private String NAME;

    private String FULL_NAME;

    private String PAR_ZONE;

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

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

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public String getPAR_ZONE() {
        return PAR_ZONE;
    }

    public void setPAR_ZONE(String PAR_ZONE) {
        this.PAR_ZONE = PAR_ZONE;
    }
}
