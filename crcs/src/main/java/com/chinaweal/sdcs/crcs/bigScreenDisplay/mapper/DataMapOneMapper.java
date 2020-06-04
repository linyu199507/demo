package com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.MapOneVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liny
 * @since 2020-03-16
 */
public interface DataMapOneMapper extends BaseMapper<MapOneVO> {

   List<MapOneVO> searchAllDataMapByMap(@Param("map") Map<String, Object> requestMap);

   MapOneVO searchAllDataMapByMapOne(@Param("map") Map<String, Object> requestMap);

   MapOneVO searchSequentialByMap(@Param("map") Map<String, Object> requestMap);

   MapOneVO searchSettlementAmountByMap(@Param("map") Map<String, Object> requestMap);

   MapOneVO searchTotalEconomyByMap(@Param("map") Map<String, Object> requestMap);

   MapOneVO searchSettlementAmountOfEconomyByMap(@Param("map") Map<String, Object> requestMap);



}
