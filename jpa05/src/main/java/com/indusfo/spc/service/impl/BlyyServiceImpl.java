package com.indusfo.spc.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.indusfo.spc.exception.GlobalException;
import com.indusfo.spc.exception.ModifyFailedException;
import com.indusfo.spc.exception.ParamsErrorException;
import com.indusfo.spc.mapper.BlyyMapper;
import com.indusfo.spc.mapper.BlyytypeMapper;
import com.indusfo.spc.pojo.Blyy;
import com.indusfo.spc.pojo.Blyytype;
import com.indusfo.spc.service.BlyyService;
import com.indusfo.spc.vo.JSONObject;
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
 * @ClassName: BlyyServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/8/12 14:27
 * @Version: 1.0
 */
@Service
public class BlyyServiceImpl implements BlyyService {

    //日志记录
    private static final Logger logger = LoggerFactory.getLogger(BlyyServiceImpl.class);

    @Autowired
    private BlyyMapper blyyMapper;

    @Autowired
    private BlyytypeMapper blyytypeMapper;

    //查询
    @Override
    public JSONObject listBlyy(Blyy blyy) {
        try {


            // 获取一页显示多少行
            Integer pagesize = blyy.getPagesize();
            // 获取查询第几页
            Integer pageindex = blyy.getPageindex();
            //初始值
            // 若不为空计算从第几条开始查询
            if (pagesize != null && pageindex != null) {
                blyy.setlN(pagesize * (pageindex - 1));
            }
            Integer blyytypeId = null;
            if (blyy.getBlyytypeId() != null) {
                blyytypeId = blyy.getBlyytypeId();
            }
            //是否有类型
            System.out.println(blyytypeId);
            //无类型查询第一个类型数据
            if (blyytypeId == null) {
                //获取第一个数据
                Blyytype blyytype = new Blyytype();
                blyytype.setBlyytypePid(0);
                List<Blyytype> blyytypeList = blyytypeMapper.selectAllBlyytype(blyytype);
                if (blyytypeList != null && blyytypeList.size() >= 1) {
                    blyytype = blyytypeList.get(0);
//                    blyy.setBlyytypeId(blyytype.getBlyytypeId());
                }
//                blyytype.getBlyytypeId();
                //生成树
                List<XTreeNode> tree = new ArrayList<>();
                List<XTreeNode> nodes = new ArrayList<>();
                //查询全部
                List<Blyytype> list = blyytypeMapper.lookBlyytype(new Blyytype());
                List<XTreeNode> treelist = new ArrayList<>();
                //将树放入list
                for (Blyytype _MouldType : list) {
                    XTreeNode node = new XTreeNode();
                    node.setId(_MouldType.getBlyytypeId());
                    node.setpId(_MouldType.getBlyytypePid());
                    node.setTitle(_MouldType.getBlyytypeName());
                    treelist.add(node);
                }
                System.out.println(blyytype.getBlyytypeId());
                XTreeUtil xTreeUtil = new XTreeUtil(blyytype.getBlyytypeId(), treelist);
                List<Integer> childs = new ArrayList<>();
                List<Integer> parents = new ArrayList<>();
                childs=xTreeUtil.getChilds();
                parents=xTreeUtil.getParents();
//                List<Integer> ids=(List<Integer>)childs;
                System.out.println(childs);
                List<Blyy> listForPage = blyyMapper.queryListBlyy(blyy, childs);
                Integer count = blyyMapper.countListBlyy(blyy, childs);
                return JSONObject.oK(listForPage, count);


            } else {

                if (blyytypeId == 0) {
                    //全查
                    blyy.setBlyytypeId(null);
                    List<Blyy> listForPage = blyyMapper.selectAllBlyy(blyy);
                    if (pagesize != null && pageindex != null) {
                        Integer count = blyyMapper.countBlyy(blyy);
                        return JSONObject.oK(listForPage, count);
                    }else{
                        return JSONObject.oK(listForPage.get(0));
                    }

                } else {

                    List<XTreeNode> tree = null;
                    tree = new ArrayList<>();
                    List<XTreeNode> nodes = new ArrayList<>();
                    List<Blyytype> list = blyytypeMapper.lookBlyytype(new Blyytype());
                    List<XTreeNode> treelist = new ArrayList<>();
                    for (Blyytype _MouldType : list) {
                        XTreeNode node = new XTreeNode();
                        node.setId(_MouldType.getBlyytypeId());
                        node.setpId(_MouldType.getBlyytypePid());
                        node.setTitle(_MouldType.getBlyytypeName());
                        treelist.add(node);
                    }
                    XTreeUtil xTreeUtil = new XTreeUtil(blyytypeId, treelist);
                    List<Integer> childs = new ArrayList<>();
                    List<Integer> parents = new ArrayList<>();
                    childs=xTreeUtil.getChilds();
                    parents=xTreeUtil.getParents();
                    List<Blyy> listForPage = blyyMapper.queryListBlyy(blyy, childs);
                    Integer count = blyyMapper.countListBlyy(blyy, childs);
                    return JSONObject.oK(listForPage, count);
                }
            }


            // 为空进行多条的分页查询
            // 调用Mapper分页查询方法

//            if(blyytypeId==null){
//                Blyytype blyytype = new Blyytype();
//                blyytype.setBlyytypePid(0);
//                List<Blyytype> blyytypeList = blyytypeMapper.selectAllBlyytype(blyytype);
//                if(blyytypeList != null && blyytypeList.size() >= 1){
//                    blyytype = blyytypeList.get(0);
//                    blyy.setBlyytypeId(blyytype.getBlyytypeId());
//                }
//            }else if(blyytypeId==0){
//                blyy.setBlyytypeId(null);
//            }
//
//
//            // 判断是否查询到数据
//            if (listForPage.isEmpty()) {
//                return JSONObject.oK("没有查询到数据",listForPage, 0);
//            }
            // 查询有多少条数据条数

//            return JSONObject.oK(listForPage.get(0));


        } catch (GlobalException e) {
            // 捕获异常,打印并返回数
            logger.error(e.getMessage(), e);
            return JSONObject.build(500, e.getMessage());
        }
    }

