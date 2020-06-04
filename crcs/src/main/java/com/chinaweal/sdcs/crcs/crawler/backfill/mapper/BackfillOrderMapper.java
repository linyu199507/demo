package com.chinaweal.sdcs.crcs.crawler.backfill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.crawler.backfill.entity.BackfillOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BackfillOrderMapper extends BaseMapper<BackfillOrder> {


    List<BackfillOrder > searchBackOrderByMap(@Param("map") Map<String, Object> map);

    BackfillOrder searchBackOrder12345(@Param("map") Map<String, Object> map);

    BackfillOrder searchBackOrderOfHomeByMap(@Param("map") Map<String, Object> map);

    BackfillOrder selectByUuid(@Param("uuid") String uuid);

    void updateByUuid(@Param("data") BackfillOrder data);

}
