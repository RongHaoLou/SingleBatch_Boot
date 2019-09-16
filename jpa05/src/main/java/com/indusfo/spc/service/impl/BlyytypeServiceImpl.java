package com.indusfo.spc.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.indusfo.spc.exception.GlobalException;
import com.indusfo.spc.exception.ModifyFailedException;
import com.indusfo.spc.exception.ParamsErrorException;
import com.indusfo.spc.mapper.BlyyMapper;
import com.indusfo.spc.mapper.BlyytypeMapper;
import com.indusfo.spc.pojo.Blyy;
import com.indusfo.spc.pojo.Blyytype;
import com.indusfo.spc.service.BlyytypeService;
import com.indusfo.spc.vo.JSONObject;
import com.indusfo.spc.vo.XCommonUtil;
import com.indusfo.spc.vo.XTreeNode;
import com.indusfo.spc.vo.XTreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: jpa05
 * @Package: com.indusfo.spc.service.impl
 * @ClassName: BlyytypeServiceImpl
 * @Author: 熊冰
 * @Description: ${description}
 * @Date: 2019/8/9 14:37
 * @Version: 1.0
 * 不良原因类型 业务层
 */
@Service
public class BlyytypeServiceImpl implements BlyytypeService {
    //开启日志
    private static final Logger logger = LoggerFactory.getLogger(BlyytypeServiceImpl.class);

    @Autowired
    private BlyytypeMapper blyytypeMapper;

    @Autowired
    private BlyyMapper blyyMapper;

    //全查
    @Override
    public JSONObject listBlyytype(Blyytype blyytype) {
        try {


            // 获取一页显示多少行
            Integer pagesize = blyytype.getPagesize();
            // 获取查询第几页
            Integer pageindex = blyytype.getPageindex();

            //初始值


            // 若不为空计算从第几条开始查询
            if (pagesize != null && pageindex != null) {
                blyytype.setlN(pagesize * (pageindex - 1));
            }
            // 为空进行多条的分页查询
            // 调用Mapper分页查询方法

            List<Blyytype> listForPage = blyytypeMapper.selectAllBlyytype(blyytype);
            // 判断是否查询到数据
            if (listForPage.isEmpty()) {
                return JSONObject.oK("没有查询到数据", listForPage, 0);
            }
            // 查询有多少条数据条数
            if (pagesize != null && pageindex != null) {
                Integer count = blyytypeMapper.countBlyytype(blyytype);
                return JSONObject.oK(listForPage, count);
            }
            return JSONObject.oK(listForPage.get(0));


        } catch (GlobalException e) {
            // 捕获异常,打印并返回数
            logger.error(e.getMessage(), e);
            return JSONObject.build(500, e.getMessage());
        }
    }

