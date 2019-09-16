package com.indusfo.spc.controller;

import com.indusfo.spc.aspect.SystemControllerLog;
import com.indusfo.spc.pojo.BldcType;
import com.indusfo.spc.service.BldcTypeService;
import com.indusfo.spc.vo.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 不良对策类控制层
 */
@Controller
@RequestMapping("/bldcType")
public class BldcTypeController {


    @Autowired
    private BldcTypeService bldcTypeService;

    /**
     * 更新不良对策类
     *
     * @param bldcType
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/updateBldcType", method= RequestMethod.POST)
    @SystemControllerLog(description="修改不良对策类")
    @ResponseBody
    public JSONObject updateBldcType(BldcType bldcType) {

        JSONObject json = bldcTypeService.updateBldcType(bldcType);
        return json;

    }

    /**
     * 插入
     *
     * @param bldcType
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/insertBldcType", method= RequestMethod.POST)
    @SystemControllerLog(description="新增部门")
    @ResponseBody
    public JSONObject insertBldcType(BldcType bldcType) {

        JSONObject json = bldcTypeService.insertBldcType(bldcType);
        return json;

    }

    /**
     * 查询不良对策类树
     *
     * @param bldcType
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/queryBldcTypeTree")
    @SystemControllerLog(description="查询不良对策类树")
    @ResponseBody
    public JSONObject queryBldcTypeTree(BldcType bldcType) {

        JSONObject json = bldcTypeService.detpsTree(bldcType);
        return json;

    }

    /**
     * 查询子类不良对策类
     *
     * @param bldcType
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/queryBldcType")
    @SystemControllerLog(description="查询子类不良对策类")
    @ResponseBody
    public JSONObject queryBldcType(BldcType bldcType) {

        JSONObject json = bldcTypeService.queryBldcType(bldcType);
        return json;

    }

    /**
     * 删除
     *
     * @param bldcTypeIds
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/deleteBldcType", method= RequestMethod.POST)
    @SystemControllerLog(description="删除不良对策类")
    @ResponseBody
    public JSONObject delBldcType(Long[] bldcTypeIds) {
        JSONObject json = bldcTypeService.deleteBldcType(bldcTypeIds);
        return json;
    }

}
