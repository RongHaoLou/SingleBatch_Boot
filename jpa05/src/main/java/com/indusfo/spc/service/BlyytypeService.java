package com.indusfo.spc.service;

import com.indusfo.spc.pojo.Blyytype;
import com.indusfo.spc.vo.JSONObject;

/**
 * @Date 2019/8/9 14:39
 * @Author 熊冰
 * @Method
 * @Description
 * 不良原因类型 业务接口
 * @Return
 */
public interface BlyytypeService  {
    /**
     * @Date 2019/8/9 14:48
     * @Author 熊冰
     * @Method listBlyytype
     * @Description
     * @param blyytype
     * @Return com.indusfo.spc.vo.JSONObject
     * 不良原因类型 全查
     */
    JSONObject listBlyytype(Blyytype blyytype);

    /**
     * @Date 2019/8/9 14:49
     * @Author 熊冰
     * @Method insertOrUpdateBlyytype
     * @Description
     * @param blyytype
     * @Return com.indusfo.spc.vo.JSONObject
     * 不良原因类型 增加或修改
     */
    JSONObject insertOrUpdateBlyytype(Blyytype blyytype);

    /**
     * @Date 2019/8/9 14:49
     * @Author 熊冰
     * @Method deleteBlyytype
     * @Description
     * @param deteBlyytype
     * @param lDataState
     * @Return com.indusfo.spc.vo.JSONObject
     * 不良原因类型 删除
     */
    JSONObject deleteBlyytype(Long[] deteBlyytype, Integer lDataState);

    /**
     * @Date 2019/8/10 13:44
     * @Author 熊冰
     * @Method detpsTree
     * @Description
     * @param blyytype
     * @Return com.indusfo.spc.vo.JSONObject
     *  树
     */
    JSONObject blyytypeTree(Blyytype blyytype);

    /**
     * @Date 2019/8/10 13:47
     * @Author 熊冰
     * @Method lookBlyytype
     * @Description
     * @param blyytype
     * @Return com.indusfo.spc.vo.JSONObject
     *  c查询数量
     */
     JSONObject lookBlyytype(Blyytype blyytype);
//    /**
//     * @Date 2019/8/10 13:06
//     * @Author 熊冰
//     * @Method getBlyytypeTree
//     * @Description
//     * @param blyytypeList
//     * @Return java.util.List<com.indusfo.spc.vo.SimpleTreeNode>
//     */
//    List<SimpleTreeNode> getBlyytypeTree(List<Integer> blyytypeList);

//    /**
//     * @Date 2019/8/9 14:49
//     * @Author 熊冰
//     * @Method selectBlyytype
//     * @Description
//     * @param lDataState
//     * @Return com.indusfo.spc.vo.JSONObject
//     * 不良原因类型 单查
//     */
//    JSONObject selectBlyytype(Integer lDataState);

}
