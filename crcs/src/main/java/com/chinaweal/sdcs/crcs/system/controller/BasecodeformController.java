package com.chinaweal.sdcs.crcs.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.entity.TreeModel;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeformService;
import com.chinaweal.sdcs.crcs.util.BaseUtil;
import com.chinaweal.sdcs.crcs.util.RequestUtil;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Lin Yu
 * @since 2019-09-29
 */
@Api(tags = "BasecodeformController", description = "查询接口类")
@Controller
@RequestMapping("/basecodeSearch")
public class BasecodeformController {


    @Qualifier("basecodeformServiceImpl")
    @Autowired
    private IBasecodeformService iBasecodeformService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 基础数据查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "BasecodeformController",
            value = "基础数据信息查询")
    @PostMapping("listBasicData")
    @ResponseBody
    public RestResult listBasicData(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestBody Map<String, String> requestMap) {

//            String orglevel = "1"; //默认级别是1
        int size = StringUtils.isBlank(requestMap.get("size")) ? 10 : Integer.parseInt(requestMap.get("size"));
        int page = StringUtils.isBlank(requestMap.get("page")) ? 1 : Integer.parseInt(requestMap.get("page"));
//            if (StringUtils.isBlank(requestMap.get("orglevel"))) {
//                requestMap.put("orglevel", orglevel);
//            } else {
//                orglevel = Integer.toString(Integer.parseInt(requestMap.get("orglevel")) + 1);
//                requestMap.put("orglevel", orglevel);
//            }
        IPage<Basecodeform> basecodeform = iBasecodeformService.findBasecodeformByMap(page, size, requestMap);
        System.out.println(basecodeform);
        return RestResult.ok(basecodeform);

    }

    /**
     * 基础数据添加接口
     *
     * @return
     */
    @ApiOperation(
            tags = "BasecodeformController",
            value = "基础数据信息添加")
    @PostMapping("addBasicData")
    @ResponseBody
    public RestResult addBasicData(@ApiParam(value = "查询相关参数", required = true)
                                   @RequestBody Map<String, Object> requestMap) {
        iBasecodeformService.addData(requestMap);
        return RestResult.ok();
    }

    @ApiOperation(
            tags = "BasecodeformController",
            value = "基础数据删除")
    @GetMapping("deleteBasicData")
    @ResponseBody
    public RestResult deleteBasicData(@ApiParam(value = "基础数据信息", required = true)
                                      @RequestParam String[] ids, @RequestParam Object user) {
        AICUser aicUser = null;
        if (user != null) {
            JSONObject userJson = JSONObject.parseObject(user.toString());
            aicUser = JSON.toJavaObject(userJson, AICUser.class);
        }
        if (aicUser == null) {
            aicUser = new AICUser();
        }
        iBasecodeformService.deleteByIds(ids, aicUser);
        return RestResult.ok();
    }

    /**
     * 基础数据修改接口
     *
     * @return
     */
    @ApiOperation(
            tags = "BasecodeformController",
            value = "基础数据信息修改")
    @PostMapping("updateBasicData")
    @ResponseBody
    public RestResult updateBasicData(@ApiParam(value = "基础数据信息", required = true)
                                      @RequestBody Map<String, Object> requestMap) {
        Basecodeform basecodeform = null;
        if (requestMap.get("basecodeform") != null) {
            basecodeform = (Basecodeform) RequestUtil.mapToEntity(Basecodeform.class, requestMap.get("basecodeform"));
            iBasecodeformService.saveOrUpdate(basecodeform);
        }
        return RestResult.ok();
    }

    @ApiOperation(
            tags = "BasecodeformController",
            value = "根据类型查询基础数据信息")
    @GetMapping("selectBaseCodeByType")
    @ResponseBody
    public RestResult selectBaseCodeByType(@ApiParam(value = "基础数据类型", required = true)
                                           @RequestParam String type) {
        QueryWrapper<Basecodeform> basecodeformWrapper = new QueryWrapper<Basecodeform>();
        basecodeformWrapper.lambda().eq(Basecodeform::getType, type).eq(Basecodeform::getOrglevel, "1");
        List<Basecodeform> basecodeformList = iBasecodeformService.list(basecodeformWrapper);
        return RestResult.ok(basecodeformList);
    }

    @ApiOperation(
            tags = "BasecodeformController",
            value = "根据Parentid查询基础数据信息")
    @GetMapping("selectBaseCodeByParentid")
    @ResponseBody
    public RestResult selectBaseCodeByParentid(@ApiParam(value = "父类主键", required = true)
                                               @RequestParam String parentid) {
        QueryWrapper<Basecodeform> basecodeformWrapper = new QueryWrapper<Basecodeform>();
        basecodeformWrapper.lambda().eq(Basecodeform::getParentid, parentid);
        List<Basecodeform> basecodeformList = iBasecodeformService.list(basecodeformWrapper);
        return RestResult.ok(basecodeformList);
    }

    @ApiOperation(
            tags = "BasecodeformController",
            value = "根据基础代码类型查询基础数据树")
    @GetMapping("getBaseCodeTreeByType")
    @ResponseBody
    public RestResult getBaseCodeTreeByType(@ApiParam(value = "基础代码类型", required = true)
                                            @RequestParam String type) {
        JSONArray jsonArray = null;
        QueryWrapper<Basecodeform> basecodeformWrapper = new QueryWrapper<Basecodeform>();
        basecodeformWrapper.lambda().eq(Basecodeform::getType, type);
        basecodeformWrapper.orderByAsc("type");
        basecodeformWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeformList = iBasecodeformService.list(basecodeformWrapper);
        if (basecodeformList != null) {
            TreeModel treeModel = BaseUtil.formatBaseCodeTree(basecodeformList);
            jsonArray = JSONArray.fromObject(treeModel);
        }
        return RestResult.ok(jsonArray);
    }

    @ApiOperation(
            tags = "BasecodeformController",
            value = "根据基础代码类型查询基础数据树")
    @GetMapping("selectBaseCodeTree")
    @ResponseBody
    public RestResult selectBaseCodeTree(@ApiParam(value = "基础数据类型", required = true)
                                         @RequestParam String type, @RequestParam(defaultValue = "1") String orgLevel) {
        return RestResult.ok(iBasecodeformService.listBaseCodeByTypeAndLevel(type, orgLevel));
    }

    /**
     * 基础数据查询内容按钮
     *
     * @return
     */
    @ApiOperation(
            tags = "BasecodeformController",
            value = "基础数据查询内容按钮")
    @PostMapping("selectBasicData")
    @ResponseBody
    public RestResult selectBasicData(@ApiParam(value = "基础数据查询内容按钮", required = true)
                                      @RequestBody Map<String, String> requestMap) {
        int size = Integer.parseInt((String) requestMap.get("size"));
        int page = Integer.parseInt((String) requestMap.get("page"));

        Basecodeform basecodeform = null;

        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(Basecodeform::getComment, requestMap.get("comment"));

//        IPage<Basecodeform> basecodeformIPage= iBasecodeformService.page(new Page(current, size), queryWrapper);
        IPage<Basecodeform> basecodeformIPage = iBasecodeformService.findBasecodeformByMap(page, size, requestMap);
        System.out.println(basecodeform);
        return RestResult.ok(basecodeformIPage);
    }

    @ApiOperation(
            tags = "BasecodeformController",
            value = "根据Codeid查询基础数据信息")
    @GetMapping("selectBaseCodeByCodeid")
    @ResponseBody
    public RestResult selectBaseCodeByCodeid(@ApiParam(value = "根据Codeid查询基础数据信息", required = true)
                                             @RequestParam String providetype) {
        QueryWrapper<Basecodeform> basecodeformWrapper = new QueryWrapper<Basecodeform>();
        basecodeformWrapper.lambda().eq(Basecodeform::getCodeid, providetype);
        List<Basecodeform> basecodeformList = iBasecodeformService.list(basecodeformWrapper);
        return RestResult.ok(basecodeformList);
    }

    /**
     * 基础数据查询内容按钮
     * @return
     */
    /*@ApiOperation(
            tags = "BasecodeformController",
            value = "基础数据父集查询内容")
    @PostMapping("selectDataList")
    @ResponseBody
    public RestResult selectDataList(@ApiParam(value = "基础数据父集查询内容", required = true)
                                      @RequestBody Map<String,Object> requestMap) {

        Map<String,Object> basecodeform = new HashMap<>();

        basecodeform = (Map<String, Object>) requestMap.get("caseprocesform");

//        Map<String, Object> stringObjectMap = BeanUtils.beanToMap(caseprocesform);
//        basecodeform.putAll(stringObjectMap);
        System.out.println(basecodeform);

//        String[] subjecttype = (String[]) basecodeform.get("subjecttype");
        String[] subjecttype={String.valueOf(basecodeform.get("subjecttype"))};

        System.out.println(subjecttype);
        if(basecodeform.get("subjecttype")!=null & basecodeform.get("subjecttype")!=""){
            QueryWrapper<Basecodeform> basecodeformWrapper = new QueryWrapper<Basecodeform>();
            basecodeformWrapper.lambda().eq(Basecodeform::getComment, basecodeform.get("subjecttype"));
            List<Basecodeform> basecodeformList  = iBasecodeformService.list(basecodeformWrapper);
            System.out.println(basecodeformList.get(0).getParentid());


            if(basecodeformList.get(0).getParentid()!=null && basecodeformList.get(0).getParentid()!=""){
                QueryWrapper<Basecodeform> basecodeformWrapper1 = new QueryWrapper<Basecodeform>();
                basecodeformWrapper1.lambda().eq(Basecodeform::getId,basecodeformList.get(0).getParentid());
                List<Basecodeform> basecodeformList1  = iBasecodeformService.list(basecodeformWrapper1);
                System.out.println(basecodeformList1.get(0).getParentid());
                System.out.println(basecodeformList1.get(0));
            }
        }



        return RestResult.ok();
    }*/

    /**
     * 6小时重新刷新一遍基础数据
     */
//    @Scheduled(fixedDelay = 21600000)
    public void restartLoadBase() {
        logger.info("开始刷新基础数据缓存");
        iBasecodeformService.listBaseCodeByTypeAndLevel("", "clean");//手动清空cache
        List<String> types = iBasecodeformService.listDistinctType();
        for (String type : types) {
            //最大位数级别
//            int number = iBasecodeformService.getMaxLevelByType(type);
//            for (int i = 0; i <= number; i++) {
            //手动加载所有toString
            iBasecodeformService.listBaseCodeByTypeAndLevel(String.valueOf(type), "1").toString();
//            }
        }
        logger.info("刷新基础数据缓存结束");
    }
}
