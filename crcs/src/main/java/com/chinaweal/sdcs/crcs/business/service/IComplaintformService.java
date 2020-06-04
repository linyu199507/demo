package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.sdcs.crcs.business.entity.Complaintform;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.statistical.common.entity.BusinessTopCount;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface IComplaintformService extends IService<Complaintform> {

    List<BusinessTopCount> selectBySubjectname();

    List<BusinessTopCount> selectBySubjectnameAndDate(Date begin,Date end);

    IPage<Complaintform> searchComplaintformList(int current, int size, Map<String, Object> requestMap);

}
