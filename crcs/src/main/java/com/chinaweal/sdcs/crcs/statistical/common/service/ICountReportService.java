package com.chinaweal.sdcs.crcs.statistical.common.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: NaJim
 * @Date: 2019/9/29
 */
public interface ICountReportService {

    Map<String, Object> searchByRegion(String[] regions);

    Map<String, Object> searchByRegionAndDate(String[] regions,Date begindate,Date enddate);

    Map<String, Object> searchByRegionAndTimes(String[] regions);

    Map<String, Object> searchCountByDate(List<String> betweenDate);

    Map<String, Object> searchRegionCountByDate(String[] regions,Date begindate,Date enddate);
}
