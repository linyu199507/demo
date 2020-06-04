package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.business.entity.Newassign;

import java.util.List;

/**
 * @param
 * @author mo sheng xin
 * @since
 **/
public interface NewassignMapper extends BaseMapper<Newassign> {
    List<Newassign> newAssign(); //查询当天新派单
}
