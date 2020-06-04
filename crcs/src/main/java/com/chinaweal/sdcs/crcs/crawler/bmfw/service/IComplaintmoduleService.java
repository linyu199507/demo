package com.chinaweal.sdcs.crcs.crawler.bmfw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Complaintmodule;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface IComplaintmoduleService extends IService<Complaintmodule> {

    List<Map> countBySubjectname();

    List<Map> countBySubjectnameAndDate(Date begindate, Date enddate);
}
