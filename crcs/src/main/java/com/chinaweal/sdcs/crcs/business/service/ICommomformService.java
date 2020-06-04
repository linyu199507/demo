package com.chinaweal.sdcs.crcs.business.service;

import com.chinaweal.sdcs.crcs.business.entity.Commomform;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.statistical.common.entity.BusinessTopCount;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface ICommomformService extends IService<Commomform> {

    void insertConsultationform(Commomform commomform);

    List<BusinessTopCount> countByEntname();

    List<BusinessTopCount> countByEntnameAndDate(Date begin,Date end);
}
