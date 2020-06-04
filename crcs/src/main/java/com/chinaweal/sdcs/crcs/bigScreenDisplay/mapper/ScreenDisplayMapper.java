package com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.GeneralVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.ScreenDisplayVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liny
 * @since 2020-03-07
 */
public interface ScreenDisplayMapper extends BaseMapper<ScreenDisplayVO> {

    List<ScreenDisplayVO> searchBusTOP10ByMap(@Param("map") Map<String, Object> requestMap);



}
