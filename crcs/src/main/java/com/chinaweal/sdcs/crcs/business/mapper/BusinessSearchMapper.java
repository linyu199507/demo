package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Business12315VO;
import com.chinaweal.sdcs.crcs.business.entity.BusinessSearchVO;
import com.chinaweal.sdcs.crcs.business.entity.BusinesslocalVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface BusinessSearchMapper extends BaseMapper<BusinessSearchVO> {

    IPage<BusinessSearchVO> searchBusinessInfo(Page<BusinessSearchVO> page, @Param("map") Map<String, Object> requestMap);

    List<BusinessSearchVO> searchBusinessInfo(@Param("map") Map<String, String> requestMap);

    String[] searchBusinessExpectedInfoBysystemsource(@Param("map") Map<String, Object> requestMap);
}
