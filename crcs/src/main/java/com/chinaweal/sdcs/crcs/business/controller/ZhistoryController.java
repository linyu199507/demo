package com.chinaweal.sdcs.crcs.business.controller;

import com.chinaweal.sdcs.crcs.business.service.IEconomyformService;
import com.chinaweal.sdcs.crcs.business.service.IZhistoryService;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chinaweal
 * @since 2020-03-17
 */
@Controller
@RequestMapping("/zhistory")
public class ZhistoryController {


    @Qualifier("zhistoryServiceImpl")
    @Autowired
    private IZhistoryService izhistoryservice;

    @ApiOperation(
            tags = "zConsumption",
            value = "手动调回填12345投诉")
    @PostMapping("Zhistory")
    @ResponseBody
    public RestResult zhistory() {
        izhistoryservice.manualConsumption();
        return RestResult.ok();
    }


    @ApiOperation(
            tags = "zEconomy",
            value = "手动调回填12345经济")
    @PostMapping("Zhistory2")
    @ResponseBody
    public RestResult zhistory2() {
        izhistoryservice.manualEconomy();
        return RestResult.ok();
    }

    @ApiOperation(
            tags = "economyhistorydata",
            value = "经济类历史工单查询")
    @PostMapping("EconomyHistorydata")
    @ResponseBody
    public RestResult getEconomyHistorydata() {
        izhistoryservice.selectEconomyHistory();
        return RestResult.ok();
    }

    @ApiOperation(
            tags = "complainthistorydata",
            value = "投诉类历史工单查询")
    @PostMapping("ComplaintHistorydata")
    @ResponseBody
    public RestResult getComplaintHistorydata() {
        izhistoryservice.selectComplaintHistory();
        return RestResult.ok();
    }
}
