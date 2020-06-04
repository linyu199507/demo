package com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.EachTypeVO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author liny
 * @since 2020-03-31
 */
public interface EachTypeVOMapper extends BaseMapper<EachTypeVO> {

//    EachTypeVO searchAppliancesTOP10ByMap(@Param("map") Map<String, Object> requestMap);

    EachTypeVO searchTotalOfMounth(@Param("map") Map<String, Object> requestMap);

    EachTypeVO searchTotalOfToday(@Param("map") Map<String, Object> requestMap);







}
