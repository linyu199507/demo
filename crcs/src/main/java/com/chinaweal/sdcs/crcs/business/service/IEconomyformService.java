package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.sdcs.crcs.business.entity.Economyform;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.statistical.common.entity.BusinessTopCount;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface IEconomyformService extends IService<Economyform> {

    List<BusinessTopCount> selectBySubjectname();

    List<BusinessTopCount> selectBySubjectnameAndDate(Date begin,Date end);

    IPage<Economyform> searchEconomyformList(int current, int size, Map<String, Object> requestMap);
}
