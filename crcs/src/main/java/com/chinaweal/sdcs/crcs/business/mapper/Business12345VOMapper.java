package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.business.entity.Business12315VO;
import com.chinaweal.sdcs.crcs.business.entity.Business12345VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-11-11
 */
public interface Business12345VOMapper extends BaseMapper<Business12345VO> {

    Business12345VO searchBusiness12345Info(@Param("map") Map<String, String> requestMap);

    String[] searchBusiness12345InfoBysystemsource(@Param("map") Map<String, Object> requestMap);

}
