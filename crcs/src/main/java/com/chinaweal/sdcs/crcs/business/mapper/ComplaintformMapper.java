package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Complaintform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.statistical.common.entity.BusinessTopCount;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface ComplaintformMapper extends BaseMapper<Complaintform> {

    List<BusinessTopCount> selectBySubjectname();

    List<BusinessTopCount> selectByEntnameAndDate(@Param("begindate") Date begindate, @Param("enddate")Date enddate);

    Integer selectcountbyfinishregion(@Param("regionstr") String regionstr, @Param("begindate") Date begindate, @Param("enddate")Date enddate);

    IPage<Complaintform> searchComplaintformList(Page<Complaintform> page, @Param("map")Map<String, Object> requestMap);
}
