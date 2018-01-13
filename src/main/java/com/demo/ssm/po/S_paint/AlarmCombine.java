package com.demo.ssm.po.S_paint;

import org.springframework.stereotype.Repository;

@Repository
public class AlarmCombine {
    private String NE_OBJ_ID;

    private String ALARM_EMS_TIME_DAY;

    private String ALARM_EMS_TIME;

    private String ALARM_EMS_RESUME_TIME;

    private String ALARM_CAUSE;

    private String NE_NAME;

    private String DEV_TYPE_NAME;

    private String PRODUCER_NAME;

    private String PRODUCER_ID;

    private Integer WORK_YEAR;

    private Integer DEV_TYPE;

    private String PROVINCE;

    public String getNE_OBJ_ID() {
        return NE_OBJ_ID;
    }

    public void setNE_OBJ_ID(String NE_OBJ_ID) {
        this.NE_OBJ_ID = NE_OBJ_ID;
    }

    public String getALARM_EMS_TIME_DAY() {
        return ALARM_EMS_TIME_DAY;
    }

    public void setALARM_EMS_TIME_DAY(String ALARM_EMS_TIME_DAY) {
        this.ALARM_EMS_TIME_DAY = ALARM_EMS_TIME_DAY;
    }

    public String getALARM_EMS_TIME() {
        return ALARM_EMS_TIME;
    }

    public void setALARM_EMS_TIME(String ALARM_EMS_TIME) {
        this.ALARM_EMS_TIME = ALARM_EMS_TIME;
    }

    public String getALARM_EMS_RESUME_TIME() {
        return ALARM_EMS_RESUME_TIME;
    }

    public void setALARM_EMS_RESUME_TIME(String ALARM_EMS_RESUME_TIME) {
        this.ALARM_EMS_RESUME_TIME = ALARM_EMS_RESUME_TIME;
    }

    public String getALARM_CAUSE() {
        return ALARM_CAUSE;
    }

    public void setALARM_CAUSE(String ALARM_CAUSE) {
        this.ALARM_CAUSE = ALARM_CAUSE;
    }

    public String getNE_NAME() {
        return NE_NAME;
    }

    public void setNE_NAME(String NE_NAME) {
        this.NE_NAME = NE_NAME;
    }

    public String getDEV_TYPE_NAME() {
        return DEV_TYPE_NAME;
    }

    public void setDEV_TYPE_NAME(String DEV_TYPE_NAME) {
        this.DEV_TYPE_NAME = DEV_TYPE_NAME;
    }

    public String getPRODUCER_NAME() {
        return PRODUCER_NAME;
    }

    public void setPRODUCER_NAME(String PRODUCER_NAME) {
        this.PRODUCER_NAME = PRODUCER_NAME;
    }

    public String getPRODUCER_ID() {
        return PRODUCER_ID;
    }

    public void setPRODUCER_ID(String PRODUCER_ID) {
        this.PRODUCER_ID = PRODUCER_ID;
    }

    public Integer getWORK_YEAR() {
        return WORK_YEAR;
    }

    public void setWORK_YEAR(Integer WORK_YEAR) {
        this.WORK_YEAR = WORK_YEAR;
    }

    public Integer getDEV_TYPE() {
        return DEV_TYPE;
    }

    public void setDEV_TYPE(Integer DEV_TYPE) {
        this.DEV_TYPE = DEV_TYPE;
    }

    public String getPROVINCE() {
        return PROVINCE;
    }

    public void setPROVINCE(String PROVINCE) {
        this.PROVINCE = PROVINCE;
    }
}
