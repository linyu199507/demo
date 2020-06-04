package com.chinaweal.sdcs.crcs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.system.entity.StatisticsDate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-1-9
 */
public interface StatisticsDataMapper extends BaseMapper<StatisticsDate> {

    StatisticsDate getStatisticsDateByMap(@Param("map") Map<String, Object> map);

    StatisticsDate getFormTotalsByMap(@Param("map") Map<String, Object> map);

    List<Object> getComplaintRankingByMap(@Param("map") Map<String, Object> map);

    List<Object> getTenBusinessByMap(@Param("map") Map<String, Object> map);

    StatisticsDate getComplaintsAreaByMap(@Param("map") Map<String, Object> map);

    StatisticsDate getRegionCompleteByMap(@Param("map") Map<String, Object> map);

    StatisticsDate getNatureAnalysis(@Param("map") Map<String, Object> map);

    StatisticsDate getIllegalBehaviorByMap(@Param("map") Map<String, Object> map);

    StatisticsDate getHotQuestionByMap(@Param("map") Map<String, Object> map);

    StatisticsDate getOverallBusinessByMap(@Param("map") Map<String, Object> map);

    StatisticsDate getBusinessTotal(@Param("map") Map<String, Object> map);


}
