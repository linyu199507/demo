package com.chinaweal.sdcs.crcs.statistical.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.business.entity.Commomform;
import com.chinaweal.sdcs.crcs.business.entity.Complaintform;
import com.chinaweal.sdcs.crcs.business.entity.Consultationform;
import com.chinaweal.sdcs.crcs.business.entity.Economyform;
import com.chinaweal.sdcs.crcs.business.service.ICommomformService;
import com.chinaweal.sdcs.crcs.business.service.IComplaintformService;
import com.chinaweal.sdcs.crcs.business.service.IConsultationformService;
import com.chinaweal.sdcs.crcs.business.service.IEconomyformService;
import com.chinaweal.sdcs.crcs.statistical.common.entity.BusinessTopCount;
import com.chinaweal.sdcs.crcs.statistical.common.service.ICountReportService;
import com.chinaweal.sdcs.crcs.statistical.common.util.MakeTopTen;
import com.chinaweal.sdcs.crcs.statistical.common.util.TimeRelevantUtil;
import com.chinaweal.youfool.framework.rest.RestResult;
import com.chinaweal.youfool.framework.rest.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: NaJim
 * @Date: 2019/9/29
 */

@Api(tags = "CountReportController", description = "统计报表数据类")
@Controller
@RequestMapping("/countreport")
public class CountReportController {

    @Qualifier("economyformServiceImpl")
    @Autowired
    private IEconomyformService iEconomyformService;

    @Qualifier("consultationformServiceImpl")
    @Autowired
    private IConsultationformService iConsultationformService;

    @Qualifier("complaintformServiceImpl")
    @Autowired
    private IComplaintformService iComplaintformService;

    @Qualifier("commomformServiceImpl")
    @Autowired
    private ICommomformService iCommomformService;

    @Qualifier("countReportServiceImpl")
    @Autowired
    private ICountReportService iCountReportService;

    private String[] regions = {"陈村镇","乐从镇","龙江镇","北滘镇","伦教镇","勒流镇","杏坛镇","均安镇","容桂镇","大良镇"};

    private List<BusinessTopCount> list(){
        List<BusinessTopCount> list = new ArrayList();
        List<BusinessTopCount> list1 = iCommomformService.countByEntname();
        List<BusinessTopCount> list2 = iComplaintformService.selectBySubjectname();
        List<BusinessTopCount> list3 = iEconomyformService.selectBySubjectname();
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        return list;
    }


    /**
     * @return 各业务量占比统计
     */
    @ApiOperation(
            tags = "CountReportController",
            value = "各业务量占比统计卡片")
    @GetMapping("countBusiness")
    @ResponseBody
    public RestResult countBusiness(@ApiParam(value = "各业务量占比统计参数", required = false)
                                    @RequestParam Map<String, Object> requestMap) {

        String begin = (String) requestMap.get("begindate");
        String end = (String) requestMap.get("enddate");
        Map<String,Integer> map = new HashMap<>();
        if (StringUtils.isNotBlank(begin) && StringUtils.isNotBlank(end)) {
            Date begindate = TimeRelevantUtil.strToDate(begin);
            Date enddate = TimeRelevantUtil.strToDate(end);
            QueryWrapper<Complaintform> wrapper = new QueryWrapper<>();
            wrapper.lambda().between(Complaintform::getRecorddate, begindate, enddate);
            map.put("Complaintform",iComplaintformService.count(wrapper));
            QueryWrapper<Economyform> wrapper1 = new QueryWrapper<>();
            wrapper1.lambda().between(Economyform::getRecorddate, begindate, enddate);
            map.put("Economyform",iEconomyformService.count(wrapper1));
            QueryWrapper<Consultationform> wrapper2 = new QueryWrapper<>();
            wrapper2.lambda().between(Consultationform::getUpdatedate, begindate, enddate);
            map.put("Consultationform",iConsultationformService.count(wrapper2));
            return RestResult.ok(map);
        } else {
            map.put("Complaintform",iComplaintformService.count());
            map.put("Consultationform",iConsultationformService.count());
            map.put("Economyform",iEconomyformService.count());
            System.out.println(map);
            System.out.println(map.get("Complaintform"));
            return RestResult.ok(map);
        }
    }



