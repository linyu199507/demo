package com.chinaweal.sdcs.crcs.statistical.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.service.*;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Datagriddata;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.IDatagriddataService;
import com.chinaweal.sdcs.crcs.statistical.common.entity.BusinessTopCount;
import com.chinaweal.sdcs.crcs.statistical.common.service.ICountReportService;
import com.chinaweal.sdcs.crcs.statistical.common.util.MakeTopTen;
import com.chinaweal.sdcs.crcs.statistical.common.util.TimeRelevantUtil;
import com.chinaweal.sdcs.crcs.util.RequestUtil;
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
 * @Date: 2019/9/22
 */
@Api(tags = "LeaderIndexCountListController", description = "统计表单数量类")
@Controller
@RequestMapping("/countList")
public class LeaderIndexCountListController {

    @Qualifier("tasklistServiceImpl")
    @Autowired
    private ITasklistService iTasklistService;

    @Qualifier("datagriddataServiceImpl")
    @Autowired
    private IDatagriddataService iDatagriddataService;

    @Qualifier("economyformServiceImpl")
    @Autowired
    private IEconomyformService iEconomyformService;

    @Qualifier("commomformServiceImpl")
    @Autowired
    private ICommomformService iCommomformService;

    @Qualifier("complaintformServiceImpl")
    @Autowired
    private IComplaintformService iComplaintformService;

    @Qualifier("countReportServiceImpl")
    @Autowired
    private ICountReportService iCountReportService;

    private String[] formtypes = {"01", "02", "03"};

    private String[] regionss = {"CCZ","LCZ","LJZ","BJZ","LDZ","LLZ","XTZ","JAZ","RGZ","DLZ"};
    private String[] regions = {"陈村镇","乐从镇","龙江镇","北滘镇","伦教镇","勒流镇","杏坛镇","均安镇","容桂镇","大良镇"};

