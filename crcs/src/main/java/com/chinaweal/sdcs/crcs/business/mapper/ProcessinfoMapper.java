package com.chinaweal.sdcs.crcs.business.mapper;

import com.chinaweal.sdcs.crcs.business.entity.Processinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface ProcessinfoMapper extends BaseMapper<Processinfo> {

    //liny --2020-04-02
    List<Processinfo> getProcessinfoByCaseguid(@Param("map") Map<String, Object> requestMap);

}
