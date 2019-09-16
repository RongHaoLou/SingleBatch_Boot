package com.indusfo.spc.vo;

import com.indusfo.spc.common.pojo.BasePojo;
import com.indusfo.spc.exception.ParamsErrorException;

/**
 * 分页工具
 *
 * @author xuz
 * @date 2019/7/17 4:43 PM
 */
public class PageUtil {

    /**
     * 计算传入对象的分页
     *
     * @param basePojo 
     * @return void
     * @author xuz
     * @date 2019/7/17 5:33 PM
     */
    public static void calculatePageSize(BasePojo basePojo) {
        Integer pageindex = basePojo.getPageindex();
        Integer pagesize = basePojo.getPagesize();
        if (null==pageindex && null==pagesize) {
            return;
        }
        if (null==pageindex && null!=pagesize) {
            throw new ParamsErrorException("请设置分页起始位置");
        }
        if (null!=pageindex && null==pagesize) {
            throw new ParamsErrorException("请设置分页大小");
        }
        if (pageindex<=0 || pagesize<0) {
            throw new ParamsErrorException("分页参数错误：不能为负数");
        }
        basePojo.setlN(pagesize*(pageindex-1));
    }
}
