package com.chinaweal.sdcs.crcs.bigScreenDisplay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.MapChangeVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.MapVO;

import java.util.List;
import java.util.Map;


/**
 * @author liny
 * @since 2020-03-16
 */
public interface IDataMapService extends IService<MapVO> {

    List<MapChangeVO> searchDataMap(Map<String, Object> requestMap);





}
