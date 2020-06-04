package com.chinaweal.sdcs.crcs.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.entity.StatisticsDate;
import com.chinaweal.sdcs.crcs.system.entity.TreeModel;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeformService;
import com.chinaweal.sdcs.crcs.system.service.IStatisticsDateService;
import com.chinaweal.sdcs.crcs.util.BaseUtil;
import com.chinaweal.sdcs.crcs.util.RequestUtil;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Lin Yu
 * @since 2019-09-29
 */
@Api(tags = "StatisticsDataController", description = "查询接口类")
@Controller
@RequestMapping("/statistics")
public class StatisticsDataController {


    @Qualifier("statisticsDateServiceImpl")
    @Autowired
    private IStatisticsDateService iStatisticsDateService;

    /**
     *
     * 各地区业务量比较查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "各地区业务量比较查询接口")
    @PostMapping("regionalBusiness")
    @ResponseBody
    public RestResult regionalBusiness(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestBody Map<String, Object> requestMap) {

        Map<String,Object> map = iStatisticsDateService.selectRegionalData(requestMap);
        System.out.println(map);
        return RestResult.ok(map);

    }


    /**
     *
     * 投诉热点行业Top5--查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "投诉热点行业Top6--查询")
    @PostMapping("complaintRanking")
    @ResponseBody
    public RestResult complaintRanking(@ApiParam(value = "查询相关参数", required = true)
                                       @RequestBody Map<String, Object> requestMap) {

        Map<String ,Object> map = iStatisticsDateService.selectComplaintRanking(requestMap);
        return RestResult.ok(map);

    }


    /**
     *
     * 投诉热点商家Top10--查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "投诉热点商家Top10--查询")
    @PostMapping("tenBusiness")
    @ResponseBody
    public RestResult tenBusiness(@ApiParam(value = "查询相关参数", required = true)
                                  @RequestBody Map<String, Object> requestMap) {

        Map<String,Object> map = iStatisticsDateService.selectTenBusiness(requestMap);
        return RestResult.ok(map);

    }

    /**
     *
     * 投诉热点地区--查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "投诉热点地区--查询")
    @PostMapping("complaintsArea")
    @ResponseBody
    public RestResult complaintsArea(@ApiParam(value = "查询相关参数", required = true)
                                  @RequestBody Map<String, Object> requestMap) {

        Map<String,Object> list = iStatisticsDateService.selectComplaintsArea(requestMap);
        return RestResult.ok(list);

    }

    /**
     *
     * 各地区办结率统计--查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "各地区办结率统计--查询")
    @PostMapping("regionComplete")
    @ResponseBody
    public RestResult regionComplete(@ApiParam(value = "查询相关参数", required = true)
                                     @RequestBody Map<String, Object> requestMap) {

        List<StatisticsDate> list = iStatisticsDateService.selectRegionComplete(requestMap);
        return RestResult.ok(list);

    }

    /**
     *
     * 投诉举报性质统计分析(客体分类)--查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "投诉举报性质统计分析(商品服务类)--查询")
    @PostMapping("natureAnalysis")
    @ResponseBody
    public RestResult natureAnalysis(@ApiParam(value = "查询相关参数", required = true)
                                     @RequestBody Map<String, Object> requestMap) {

        List<StatisticsDate> list = iStatisticsDateService.selectNatureAnalysis(requestMap);
        return RestResult.ok(list);

    }


    /**
     *
     * 举报性质统计分析(涉嫌违法分类)--查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "举报性质统计分析(涉嫌违法分类)--查询")
    @PostMapping("illegalBehavior")
    @ResponseBody
    public RestResult illegalBehavior(@ApiParam(value = "查询相关参数", required = true)
                                     @RequestBody Map<String, Object> requestMap) {

        Map<String, Object>  map = iStatisticsDateService.selectIllegalBehavior(requestMap);
        return RestResult.ok(map);

    }

    /**
     *
     * 投诉举报热点事件(问题分类)--查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "投诉举报热点事件(问题分类)--查询")
    @PostMapping("hotQuestion")
    @ResponseBody
    public RestResult hotQuestion(@ApiParam(value = "查询相关参数", required = true)
                                     @RequestBody Map<String, Object> requestMap) {

        Map<String,Object> map = iStatisticsDateService.selectHotQuestion(requestMap);
        return RestResult.ok(map);

    }

    /**
     *
     * 投诉举报案件数据对比(案件,受理,立案,办结,诉转案总数)--查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "投诉举报案件数据对比(案件,受理,立案,办结,诉转案总数)--查询")
    @PostMapping("overallBusiness")
    @ResponseBody
    public RestResult overallBusiness(@ApiParam(value = "查询相关参数", required = true)
                                  @RequestBody Map<String, Object> requestMap) {

        Map<String, Object> map = iStatisticsDateService.selectOverallBusiness(requestMap);
        return RestResult.ok(map);

    }

    /**
     *
     * 投诉举报热点(商品服务类)变化情况统计--查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "投诉举报热点(商品服务类)变化情况统计--查询")
    @PostMapping("hotQuestionChange")
    @ResponseBody
    public RestResult hotQuestionChange(@ApiParam(value = "查询相关参数", required = true)
                                  @RequestBody Map<String, Object> requestMap) {

        List<StatisticsDate> map = iStatisticsDateService.selectHotQuestionChange(requestMap);
        return RestResult.ok(map);

    }



    /**
     *
     * (三大表单)同类型案件数量--查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsDataController",
            value = "(三大表单)同类型案件数量--查询")
    @PostMapping("businessTotal")
    @ResponseBody
    public RestResult businessTotal(@ApiParam(value = "查询相关参数", required = true)
                                  @RequestBody Map<String, Object> requestMap) {

        List<StatisticsDate> list = iStatisticsDateService.selectBusinessTotal(requestMap);
        return RestResult.ok(list);

    }



}
