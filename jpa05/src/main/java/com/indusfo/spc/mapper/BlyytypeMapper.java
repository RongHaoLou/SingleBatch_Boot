package com.indusfo.spc.mapper;

import com.indusfo.spc.pojo.Blyytype;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @ProjectName: IEIS2
 * @Package: com.indusfo.spc.mapper
 * @ClassName: BlyytypeMapper
 * @Author: 熊冰
 * @Description: ${description}
 * @Date: 2019/8/8 16:49
 * @Version: 1.0
 * 不良原因类型mapper类
 */
public interface BlyytypeMapper{
/**
 * @Date 2019/8/10 8:47
 * @Author 熊冰
 * @Method getAllBlyytype
 * @Description
 * @param
 * @Return java.util.List<com.indusfo.spc.pojo.Blyytype>
 * 测试数据
 * */
    List<Blyytype> getAllBlyytype();

    /**
     * @Date 2019/8/10 10:20
     * @Author 熊冰
     * @Method selectAllBlyytype
     * @Description
     * @param record
     * @Return java.util.List<com.indusfo.spc.pojo.Blyytype>
     *     按条件查询
     */
    List<Blyytype> selectAllBlyytype(Blyytype record);

    /**
     * @Date 2019/8/9 15:25
     * @Author 熊冰
     * @Method countallBlyytype
     * @Description
     * @param
     * @Return int
     *  查询数据条数
     */
    int countallBlyytype();

    /**
     * @Date 2019/8/10 11:12
     * @Author 熊冰
     * @Method countBlyytype
     * @Description
     * @param record
     * @Return int
     * 按条件查询总数
     */
    int countBlyytype(Blyytype record);
    /**
     * @Date 2019/8/8 16:51
     * @Author 熊冰
     * @Method updateByPrimaryKeySelective
     * @Description
     * @param record
     * @Return int
     * 通过主键选择更新
     */
    int updateByPrimaryKeySelective(Blyytype record);

    /**
     * @Date 2019/8/8 16:51
     * @Author 熊冰
     * @Method insertSelective
     * @Description
     * @param record
     * @Return int
     * 选择性插入
     */
    int insertSelective(Blyytype record);
    /**
     * @Date 2019/8/9 15:39
     * @Author 熊冰
     * @Method deleteDetePro
     * @Description
     * @param deteBlyytype
     * @param lDataState
     * @Return int
     * 状态修改,实际为:将状态字段改为2,按主键删除
     */
    int deleteBlyytype(@Param("deteBlyytype") Long[] deteBlyytype, @Param("lDataState") Integer lDataState);
    /**
     * @Date 2019/8/9 15:13
     * @Author 熊冰
     * @Method countParamsNotRepeat
     * @Description
     * @param record
     * @Return int
     * 校检数据不重复使用,根据不为null的字段查询未删除的数据条数
     */
    int countParamsNotRepeat(Blyytype record);

    /**
     * @Date 2019/8/10 13:55
     * @Author 熊冰
     * @Method lookBlyytype
     * @Description
     * @param record
     * @Return java.util.List<com.indusfo.spc.pojo.Blyytype>
     *     查询
     */

    List<Blyytype> lookBlyytype(Blyytype record);


    /**
     * @Date 2019/8/10 14:07
     * @Author 熊冰
     * @Method tallys
     * @Description
     * @param record
     * @Return java.lang.Integer
     * 查询条数
     */
    Integer tallys(Blyytype record);
    /**
     * 通过不良原因类型父编号获取子类的个数
     */
    Integer countByblyytypePid(@Param("blyytypePids") Long[] deteBlyytype);
}
