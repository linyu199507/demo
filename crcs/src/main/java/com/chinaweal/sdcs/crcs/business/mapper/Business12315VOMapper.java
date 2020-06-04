package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Business12315VO;
import com.chinaweal.sdcs.crcs.business.entity.BusinessSearchVO;
import com.chinaweal.sdcs.crcs.business.entity.BusinesslocalVO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-11-11
 */
public interface Business12315VOMapper extends BaseMapper<Business12315VO> {

    Business12315VO searchBusiness12315Info(@Param("map") Map<String, String> requestMap);

    String[] searchBusiness12315InfoBysystemsource(@Param("map") Map<String, Object> requestMap);

}
