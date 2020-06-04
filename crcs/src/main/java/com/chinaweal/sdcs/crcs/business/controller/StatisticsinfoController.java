package com.chinaweal.sdcs.crcs.business.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.Statisticsinfo;
import com.chinaweal.sdcs.crcs.business.service.IStatisticsinfoService;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-11-25
 */
@Api(tags = "StatisticsinfoController", description = "历史统计数据上传类")
@Controller
@RequestMapping("/statisticsinfo")
public class StatisticsinfoController {

    @Qualifier("statisticsinfoServiceImpl")
    @Autowired
    private IStatisticsinfoService iStatisticsinfoService;

    /**
     * 历史统计数据列表查询
     *
     * @return
     */
    @ApiOperation(
            tags = "StatisticsinfoController",
            value = "历史统计数据列表查询")
    @PostMapping(value = "/searchStatisticsinfos")
    @ResponseBody
    public RestResult searchFilesInfo(@ApiParam(value = "历史统计数据列表信息", required = true)
                                      @RequestBody Map<String, Object> requestMap) {
        int size = (int) requestMap.get("size");
        int current = (int) requestMap.get("page");
        IPage<Statisticsinfo> statisticsIPage = iStatisticsinfoService.searchStatisticsinfos(size, current, requestMap);
        return RestResult.ok(statisticsIPage);
    }

    @ApiOperation(
            tags = "StatisticsinfoController",
            value = "历史统计数据删除")
    @GetMapping("deleteStatisticsinfos")
    @ResponseBody
    public RestResult deletedeleteStatisticsinfos(@ApiParam(value = "历史统计数据", required = true)
                                     @RequestParam String[] ids, @RequestParam Object user) {
        AICUser aicUser = null;
        if (user != null) {
            JSONObject userJson = JSONObject.parseObject(user.toString());
            aicUser = JSON.toJavaObject(userJson, AICUser.class);
        }
        if (aicUser == null) {
            aicUser = new AICUser();
        }
        iStatisticsinfoService.deleteByIds(ids, aicUser);
        return RestResult.ok();
    }
}
