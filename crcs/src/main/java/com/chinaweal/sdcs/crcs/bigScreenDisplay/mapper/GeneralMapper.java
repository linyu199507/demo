package com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.GeneralVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.ScreenDisplayVO;
import com.chinaweal.sdcs.crcs.business.entity.Processinfo;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liny
 * @since 2020-03-07
 */
public interface GeneralMapper extends BaseMapper<GeneralVO> {

    GeneralVO searchBusTOP10ByMap(@Param("map") Map<String, Object> requestMap);

    List<Tasklist> selectBusinessdeptNull();

    Processinfo getProcessinfoByCaseguid(@Param("map") Map<String, Object> requestMap);



}
