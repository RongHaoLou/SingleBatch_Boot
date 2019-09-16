package com.indusfo.spc.service;

import com.indusfo.spc.pojo.Bldc;
import com.indusfo.spc.vo.JSONObject;


public interface BldcService {

    /**
     *
     * @param bldc
     * @Title updateBldc
     * @Description 修改不良对策
     */
    JSONObject updateBldc(Bldc bldc);


    /**
     *
     * @Title selectAll
     * @Description 查询所有不良对策
     */
    JSONObject selectAll(Bldc bldc);

    /**
     *
     * @Title insertBldc
     * @Description 添加不良对策
     */
    JSONObject insertBldc(Bldc bldc);

    /**
     *
     * @Title deleteBldc
     * @Description 1 启用 2 删除 3 停用  不良对策
     */
    JSONObject deleteBldc(Long[] bldcIds, Integer dataState);
}
