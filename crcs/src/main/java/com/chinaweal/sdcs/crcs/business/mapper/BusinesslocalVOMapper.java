package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.business.entity.BusinesslocalVO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-11-11
 */
public interface BusinesslocalVOMapper extends BaseMapper<BusinesslocalVO> {

    BusinesslocalVO searchBusinesslocalInfo(@Param("map") Map<String, String> requestMap);

    String[] searchBusinesslocalInfoBysystemsource(@Param("map") Map<String, Object> requestMap);
}
