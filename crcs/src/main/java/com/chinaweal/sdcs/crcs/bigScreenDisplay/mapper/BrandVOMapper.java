package com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.BrandVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liny
 * @since 2020-03-23
 */
public interface BrandVOMapper extends BaseMapper<BrandVO> {

    List<BrandVO> searchBrandByMap(@Param("map") Map<String, Object> requestMap);

    BrandVO searchIllegalTOP10ByMap(@Param("map") Map<String, Object> requestMap);

    BrandVO searchProblemOfComplaintByMap(@Param("map") Map<String, Object> requestMap);

    BrandVO searchProblemOfEconomyByMap(@Param("map") Map<String, Object> requestMap);

    BrandVO searchObjectOfComplaint(@Param("map") Map<String, Object> requestMap);






}
