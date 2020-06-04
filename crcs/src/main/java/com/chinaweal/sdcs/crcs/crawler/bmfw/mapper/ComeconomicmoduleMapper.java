package com.chinaweal.sdcs.crcs.crawler.bmfw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Comeconomicmodule;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface ComeconomicmoduleMapper extends BaseMapper<Comeconomicmodule> {

    List<Map> selectBySubjectname();

    List<Map> selectBySubjectnameAndDate(@Param("begindate")Date begindate, @Param("enddate")Date enddate);
}
