package com.indusfo.spc.pojo;



public class BldcType {

    //不良对策类ID
    private Integer bldcTypeId;

    //不良对策类名称
    private String bldcTypeName;

    //所属不良对策类ID(0为最大)
    private Integer bldcTypePid;

    //备注
    private String remark;

    //状态
    private Integer dataState;

    //标记
    private Integer flag;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getBldcTypeId() {
        return bldcTypeId;
    }

    public void setBldcTypeId(Integer bldcTypeId) {
        this.bldcTypeId = bldcTypeId;
    }

    public String getBldcTypeName() {
        return bldcTypeName;
    }

    public void setBldcTypeName(String bldcTypeName) {
        this.bldcTypeName = bldcTypeName;
    }

    public Integer getBldcTypePid() {
        return bldcTypePid;
    }

    public void setBldcTypePid(Integer bldcTypePid) {
        this.bldcTypePid = bldcTypePid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDataState() {
        return dataState;
    }

    public void setDataState(Integer dataState) {
        this.dataState = dataState;
    }
}