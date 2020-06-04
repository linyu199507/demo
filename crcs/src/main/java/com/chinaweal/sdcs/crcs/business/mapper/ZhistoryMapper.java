package com.chinaweal.sdcs.crcs.business.mapper;

import com.chinaweal.sdcs.crcs.business.entity.Zhistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2020-03-17
 */
public interface ZhistoryMapper extends BaseMapper<Zhistory> {

    List<Map<String, String>> selectEconomyJson();

    List<Map<String, String>> selectComplaintJson();

}
