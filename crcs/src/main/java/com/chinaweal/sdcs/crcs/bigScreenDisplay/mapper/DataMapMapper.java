package com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.MapVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liny
 * @since 2020-03-16
 */
public interface DataMapMapper extends BaseMapper<MapVO> {

   MapVO searchDataMapByMap(@Param("map") Map<String, Object> requestMap);

   List<Object> searchAllDataMapByMap(@Param("map") Map<String, Object> requestMap);




}
