package com.chinaweal.sdcs.crcs.crawler.bmfw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Commommodule;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface ICommommoduleService extends IService<Commommodule> {

    List<Map> countByEntname();

    List<Map> countByEntnameAndData(Date begindate,Date enddate);


}
