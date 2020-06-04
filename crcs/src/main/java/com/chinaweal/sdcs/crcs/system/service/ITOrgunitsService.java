package com.chinaweal.sdcs.crcs.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.system.entity.TOrgunits;

import java.util.List;
import java.util.Map;


/**
 * @author chinaweal
 * @since 2020-04-21
 */
public interface ITOrgunitsService extends IService<TOrgunits> {

    List<Map> selectMessageCascadeData();

}
