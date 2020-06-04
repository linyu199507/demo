package com.chinaweal.sdcs.crcs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.system.entity.TOrgunits;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2020-04-21
 */
public interface TOrgunitsMapper extends BaseMapper<TOrgunits> {

    List<Map> selectMessageCascadeData();
//    HashMap<String,Object> selectMessageCascadeData();

    List<Map> selectMarkPerson(@Param("orgunitid") String orgunitid);

    //查询有SMS短信权限的人
    List<Map> selectSMSPerson(@Param("orgunitid") String orgunitid);


}
