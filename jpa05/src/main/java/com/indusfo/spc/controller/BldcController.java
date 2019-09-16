package com.indusfo.spc.controller;

import com.indusfo.spc.aspect.SystemControllerLog;
import com.indusfo.spc.pojo.Bldc;
import com.indusfo.spc.service.BldcService;
import com.indusfo.spc.vo.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/bldc")
public class BldcController {

    @Autowired
    private BldcService bldcService;

    /**
     * 修改不良对策
     *
     * @param bldc
     * @return
     */
    @RequestMapping(value = "/updateBldc",method= RequestMethod.POST)
    @SystemControllerLog(description="修改不良对策")
    @ResponseBody
    public JSONObject updateBldc (Bldc bldc){

        JSONObject json=bldcService.updateBldc(bldc);

        return json;
    }

    /**
     * 查询
     *
     * @param
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/queryBldc")
    @SystemControllerLog(description="查询不良对策")
    @ResponseBody
    public JSONObject queryDep(Bldc bldc) {

        JSONObject json = bldcService.selectAll(bldc);
        return json;

    }

    /**
     * 插入
     *
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/insertBldc", method= RequestMethod.POST)
    @SystemControllerLog(description="新增不良对策")
    @ResponseBody
    public JSONObject insertBldc(Bldc bldc) {

        JSONObject json = bldcService.insertBldc(bldc);
        return json;
    }

    /**
     * 启用/停用
     *
     * @param bldcIds, dataState
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/definesBldc", method= RequestMethod.POST)
    @SystemControllerLog(description="启用/停用不良对策")
    @ResponseBody
    public JSONObject definesBldc(Long[] bldcIds, Integer dataState) {

        JSONObject json = bldcService.deleteBldc(bldcIds, dataState);
        return json;
    }

    /**
     * 删除
     *
     * @param bldcIds, dataState
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/deleteBldc", method= RequestMethod.POST)
    @SystemControllerLog(description="删除不良对策")
    @ResponseBody
    public JSONObject delBldc(Long[] bldcIds, Integer dataState) {

        dataState = 2;
        JSONObject json = bldcService.deleteBldc(bldcIds, dataState);
        return json;
    }
}
