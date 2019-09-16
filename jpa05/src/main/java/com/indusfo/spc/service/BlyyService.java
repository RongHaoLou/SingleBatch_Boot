package com.indusfo.spc.service;

import com.indusfo.spc.pojo.Blyy;
import com.indusfo.spc.vo.JSONObject;

/**
 * @Package: com.indusfo.spc.service
 * @ClassName: BlyyService
 * @Author: 熊冰
 * @Description: ${description}
 * @Date: 2019/8/12 14:21
 * @Version: 1.0
 * 不良原因 业务接口
 */
public interface BlyyService {
    /**
     * @Date 2019/8/12 14:26
     * @Author 熊冰
     * @Method listBlyy
     * @Description
     * @param blyy
     * @Return com.indusfo.spc.vo.JSONObject
     * 不良原因 全查
     */
    JSONObject listBlyy(Blyy blyy);
    /**
     * @Date 2019/8/12 14:26
     * @Author 熊冰
     * @Method insertOrUpdateBlyy
     * @Description
     * @param blyy
     * @Return com.indusfo.spc.vo.JSONObject
     * 不良原因 增加或修改
     */
    JSONObject insertOrUpdateBlyy(Blyy blyy);
    /**
     * @Date 2019/8/12 14:26
     * @Author 熊冰
     * @Method deleteBlyy
     * @Description
     * @param deteBlyy
     * @param lDataState
     * @Return com.indusfo.spc.vo.JSONObject
     * 不良原因  删除
     */
    JSONObject deleteBlyy(Long[] deteBlyy, Integer lDataState);

}
