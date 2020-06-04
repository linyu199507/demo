package com.chinaweal.sdcs.crcs.bigScreenDisplay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.BrandVO;

import java.util.List;
import java.util.Map;


/**
 * @author liny
 * @since 2020-03-23
 */
public interface IBrandVOService extends IService<BrandVO> {

    List<BrandVO> searchBrand(Map<String, Object> requestMap);

    List<BrandVO> searchIllegalTOP10(Map<String, Object> requestMap);

    List<BrandVO> searchProblemOfComplaint(Map<String, Object> requestMap);

    List<BrandVO> searchProblemOfEconomy(Map<String, Object> requestMap);

    List<BrandVO> searchObjectOfComplaint(Map<String, Object> requestMap);






}
