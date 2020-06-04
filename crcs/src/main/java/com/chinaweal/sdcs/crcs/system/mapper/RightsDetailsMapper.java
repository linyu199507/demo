package com.chinaweal.sdcs.crcs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.system.entity.RightsDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2020-05-28
 */
public interface RightsDetailsMapper extends BaseMapper<RightsDetails> {

    List<String> selectEnterpriseByRegion(@Param("map") Map<String, Object> requestMap);


}
