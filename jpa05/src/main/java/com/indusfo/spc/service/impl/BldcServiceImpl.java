package com.indusfo.spc.service.impl;

import com.indusfo.spc.exception.GlobalException;
import com.indusfo.spc.exception.ModifyFailedException;
import com.indusfo.spc.exception.ParamsErrorException;
import com.indusfo.spc.mapper.BldcMapper;
import com.indusfo.spc.mapper.BldcTypeMapper;
import com.indusfo.spc.pojo.Bldc;
import com.indusfo.spc.pojo.BldcType;
import com.indusfo.spc.service.BldcService;
import com.indusfo.spc.vo.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class BldcServiceImpl implements BldcService {

    private  static  final Logger logger = LoggerFactory.getLogger(BldcServiceImpl.class);

    @Autowired
    private BldcMapper bldcMapper;

    @Autowired
    private BldcTypeMapper bldcTypeMapper;

    /**
     * 不良对策更新
     *
     * @param bldc
     * @return  JSONObject
     */
    @Override
    public JSONObject updateBldc(Bldc bldc) {

        String remark = bldc.getRemark();
        if(remark == null) {
            remark = "";
        }

        try {
            if(bldc.getBldcId() == null) {
                throw new ParamsErrorException("请选择要更新的工序");
            }
            checkParam(bldc, remark);

            // 调用存储过程
            int row = bldcMapper.updateByPrimaryKeySelective(bldc);
            if(row == 0) {
                throw new ModifyFailedException("数据更新失败！");
            }
            return JSONObject.oK("更新成功！");
        } catch (GlobalException e) {  //这里只捕获自定义异常
            logger.error(e.getMessage(), e);
            return JSONObject.build(403, e.getMessage());
        }
    }

    /***
     * 不良对策查询（分页）
     *
     * @return com.indusfo.spc.vo.JSONObject
     */
    @Override
    public JSONObject selectAll(Bldc bldc) {
        List<Bldc> listBldc = null;
        int count = 0;
        Integer bldctypeId = bldc.getBldctypeId();
        List<Integer> bldctypeIds = new ArrayList<>();
        try {
            Integer pagesize = bldc.getPagesize();
            Integer pageindex = bldc.getPageindex();
            if(pagesize != null && pageindex != null) {
                bldc.setlN(pagesize*(pageindex - 1));
            }
            Integer bldcId = bldc.getBldcId();
            if(bldcId != null) {  //如果有不良对策id,则查询单个不良对策
                listBldc = bldcMapper.selectByPrimaryKey(bldc);
                //查询分页总记录数
                count = bldcMapper.countBldc(bldc); //返回查询到的总记录数
                if (listBldc.isEmpty()) {
                    return JSONObject.oK("没有不良对策相关数据", listBldc, count);
                }
                return JSONObject.oK("查询成功", listBldc, count);
            }else if(bldctypeId==null) {  // 判断 bldctypeId=null  显示第一条    0  全部
                BldcType bldcType = new BldcType();
                bldcType.setBldcTypePid(0);
                List<BldcType> bldcTypeList = bldcTypeMapper.AllBldcType(bldcType);
                if (bldcTypeList != null && bldcTypeList.size() >= 1) {
                    BldcType bldcType1 = bldcTypeList.get(0);
                    bldctypeId = bldcType1.getBldcTypeId();
                }
            }else if(bldctypeId==0){
                // 如果bldctypeId == 0 则查全部
                bldc.setBldctypeId(null);
                listBldc = bldcMapper.selectAll(bldc);
                //查询分页总记录数
                count = bldcMapper.countBldc(bldc); //返回查询到的总记录数
                if (listBldc.isEmpty()) {
                    return JSONObject.oK("没有不良对策相关数据", listBldc, count);
                }
                return JSONObject.oK("查询成功", listBldc, count);
            }
            // 递归查询子类
            recursion(bldctypeIds,bldctypeId);
            bldctypeIds.add(bldctypeId);
            // 查询类下所有不良对策
            listBldc = bldcMapper.queryBldcByBldctypeId(bldc,bldctypeIds);
            count = bldcMapper.countBldcByBldctypeId(bldc,bldctypeIds);
            return JSONObject.oK("查询成功", listBldc, count);
        } catch (GlobalException e) {
            logger.error(e.getMessage(), e);
            return JSONObject.build(403, e.getMessage());
        }
    }

    /**
     * wangqi
     *
     * 递归查询子类 及其子类的子类
     *
     * 19/9/9   13:22
     * @param bldctypeIds
     * @param bldctypeId
     */
    private void recursion(List<Integer> bldctypeIds, Integer bldctypeId) {
        List<Integer> integers = bldcTypeMapper.queryChild(bldctypeId);
        if(integers.size()>0){
            bldctypeIds.addAll(integers);
            for (Integer integer :integers){
                recursion(bldctypeIds,integer);
            }
        }
    }

    /***
     * 不良对策新增
     *
     * @param bldc
     * @return com.indusfo.spc.vo.JSONObject
     */
    @Override
    public JSONObject insertBldc(Bldc bldc) throws ParamsErrorException {

        bldc.setDataState(1);
        String remark = bldc.getRemark();
        if(remark == null) {
            remark = "";
        }
        try {
            if(bldc.getBldcId() != null) {
                throw new ParamsErrorException("新增不良对策时，不能填写id");
            }

            checkParam(bldc, remark);

            int	row = bldcMapper.insertBldc(bldc);

            if(row == 0) {
                throw new ModifyFailedException("数据新增失败！");
            }
            return JSONObject.oK("新增成功！");
        } catch (GlobalException e) {  //这里只捕获自定义异常
            logger.error(e.getMessage(), e);
            return JSONObject.build(403, e.getMessage());
        }
    }

    /**
     * 1 启用  2 删除  3停用 不良对策
     *
     * @param bldcIds
     * @param dataState
     * @return com.indusfo.spc.vo.JSONObject
     */
    @Override
    public JSONObject deleteBldc(Long[] bldcIds, Integer dataState) {
        try {
            if(dataState == null) {
                throw new ParamsErrorException("数据状态不能为空");
            }else if(dataState != 1 && dataState != 2 && dataState != 3) {
                throw new ParamsErrorException("数据状态错误");
            }
            if (bldcIds == null) {
                throw new ParamsErrorException("不良对策id不能为空");
            }
            // 执行存储过程
            int row = bldcMapper.delteBldc(bldcIds, dataState);
            String msg = "";
            if (row == 0) {
                // 判断传入的数据状态参数lDataState,返回相应信息
                switch (dataState) {
                    case 1:
                        throw new ModifyFailedException("启用失败");
                    case 2:
                        throw new ModifyFailedException("删除失败");
                    case 3:
                        throw new ModifyFailedException("停用失败");
                    default:
                }
            } else {
                switch (dataState) {
                    case 1:
                        msg = "启用成功";break;
                    case 2:
                        msg = "删除成功";break;
                    case 3:
                        msg = "停用成功";break;
                    default:
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
     * @param bldc
     * @param vcRemark
     */
    private void checkParam(Bldc bldc, String vcRemark) {
        if (bldc.getBldctypeId() == null) {
            throw new ParamsErrorException("不良对策类型不能为空");
        }
        if (StringUtils.isEmpty(bldc.getBldcName())) {
            throw new ParamsErrorException("请填写不良对策名称");
        }
        if (bldc.getBldcName().getBytes().length > 50) {
            throw new ParamsErrorException("不良对策名超过规定,长度50");
        }
        if (vcRemark.getBytes().length > 100) {
            throw new ParamsErrorException("说明字数超过规定,长度100");
        }
        // 名称不能重复
        Integer depCounts = bldcMapper.selectBldc(bldc);
        if (depCounts !=0) {
            throw new ParamsErrorException("该不良对策已存在");
        }
    }
}
