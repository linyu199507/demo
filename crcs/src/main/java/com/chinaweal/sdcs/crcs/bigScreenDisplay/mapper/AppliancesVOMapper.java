package com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.AppliancesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liny
 * @since 2020-03-28
 */
public interface AppliancesVOMapper extends BaseMapper<AppliancesVO> {

    AppliancesVO searchAppliancesTOP10ByMap(@Param("map") Map<String, Object> requestMap);







}
