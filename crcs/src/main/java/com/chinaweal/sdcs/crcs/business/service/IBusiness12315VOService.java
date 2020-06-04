package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.business.entity.Business12315VO;
import com.chinaweal.sdcs.crcs.business.entity.BusinessSearchVO;
import com.chinaweal.sdcs.crcs.business.entity.BusinesslocalVO;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-11-11
 */
public interface IBusiness12315VOService extends IService<Business12315VO> {

    Business12315VO searchBusiness12315Info(Map<String, String> requestMap);

    String[] searchBusiness12315InfoBysystemsource(Map<String, Object> requestMap);
}
