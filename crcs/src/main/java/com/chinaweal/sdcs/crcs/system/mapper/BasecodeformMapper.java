package com.chinaweal.sdcs.crcs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-09-29
 */
public interface BasecodeformMapper extends BaseMapper<Basecodeform> {


    IPage<Basecodeform> findBasecodeformByMap(Page<Basecodeform> page, @Param("map") Map<String, String> requestMap);

    List<String> findDistinctType();

    Integer getMaxLevelByType(@Param("type") String type);

    List<Basecodeform> getbasecodeformByComment(@Param("map") Map<String, Object> requestMap);
}
