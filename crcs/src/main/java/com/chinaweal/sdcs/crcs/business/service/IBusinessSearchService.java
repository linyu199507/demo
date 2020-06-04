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
public interface IBusinessSearchService extends IService<BusinessSearchVO> {

    IPage<BusinessSearchVO> searchBusinessInfo(int current, int size, Map<String, Object> requestMap);

    BusinessSearchVO searchBusinessInfo(Map<String, String> requestMap);

    String[] searchBusinessExpectedInfoBysystemsource(Map<String, Object> requestMap);
}
