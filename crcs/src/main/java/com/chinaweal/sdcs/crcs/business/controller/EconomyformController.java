package com.chinaweal.sdcs.crcs.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.sdcs.crcs.business.entity.Economyform;
import com.chinaweal.sdcs.crcs.business.service.IEconomyformService;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.IComeconomicmoduleService;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
@Controller
@RequestMapping("/economyform")
public class EconomyformController {

    @Qualifier("economyformServiceImpl")
    @Autowired
    private IEconomyformService iEconomyformService;

    /**
     * 举报表单查询接口  2020-01-02
     *
     * @return
     */
    @ApiOperation(
            tags = "EconomyformController",
            value = "举报表单查询")
    @PostMapping("searchEconomyformList")
    @ResponseBody
    public RestResult searchEconomyformList(@ApiParam(value = "查询相关参数", required = true)
                                         @RequestBody Map<String, Object> requestMap) {
        int size = requestMap.get("size") != null ? ((Integer) requestMap.get("size")).intValue() : 10;
        int current = requestMap.get("page") != null ? ((Integer) requestMap.get("page")).intValue() : 1;
        IPage<Economyform> economyformIPage = iEconomyformService.searchEconomyformList(current, size, requestMap);
        return RestResult.ok(economyformIPage);
    }

}
