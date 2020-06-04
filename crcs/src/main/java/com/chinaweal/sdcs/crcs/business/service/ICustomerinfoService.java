package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.sdcs.crcs.business.entity.Consultationform;
import com.chinaweal.sdcs.crcs.business.entity.Customerinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface ICustomerinfoService extends IService<Customerinfo> {

    IPage<Consultationform> searchConsultationformList(int current, int size, Map<String, Object> requestMap);
}
