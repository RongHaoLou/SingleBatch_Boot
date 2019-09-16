package com.indusfo.spc.service.impl;

import com.indusfo.spc.exception.GlobalException;
import com.indusfo.spc.exception.ModifyFailedException;
import com.indusfo.spc.exception.ParamsErrorException;
import com.indusfo.spc.mapper.BldcMapper;
import com.indusfo.spc.mapper.BldcTypeMapper;
import com.indusfo.spc.pojo.Bldc;
import com.indusfo.spc.pojo.BldcType;
import com.indusfo.spc.service.BldcTypeService;
import com.indusfo.spc.vo.JSONObject;
import com.indusfo.spc.vo.XCommonUtil;
import com.indusfo.spc.vo.XTreeNode;
import com.indusfo.spc.vo.XTreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class BldcTypeServiceImpl implements BldcTypeService {

    private  static  final Logger logger = LoggerFactory.getLogger(BldcServiceImpl.class);

    @Autowired
    private BldcTypeMapper bldcTypeMapper;

    @Autowired
    private BldcMapper bldcMapper;

    /**
     * 更新不良对策类
     *
     * @param bldcType
     * @return JSONObject
     */
    @Override
    public JSONObject updateBldcType(BldcType bldcType) {
        String remark = bldcType.getRemark();
        if(remark == null) {
            remark = "";
        }
        if(bldcType.getBldcTypePid()==null){
            bldcType.setBldcTypePid(0);
        }
        try {
            if(bldcType.getBldcTypeId() == null) {
                throw new ParamsErrorException("请选择要更新的工序");
            }
            checkParam(bldcType, remark);

            // 调用存储过程
            int row = bldcTypeMapper.updateBldcType(bldcType);
            if(row == 0) {
                throw new ModifyFailedException("数据更新失败！");
            }
            return JSONObject.oK("更新成功！");
        } catch (GlobalException e) {  //这里只捕获自定义异常
            logger.error(e.getMessage(), e);
            return JSONObject.build(403, e.getMessage());
        }
    }

    /**
     * 获取不良对策类树
     *
     * @param bldcType
     * @return JSONObject
     */
    @Override
    public JSONObject detpsTree(BldcType bldcType) {
        List<XTreeNode> tree = null;

        try {
            tree = new ArrayList<>();
            List<XTreeNode> nodes = new ArrayList<>();
            List<BldcType> list = bldcTypeMapper.AllBldcType(bldcType);
            // 树中加入全部
            if(bldcType.getFlag()!=null) {
                BldcType bldcType1 = new BldcType();
                bldcType1.setBldcTypeId(0);
                bldcType1.setBldcTypePid(0);
                bldcType1.setBldcTypeName("全部");
                list.add(0, bldcType1);
                //生成树list
                if (null != list) {
                    List<XTreeNode> treelist = new ArrayList<>();
                    for (BldcType _MouldType : list) {
                        XTreeNode node = new XTreeNode();
                        node.setId(_MouldType.getBldcTypeId());
                        node.setpId(_MouldType.getBldcTypePid());
                        node.setTitle(_MouldType.getBldcTypeName());
                        treelist.add(node);
                    }
                    XTreeUtil xTreeUtil = new XTreeUtil(bldcType.getFlag(), treelist);
                    List<Integer> childs = new ArrayList<>();
                    List<Integer> parents = new ArrayList<>();
                    childs = xTreeUtil.getChilds();
                    parents = xTreeUtil.getParents();
                    if (null != list) {
                        for (BldcType _MouldType : list) {
                            XTreeNode node = new XTreeNode();
                            node.setId(_MouldType.getBldcTypeId());
                            node.setpId(_MouldType.getBldcTypePid() == 0 ? null : _MouldType.getBldcTypePid());
                            if (parents.contains(_MouldType.getBldcTypeId())) {
                                node.setExpand(true);
                            }
                            if (!bldcType.getFlag().equals(0)) {
                                if (bldcType.getFlag().equals(_MouldType.getBldcTypeId())) {
                                    node.setSelected(true);
                                }
                            }
                            node.setTitle(_MouldType.getBldcTypeName());
                            nodes.add(node);
                        }
                    }
                }
                tree = XCommonUtil.getTree(nodes, null);
                if(bldcType.getFlag().equals(0)){
                    tree.get(1).setSelected(true);
                }
            }else {
                if (null != list) {
                    for (BldcType _MouldType : list) {
                        XTreeNode node = new XTreeNode();
                        node.setId(_MouldType.getBldcTypeId());
                        node.setpId(_MouldType.getBldcTypePid() == 0 ? null : _MouldType.getBldcTypePid());
                        node.setTitle(_MouldType.getBldcTypeName());
                        nodes.add(node);
                    }
                }
                tree = XCommonUtil.getTree(nodes, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.oK(tree);
    }
    /**
     * 添加不良对策类
     *
     * @param bldcType
     * @return JSONObject
     */
    @Override
    public JSONObject insertBldcType(BldcType bldcType) {
        bldcType.setDataState(1);
        String remark = bldcType.getRemark();
        if(remark == null) {
            remark = "";
        }
        try {
            if(bldcType.getBldcTypeId() != null) {
                throw new ParamsErrorException("新增不良对策时，不能填写id");
            }
            if(bldcType.getBldcTypePid()==null){
                bldcType.setBldcTypePid(0);
            }
            checkParam(bldcType, remark);

            int	row = bldcTypeMapper.insertBldcType(bldcType);

            if(row == 0) {
                throw new ModifyFailedException("数据新增失败！");
            }
            return JSONObject.oK("新增成功！",bldcType.getBldcTypeId());
        } catch (GlobalException e) {  //这里只捕获自定义异常
            logger.error(e.getMessage(), e);
            return JSONObject.build(403, e.getMessage());
        }
    }

    @Override
    public JSONObject queryBldcType(BldcType bldcType) {

        return null;
    }

    /**
     * 删除不良对策类
     *
     * @param bldcTypeIds
     * @return JSONObject
     */
    // 开启事务
    @Transactional
    @Override
    public JSONObject deleteBldcType(Long[] bldcTypeIds){
        try {
            if (bldcTypeIds == null) {
                throw new ParamsErrorException("不良对策类id不能为空");
            }
            String msg = "";
            for(Long bldcTypeId:bldcTypeIds){
                // 判断是否有子集
                BldcType bldcType  = new BldcType();
                bldcType.setBldcTypePid(bldcTypeId.intValue());
                List<BldcType> bldcTypeList = bldcTypeMapper.AllBldcType(bldcType);
                if(bldcTypeList.size()!=0){
                    throw new ParamsErrorException("该不良对策类下还有子集");
                }
                // 判断下面是否有不良对策
                Bldc bldc  = new Bldc();
                bldc.setBldctypeId(bldcTypeId.intValue());
                List<Bldc> bldcs = bldcMapper.selectAll(bldc);
                if(bldcs.size()>0){
                    throw new ParamsErrorException("该不良对策类下还有不良对策");
                }
                // 执行删除
                int row = bldcTypeMapper.delteBldcType(bldcTypeIds);
                if (row == 0) {
                    new ModifyFailedException("删除失败");
                } else {
                    msg = "删除成功";
                }
            }
            return JSONObject.oK(msg);
        } catch (GlobalException e) {
            logger.error(e.getMessage(), e);
            return JSONObject.build(403, e.getMessage());
        }
    }

    /**
     * 参数校验
     *
     * @param bldcType
     * @param vcRemark
     */
    private void checkParam(BldcType bldcType, String vcRemark) {
        if (bldcType.getBldcTypePid() == null) {
            throw new ParamsErrorException("不良对策类型不能为空");
        }
        if (StringUtils.isEmpty(bldcType.getBldcTypeName())) {
            throw new ParamsErrorException("请填写不良对策名称");
        }
        if (bldcType.getBldcTypeName().getBytes().length > 50) {
            throw new ParamsErrorException("名称超过规定长度");
        }
        if (vcRemark.getBytes().length > 100) {
            throw new ParamsErrorException("说明字数超过规定长度");
        }
        // 名称不能重复
        Integer depCounts = bldcTypeMapper.selectBldcTypeName(bldcType);
        if (depCounts !=0) {
            throw new ParamsErrorException("该不良对策已存在");
        }
    }
}
