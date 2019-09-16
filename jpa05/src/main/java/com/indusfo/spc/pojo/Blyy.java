package com.indusfo.spc.pojo;

import com.indusfo.spc.common.pojo.BasePojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: IEIS2
 * @Package: com.indusfo.spc.pojo
 * @ClassName: Blyy
 * @Author:  熊冰
 * @Description: ${description}
 * @Date: 2019/8/8 15:41
 * @Version: 1.0
 * 不良原因pojo类
 */

@Table(name = "T_B77_BLYY")
public class Blyy  extends BasePojo {


    @Id
    @Column(name = "BLYY_ID",insertable=false)
    //不良原因编号
    private Integer blyyId;

    //不良原因名称
    private String blyyName;

    //不良原因类型
    private Integer blyytypeId;

    //不良原因类型名称
    private String blyytypeName;

    //备注
    private String remark;

    //数据状态 1 启用 2 删除 3停用
    private Integer dataState=1;


    public Integer getBlyyId() {
        return blyyId;
    }

    public void setBlyyId(Integer blyyId) {
        this.blyyId = blyyId;
    }

    public String getBlyyName() {
        return blyyName;
    }

    public void setBlyyName(String blyyName) {
        this.blyyName = blyyName;
    }

    public Integer getBlyytypeId() {
        return blyytypeId;
    }

    public void setBlyytypeId(Integer blyytypeId) {
        this.blyytypeId = blyytypeId;
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

    public String getBlyytypeName() {
        return blyytypeName;
    }

    public void setBlyytypeName(String blyytypeName) {
        this.blyytypeName = blyytypeName;
    }

    @Override
    public String toString() {
        return "Blyy{" +
                "blyyId=" + blyyId +
                ", blyyName='" + blyyName + '\'' +
                ", blyytypeId=" + blyytypeId +
                ", remark='" + remark + '\'' +
                ", dataState=" + dataState +
                '}';
    }

    public String BasePojoToString() {
        return super.toString();
    }
}
