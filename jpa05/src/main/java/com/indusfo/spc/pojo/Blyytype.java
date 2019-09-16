package com.indusfo.spc.pojo;

import com.indusfo.spc.common.pojo.BasePojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: IEIS2
 * @Package: com.indusfo.spc.pojo
 * @ClassName: Blyytype
 * @Author: 熊冰
 * @Description: ${description}
 * @Date: 2019/8/8 16:44
 * @Version: 1.0
 * 不良原因类型 pojo类
 */

@Table(name = "T_B76_BLYYTYPE")
public class Blyytype   extends BasePojo {

    @Id
    @Column(name = "BLYYTYPE_ID",insertable=false)
    //类型编号
    private Integer blyytypeId;

    //类型名称
    private String blyytypeName;

    //类型父编号 顶级父标签为0
    private Integer blyytypePid;

    //备注
    private String remark;

    //数据状态
    //1，正常，2.删除，3、停用
    private Integer dataState= 1;

    //标记
    private Integer flag;


    public Integer getBlyytypeId() {
        return blyytypeId;
    }

    public void setBlyytypeId(Integer blyytypeId) {
        this.blyytypeId = blyytypeId;
    }

    public String getBlyytypeName() {
        return blyytypeName;
    }

    public void setBlyytypeName(String blyytypeName) {
        this.blyytypeName = blyytypeName;
    }

    public Integer getBlyytypePid() {
        return blyytypePid;
    }

    public void setBlyytypePid(Integer blyytypePid) {
        this.blyytypePid = blyytypePid;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Blyytype{" +
                "blyytypeId=" + blyytypeId +
                ", blyytypeName='" + blyytypeName + '\'' +
                ", blyytypePid=" + blyytypePid +
                ", remark='" + remark + '\'' +
                ", dataState=" + dataState +
                '}';
    }


    public String BasePojoToString() {
        return super.toString();
    }

}
