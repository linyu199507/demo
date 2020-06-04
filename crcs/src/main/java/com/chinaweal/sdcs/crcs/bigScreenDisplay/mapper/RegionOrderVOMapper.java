package com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.RegionOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liny
 * @since 2020-03-21
 */
public interface RegionOrderVOMapper extends BaseMapper<RegionOrderVO> {

   RegionOrderVO searchRegionOrderByMap(@Param("map") Map<String, Object> requestMap);




}
