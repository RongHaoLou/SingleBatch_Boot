package com.indusfo.spc.mapper;


import com.indusfo.spc.pojo.BldcType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BldcTypeMapper {

    //添加不良对策类
    int insertBldcType(BldcType bldcType);

    //根据ID查询不良对策类
    BldcType selectByPrimaryKey(Integer bldcTypeId);

    //修改不良对策类
    int updateBldcType(BldcType bldcType);

    //查询不良对策类名是否存在
    Integer selectBldcTypeName(BldcType bldcType);

    //查询所有不良对策类
    List<BldcType> AllBldcType(BldcType bldcType);

    //删除不良对策类
    int delteBldcType(@Param("bldcTypeIds") Long[] bldcTypeIds);;

    // 查询子类ID
    List<Integer> queryChild(@Param("bldctypeId") Integer bldctypeId);
}