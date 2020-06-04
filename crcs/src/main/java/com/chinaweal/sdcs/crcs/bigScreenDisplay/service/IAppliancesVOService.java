package com.chinaweal.sdcs.crcs.bigScreenDisplay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.AppliancesVO;

import java.util.List;
import java.util.Map;


/**
 * @author liny
 * @since 2020-03-28
 */
public interface IAppliancesVOService extends IService<AppliancesVO> {

    List<AppliancesVO> searchAppliancesTOP10(Map<String, Object> requestMap);







}
