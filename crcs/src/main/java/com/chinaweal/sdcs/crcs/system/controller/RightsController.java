package com.chinaweal.sdcs.crcs.system.controller;

import com.chinaweal.sdcs.crcs.system.entity.Rights;
import com.chinaweal.sdcs.crcs.system.service.IRightsService;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2020-05-26
 */
@Api(tags = "RightsController", description = "移动端接口类")
@Controller
@RequestMapping("/rights")
public class RightsController {


    @Qualifier("rightsServiceImpl")
    @Autowired
    private IRightsService iRightsService;

    /**
     *
     * 移动端维权服务信息辖区主体全称查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "RightsController",
            value = "移动端维权服务信息辖区主体全称查询接口")
    @PostMapping("selectEnterprise")
    @ResponseBody
    public RestResult selectEnterprise(@ApiParam(value = "查询相关参数", required = true)
                              @RequestBody Map<String, Object> requestMap) {

        List<List<String>> list = iRightsService.selectEnterprise(requestMap);
        System.out.println(list);
        return RestResult.ok(list);

    }




}
