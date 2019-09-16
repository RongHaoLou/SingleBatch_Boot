package com.indusfo.spc.service.impl;

import com.indusfo.spc.exception.GlobalException;
import com.indusfo.spc.exception.ModifyFailedException;
import com.indusfo.spc.exception.ParamsErrorException;
import com.indusfo.spc.mapper.ClintMapper;
import com.indusfo.spc.pojo.ClintVO;
import com.indusfo.spc.service.ClintService;
import com.indusfo.spc.vo.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ClintServiceImpl implements ClintService {

    private  static  final Logger logger = LoggerFactory.getLogger(BldcServiceImpl.class);

    @Autowired
    private ClintMapper clintMapper;

    /**
     * 客户端设置更新
     *
     * @param clintVO
     * @return  JSONObject
     */
    @Override
    public JSONObject updateClint(ClintVO clintVO) {
        String remark = clintVO.getRemark();
        if(remark == null) {
            remark = "";
        }
        try {
            if(clintVO.getClientId() == null) {
                throw new ParamsErrorException("请选择要更新的工序");
            }
            Long[] shuzu = clintVO.getShuzu();
            checkParam(clintVO, remark,shuzu);

            // 调用存储过程
            int row = clintMapper.updateClint(clintVO);
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
    public JSONObject selectAll(ClintVO clintVO) {
        List<ClintVO> listClintVO = null;
        try {
            Integer pagesize = clintVO.getPagesize();
            Integer pageindex = clintVO.getPageindex();
            if(pagesize != null && pageindex != null) {
                clintVO.setlN(pagesize*(pageindex - 1));
            }
            Integer clientId = clintVO.getClientId();
            String dim = clintVO.getDim();
            if(clientId != null) {  //如果有id,则查询单个
                listClintVO = clintMapper.selectByClientId(clintVO);
            }
            else if(!StringUtils.isEmpty(dim)){//模糊查询
                listClintVO = clintMapper.selectLikeClint(clintVO);
            }else{  //查询全部
                listClintVO = clintMapper.selectAll(clintVO);
            }

            if (listClintVO.isEmpty()) {
                return JSONObject.oK("没有查询到数据", listClintVO, 0);
            }
            //查询分页总记录数
            int count = clintMapper.countClint(clintVO); //返回查询到的总记录数
            return JSONObject.oK("查询成功", listClintVO, count);
        } catch (GlobalException e) {
            logger.error(e.getMessage(), e);
            return JSONObject.build(403, e.getMessage());
        }
    }

    /***
     * 部门新增
     *
     * @param clintVO
     * @return com.indusfo.spc.vo.JSONObject
     */
    @Override
    public JSONObject insertBldc(ClintVO clintVO) {
        clintVO.setDataState(1);
        String remark = clintVO.getRemark();
        if(remark == null) {
            remark = "";
        }
        try {
            if(clintVO.getClientId() != null) {
                throw new ParamsErrorException("新增不良对策时，不能填写id");
            }
            Long[] shuzu = clintVO.getShuzu();
            checkParam(clintVO, remark,shuzu);
            int	row = clintMapper.insertClint(clintVO);
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
     * @param clintIds
     * @param dataState
     * @return com.indusfo.spc.vo.JSONObject
     */
    @Override
    public JSONObject deleteClint(Long[] clintIds, Integer dataState) {
        try {
            if(dataState == null) {
                throw new ParamsErrorException("数据状态不能为空");
            }else if(dataState != 1 && dataState != 2 && dataState != 3) {
                throw new ParamsErrorException("数据状态错误");
            }
            if (clintIds == null) {
                throw new ParamsErrorException("部门id不能为空");
            }
            // 执行存储过程
            int row = clintMapper.delteClint(clintIds, dataState);
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
     * @param clintVO
     * @param vcRemark
     */
    private void checkParam(ClintVO clintVO, String vcRemark, Long[] shuzu) {
        if (clintVO.getClientType() !=  1 && clintVO.getClientType() != 2 && clintVO.getClientType() != 3) {
            throw new ParamsErrorException("客户类型错误");
        }
        if (clintVO.getProXia() == null) {
            throw new ParamsErrorException("下线工序不能为空");
        }
        if (clintVO.getScanType() !=  1 && clintVO.getScanType() != 2 && clintVO.getScanType() != 3) {
            throw new ParamsErrorException("扫描类型错误");
        }
        if (StringUtils.isEmpty(clintVO.getClientCode())) {
            throw new ParamsErrorException("请填写客户端编号");
        }
        if (clintVO.getClientCode().getBytes().length > 50) {
            throw new ParamsErrorException("客户端编号超过规定长度");
        }
        if (vcRemark.getBytes().length > 100) {
            throw new ParamsErrorException("说明字数超过规定长度");
        }
        // 客户端编号不能重复
        Integer depCounts = clintMapper.selectClint(clintVO);
        if (depCounts !=0) {
            throw new ParamsErrorException("该客户端编号已存在");
        }
        clintVO.setAutoprint(0);
        clintVO.setProJump(0);
        clintVO.setRecodeErr(0);
        clintVO.setPabang(0);
        clintVO.setBatchnoBegin(0);
        clintVO.setXiatip(0);
        for(int i=0 ; i<shuzu.length ;i++){
            switch(shuzu[i].intValue()) {
                case 1:
                    clintVO.setAutoprint(1);
                    break;
                case 2:
                    clintVO.setProJump(1);
                    break;
                case 3:
                    clintVO.setRecodeErr(1);
                    break;
                case 4:
                    clintVO.setPabang(1);
                    break;
                case 5:
                    clintVO.setBatchnoBegin(1);
                    break;
                case 6:
                    clintVO.setXiatip(1);
                    break;
                default:
                    throw new ParamsErrorException("数组不正确!");
            }
        }
    }
}