        /**
         * @return 各品牌投诉量占比统计
         */
        @ApiOperation(
                tags = "CountReportController",
                value = "各品牌投诉量Top10占比统计")
        @GetMapping("countBusinessBySubject")
        @ResponseBody
        public RestResult countBusinessBySubject (@ApiParam(value = "各品牌投诉量占比统计参数", required = false)
        @RequestParam Map < String, Object > requestMap){
            String begin = (String) requestMap.get("begindate");
            String end = (String) requestMap.get("enddate");
            List<BusinessTopCount> list1 = list();
            System.out.println(list1);
            List<String> topTenList = MakeTopTen.topTenList(list());
            Map<String,Object> countByBus = new HashMap<>();
            if (StringUtils.isNotBlank(begin) && StringUtils.isNotBlank(end)) {
                Date begindate = TimeRelevantUtil.strToDate(begin);
                Date enddate = TimeRelevantUtil.strToDate(end);
                for (int i=0;i<topTenList.size();i++) {
                    String str = topTenList.get(i);
                    Map<String, Object> map=new HashMap<>();
                    map.put("subjectName",str);
                    QueryWrapper<Commomform> wrappercom = new QueryWrapper<>();
                    wrappercom.lambda().eq(Commomform::getEntname, str);
                    wrappercom.lambda().between(Commomform::getUpdatedate, begindate, enddate);
                    int countcom = iCommomformService.count(wrappercom);
                    map.put("Commomform",countcom);
                    QueryWrapper<Economyform> wrappereco = new QueryWrapper<>();
                    wrappereco.lambda().eq(Economyform::getSubjectname, str);
                    wrappereco.lambda().between(Economyform::getRecorddate, begindate, enddate);
                    int counteco = iEconomyformService.count(wrappereco);
                    map.put("Economyform",counteco);
                    QueryWrapper<Complaintform> wrappercomm = new QueryWrapper<>();
                    wrappercomm.lambda().eq(Complaintform::getSubjectname, str);
                    wrappercomm.lambda().between(Complaintform::getRecorddate, begindate, enddate);
                    int countcomm = iComplaintformService.count(wrappercomm);
                    map.put("Complaintform",countcomm);

                    countByBus.put("Subject"+i,map);
                }
                return RestResult.ok(countByBus);
            } else { return RestResult.error(ResultCode.PARAM_IS_INVALID); }

        }

    /**
     * @return 各地区投诉量占比统计
     */
    @ApiOperation(
            tags = "CountReportController",
            value = "各地区投诉量Top10占比统计")
    @GetMapping("countByReginon")
    @ResponseBody
    public RestResult countByReginon (@ApiParam(value = "各地区投诉量占比统计参数", required = false)
                                              @RequestParam Map < String, Object > requestMap){
        String begin = (String) requestMap.get("begindate");
        String end = (String) requestMap.get("enddate");
        if(StringUtils.isNotBlank(begin) && StringUtils.isNotBlank(end)){
            Date begindate = TimeRelevantUtil.strToDate(begin);
            Date enddate = TimeRelevantUtil.strToDate(end);
            Map<String, Object> map = iCountReportService.searchByRegionAndDate(regions, begindate, enddate);
            return RestResult.ok(map);

        }else {
            Map<String, Object> map = iCountReportService.searchByRegion(regions);
            return RestResult.ok(map);
        }
    }

    /**
     * @return 各地区办结率统计
     */
    @ApiOperation(
            tags = "CountReportController",
            value = "各地区办结率统计")
    @GetMapping("countByReginonFinish")
    @ResponseBody
    public RestResult countByReginonFinish (@ApiParam(value = "各地区投诉量占比统计参数", required = false)
                                                @RequestParam Map < String, Object > requestMap){
        Map<String, Object> map = iCountReportService.searchByRegionAndTimes(regions);
        System.out.println(map.size());
        return RestResult.ok(map);
    }
}





