package com.chinaweal.sdcs.crcs.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.system.entity.Deadlinemanagement;
import com.chinaweal.sdcs.crcs.system.service.IDeadlinemanagementService;
import com.chinaweal.sdcs.crcs.util.RequestUtil;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author chinaweal
 * @since 2019-09-16
 */
@Controller
@RequestMapping("/deadlinemanagement")
public class DeadlinemanagementController {

    @Qualifier("deadlinemanagementServiceImpl")
    @Autowired
    private IDeadlinemanagementService iDeadlinemanagementService;

    @ApiOperation(
            tags = "DeadlinemanagementController",
            value = "业务保存时限信息")
    @PostMapping("saveDeadlinemanagement")
    @ResponseBody
    public RestResult saveDeadlinemanagement(@ApiParam(value = "业务时限信息参数", required = true)
                                      @RequestBody Map<String, Object> requestMap) {

        Deadlinemanagement deadlinemanagement = null;
        if (requestMap.get("deadlinemanagement") != null) {
            deadlinemanagement = (Deadlinemanagement) RequestUtil.mapToEntity(Deadlinemanagement.class, requestMap.get("deadlinemanagement"));
            if (deadlinemanagement != null) {
                if (StringUtils.isBlank(deadlinemanagement.getId())) {
                    deadlinemanagement.setId(UUID.randomUUID().toString());
                }
                iDeadlinemanagementService.saveOrUpdate(deadlinemanagement);
            }
        }
        return RestResult.ok();
    }

    @ApiOperation(
            tags = "DeadlinemanagementController",
            value = "业务时限信息查询")
    @PostMapping("searchDeadlinemanagement")
    @ResponseBody
    public RestResult searchDeadlinemanagement(@ApiParam(value = "业务时限信息查询参数", required = true)
                                             @RequestBody Map<String, String> requestMap) {
        String level = requestMap.get("level"); //工单级别
        String bustype = requestMap.get("bustype"); //业务分类
        String deadlinetype = requestMap.get("deadlinetype"); //时限类型
        int size = Integer.parseInt(requestMap.get("size"));
        int current = Integer.parseInt(requestMap.get("page"));
        QueryWrapper<Deadlinemanagement> queryWrapper = new QueryWrapper<Deadlinemanagement>();
        if (StringUtils.isNotBlank(level)) {
            queryWrapper.lambda().eq(Deadlinemanagement::getLevel, level);
        }
        if (StringUtils.isNotBlank(bustype)) {
            queryWrapper.lambda().eq(Deadlinemanagement::getBustype, bustype);
        }
        if (StringUtils.isNotBlank(deadlinetype)) {
            queryWrapper.lambda().eq(Deadlinemanagement::getDeadlinetype, deadlinetype);
        }
        IPage<Deadlinemanagement> deadlinemanagementIPage = iDeadlinemanagementService.page(new Page(current, size), queryWrapper);
        return RestResult.ok(deadlinemanagementIPage);
    }

    @ApiOperation(
            tags = "DeadlinemanagementController",
            value = "业务时限信息删除")
    @GetMapping("deleteDeadlinemanagement")
    @ResponseBody
    public RestResult deleteDeadlinemanagement(@ApiParam(value = "业务时限信息参数", required = true)
                                     @RequestParam List idList) {
        return RestResult.ok(iDeadlinemanagementService.removeByIds(idList));
    }
}
