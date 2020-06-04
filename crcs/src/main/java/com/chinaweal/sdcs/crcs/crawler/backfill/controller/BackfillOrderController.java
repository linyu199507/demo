package com.chinaweal.sdcs.crcs.crawler.backfill.controller;

import com.chinaweal.sdcs.crcs.crawler.backfill.server.IBackfillOrderService;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.*;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liny
 * @since 2020-03-03
 */
@Api(tags = "BackfillController", description = "顺德：回填失败单查询接口类")
@Controller
@RequestMapping("/Back")
public class BackfillOrderController {

    @Qualifier("backfillOrderServiceImpl")
    @Autowired
    private IBackfillOrderService iBackfillOrderServiceService;



    /**
     * @return
     */
    @ApiOperation(
            tags = "BackfillController",
            value = "回填失败单查询")
    @PostMapping("searchBackOrder")
    @ResponseBody
    public RestResult searchBackOrder(@ApiParam(value = "查询相关参数", required = true)
                                         @RequestBody Map<String, Object> requestMap) {

        Map<String,Object> backOrder = iBackfillOrderServiceService.searchBackOrder(requestMap);
        return RestResult.ok(backOrder);
    }

    /**
     * @return
     */
    @ApiOperation(
            tags = "BackfillController",
            value = "首页--回填失败单总条数查询")
    @PostMapping("searchBackOrderOfHome")
    @ResponseBody
    public RestResult searchBackOrderOfHome(@ApiParam(value = "查询相关参数", required = true)
                                      @RequestBody Map<String, Object> requestMap) {

        Integer total = iBackfillOrderServiceService.searchBackOrderOfHome(requestMap);
        return RestResult.ok(total);
    }

    /**
     * @return
     */
    @ApiOperation(
            tags = "BackfillController",
            value = "回填失败单删除")
    @GetMapping("deleteBackOrder")
    @ResponseBody

    public RestResult deleteBackOrder(@ApiParam(value = "查询相关参数", required = true)
                                          @RequestParam String[] ids) {

        iBackfillOrderServiceService.deleteBackOrder(ids);
        return RestResult.ok();
    }

    /**
     * @return
     */
    @ApiOperation(
            tags = "BackfillController",
            value = "回填失败单确认")
    @GetMapping("changeBackOrder")
    @ResponseBody

    public RestResult changeBackOrder(@ApiParam(value = "查询相关参数", required = true)
                                      @RequestParam String[] ids) {

        iBackfillOrderServiceService.changeBackOrder(ids);
        return RestResult.ok();
    }

}
