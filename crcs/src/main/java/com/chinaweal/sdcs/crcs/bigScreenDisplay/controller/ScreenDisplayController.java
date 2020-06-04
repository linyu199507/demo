package com.chinaweal.sdcs.crcs.bigScreenDisplay.controller;


import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.*;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author liny
 * @since 2020-03-07
 */
@CrossOrigin
@Api(tags = "ScreenDisplayController", description = "查询接口类")
@Controller
@RequestMapping("/screenDisplay")
public class ScreenDisplayController {


    @Qualifier("screenDisplayServiceImpl")
    @Autowired
    private IScreenDisplayService iScreenDisplayService;
    @Qualifier("dataMapServiceImpl")
    @Autowired
    private IDataMapService iDataMapService;
    @Qualifier("dataMapOneServiceImpl")
    @Autowired
    private IDataMapOneService iDataMapOneService;
    @Qualifier("regionOrderVOServiceImpl")
    @Autowired
    private IRegionOrderVOService iRegionOrderVOService;
    @Qualifier("brandVOServiceImpl")
    @Autowired
    private IBrandVOService iBrandVOService;
    @Qualifier("appliancesVOServiceImpl")
    @Autowired
    private IAppliancesVOService iAppliancesVOService;
    @Qualifier("eachTypeVOServiceImpl")
    @Autowired
    private IEachTypeVOService iEachTypeVOService;



