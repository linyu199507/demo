package com.chinaweal.sdcs.crcs.crawler.bmfw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Datagriddata;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.IDatagriddataService;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author chents
 * @since 2019-08-15
 */
@Api(tags = "DatagriddataController", description = "佛山12345：未受理待办列表查询接口类")
@Controller
@RequestMapping("/datagriddata")
public class DatagriddataController {

    @Qualifier("datagriddataServiceImpl")
    @Autowired
    private IDatagriddataService iDatagriddataService;

    /**
     * 获取未处理待办列表数据(爬取回来的数据)
     * @param requestMap
     * @return
     */
    @ApiOperation(
            tags = "DatagriddataController",
            value = "未受理待办列表查询")
    @PostMapping("list")
    @ResponseBody
    public RestResult list(@RequestBody Map<String, String> requestMap) {
        String tserialnum = requestMap.get("tserialnum");//工单号
        String rqsttitle = requestMap.get("rqsttitle");//标题
        String serialnum = requestMap.get("serialnum");//表单号
        QueryWrapper<Datagriddata> wrapper = new QueryWrapper<Datagriddata>();
        wrapper.lambda().eq(Datagriddata:: getStatus, "0");
        wrapper.lambda().ne(Datagriddata:: getState, "1");
        //查询条件
        if (StringUtils.isNotBlank(tserialnum)) {
            wrapper.lambda().eq(Datagriddata::getTserialnum,tserialnum);//工单号
        }
        if (StringUtils.isNotBlank(rqsttitle)) {
            wrapper.lambda().like(Datagriddata::getRqsttitle, rqsttitle);//标题
        }
        if (StringUtils.isNotBlank(serialnum)) {
            wrapper.lambda().eq(Datagriddata::getSerialnum,serialnum);//表单号
        }
        wrapper.orderByDesc("createdate");
        int size = Integer.parseInt(requestMap.get("size"));
        int current = Integer.parseInt(requestMap.get("page"));
        IPage<Datagriddata> datagriddataIPage = iDatagriddataService.page(new Page(current, size), wrapper);
        return RestResult.ok(datagriddataIPage);
    }
}
