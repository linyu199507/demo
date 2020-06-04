package com.chinaweal.sdcs.crcs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.system.entity.ComplaintSituation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-02-06
 */
public interface ComplaintSituationMapper extends BaseMapper<ComplaintSituation> {



    ComplaintSituation selectComplaintdataByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectDataOfTwelveByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectHotNatureByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectappliancesCategoryByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectappliancesNatureByMap(@Param("map") Map<String, Object> map);

    List<ComplaintSituation> selectappliancesBrandByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectBrandByMap(@Param("map") Map<String, Object> map);

    List<ComplaintSituation> selectsubjectTenByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectsubjectOneByMap(@Param("map") Map<String, Object> map);

    List<ComplaintSituation> selectbusinesscircleByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectbusinesscircleOneByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectdailyCategoryByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectDailyNatureByMap(@Param("map") Map<String, Object> map);

    List<ComplaintSituation> selectDailyBrandByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectDailyBrandOneByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectTrafficCategoryByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectTrafficNatureByMap(@Param("map") Map<String, Object> map);

    List<ComplaintSituation> selectTrafficBrandByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectTrafficBrandOneByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectdecorationByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectfoodLinkByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectmedicineLinkByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selecthealthfoodLinkByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectCosmeticsLinkByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectMedicalLinkByMap(@Param("map") Map<String, Object> map);

    ComplaintSituation selectComplaintHotSpotByMap(@Param("map") Map<String, Object> map);


}
