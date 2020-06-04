package com.chinaweal.sdcs.crcs.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.system.entity.EconomySituation;
import com.chinaweal.sdcs.crcs.system.entity.OverallSituation;

import java.util.List;
import java.util.Map;


/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-02-05
 */
public interface IEconomySituationService extends IService<EconomySituation> {

    Map<String,Object> selectEconomydata(Map<String, Object> requestMap);

    Map<String,Object> selectEconomyIllegal(Map<String, Object> requestMap);
//
//    Map<String,Object> selectAllArea(Map<String, Object> requestMap);

}
