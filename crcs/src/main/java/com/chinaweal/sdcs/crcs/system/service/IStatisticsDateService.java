package com.chinaweal.sdcs.crcs.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.system.entity.StatisticsDate;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-1-9
 */
public interface IStatisticsDateService extends IService<StatisticsDate> {

        Map<String,Object> selectRegionalData(Map<String,Object> requestMap);

        Map<String ,Object> selectComplaintRanking(Map<String,Object> requestMap);

        Map<String,Object>  selectTenBusiness(Map<String,Object> requestMap);

        Map<String,Object>  selectComplaintsArea(Map<String,Object> requestMap);

        List<StatisticsDate>  selectRegionComplete(Map<String,Object> requestMap);

        List<StatisticsDate>  selectNatureAnalysis(Map<String,Object> requestMap);

        Map<String, Object>   selectIllegalBehavior(Map<String,Object> requestMap);

        Map<String,Object>  selectHotQuestion(Map<String,Object> requestMap);

        List<StatisticsDate>   selectHotQuestionChange(Map<String,Object> requestMap);

        Map<String, Object>  selectOverallBusiness(Map<String,Object> requestMap);

        List<StatisticsDate>  selectBusinessTotal(Map<String,Object> requestMap);

}
