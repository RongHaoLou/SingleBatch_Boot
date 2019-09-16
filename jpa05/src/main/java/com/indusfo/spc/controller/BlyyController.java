package com.indusfo.spc.controller;


import com.indusfo.spc.pojo.Blyy;
import com.indusfo.spc.service.BlyyService;
import com.indusfo.spc.vo.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Package: com.indusfo.spc.controller
 * @ClassName: BlyyController
 * @Author: 熊冰
 * @Description: ${description}
 * @Date: 2019/8/9 10:00
 * @Version: 1.0
 */
@Controller
@RequestMapping("/blyy")
public class BlyyController {
    /**
     * 注入service
     */

    @Resource
    BlyyService blyyService;

    /**
     * @Date 2019/8/10 9:36
     * @Author 熊冰
     * @Method insertBlyy
     * @Description
     * @param blyy
     * @Return com.indusfo.spc.vo.JSONObject
     * 新增
     */
    @RequestMapping(value="insertBlyy")
    @ResponseBody
    public JSONObject insertBlyy(Blyy blyy){
        blyy.setBlyyId(null);
        JSONObject json = blyyService.insertOrUpdateBlyy(blyy) ;
        return json;

    }

    /**
     * @Date 2019/8/10 9:40
     * @Author 熊冰
     * @Method delBlyy
     * @Description
     * @param ids
     * @Return com.indusfo.spc.vo.JSONObject
     * 删除
     */
    @RequestMapping(value="delBlyy")
    @ResponseBody
    public JSONObject delBlyy(Long[] ids){
        Integer   dataState = 2;
        JSONObject json = blyyService.deleteBlyy(ids,dataState);
        return json;

    }

    /**
     * @Date 2019/8/10 9:37
     * @Author 熊冰
     * @Method updateBlyy
     * @Description
     * @param blyy
     * @Return com.indusfo.spc.vo.JSONObject
     * 修改
     */
    @RequestMapping(value="updateBlyy")
    @ResponseBody
    public JSONObject updateBlyy(Blyy blyy){
        JSONObject json = blyyService.insertOrUpdateBlyy(blyy) ;
        return json;

    }

    /**
     * @Date 2019/8/10 9:38
     * @Author 熊冰
     * @Method queryBlyy
     * @Description
     * @param blyy
     * @Return com.indusfo.spc.vo.JSONObject
     * 查询
     */
    @RequestMapping(value="queryBlyy")
    @ResponseBody
    public JSONObject queryBlyy(Blyy blyy){
        JSONObject json = blyyService.listBlyy(blyy);
        return json;
    }


    /**
     * @Date 2019/8/10 9:40
     * @Author 熊冰
     * @Method delBlyy
     * @Description
     * @param ids
     * @Return com.indusfo.spc.vo.JSONObject
     * 停用
     */
    @RequestMapping(value="stopBlyy")
    @ResponseBody
    public JSONObject stopBlyy(Long[] ids){
        Integer   dataState = 3;
        JSONObject json = blyyService.deleteBlyy(ids,dataState);
        return json;

    }



}
