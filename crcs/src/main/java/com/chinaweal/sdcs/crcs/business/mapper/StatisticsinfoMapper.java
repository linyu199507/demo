package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Statisticsinfo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-11-22
 */
public interface StatisticsinfoMapper extends BaseMapper<Statisticsinfo> {

    IPage<Statisticsinfo> searchStatisticsinfos(Page<Statisticsinfo> page, @Param("map") Map<String, Object> requestMap);
}