    //新增
    @Override
    public JSONObject insertOrUpdateBlyy(Blyy blyy) {
        try {
            if (blyy.getBlyytypeId() == null) {
                blyy.setBlyytypeId(0);
            }
            if (blyy.getDataState() == null) {
                blyy.setDataState(1);
            }

            JSONObject checkParamsNotRepeatJson = checkParamsNotRepeat(blyy);
            if (checkParamsNotRepeatJson.isOk()) {

                if (blyy.getBlyyId() != null) {// 有id,做更新
                    validate(blyy);
                    Integer row = blyyMapper.updateByPrimaryKeySelective(blyy);
                    if (row == 0) {
                        throw new ModifyFailedException("更新不良原因失败！");
                    }
                    return JSONObject.oK("更新成功！", row);
                } else {// 没id,做新增
                    // 校验传入参数
                    validate(blyy);
                    Integer row = blyyMapper.insertSelective(blyy);
                    if (row == 0) {
                        throw new ModifyFailedException("新增不良原因失败！");
                    }
                    return JSONObject.oK("新增成功！", row);
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
    public JSONObject deleteBlyy(Long[] deteBlyy, Integer lDataState) {
        try {
            //1.校验
            if (deteBlyy == null)
                throw new ParamsErrorException("控制项目id不能为空");
            if (lDataState == null)
                throw new ParamsErrorException("控制项目状态不能为空");

            int row = blyyMapper.deleteBlyy(deteBlyy, lDataState);
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

    //验证重复
    private JSONObject checkParamsNotRepeat(Blyy blyy) {
        try {
            // 判断不良原因类型ID是否传入
            if (blyy.getBlyytypeId() == null || blyy.getBlyytypeId() == 0) {
                throw new ParamsErrorException("未传入不良原因ID");
            } else {
                Blyy blyyCheck = new Blyy();
                blyyCheck.setBlyyName(blyy.getBlyyName());
                if (blyy.getBlyyId() != null) {
                    // 剔除本条记录是否有重复
                    blyyCheck.setBlyyId(blyy.getBlyyId());
                    int row = blyyMapper.countParamsNotRepeat(blyyCheck);
                    if (row > 0) {
                        throw new ParamsErrorException("已存在该产品!");
                    }
                } else {
                    // 查询所有是否有重复
                    int row = blyyMapper.countParamsNotRepeat(blyyCheck);
                    if (row > 0) {
                        throw new ParamsErrorException("已存在该产品!");
                    }
                }
                return JSONObject.oK();
            }
        } catch (GlobalException e) {
            // 捕获异常,打印并返回
            logger.error(e.getMessage(), e);
            return JSONObject.build(500, e.getMessage());
        }
    }


    //非空判断
    private void validate(Blyy blyy) {
        //不良原因id
        Integer blyyId = blyy.getBlyyId();
        //不良原因名称
        String blyyName = blyy.getBlyyName();
        //不良原因类型id
        Integer blyytypeid = blyy.getBlyytypeId();
        //不良原因名称
        String remark = blyy.getRemark();

        if (StringUtils.isEmpty(blyyName)) {
            throw new ParamsErrorException("不良原因名称不能为空");
        }
        if (blyyName.getBytes().length > 50) {
            throw new ParamsErrorException("不良原因名过长");
        }
        if (remark != null) {
            if (remark.getBytes().length > 100) {
                throw new ParamsErrorException("说明过长");
            }
        }


        if (blyytypeid == null) {
            throw new ParamsErrorException("不良原因类型不能为空");
        }
//        if(typeId==null) {
//            throw new ParamsErrorException("不良原因类型id不能为空");
//        }


    }
}
