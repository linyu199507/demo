package com.chinaweal.sdcs.crcs.system.controller;
import com.chinaweal.sdcs.crcs.system.service.IComplaintSituationService;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * @author Lin Yu
 * @since 2020-02-06
 */
@Api(tags = "EconomySituationController", description = "查询接口类")
@Controller
@RequestMapping("/complaint")
public class ComplaintSituationController {


    @Qualifier("complaintSituationServiceImpl")
    @Autowired
    private IComplaintSituationService iComplaintSituationService;

    /**
     *
     * 全区投诉总量及调解查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "全区投诉总量及调解查询接口")
    @PostMapping("complaintdata")
    @ResponseBody
    public RestResult complaintdata(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectComplaintdata(requestMap);
        return RestResult.ok(map);

    }


    /**
     *
     * 各单位投诉量动态变化查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "各单位投诉量动态变化查询接口")
    @PostMapping("dataOfTwelve")
    @ResponseBody
    public RestResult dataOfTwelve(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectDataOfTwelve(requestMap);
        return RestResult.ok(map);

    }

    /**
     *
     * 投诉热点性质查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "各单位投诉量动态变化查询接口")
    @PostMapping("hotNature")
    @ResponseBody
    public RestResult hotNature(@ApiParam(value = "查询相关参数", required = true)
                                   @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectHotNature(requestMap);
        return RestResult.ok(map);

    }

    /**
     *
     * 家用电器类产品具体投诉品类查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "家用电器类产品具体投诉品类查询接口")
    @PostMapping("appliancesCategory")
    @ResponseBody
    public RestResult appliancesCategory(@ApiParam(value = "查询相关参数", required = true)
                                @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectappliancesCategory(requestMap);
        return RestResult.ok(map);

    }

    /**
     *
     * 家用电器类产品具体投诉性质查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "家用电器类产品具体投诉性质查询接口")
    @PostMapping("appliancesNature")
    @ResponseBody
    public RestResult appliancesNature(@ApiParam(value = "查询相关参数", required = true)
                                       @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectappliancesNature(requestMap);
        return RestResult.ok(map);

    }

    /**
     *
     * 家用电器类产品具体投诉品牌查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "家用电器类产品具体投诉品牌查询接口")
    @PostMapping("appliancesBrand")
    @ResponseBody
    public RestResult appliancesBrand(@ApiParam(value = "查询相关参数", required = true)
                                       @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectappliancesBrand(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 被投诉主体TOP10查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "被投诉主体TOP10查询接口")
    @PostMapping("subjectTen")
    @ResponseBody
    public RestResult subjectTen(@ApiParam(value = "查询相关参数", required = true)
                                      @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectsubjectTen(requestMap);
        return RestResult.ok(map);

    }

    /**
     *
     * 被投诉热点商圈TOP10查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "被投诉热点商圈TOP10查询接口")
    @PostMapping("businesscircle")
    @ResponseBody
    public RestResult businesscircle(@ApiParam(value = "查询相关参数", required = true)
                                       @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectbusinesscircle(requestMap);
        return RestResult.ok(map);

    }

    /**
     *
     * 日用百货类产品具体投诉品类查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "日用百货类产品具体投诉品类查询接口")
    @PostMapping("dailyCategory")
    @ResponseBody
    public RestResult dailyCategory(@ApiParam(value = "查询相关参数", required = true)
                                     @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectdailyCategory(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 日用百货类产品具体投诉性质查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "日用百货类产品具体投诉性质查询接口")
    @PostMapping("dailyNature")
    @ResponseBody
    public RestResult dailyNature(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectDailyNature(requestMap);
        return RestResult.ok(map);
    }


    /**
     *
     * 日用百货类产品具体投诉性质查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "日用百货类产品具体投诉性质查询接口")
    @PostMapping("dailyBrand")
    @ResponseBody
    public RestResult dailyBrand(@ApiParam(value = "查询相关参数", required = true)
                                  @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectDailyBrand(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 交通工具类产品具体投诉品类查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "交通工具类产品具体投诉品类查询接口")
    @PostMapping("trafficCategory")
    @ResponseBody
    public RestResult trafficCategory(@ApiParam(value = "查询相关参数", required = true)
                                 @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectTrafficCategory(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 交通工具类产品具体投诉性质查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "交通工具类产品具体投诉性质查询接口")
    @PostMapping("trafficNature")
    @ResponseBody
    public RestResult trafficNature(@ApiParam(value = "查询相关参数", required = true)
                                      @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectTrafficNature(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 交通工具类产品具体投诉品牌查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "交通工具类产品具体投诉品牌查询接口")
    @PostMapping("trafficBrand")
    @ResponseBody
    public RestResult trafficBrand(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectTrafficBrand(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 装修建材类产品具体投诉品类查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "装修建材类产品具体投诉品类查询接口")
    @PostMapping("decoration")
    @ResponseBody
    public RestResult decoration(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectdecoration(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 房屋类产品具体投诉品类查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "房屋类产品具体投诉品类查询接口")
    @PostMapping("housing")
    @ResponseBody
    public RestResult housing(@ApiParam(value = "查询相关参数", required = true)
                                 @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selecthousing(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 食品类产品具体投诉品类查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "食品类产品具体投诉品类查询接口")
    @PostMapping("food")
    @ResponseBody
    public RestResult food(@ApiParam(value = "查询相关参数", required = true)
                              @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectfood(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 食品类产品具体投诉环节查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "食品类产品具体投诉环节查询接口")
    @PostMapping("foodLink")
    @ResponseBody
    public RestResult foodLink(@ApiParam(value = "查询相关参数", required = true)
                           @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectfoodLink(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 药品类产品具体投诉环节查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "药品类产品具体投诉环节查询接口")
    @PostMapping("medicineLink")
    @ResponseBody
    public RestResult foodmedicineLink(@ApiParam(value = "查询相关参数", required = true)
                               @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectmedicineLink(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 保健品类产品具体投诉环节查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "保健食品类产品具体投诉环节查询接口")
    @PostMapping("HealthFoodLink")
    @ResponseBody
    public RestResult healthFoodLink(@ApiParam(value = "查询相关参数", required = true)
                                       @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selecthealthfoodLink(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 化妆品类产品具体投诉环节查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "化妆品类产品具体投诉环节查询接口")
    @PostMapping("CosmeticsLink")
    @ResponseBody
    public RestResult cosmeticsLink(@ApiParam(value = "查询相关参数", required = true)
                                     @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectCosmeticsLink(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 化妆品类产品具体投诉环节查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "医疗器械类产品具体投诉环节查询接口")
    @PostMapping("MedicalLink")
    @ResponseBody
    public RestResult medicalLink(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectMedicalLink(requestMap);
        return RestResult.ok(map);
    }

    /**
     *
     * 投诉热点性质查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "ComplaintSituationController",
            value = "投诉热点类别查询接口")
    @PostMapping("complaintHotSpot")
    @ResponseBody
    public RestResult hotSpot(@ApiParam(value = "查询相关参数", required = true)
                                @RequestBody Map<String, Object> requestMap) {
        Map<String,Object> map = iComplaintSituationService.selectComplaintHotSpot(requestMap);
        return RestResult.ok(map);

    }
}