    /**
     * @return
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-被投诉主体 TOP10")
    @GetMapping("searchBusTOP10")
    @ResponseBody
    public List searchBusTOP10(@ApiParam(value = "查询相关参数", required = true)
                                      @RequestParam Map < String, Object > requestMap ) {
        List<ScreenDisplayVO> data = iScreenDisplayService.searchBusTOP10(requestMap);
        return data;
    }


//    /**
//     * @return
//     *勒流-陈村-乐从-北滘-伦教-大良-容桂-杏坛-龙江-均安
//     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-顺德区各地方地区工单总量分布图(内)--年")
    @GetMapping("searchDataMapOfYear")
    @ResponseBody
    public List searchDataMapOfYear(@ApiParam(value = "查询相关参数", required = true)
                                     @RequestParam Map<String, Object> requestMap) {
        requestMap.put("type","year");
        List<MapChangeVO> data = iDataMapService.searchDataMap(requestMap);
        return data;
    }

    //    /**
//     * @return
//     *勒流-陈村-乐从-北滘-伦教-大良-容桂-杏坛-龙江-均安
//     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-顺德区各地方地区工单总量分布图(内)--月")
    @GetMapping("searchDataMapOfMounth")
    @ResponseBody
    public List searchDataMapOfMounth(@ApiParam(value = "查询相关参数", required = true)
                              @RequestParam Map<String, Object> requestMap) {
        requestMap.put("type","mounth");
        List<MapChangeVO> data = iDataMapService.searchDataMap(requestMap);
        return data;
    }


    //    /**
//     * @return
//     *勒流-陈村-乐从-北滘-伦教-大良-容桂-杏坛-龙江-均安
//     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-顺德区各地方地区工单总量分布图(内)--日")
    @GetMapping("searchDataMapOfDay")
    @ResponseBody
    public List searchDataMapOfDay(@ApiParam(value = "查询相关参数", required = true)
                              @RequestParam Map<String, Object> requestMap) {
        requestMap.put("type","day");
        List<MapChangeVO> data = iDataMapService.searchDataMap(requestMap);
        return data;
    }


    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-顺德区各地方地区工单总量分布图(外-数字翻牌-1)")
    @GetMapping("searchAllDataMap")
    @ResponseBody
    public List searchAllDataMap(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestParam Map<String, Object> requestMap) {
        List<MapOneVO> data = iDataMapOneService.searchAllDataMap(requestMap);
        return data;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-顺德区各地方地区工单总量分布图(外-数字翻牌-2-同比增长)")
    @GetMapping("searchRatioDataMap")
    @ResponseBody
    public List searchRatioDataMap(@ApiParam(value = "查询相关参数", required = true)
                                       @RequestParam Map<String, Object> requestMap) {
        List<MapOneVO> data = iDataMapOneService.searchRatioDataMap(requestMap);
        return data;
    }


    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-各地区投诉业务量")
    @GetMapping("searchRegionOrder")
    @ResponseBody
    public List searchRegionOrder(@ApiParam(value = "查询相关参数", required = true)
                                         @RequestParam Map<String, Object> requestMap) {
        List<RegionOrderVO> list = iRegionOrderVOService.searchRegionOrder(requestMap);
        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-各地区投诉业务量(环比)")
    @GetMapping("searchRegionOrderOne")
    @ResponseBody
    public List searchRegionOrderOne(@ApiParam(value = "查询相关参数", required = true)
                                        @RequestParam Map<String, Object> requestMap) {
        List<MapOneVO> list = iDataMapOneService.searchSequential(requestMap);
        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-投诉总量与调解总量--投诉总量")
    @GetMapping("searchTotalComplaints")
    @ResponseBody
    public List searchTotalComplaints(@ApiParam(value = "查询相关参数", required = true)
                                            @RequestParam Map<String, Object> requestMap) {
        List<MapOneVO> list = iDataMapOneService.searchTotalComplaints(requestMap);
        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-投诉总量与调解总量--调解总量")
    @GetMapping("searchSettlementAmount")
    @ResponseBody
    public List searchSettlementAmount(@ApiParam(value = "查询相关参数", required = true)
                                            @RequestParam Map<String, Object> requestMap) {
        List<MapOneVO> list = iDataMapOneService.searchSettlementAmount(requestMap);
        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-投诉总量与调解总量--调解率")
    @GetMapping("searchMediation")
    @ResponseBody
    public List searchMediation(@ApiParam(value = "查询相关参数", required = true)
                                             @RequestParam Map<String, Object> requestMap) {
        List<DoubleVO> list = new ArrayList<>();
        List<MapOneVO> listAllTotal = iDataMapOneService.searchTotalComplaints(requestMap);
        List<MapOneVO> listEndTotal = iDataMapOneService.searchSettlementAmount(requestMap);
        MapOneVO dataAllTotal = listAllTotal.get(0);
        MapOneVO dataEndTotal = listEndTotal.get(0);
        MapOneVO data = new MapOneVO();
        data.setValue( dataEndTotal.getValue()*100/dataAllTotal.getValue() );
        data.setName("");

        DoubleVO dataOne = new DoubleVO();
        dataOne.setName("");
        dataOne.setValue(Double.valueOf(dataEndTotal.getValue())/Double.valueOf(dataAllTotal.getValue()));


        list.add(dataOne);
        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-被投诉品牌 --TOP10")
    @GetMapping("searchBrand")
    @ResponseBody
    public List searchBrand(@ApiParam(value = "查询相关参数", required = true)
                                      @RequestParam Map<String, Object> requestMap) {

        List<BrandVO> list = iBrandVOService.searchBrand(requestMap);

        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-消费维权涉嫌违法行为排名TOP10")
    @GetMapping("searchIllegalTOP10")
    @ResponseBody
    public List searchIllegalTOP10(@ApiParam(value = "查询相关参数", required = true)
                                  @RequestParam Map<String, Object> requestMap) {

        List<BrandVO> list = iBrandVOService.searchIllegalTOP10(requestMap);

        return list;
    }


    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-家用电器类产品具体投诉品类TOP10")
    @GetMapping("searchAppliancesTOP10")
    @ResponseBody
    public List searchAppliancesTOP10(@ApiParam(value = "查询相关参数", required = true)
                                            @RequestParam Map<String, Object> requestMap) {

        List<AppliancesVO> list = iAppliancesVOService.searchAppliancesTOP10(requestMap);

        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-消费投诉涉及问题变化情况")
    @GetMapping("searchProblemOfComplaint")
    @ResponseBody
    public List searchProblemOfComplaint(@ApiParam(value = "查询相关参数", required = true)
                                            @RequestParam Map<String, Object> requestMap) {

        List<BrandVO> list = iBrandVOService.searchProblemOfComplaint(requestMap);

        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-经济违法举报涉及问题变化情况")
    @GetMapping("searchProblemOfEconomy")
    @ResponseBody
    public List searchProblemOfEconomy(@ApiParam(value = "查询相关参数", required = true)
                                               @RequestParam Map<String, Object> requestMap) {

        List<BrandVO> list = iBrandVOService.searchProblemOfEconomy(requestMap);

        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-各月商品类和服务类前三的消费投诉变化趋势")
    @GetMapping("searchObjectOfComplaint")
    @ResponseBody
    public List searchObjectOfComplaint(@ApiParam(value = "查询相关参数", required = true)
                                        @RequestParam Map<String, Object> requestMap) {

        List<BrandVO> list = iBrandVOService.searchObjectOfComplaint(requestMap);

        return list;
    }


    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-近一个月的业务量(轮播饼图)")
    @GetMapping("searchTotalOfMounth")
    @ResponseBody
    public List searchTotalOfMounth(@ApiParam(value = "查询相关参数", required = true)
                                        @RequestParam Map<String, Object> requestMap) {

        List<EachTypeVO> list = iEachTypeVOService.searchTotalOfMounth(requestMap);

        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-近一个月的业务量(今天新增数)")
    @GetMapping("searchTotalOfToday")
    @ResponseBody
    public List searchTotalOfToday(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestParam Map<String, Object> requestMap) {

        List<EachTypeVO> list = iEachTypeVOService.searchTotalOfToday(requestMap);

        return list;
    }


    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-近一个月的业务量(同比增长)")
    @GetMapping("searchRatioOfToday")
    @ResponseBody
    public List searchRatioOfToday(@ApiParam(value = "查询相关参数", required = true)
                                   @RequestParam Map<String, Object> requestMap) {

        List<EachTypeVO> list = iEachTypeVOService.searchRatioOfToday(requestMap);

        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-近一个月的业务量(业务总量)")
    @GetMapping("searchRatioOfAll")
    @ResponseBody
    public List searchRatioOfAll(@ApiParam(value = "查询相关参数", required = true)
                                   @RequestParam Map<String, Object> requestMap) {

        List<EachTypeVO> list = iEachTypeVOService.searchRatioOfAll(requestMap);

        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-举报总量与立案总量--举报总量")
    @GetMapping("searchTotalEconomy")
    @ResponseBody
    public List searchTotalEconomy(@ApiParam(value = "查询相关参数", required = true)
                                      @RequestParam Map<String, Object> requestMap) {
        List<MapOneVO> list = iDataMapOneService.searchTotalEconomy(requestMap);
        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-举报总量与立案总量--立案总量")
    @GetMapping("searchSettlementAmountOfEconomy")
    @ResponseBody
    public List searchSettlementAmountOfEconomy(@ApiParam(value = "查询相关参数", required = true)
                                       @RequestParam Map<String, Object> requestMap) {
        List<MapOneVO> list = iDataMapOneService.searchSettlementAmountOfEconomy(requestMap);
        return list;
    }

    /**
     * @return
     *
     */
    @ApiOperation(
            tags = "ScreenDisplayController",
            value = "领导视角-举报总量与立案总量--立案率")
    @GetMapping("searchMediationOfEconomy")
    @ResponseBody
    public List searchMediationOfEconomy(@ApiParam(value = "查询相关参数", required = true)
                                @RequestParam Map<String, Object> requestMap) {
        List<DoubleVO> list = new ArrayList<>();
        List<MapOneVO> listAllTotal = iDataMapOneService.searchTotalEconomy(requestMap);
        List<MapOneVO> listEndTotal = iDataMapOneService.searchSettlementAmountOfEconomy(requestMap);
        MapOneVO dataAllTotal = listAllTotal.get(0);
        MapOneVO dataEndTotal = listEndTotal.get(0);
        MapOneVO data = new MapOneVO();
        data.setValue( dataEndTotal.getValue()*100/dataAllTotal.getValue() );
        data.setName("");

        DoubleVO dataOne = new DoubleVO();
        dataOne.setName("");
        dataOne.setValue(Double.valueOf(dataEndTotal.getValue())/Double.valueOf(dataAllTotal.getValue()));


        list.add(dataOne);
        return list;
    }


}
