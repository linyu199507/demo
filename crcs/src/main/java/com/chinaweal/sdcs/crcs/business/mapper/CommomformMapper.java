package com.chinaweal.sdcs.crcs.business.mapper;

import com.chinaweal.sdcs.crcs.business.entity.Commomform;
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
public interface CommomformMapper extends BaseMapper<Commomform> {

    List<BusinessTopCount> selectByEntname();

    List<BusinessTopCount> selectByEntnameAndDate(@Param("begindate")Date begin,@Param("enddate")Date end);

}
