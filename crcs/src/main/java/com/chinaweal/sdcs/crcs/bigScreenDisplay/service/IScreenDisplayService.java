package com.chinaweal.sdcs.crcs.bigScreenDisplay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.ScreenDisplayVO;

import java.util.List;
import java.util.Map;


/**
 * @author liny
 * @since 2020-03-08
 */
public interface IScreenDisplayService extends IService<ScreenDisplayVO> {

    List<ScreenDisplayVO> searchBusTOP10(Map<String, Object> requestMap);

}
