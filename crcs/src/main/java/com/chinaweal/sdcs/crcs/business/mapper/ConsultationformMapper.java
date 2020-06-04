package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Consultationform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-22
 */
public interface ConsultationformMapper extends BaseMapper<Consultationform> {

    Integer selectcountbyfinishregion(@Param("regionstr") String regionstr,@Param("begindate")Date begindate,@Param("enddate")Date enddate);

    IPage<Consultationform> searchConsultationformList(Page<Consultationform> page, @Param("map") Map<String, Object> requestMap);
}
