package com.chinaweal.sdcs.crcs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.system.entity.OverallSituation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-02-03
 */
public interface OverallSituationMapper extends BaseMapper<OverallSituation> {



    List<OverallSituation> selectBustypeByMap(@Param("map") Map<String, Object> map);

    OverallSituation selectAllAreaByMap(@Param("map") Map<String, Object> map);



}
