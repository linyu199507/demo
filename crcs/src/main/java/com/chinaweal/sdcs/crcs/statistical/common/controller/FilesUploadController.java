package com.chinaweal.sdcs.crcs.statistical.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.service.IMaterialinfoService;
import com.chinaweal.sdcs.crcs.business.service.IStatisticsinfoService;
import com.chinaweal.youfool.framework.rest.RestResult;
import com.chinaweal.youfool.framework.rest.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: NaJim
 * @Date: 2019/10/6
 */

@Api(tags = "FilesUploadController", description = "通用文件上传类")
@Controller
@RequestMapping("/filesUpload")
public class FilesUploadController {

    @Qualifier("materialinfoServiceImpl")
    @Autowired
    private IMaterialinfoService iMaterialinfoService;

    @Qualifier("statisticsinfoServiceImpl")
    @Autowired
    private IStatisticsinfoService iStatisticsinfoService;

    /**
     * 通用文件上传信息保存
     *
     * @return
     */
    @ApiOperation(
            tags = "MaterialinfoController",
            value = "文件上传")
    @RequestMapping(value = "/upload", method =RequestMethod.POST)
    @ResponseBody
    public RestResult uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) {
            Map<String, Object> map = iMaterialinfoService.uploadMethod(file,request);
        if ("1".equals(map.get("status"))){
            return RestResult.ok(map);
        }else {
            return RestResult.error(ResultCode.PARAM_IS_INVALID);
        }
    }

    /**
     * 历史统计数据上传信息保存
     *  2019-11-25
     * @return
     */
    @ApiOperation(
            tags = "StatisticsinfoController",
            value = "数据上传")
    @RequestMapping(value = "/uploadStatisticsinfo", method =RequestMethod.POST)
    @ResponseBody
    public RestResult uploadStatisticsinfoFile(@RequestParam MultipartFile file, HttpServletRequest request,@RequestParam Map<String, Object> aicUser) {
        Map<String, Object> map = iStatisticsinfoService.uploadMethod(file,request,aicUser);
        if ("0".equals(map.get("status"))){
            return RestResult.ok(map);
        }else {
            return RestResult.error(ResultCode.PARAM_IS_INVALID);
        }
    }
}
