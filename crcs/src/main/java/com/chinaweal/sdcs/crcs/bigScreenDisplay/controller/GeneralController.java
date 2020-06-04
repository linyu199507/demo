package com.chinaweal.sdcs.crcs.bigScreenDisplay.controller;


import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IGeneralService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IScreenDisplayService;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * @author liny
 * @since 2020-03-08
 */
@Api(tags = "GeneralController", description = "查询接口类")
@Controller
@RequestMapping("/general")
public class GeneralController {


    @Qualifier("generalServiceImpl")
    @Autowired
    private IGeneralService iGeneralService;

    @ApiOperation(
            tags = "GeneralController",
            value = "")
    @GetMapping("changeBusinessdept")
    @ResponseBody
    public RestResult changeBusinessdept(@ApiParam(value = "查询相关参数", required = true)
                                       @RequestParam Map<String, Object> requestMap) {

        iGeneralService.changeBusinessdept(requestMap);

        return RestResult.ok();
    }



}
