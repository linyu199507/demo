package com.chinaweal.sdcs.crcs.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Materialinfo;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.service.IMaterialinfoService;
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
 * @since 2019-08-18
 */
@Api(tags = "MaterialinfoController", description = "文件上传类")
@Controller
@RequestMapping("/materialinfo")
public class MaterialinfoController {

    @Qualifier("materialinfoServiceImpl")
    @Autowired
    private IMaterialinfoService iMaterialinfoService;

    /**
     * 文件业务列表查询
     *
     * @return
     */
    @ApiOperation(
            tags = "MaterialinfoController",
            value = "文件业务列表查询")
    @PostMapping("searchFilesInfo")
    @ResponseBody
    public RestResult searchFilesInfo(@ApiParam(value = "文件业务列表查询", required = true)
                                 @RequestBody Map<String, Object> requestMap) {
        int size = (int) requestMap.get("size");
        int current = (int) requestMap.get("page");
        IPage<Tasklist> tasklistIPage = iMaterialinfoService.searchFilesInfo(size, current, requestMap);
        return RestResult.ok(tasklistIPage);
    }

    /**
     * 文件业务列表查询
     *
     * @return
     */
    @ApiOperation(
            tags = "MaterialinfoController",
            value = "文件材料列表查询")
    @PostMapping("searchMaterials")
    @ResponseBody
    public RestResult searchMaterials(@ApiParam(value = "文件材料列表查询", required = true)
                                 @RequestBody Map<String, Object> requestMap) {
        int size = (int) requestMap.get("size");
        int current = (int) requestMap.get("page");
        String caseguid = (String) requestMap.get("caseguid");
        String id= (String) requestMap.get("id");
        QueryWrapper<Materialinfo> queryWrapper = new QueryWrapper<Materialinfo>();
        queryWrapper.lambda().eq(Materialinfo::getCaseguid, caseguid);
        IPage<Materialinfo> materialinfoIPage = iMaterialinfoService.page(new Page(current, size), queryWrapper);
        return RestResult.ok(materialinfoIPage);
    }

    /**
     * 附件删除
     *
     * @return
     */
    @ApiOperation(
            tags = "MaterialinfoController",
            value = "附件删除")
    @RequestMapping(value = "/deletefile", method = RequestMethod.POST)
    @ResponseBody
    public RestResult deleteUploadFile(@RequestBody Map<String, String> requestMap) {
        Map<String,String> map=requestMap;
        iMaterialinfoService.deleteByPathAndName(map);
        return RestResult.ok();
    }

}
