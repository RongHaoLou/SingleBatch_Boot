package com.indusfo.spc.mapper;

import com.indusfo.spc.pojo.Bldc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BldcMapper {

    //添加不良对策
    int insertBldc(Bldc record);

    //根据ID查询
    List<Bldc> selectByPrimaryKey(Bldc bldc);

    //修改不良对策
    int updateByPrimaryKeySelective(Bldc record);

    //查询所有不良对策
    List<Bldc> selectAll(Bldc bldc);
    // 查询所有不良对策   及其子类
    List<Bldc> queryBldcByBldctypeId(@Param("bldc") Bldc bldc, @Param("bldctypeIds") List<Integer> bldctypeIds);

    //查询不良对策是否存在
    Integer selectBldc(Bldc bldc);

    //1 启用  2 删除  3停用 不良对策
    int delteBldc(@Param("bldcIds") Long[] bldcIds, @Param("dataState") Integer dataState);

    //查询总数
    int countBldc(Bldc bldc);
    // 查询总数   及其子类
    int countBldcByBldctypeId(@Param("bldc") Bldc bldc, @Param("bldctypeIds") List<Integer> bldctypeIds);

    //根据不良对策类ID  批量删除
    int delteBldcByBldcTypeId(Bldc bldc);



}