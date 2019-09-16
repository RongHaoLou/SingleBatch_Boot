package com.indusfo.spc.service;

import com.indusfo.spc.pojo.ClintVO;
import com.indusfo.spc.vo.JSONObject;

public interface ClintService {

    JSONObject updateClint(ClintVO clintVO);

    JSONObject selectAll(ClintVO clintVO);

    JSONObject insertBldc(ClintVO clintVO);

    JSONObject deleteClint(Long[] clintIds, Integer dataState);
}
