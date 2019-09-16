package com.indusfo.spc.controller;

import com.indusfo.spc.pojo.Blyytype;
import com.indusfo.spc.service.BlyytypeService;
import com.indusfo.spc.vo.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Package: com.indusfo.spc.controller
 * @ClassName: BlyytypeController
 * @Author: 熊冰
 * @Description: ${description}
 * @Date: 2019/8/9 15:55
 * @Version: 1.0
 */
@Controller
@RequestMapping("/blyytype")
public class BlyytypeController {
    /**
     * 注入service
     */

    @Resource
    BlyytypeService blyytypeService;

    /**
     * @Date 2019/8/10 9:36
     * @Author 熊冰
     * @Method insertBlyytype
     * @Description
     * @param blyytype
     * @Return com.indusfo.spc.vo.JSONObject
     * 新增
     */
    @RequestMapping(value="insertBlyytype")
    @ResponseBody
    public JSONObject insertBlyytype(Blyytype blyytype){
        blyytype.setBlyytypeId(null);
        JSONObject json = blyytypeService.insertOrUpdateBlyytype(blyytype) ;
        return json;

    }

    /**
     * @Date 2019/8/10 9:40
     * @Author 熊冰
     * @Method delBlyytype
     * @Description
     * @param ids
     * @Return com.indusfo.spc.vo.JSONObject
     * 删除
     */
    @RequestMapping(value="delBlyytype")
    @ResponseBody
    public JSONObject delBlyytype(Long[] ids){
        Integer   dataState = 2;
        JSONObject json = blyytypeService.deleteBlyytype(ids,dataState);
        return json;

    }

    /**
     * @Date 2019/8/10 9:37
     * @Author 熊冰
     * @Method updateBlyytype
     * @Description
     * @param blyytype
     * @Return com.indusfo.spc.vo.JSONObject
     * 修改
     */
    @RequestMapping(value="updateBlyytype")
    @ResponseBody
    public JSONObject updateBlyytype(Blyytype blyytype){
        JSONObject json = blyytypeService.insertOrUpdateBlyytype(blyytype) ;
        return json;

    }

    /**
     * @Date 2019/8/10 9:38
     * @Author 熊冰
     * @Method queryBlyytype
     * @Description
     * @param blyytype
     * @Return com.indusfo.spc.vo.JSONObject
     * 查询
     */
    @RequestMapping(value="queryBlyytype")
    @ResponseBody
    public JSONObject queryBlyytype(Blyytype blyytype){
        JSONObject json = blyytypeService.listBlyytype(blyytype);
        return json;

    }


    /**
     * @Date 2019/8/10 9:40
     * @Author 熊冰
     * @Method delBlyytype
     * @Description
     * @param ids
     * @Return com.indusfo.spc.vo.JSONObject
     * 停用
     */
    @RequestMapping(value="stopBlyytype")
    @ResponseBody
    public JSONObject stopBlyytype(Long[] ids){
        Integer   dataState = 3;
        JSONObject json = blyytypeService.deleteBlyytype(ids,dataState);
        return json;

    }

    /**
     * @Date 2019/8/12 9:17
     * @Author 熊冰
     * @Method treeBlyytype
     * @Description
     * @param blyytype
     * @Return com.indusfo.spc.vo.JSONObject
     * 树
     */
    @RequestMapping(value="treeBlyytype")
    @ResponseBody
    public JSONObject treeBlyytype(Blyytype blyytype){
        JSONObject json = blyytypeService.blyytypeTree(blyytype);
        return json;

    }





}
