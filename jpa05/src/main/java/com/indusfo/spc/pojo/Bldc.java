package com.indusfo.spc.pojo;

import com.indusfo.spc.common.pojo.BasePojo;

public class Bldc extends BasePojo {
    //不良对策ID
    private Integer bldcId;

    //不良对策名称
    private String bldcName;

    //不良对策类ID
    private Integer bldctypeId;
    //不良对策类名称
    private String bldctypeName;
    //备注
    private String remark;

    //状态码
    private Integer dataState;

    public Integer getBldcId() {
        return bldcId;
    }

    public void setBldcId(Integer bldcId) {
        this.bldcId = bldcId;
    }

    public String getBldcName() {
        return bldcName;
    }

    public void setBldcName(String bldcName) {
        this.bldcName = bldcName == null ? null : bldcName.trim();
    }

    public Integer getBldctypeId() {
        return bldctypeId;
    }

    public void setBldctypeId(Integer bldctypeId) {
        this.bldctypeId = bldctypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDataState() {
        return dataState;
    }

    public void setDataState(Integer dataState) {
        this.dataState = dataState;
    }

    public String getBldctypeName() {
        return bldctypeName;
    }

    public void setBldctypeName(String bldctypeName) {
        this.bldctypeName = bldctypeName;
    }
}