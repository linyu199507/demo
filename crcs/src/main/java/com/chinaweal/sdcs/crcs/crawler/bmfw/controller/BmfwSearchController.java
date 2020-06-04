package com.chinaweal.sdcs.crcs.crawler.bmfw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.*;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.*;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author chents
 * @since 2019-08-19
 */
@Api(tags = "BmfwSearchController", description = "佛山12345：未处理待办详细信息查询接口类")
@Controller
@RequestMapping("/bmfwSearch")
public class BmfwSearchController {

    @Qualifier("datagriddataServiceImpl")
    @Autowired
    private IDatagriddataService iDatagriddataService;

    @Qualifier("seatsServiceImpl")
    @Autowired
    private ISeatsService iSeatsService;

    @Qualifier("commommoduleServiceImpl")
    @Autowired
    private ICommommoduleService iCommommoduleService;

    @Qualifier("comeconomicmoduleServiceImpl")
    @Autowired
    private IComeconomicmoduleService iComeconomicmoduleService;

    @Qualifier("complaintmoduleServiceImpl")
    @Autowired
    private IComplaintmoduleService iComplaintmoduleService;

    @Qualifier("askdepthandleServiceImpl")
    @Autowired
    private IAskdepthandleService iAskdepthandleService;

    @Qualifier("inrequestServiceImpl")
    @Autowired
    private IInrequestService iInrequestService;


    /**
     * 未处理待办详细信息查询
     * @return
     */
    @ApiOperation(
            tags = "BmfwSearchController",
            value = "未处理待办详细信息查询")
    @PostMapping("searchBaseInfo")
    @ResponseBody
    public RestResult searchBaseInfo(@ApiParam(value = "查询相关参数", required = true)
                                         @RequestBody Map<String, String> requestMap) {
        Map searchMap = new HashMap();
        String id = requestMap.get("id");
        String caseguid = requestMap.get("caseguid");
        //待办列表
        Datagriddata datagriddata = iDatagriddataService.getById(id);

        //话务信息
        QueryWrapper<Seats> seatsWrapper = new QueryWrapper<Seats>();
        seatsWrapper.lambda().eq(Seats::getCaseguid, caseguid);
        Seats seats = iSeatsService.getOne(seatsWrapper,false);

        //通用表单
        QueryWrapper<Commommodule> commommoduleWrapper = new QueryWrapper<Commommodule>();
        commommoduleWrapper.lambda().eq(Commommodule::getCaseguid, caseguid);
        Commommodule commommodule = iCommommoduleService.getOne(commommoduleWrapper,false);

        //经济违法表单
        QueryWrapper<Comeconomicmodule> comeconomicmoduleWrapper = new QueryWrapper<Comeconomicmodule>();
        comeconomicmoduleWrapper.lambda().eq(Comeconomicmodule::getCaseguid, caseguid);
        Comeconomicmodule comeconomicmodule = iComeconomicmoduleService.getOne(comeconomicmoduleWrapper,false);

        //消费投诉表单
        QueryWrapper<Complaintmodule> complaintmoduleWrapper = new QueryWrapper<Complaintmodule>();
        complaintmoduleWrapper.lambda().eq(Complaintmodule::getCaseguid, caseguid);
        Complaintmodule complaintmodule = iComplaintmoduleService.getOne(complaintmoduleWrapper,false);

        //处办要求
        QueryWrapper<Inrequest> inrequestWrapper = new QueryWrapper<Inrequest>();
        inrequestWrapper.lambda().eq(Inrequest::getCaseguid, caseguid);
        Inrequest inrequest = iInrequestService.getOne(inrequestWrapper,false);

        //处理意见
        QueryWrapper<Askdepthandle> askdepthandleWrapper = new QueryWrapper<Askdepthandle>();
        askdepthandleWrapper.lambda().eq(Askdepthandle::getCaseguid, caseguid);
        List<Askdepthandle> askdepthandleList = iAskdepthandleService.list(askdepthandleWrapper);

        searchMap.put("datagriddata", datagriddata);
        searchMap.put("seats", seats);
        searchMap.put("commommodule", commommodule);
        searchMap.put("comeconomicmodule", comeconomicmodule);
        searchMap.put("complaintmodule", complaintmodule);
        searchMap.put("inrequest", inrequest);
        searchMap.put("askdepthandleList", askdepthandleList);
        return RestResult.ok(searchMap);
    }
}
