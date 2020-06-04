package com.chinaweal.sdcs.crcs.system.controller;
import com.chinaweal.sdcs.crcs.system.entity.EconomySituation;
import com.chinaweal.sdcs.crcs.system.entity.OverallSituation;
import com.chinaweal.sdcs.crcs.system.service.IEconomySituationService;
import com.chinaweal.sdcs.crcs.system.service.IOverallSituationService;
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

import java.util.List;
import java.util.Map;


/**
 * @author Lin Yu
 * @since 2020-02-05
 */
@Api(tags = "EconomySituationController", description = "查询接口类")
@Controller
@RequestMapping("/economy")
public class EconomySituationController {


    @Qualifier("economySituationServiceImpl")
    @Autowired
    private IEconomySituationService iEconomySituationService;

    /**
     *
     * 全区举报基本情况查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "EconomySituationController",
            value = "全区举报基本情况查询接口")
    @PostMapping("economydata")
    @ResponseBody
    public RestResult economydata(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestBody Map<String, Object> requestMap) {

        Map<String,Object> map = iEconomySituationService.selectEconomydata(requestMap);
        System.out.println(map);
        return RestResult.ok(map);

    }

    /**
     *
     * 涉嫌违法情况情况查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "EconomySituationController",
            value = "涉嫌违法情况情况查询接口")
    @PostMapping("economyIllegal")
    @ResponseBody
    public RestResult economyIllegal(@ApiParam(value = "查询相关参数", required = true)
                                  @RequestBody Map<String, Object> requestMap) {

        Map<String,Object> map = iEconomySituationService.selectEconomyIllegal(requestMap);
        System.out.println(map);
        return RestResult.ok(map);

    }




}
