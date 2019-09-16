package com.indusfo.spc.service;

import com.indusfo.spc.pojo.BldcType;
import com.indusfo.spc.vo.JSONObject;

public interface BldcTypeService {

    //修改不良对策类
    JSONObject updateBldcType(BldcType bldcType);

    //新增不良对策类
    JSONObject insertBldcType(BldcType bldcType);

    //查询不良对策类
    JSONObject queryBldcType(BldcType bldcType);

    //删除不良对策类
    JSONObject deleteBldcType(Long[] bldcTypeIds);

    JSONObject detpsTree(BldcType bldcType);
}
