package com.chinaweal.sdcs.crcs.system.controller;
import com.chinaweal.sdcs.crcs.system.entity.OverallSituation;
import com.chinaweal.sdcs.crcs.system.service.IOverallSituationService;
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

import java.util.List;
import java.util.Map;


/**
 * @author Lin Yu
 * @since 2020-02-03
 */
@Api(tags = "OverallSituationController", description = "查询接口类")
@Controller
@RequestMapping("/overall")
public class OverallSituationController {


    @Qualifier("overallSituationServiceImpl")
    @Autowired
    private IOverallSituationService iOverallSituationService;

    /**
     *
     * 各地区业务量比较查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "OverallSituationController",
            value = "各业务类型工单量查询接口")
    @PostMapping("bustype")
    @ResponseBody
    public RestResult bustype(@ApiParam(value = "查询相关参数", required = true)
                                    @RequestBody Map<String, Object> requestMap) {

        List<OverallSituation> list = iOverallSituationService.selectBustype(requestMap);
        System.out.println(list);
        return RestResult.ok(list);

    }

    /**
     *
     * 全区维权办结情况及分布
     *
     * @return
     */
    @ApiOperation(
            tags = "OverallSituationController",
            value = "全区维权办结情况及分布")
    @PostMapping("allArea")
    @ResponseBody
    public RestResult allArea(@ApiParam(value = "查询相关参数", required = true)
                              @RequestBody Map<String, Object> requestMap) {

        Map<String,Object> map = iOverallSituationService.selectAllArea(requestMap);
        return RestResult.ok(map);

    }




}
