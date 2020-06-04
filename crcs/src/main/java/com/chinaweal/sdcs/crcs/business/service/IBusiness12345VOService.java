package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.business.entity.Business12315VO;
import com.chinaweal.sdcs.crcs.business.entity.Business12345VO;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-11-11
 */
public interface IBusiness12345VOService extends IService<Business12345VO> {

    Business12345VO searchBusiness12345Info(Map<String, String> requestMap);

    String[] searchBusiness12345InfoBysystemsource(Map<String, Object> requestMap);
}
