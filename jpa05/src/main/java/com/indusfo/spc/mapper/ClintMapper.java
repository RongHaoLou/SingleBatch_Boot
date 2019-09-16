package com.indusfo.spc.mapper;


import com.indusfo.spc.pojo.ClintVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClintMapper {

    //添加
    int insertClint(ClintVO clintVO);

    //根据ID查找
    List<ClintVO> selectByClientId(ClintVO clintVO);

    //修改
    int updateClint(ClintVO clintVO);


    //查询客户端编号是否存在
    Integer selectClint(ClintVO clintVO);

    //查询总数
    int countClint(ClintVO clintVO);

    //查询所有
    List<ClintVO> selectAll(ClintVO clintVO);

    ////1 启用  2 删除  3停用 客户端设置
    int delteClint(@Param("clintIds") Long[] clintIds, @Param("dataState") Integer dataState);

    //模糊查询
    List<ClintVO> selectLikeClint(ClintVO clintVO);
}