package com.chinaweal.sdcs.crcs.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.system.entity.OverallSituation;

import java.util.List;
import java.util.Map;


/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-02-03
 */
public interface IOverallSituationService extends IService<OverallSituation> {

    List<OverallSituation> selectBustype(Map<String,Object> requestMap);

    Map<String,Object> selectAllArea(Map<String,Object> requestMap);

}
