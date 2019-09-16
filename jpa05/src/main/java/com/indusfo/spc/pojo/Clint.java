package com.indusfo.spc.pojo;

import com.indusfo.spc.common.pojo.BasePojo;

public class Clint extends BasePojo {

    //客户端ID
    private Integer clientId;

    //客户端编号
    private String clientCode;

    //客户类型
    private Integer clientType;

    //下线工序
    private Integer proXia;

    //扫描类型
    private Integer scanType;

    //是否自动打印    0 否，1 是
    private Integer autoprint;

    //是否允许跳序    0 否，1 是
    private Integer proJump;

    //是否记录错误物料    0 否，1 是
    private Integer recodeErr;

    //是否托盘绑定    0 否，1 是
    private Integer pabang;

    //批号是否一次开始完成    0 否，1 是
    private Integer batchnoBegin;

    //下线提醒    0 否，1 是
    private Integer xiatip;

    //备注
    private String remark;

    //数据状态
    private Integer dataState;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode == null ? null : clientCode.trim();
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Integer getProXia() {
        return proXia;
    }

    public void setProXia(Integer proXia) {
        this.proXia = proXia;
    }

    public Integer getScanType() {
        return scanType;
    }

    public void setScanType(Integer scanType) {
        this.scanType = scanType;
    }

    public Integer getAutoprint() {
        return autoprint;
    }

    public void setAutoprint(Integer autoprint) {
        this.autoprint = autoprint;
    }

    public Integer getProJump() {
        return proJump;
    }

    public void setProJump(Integer proJump) {
        this.proJump = proJump;
    }

    public Integer getRecodeErr() {
        return recodeErr;
    }

    public void setRecodeErr(Integer recodeErr) {
        this.recodeErr = recodeErr;
    }

    public Integer getPabang() {
        return pabang;
    }

    public void setPabang(Integer pabang) {
        this.pabang = pabang;
    }

    public Integer getBatchnoBegin() {
        return batchnoBegin;
    }

    public void setBatchnoBegin(Integer batchnoBegin) {
        this.batchnoBegin = batchnoBegin;
    }

    public Integer getXiatip() {
        return xiatip;
    }

    public void setXiatip(Integer xiatip) {
        this.xiatip = xiatip;
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
}