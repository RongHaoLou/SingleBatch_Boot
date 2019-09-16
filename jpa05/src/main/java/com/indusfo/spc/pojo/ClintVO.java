package com.indusfo.spc.pojo;

public class ClintVO extends Clint {

    //下线工序名称
    private String offlineName;

    //
    private Long[] shuzu;

    public Long[] getShuzu() {
        return shuzu;
    }

    public void setShuzu(Long[] shuzu) {
        this.shuzu = shuzu;
    }

    public String getOfflineName() {
        return offlineName;
    }

    public void setOfflineName(String offlineName) {
        this.offlineName = offlineName;
    }
}
