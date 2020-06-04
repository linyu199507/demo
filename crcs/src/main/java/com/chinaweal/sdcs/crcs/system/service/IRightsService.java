package com.chinaweal.sdcs.crcs.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.system.entity.Rights;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2020-05-26
 */
public interface IRightsService extends IService<Rights> {


    List<List<String>> selectEnterprise(Map<String,Object> requestMap);


}
