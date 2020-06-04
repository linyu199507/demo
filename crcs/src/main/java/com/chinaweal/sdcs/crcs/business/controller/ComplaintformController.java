package com.chinaweal.sdcs.crcs.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.sdcs.crcs.business.entity.Complaintform;
import com.chinaweal.sdcs.crcs.business.service.IComplaintformService;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.IComplaintmoduleService;
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
@RequestMapping("/complaintform")
public class ComplaintformController {

    @Qualifier("complaintformServiceImpl")
    @Autowired
    private IComplaintformService iComplaintformService;

    /**
     * 投诉表单查询接口  2020-01-02
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintformController",
            value = "投诉表单查询")
    @PostMapping("searchComplaintformList")
    @ResponseBody
    public RestResult searchComplaintformList(@ApiParam(value = "查询相关参数", required = true)
                                         @RequestBody Map<String, Object> requestMap) {
        int size = requestMap.get("size") != null ? ((Integer) requestMap.get("size")).intValue() : 10;
        int current = requestMap.get("page") != null ? ((Integer) requestMap.get("page")).intValue() : 1;
        IPage<Complaintform> complaintformIPage = iComplaintformService.searchComplaintformList(current, size, requestMap);
        return RestResult.ok(complaintformIPage);
    }

}
