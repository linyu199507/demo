package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Materialinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.entity.vo.MaterialinfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface MaterialinfoMapper extends BaseMapper<Materialinfo> {

    IPage<MaterialinfoVo> selectTasklistVo(Page page, @Param("caseguids") List<String> caseguids);

    IPage<Tasklist> searchFilesInfo(Page<Tasklist> page, @Param("map")Map<String, Object> requestMap);

    void deletematerialinfo(@Param("map") Map<String, String> requestMap);
}
