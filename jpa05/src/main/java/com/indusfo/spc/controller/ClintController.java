package com.indusfo.spc.controller;

import com.indusfo.spc.aspect.SystemControllerLog;
import com.indusfo.spc.pojo.ClintVO;
import com.indusfo.spc.service.ClintService;
import com.indusfo.spc.vo.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/clint")
public class ClintController {

    @Autowired
    private ClintService clintService;

    /**
     * 修改客户端设置
     *
     * @param clintVO
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value = "/updateClint",method= RequestMethod.POST)
    @SystemControllerLog(description="修改客户端设置")
    @ResponseBody
    public JSONObject updateBldc (ClintVO clintVO){

        JSONObject json=clintService.updateClint(clintVO);

        return json;
    }

    /**
     * 查询
     *
     * @param clintVO
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/queryClint")
    @SystemControllerLog(description="查询客户端设置")
    @ResponseBody
    public JSONObject queryClint(ClintVO clintVO) {

        JSONObject json = clintService.selectAll(clintVO);
        return json;

    }

    /**
     * 插入
     *
     * @param clintVO
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/insertClint", method= RequestMethod.POST)
    @SystemControllerLog(description="新增客户端设置")
    @ResponseBody
    public JSONObject insertClint(ClintVO clintVO) {

        JSONObject json = clintService.insertBldc(clintVO);
        return json;
    }

    /**
     * 启用/停用
     *
     * @param clintIds, dataState
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/definesClint", method= RequestMethod.POST)
    @SystemControllerLog(description="启用/停用客户端设置")
    @ResponseBody
    public JSONObject definesClint(Long[] clintIds, Integer dataState) {

        JSONObject json = clintService.deleteClint(clintIds, dataState);
        return json;
    }

    /**
     * 删除
     *
     * @param clintIds, dataState
     * @return com.indusfo.spc.vo.JSONObject
     */
    @RequestMapping(value="/deleteClint", method= RequestMethod.POST)
    @SystemControllerLog(description="删除客户端设置")
    @ResponseBody
    public JSONObject deleteClint(Long[] clintIds, Integer dataState) {

        dataState = 2;
        JSONObject json = clintService.deleteClint(clintIds, dataState);
        return json;
    }
}
