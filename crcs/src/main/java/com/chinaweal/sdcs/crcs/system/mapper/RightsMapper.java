package com.chinaweal.sdcs.crcs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.system.entity.Rights;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2020-05-26
 */
public interface RightsMapper extends BaseMapper<Rights> {

    List<String> selectEnterpriseByRegion(@Param("map") Map<String,Object> requestMap);


}
