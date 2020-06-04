package com.chinaweal.sdcs.crcs.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.sdcs.crcs.business.entity.Consultationform;
import com.chinaweal.sdcs.crcs.business.service.ICustomerinfoService;
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
 * @since 2019-08-22
 */
@Controller
@RequestMapping("/consultationform")
public class ConsultationformController {

    @Qualifier("customerinfoServiceImpl")
    @Autowired
    private ICustomerinfoService iCustomerinfoService;

    /**
     * 咨询表单查询接口  2020-01-02
     *
     * @return
     */
    @ApiOperation(
            tags = "ConsultationformController",
            value = "咨询表单查询")
    @PostMapping("searchConsultationformList")
    @ResponseBody
    public RestResult searchBusinessInfo(@ApiParam(value = "查询相关参数", required = true)
                                         @RequestBody Map<String, Object> requestMap) {
        int size = requestMap.get("size") != null ? ((Integer) requestMap.get("size")).intValue() : 10;
        int current = requestMap.get("page") != null ? ((Integer) requestMap.get("page")).intValue() : 1;
        IPage<Consultationform> consultationformIPage = iCustomerinfoService.searchConsultationformList(current, size, requestMap);
        return RestResult.ok(consultationformIPage);
    }

}
