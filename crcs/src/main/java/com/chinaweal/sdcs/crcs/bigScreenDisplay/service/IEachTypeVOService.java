package com.chinaweal.sdcs.crcs.bigScreenDisplay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.EachTypeVO;

import java.util.List;
import java.util.Map;


/**
 * @author liny
 * @since 2020-03-31
 */
public interface IEachTypeVOService extends IService<EachTypeVO> {

//    List<EachTypeVO> searchAppliancesTOP10(Map<String, Object> requestMap);


    List<EachTypeVO> searchTotalOfMounth(Map<String, Object> requestMap);

    List<EachTypeVO> searchTotalOfToday(Map<String, Object> requestMap);

    List<EachTypeVO> searchRatioOfToday(Map<String, Object> requestMap);

    List<EachTypeVO> searchRatioOfAll(Map<String, Object> requestMap);


}
