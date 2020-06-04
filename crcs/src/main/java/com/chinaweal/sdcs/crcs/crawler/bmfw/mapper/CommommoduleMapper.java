package com.chinaweal.sdcs.crcs.crawler.bmfw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Commommodule;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface CommommoduleMapper extends BaseMapper<Commommodule> {

    List<Map> selectByEntname();

    List<Map> selectByEntnameAndDate(@Param("begindate")Date begindate, @Param("enddate")Date enddate);

}