    /**
     * @return 首页待办事项卡片总数
     */
    @ApiOperation(
            tags = "LeaderIndexCountListController",
            value = "首页待办事项卡片总数")
    @PostMapping("firstCartCount")
    @ResponseBody
    public RestResult firstCartCount(@ApiParam(value = "查询相关参数", required = true)
                                         @RequestBody Map<String, Object> requestMap) {
        Map params = (Map) requestMap.get("params");
        Object user1 = params.get("user");
        AICUser aicUser = null;
        if (user1 != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, user1);
        }
        Map<String, Object> map = iTasklistService.searchTasklistCount(aicUser);
        QueryWrapper<Datagriddata> wrapper = new QueryWrapper<Datagriddata>();
        wrapper.lambda().eq(Datagriddata:: getStatus, "0");
        wrapper.lambda().ne(Datagriddata:: getState, "1");
        map.put("wsldjdb",iDatagriddataService.count(wrapper));
        return RestResult.ok(map);
    }

    /**
     * @return 首页在办统计总数
     */
    @ApiOperation(
            tags = "LeaderIndexCountListController",
            value = "首页在办统计卡片总数")
    @GetMapping("onJobStaCount")
    @ResponseBody
    public RestResult onJobStaCount() {
        Map<String,Integer> map = new HashMap<>();
        String formtypename = "";
        for (String formtype : formtypes) {
            QueryWrapper<Tasklist> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(Tasklist::getFormtype, formtype);
            if(formtype.equals("01")){
                formtypename="comm";
            }else if(formtype.equals("02")){
                formtypename="com";
            }else {
                formtypename="eco";
            }
            map.put(formtypename,iTasklistService.count(wrapper));
        }
        return RestResult.ok(map);
    }

    /**
     * @return 办结率统计[总数,办结数]
     */
    @ApiOperation(
            tags = "LeaderIndexCountListController",
            value = "办结率卡片统计")
    @GetMapping("finishCount")
    @ResponseBody
    public RestResult finishCount() {
        Map<String,Integer> map = new HashMap<>();
        String realname = "";
        for (String formtype : formtypes) {
            if(formtype.equals("01")){
                realname = "comm";
            }else if (formtype.equals("02")){
                realname = "com";
            }else if (formtype.equals("03")){
                realname = "eco";
            }
            QueryWrapper<Tasklist> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(Tasklist::getFormtype, formtype);
            map.put(realname+"Total",iTasklistService.count(wrapper));
            wrapper.lambda().eq(Tasklist::getNextnodeid, "end");
            map.put(realname+"Finish",iTasklistService.count(wrapper));
        }
        return RestResult.ok(map);
    }

    /**
     * @return 本周业务量卡片统计
     */
    @ApiOperation(
            tags = "LeaderIndexCountListController",
            value = "本周业务量卡片统计")
    @GetMapping("trafficCount")
    @ResponseBody
    public RestResult trafficCount(@ApiParam(value = "本周业务量统计参数", required = false)
                                       @RequestParam Map < String, Object > requestMap) {//todo
        String begin = (String) requestMap.get("begindate");
        String end = (String) requestMap.get("enddate");
        if (StringUtils.isNotBlank(begin) && StringUtils.isNotBlank(end)) {
            List<String> betweenDate = TimeRelevantUtil.getBetweenDate(begin, end);
            Map<String, Object> map = iCountReportService.searchCountByDate(betweenDate);
            return RestResult.ok(map);
        }else{
            return RestResult.error(ResultCode.PARAM_IS_INVALID);
        }
    }

    /**
     * @return 投诉热点商家Top10统计
     */
    @ApiOperation(
            tags = "LeaderIndexCountListController",
            value = "本周投诉热点商家统计")
    @GetMapping("businessTopCount")
    @ResponseBody
    public RestResult businessTopCount(@ApiParam(value = "投诉热点商家TOP10时间参数", required = false)
                                           @RequestParam Map<String, Object> requestMap) {
        String begindate = (String) requestMap.get("begindate");
        String enddate = (String) requestMap.get("enddate");
        List<BusinessTopCount> list = new ArrayList();
        if (StringUtils.isNotBlank(begindate) && StringUtils.isNotBlank(enddate)) {
            Date begin = TimeRelevantUtil.strToDate(begindate);
            Date end = TimeRelevantUtil.strToDate(enddate);
            List<BusinessTopCount> list1 = iCommomformService.countByEntnameAndDate(begin,end);
            List<BusinessTopCount> list2 = iComplaintformService.selectBySubjectnameAndDate(begin,end);
            List<BusinessTopCount> list3 = iEconomyformService.selectBySubjectnameAndDate(begin,end);
            list.addAll(list1);
            list.addAll(list2);
            list.addAll(list3);
        }
        List topTenList = MakeTopTen.topTenList(list);
        return RestResult.ok(topTenList);
    }


    /**
     * @return 本周各地区业务量统计
     */
    @ApiOperation(
            tags = "LeaderIndexCountListController",
            value = "本周各地区业务量统计")
    @GetMapping("regionCount")
    @ResponseBody
    public RestResult regionCount(@ApiParam(value = "本周各地区业务量时间参数", required = false)
                                      @RequestParam Map<String, Object> requestMap) {
        String begindate = (String) requestMap.get("begindate");
        String enddate = (String) requestMap.get("enddate");
        if (StringUtils.isNotBlank(begindate) && StringUtils.isNotBlank(enddate)) {
            Date begin = TimeRelevantUtil.strToDate(begindate);
            Date end = TimeRelevantUtil.strToDate(enddate);
            Map<String, Object> map = iCountReportService.searchByRegionAndDate(regions, begin, end);
            return RestResult.ok(map);
        }else{
            return RestResult.error(ResultCode.PARAM_IS_INVALID);
        }
    }


    /**
     * @return 本周投诉热点地区
     */
    @ApiOperation(
            tags = "LeaderIndexCountListController",
            value = "本周投诉热点地区统计")
    @GetMapping("regionCountByDate")
    @ResponseBody
    public RestResult regionCountByDate(@ApiParam(value = "本周投诉热点地区统计", required = false)
                                  @RequestParam Map<String, Object> requestMap) {
        String begindate = (String) requestMap.get("begindate");
        String enddate = (String) requestMap.get("enddate");
        if (StringUtils.isNotBlank(begindate) && StringUtils.isNotBlank(enddate)) {
            Date begin = TimeRelevantUtil.strToDate(begindate);
            Date end = TimeRelevantUtil.strToDate(enddate);
            Map<String, Object> map = iCountReportService.searchRegionCountByDate(regions, begin, end);
            return RestResult.ok(map);
        }else{
            return RestResult.error(ResultCode.PARAM_IS_INVALID);
        }
    }

    /**
     * @return 本周投诉热点行业TOP10
     */
    /*@ApiOperation(
            tags = "LeaderIndexCountListController",
            value = "本周投诉热点行业TOP10统计")
    @GetMapping("regionCountByDate")
    @ResponseBody
    public RestResult industrynameCountByDate(@ApiParam(value = "本周投诉热点行业TOP10", required = false)
                                        @RequestParam Map<String, Object> requestMap) {
        String begindate = (String) requestMap.get("begindate");
        String enddate = (String) requestMap.get("enddate");
        if (StringUtils.isNotBlank(begindate) && StringUtils.isNotBlank(enddate)) {
            Date begin = TimeRelevantUtil.strToDate(begindate);
            Date end = TimeRelevantUtil.strToDate(enddate);
            Map<String, Object> map = iCountReportService.searchindustrynamenCountByDate(regions, begin, end);
            return RestResult.ok(map);
        }
        return RestResult.error(ResultCode.PARAM_IS_INVALID);
    }*/

    /**
     * @return 首页已办事项卡片总数
     */
    @ApiOperation(
            tags = "LeaderIndexCountListController",
            value = "首页已办事项卡片总数")
    @PostMapping("searchFinishList")
    @ResponseBody
    public RestResult searchFinishList(@ApiParam(value = "查询相关参数", required = true)
                                     @RequestBody Map<String, Object> requestMap) {
        Map params = (Map) requestMap.get("params");
        Object user = params.get("user");
        AICUser aicUser = null;
        if (user != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, user);
        }
        Map<String, Object> map = iTasklistService.searchFinishListCount(aicUser);
        return RestResult.ok(map);
    }


    /**
     * @return 首页受理登记初步反馈，立案，最终反馈预警提醒
     */
    @ApiOperation(
            tags = "LeaderIndexCountListController",
            value = "受理登记预警数")
    @PostMapping("warningCount")
    @ResponseBody
    public RestResult warningCount(@ApiParam(value = "查询相关参数", required = true)
                                     @RequestBody Map<String, Object> requestMap) {
        Map params = (Map) requestMap.get("params");
        Object user1 = params.get("user");
        AICUser aicUser = null;
        if (user1 != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, user1);
        }
        Map<String, Object> map = iTasklistService.searchWarnCount(aicUser);
        return RestResult.ok(map);
    }

}

