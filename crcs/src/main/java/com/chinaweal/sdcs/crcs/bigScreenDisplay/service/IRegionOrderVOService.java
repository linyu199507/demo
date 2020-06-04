package com.chinaweal.sdcs.crcs.bigScreenDisplay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.RegionOrderVO;

import java.util.List;
import java.util.Map;


/**
 * @author liny
 * @since 2020-03-21
 */
public interface IRegionOrderVOService extends IService<RegionOrderVO> {

    List<RegionOrderVO> searchRegionOrder(Map<String, Object> requestMap);





}
