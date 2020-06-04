package com.chinaweal.sdcs.crcs.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.system.entity.ComplaintSituation;

import java.util.Map;


/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-02-06
 */
public interface IComplaintSituationService extends IService<ComplaintSituation> {

    Map<String,Object> selectComplaintdata(Map<String, Object> requestMap);

    Map<String,Object> selectDataOfTwelve(Map<String, Object> requestMap);

    Map<String,Object> selectHotNature(Map<String, Object> requestMap);

    Map<String,Object> selectappliancesCategory(Map<String, Object> requestMap);

    Map<String,Object> selectappliancesNature(Map<String, Object> requestMap);

    Map<String,Object> selectappliancesBrand(Map<String, Object> requestMap);

    Map<String,Object> selectsubjectTen(Map<String, Object> requestMap);

    Map<String,Object> selectbusinesscircle(Map<String, Object> requestMap);

    Map<String,Object> selectdailyCategory(Map<String, Object> requestMap);

    Map<String,Object> selectDailyNature(Map<String, Object> requestMap);

    Map<String,Object> selectDailyBrand(Map<String, Object> requestMap);

    Map<String,Object> selectTrafficCategory(Map<String, Object> requestMap);

    Map<String,Object> selectTrafficNature(Map<String, Object> requestMap);

    Map<String,Object> selectTrafficBrand(Map<String, Object> requestMap);

    Map<String,Object> selectdecoration(Map<String, Object> requestMap);

    Map<String,Object> selecthousing(Map<String, Object> requestMap);

    Map<String,Object> selectfood(Map<String, Object> requestMap);

    Map<String,Object> selectfoodLink(Map<String, Object> requestMap);

    Map<String,Object> selectmedicineLink(Map<String, Object> requestMap);

    Map<String,Object> selecthealthfoodLink(Map<String, Object> requestMap);

    Map<String,Object> selectCosmeticsLink(Map<String, Object> requestMap);

    Map<String,Object> selectMedicalLink(Map<String, Object> requestMap);

    Map<String,Object> selectComplaintHotSpot(Map<String, Object> requestMap);


}
