package com.chinaweal.sdcs.crcs.bigScreenDisplay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.MapOneVO;

import java.util.List;
import java.util.Map;


/**
 * @author liny
 * @since 2020-03-16
 */
public interface IDataMapOneService extends IService<MapOneVO> {

    List<MapOneVO> searchAllDataMap(Map<String, Object> requestMap);

    List<MapOneVO> searchRatioDataMap(Map<String, Object> requestMap);

    List<MapOneVO> searchSequential(Map<String, Object> requestMap);

    List<MapOneVO> searchTotalComplaints(Map<String, Object> requestMap);

    List<MapOneVO> searchSettlementAmount(Map<String, Object> requestMap);

    List<MapOneVO> searchTotalEconomy(Map<String, Object> requestMap);

    List<MapOneVO> searchSettlementAmountOfEconomy(Map<String, Object> requestMap);


}
