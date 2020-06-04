package com.chinaweal.sdcs.crcs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.system.entity.EconomySituation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-02-05
 */
public interface EconomySituationMapper extends BaseMapper<EconomySituation> {



    EconomySituation selectEconomydataByMap(@Param("map") Map<String, Object> map);

    EconomySituation selectEconomyIllegalByMap(@Param("map") Map<String, Object> map);
//
//    OverallSituation selectAllAreaByMap(@Param("map") Map<String, Object> map);



}