    //新增 和修改
    @Override
    public JSONObject insertOrUpdateBlyytype(Blyytype blyytype) {
        try {
            if (blyytype.getBlyytypePid() == null) {
                blyytype.setBlyytypePid(0);
            }
            if (blyytype.getDataState() == null) {
                blyytype.setDataState(1);
            }
            // 校验传入参数

            //判断是否为空
            validate(blyytype);
            //判断是否重复
            JSONObject checkParamsNotRepeatJson = checkParamsNotRepeat(blyytype);

            if (checkParamsNotRepeatJson.isOk()) {
                if (blyytype.getBlyytypeId() != null) {// 有id,做更新

                    Integer row = blyytypeMapper.updateByPrimaryKeySelective(blyytype);
                    if (row == 0) {
                        throw new ModifyFailedException("更新不良原因失败！");
                    }
                    return JSONObject.oK("更新成功！",  blyytype.getBlyytypeId());
                } else {// 没id,做新增

                    Integer row = blyytypeMapper.insertSelective(blyytype);
                    if (row == 0) {
                        throw new ModifyFailedException("新增不良原因失败！");
                    }
                    return JSONObject.oK("新增成功！", blyytype.getBlyytypeId());
                }
            } else {
                throw new ParamsErrorException(checkParamsNotRepeatJson.getMsg());
            }


        } catch (GlobalException e) {
            logger.error(e.getMessage());
            return JSONObject.build(500, e.getMessage());
        }
    }
        //删除
    @Override
    public JSONObject deleteBlyytype(Long[] deteBlyytype, Integer lDataState) {
        try {
            //1.校验
            if (deteBlyytype == null)
                throw new ParamsErrorException("控制项目id不能为空");
            if (lDataState == null)
                throw new ParamsErrorException("控制项目状态不能为空");
            Integer flag = blyytypeMapper.countByblyytypePid(deteBlyytype);
            if (flag > 0) {
                throw new ParamsErrorException("该不良原因类型下还有子集");
            }
            int row = 0;
//            int row1 = blyyMapper.deleteByblyytypePids(deteBlyytype);
            for (Long i : deteBlyytype) {
                Blyy blyy = new Blyy();
                blyy.setBlyytypeId(i.intValue());
                int row1 = blyyMapper.countBlyy(blyy);
                if (row1 == 0) {
                    row = blyytypeMapper.deleteBlyytype(deteBlyytype, lDataState);
                } else {
                    throw new ParamsErrorException("该不良原因类型下还有关联的不良原因");
                }
            }


            String msg = "";
            switch (lDataState) {
                case 1:
                    msg = "启用";
                    break;
                case 2:
                    msg = "删除";
                    break;
                case 3:
                    msg = "停用";
            }

            if (row == 0) {
                // 判断传入的数据状态参数lDataState,返回相应信息
                switch (lDataState) {
                    case 1:
                        throw new ModifyFailedException(msg + "失败!");
                    case 2:
                        throw new ModifyFailedException(msg + "失败!");
                    case 3:
                        throw new ModifyFailedException(msg + "失败!");
                }
            }

            return JSONObject.oK(msg + "成功！", row);
        } catch (GlobalException e) {
            logger.error(e.getMessage());
            return JSONObject.build(500, e.getMessage());
        }
    }
//    @Override
//    public JSONObject blyytypeTree(Blyytype blyytype,Integer flag) {
//        List<XTreeNode> tree = null;
//        try {
//            tree = new ArrayList<>();
//            List<XTreeNode> nodes = new ArrayList<>();
//            List<Blyytype> list = blyytypeMapper.lookBlyytype(blyytype);
//            Blyytype blyytype1 = new Blyytype();
//            blyytype1.setBlyytypeId(0);
//            blyytype1.setBlyytypePid(0);
//            blyytype1.setBlyytypeName("全部");
//            list.add(0,blyytype1);
//            //导入树
//            if (null != list) {
//                for (Blyytype _MouldType : list) {
//                    XTreeNode node = new XTreeNode();
//                    node.setId(_MouldType.getBlyytypeId());
//                    node.setpId(_MouldType.getBlyytypePid() == 0 ? null : _MouldType.getBlyytypePid());
//                    node.setOpen(true);
//                    node.setTitle(_MouldType.getBlyytypeName());
//                    nodes.add(node);
//                }
//            }
//
//
//
//
//            tree = XCommonUtil.getTree(nodes, null);
//            tree.get(1).setSelected(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return JSONObject.oK(tree);
//    }
    //类型树
    @Override
    public JSONObject blyytypeTree(Blyytype blyytype) {
        List<XTreeNode> tree = null;
        try {
            tree = new ArrayList<>();
            List<XTreeNode> nodes = new ArrayList<>();
            List<Blyytype> list = blyytypeMapper.lookBlyytype(blyytype);


            //获取树的信息
            if(blyytype.getFlag()!=null) {
                //插入全部
                if(blyytype.getBlyytypeId()==null){
                    Blyytype blyytype1 = new Blyytype();
                    blyytype1.setBlyytypeId(0);
                    blyytype1.setBlyytypePid(0);
                    blyytype1.setBlyytypeName("全部");
                    list.add(0,blyytype1);
                }
                //生成树list
                List<XTreeNode> treelist = new ArrayList<>();
                for (Blyytype _MouldType : list) {
                    XTreeNode node = new XTreeNode();
                    node.setId(_MouldType.getBlyytypeId());
                    node.setpId(_MouldType.getBlyytypePid());
                    node.setTitle(_MouldType.getBlyytypeName());
                    treelist.add(node);
                }
                XTreeUtil xTreeUtil = new XTreeUtil(blyytype.getFlag(), treelist);
                List<Integer> childs = new ArrayList<>();
                List<Integer> parents = new ArrayList<>();
                childs=xTreeUtil.getChilds();
                parents=xTreeUtil.getParents();
                if (null != list) {
                    for (Blyytype _MouldType : list) {
                        XTreeNode node = new XTreeNode();
                        node.setId(_MouldType.getBlyytypeId());
                        node.setpId(_MouldType.getBlyytypePid() == 0 ? null : _MouldType.getBlyytypePid());
                        if(parents.contains(_MouldType.getBlyytypeId())){
                            node.setExpand(true);
                        }
                        if(!blyytype.getFlag().equals(0)){
                            if(blyytype.getFlag().equals(_MouldType.getBlyytypeId())){
                                node.setSelected(true);
                            }
                        }
                        node.setTitle(_MouldType.getBlyytypeName());
                        nodes.add(node);
                    }
                }
                tree = XCommonUtil.getTree(nodes, null);
                if(blyytype.getFlag().equals(0)){
                    tree.get(1).setSelected(true);
                }
            }else{
                if (null != list) {
                    for (Blyytype _MouldType : list) {
                        XTreeNode node = new XTreeNode();
                        node.setId(_MouldType.getBlyytypeId());
                        node.setpId(_MouldType.getBlyytypePid() == 0 ? null : _MouldType.getBlyytypePid());
                        node.setTitle(_MouldType.getBlyytypeName());
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

    //树的全查
    @Override
    public JSONObject lookBlyytype(Blyytype blyytype) {
        List<Blyytype> list = new ArrayList<>();
        Integer num = null;
        try {

            Integer pagesize = blyytype.getPagesize();
            Integer pageindex = blyytype.getPageindex();
            if (pagesize != null && pageindex != null) {
                blyytype.setlN(pagesize * (pageindex - 1));
            }
            list = blyytypeMapper.lookBlyytype(blyytype);
            num = blyytypeMapper.tallys(blyytype);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSONObject.oK("查询成功", list, num);
    }


//    @Override
//    public List<SimpleTreeNode> getBlyytypeTree(List<Integer> blyytypeList) {
//        return null;
//    }

    //验证是否重复
    private JSONObject checkParamsNotRepeat(Blyytype blyytype) {
        try {
            // 判断不良原因类型ID是否传入


            Blyytype blyytypeCheck = new Blyytype();
            blyytypeCheck.setBlyytypeName(blyytype.getBlyytypeName());
            if (blyytype.getBlyytypeId() != null) {
                // 剔除本条记录是否有重复
                blyytypeCheck.setBlyytypeId(blyytype.getBlyytypeId());
                int row = blyytypeMapper.countParamsNotRepeat(blyytypeCheck);
                if (row > 0) {
                    throw new ParamsErrorException("已存在该产品!");
                }
            } else {
                // 询所有是否有重复
                int row = blyytypeMapper.countParamsNotRepeat(blyytypeCheck);
                if (row > 0) {
                    throw new ParamsErrorException("已存在该产品!");
                }
            }
            return JSONObject.oK();

        } catch (GlobalException e) {
            // 捕获异常,打印并返回
            logger.error(e.getMessage(), e);
            return JSONObject.build(500, e.getMessage());
        }
    }

    //判断是否为空

    private void validate(Blyytype blyytype) {
        //不良原因类型id
//        Integer typeId = blyytype.getBlyytypeId();
        //不良原因类型名称
        String typeName = blyytype.getBlyytypeName();
        //不良原因类型父级
        Integer typePid = blyytype.getBlyytypePid();
        //不良原因类型名称
        String remark = blyytype.getRemark();

        if (StringUtils.isEmpty(typeName)) {
            throw new ParamsErrorException("不良原因类型名称不能为空");
        }
        if (typeName.getBytes().length > 50) {
            throw new ParamsErrorException("不良原因类型名过长");
        }
        if(remark!=null){
            if (remark.getBytes().length > 100) {
                throw new ParamsErrorException("说明过长");
            }
        }

//        if (typePid == null) {
//            throw new ParamsErrorException("不良原因类型父级不能为空");
//        }
//        if(typeId==null) {
//            throw new ParamsErrorException("不良原因类型id不能为空");
//        }


    }
}
