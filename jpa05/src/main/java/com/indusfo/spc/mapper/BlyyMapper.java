package com.indusfo.spc.mapper;

import com.indusfo.spc.pojo.Blyy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName: IEIS2
 * @Package: com.indusfo.spc.mapper
 * @ClassName: BlyyMapper
 * @Author:  熊冰
 * @Description: ${description}
 * @Date: 2019/8/8 15:44
 * @Version: 1.0
 * 不良原因mapper类
 */

public interface BlyyMapper {


    /**
     * @Date 2019/8/12 13:05
     * @Author 熊冰
     * @Method selectAllBlyy
     * @Description
     * @param record
     * @Return java.util.List<com.indusfo.spc.pojo.Blyy>
     *     按条件查询
     */
    List<Blyy> selectAllBlyy(Blyy record);

    /**
     * @Date 2019/8/12 13:05
     * @Author 熊冰
     * @Method countBlyy
     * @Description
     * @param record
     * @Return int
     * 按条件查询总数
     */
    int countBlyy(Blyy record);
    /**
     * @Date 2019/8/12 13:05
     * @Author 熊冰
     * @Method updateByPrimaryKeySelective
     * @Description
     * @param record
     * @Return int
     * 通过主键选择更新
     */
    int updateByPrimaryKeySelective(Blyy record);
    /**
     * @Date 2019/8/12 13:06
     * @Author 熊冰
     * @Method insertSelective
     * @Description
     * @param record
     * @Return int
     * 选择性插入
     */
    int insertSelective(Blyy record);
    /**
     * @Date 2019/8/12 13:06
     * @Author 熊冰
     * @Method deleteBlyy
     * @Description
     * @param deteBlyy
     * @param lDataState
     * @Return int
     * 状态修改,实际为:将状态字段改为2,按主键删除
     */
    int deleteBlyy(@Param("deteBlyy") Long[] deteBlyy, @Param("lDataState") Integer lDataState);
    /**
     * @Date 2019/8/12 13:06
     * @Author 熊冰
     * @Method countParamsNotRepeat
     * @Description
     * @param record
     * @Return int
     * 校检数据不重复使用,根据不为null的字段查询未删除的数据条数
     */
    int countParamsNotRepeat(Blyy record);
    //级联删除
    int deleteByblyytypePids(@Param("blyytypePids") Long[] blyytypePids);
    //子集查询
    List<Blyy> queryListBlyy(@Param("blyy") Blyy blyy, @Param("childs") List<Integer> childs);
    //子集查询总数
    int countListBlyy(@Param("blyy") Blyy blyy, @Param("childs") List<Integer> childs);
}
