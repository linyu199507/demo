package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.business.entity.BusinesslocalVO;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-11-11
 */
public interface IBusinesslocalVOService extends IService<BusinesslocalVO> {

    BusinesslocalVO searchBusinesslocalInfo(Map<String, String> requestMap);

    String[] searchBusinesslocalInfoBysystemsource(Map<String,Object> requestMap);
}
