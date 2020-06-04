package com.chinaweal.sdcs.crcs.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.common.controller.BaseController;
import com.chinaweal.sdcs.crcs.common.entity.dto.login.LoginMessage;
import com.chinaweal.sdcs.crcs.common.entity.dto.page.PageRequestDto;
import com.chinaweal.sdcs.crcs.system.entity.RightsDetails;
import com.chinaweal.sdcs.crcs.system.entity.RightsProcessingLog;
import com.chinaweal.sdcs.crcs.system.entity.RightsTransformRequest;
import com.chinaweal.sdcs.crcs.system.service.IRightsDetailsService;
import com.chinaweal.sdcs.crcs.system.service.RightsProcessingLogService;
import com.chinaweal.sdcs.crcs.system.service.RightsTransformRequestService;
import com.chinaweal.sdcs.crcs.util.RequestUtil;
import com.chinaweal.sdcs.crcs.util.StringUtils;
import com.chinaweal.sdcs.crcs.util.UuidUtil;
import com.chinaweal.youfool.framework.rest.RestResult;
import com.chinaweal.youfool.framework.rest.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author chinaweal
 * @since 2020-05-28
 */
@Api(tags = "RightsdetailsController", description = "移动端接口类")
@RestController
@RequestMapping("/rightsdetails")
public class RightsdetailsController extends BaseController {


    @Qualifier("rightsDetailsServiceImpl")
    @Autowired
    private IRightsDetailsService iRightsDetailsService;

    @Autowired
    private RightsProcessingLogService rightsProcessingLogService;

    @Autowired
    private RightsTransformRequestService rightsTransformRequestService;

    @Autowired
    private UuidUtil uuidUtil;

    /**
     *
     * 移动端维权服务信息辖区主体全称查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "RightsdetailsController",
            value = "移动端维权详情信息保存接口")
    @PostMapping("save")
    public RestResult save(@ApiParam(value = "查询相关参数", required = true)
                              @RequestBody Map<String, Object> requestMap) {

        AICUser aicUser = null;
        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }
        System.out.println(aicUser);
        RightsDetails rightsDetails = (RightsDetails) RequestUtil.mapToEntity(RightsDetails.class,requestMap.get("data"));
        UUID id = UUID.randomUUID();
        UUID caseguid = UUID.randomUUID();
        rightsDetails.setId(id.toString());
        rightsDetails.setCaseguid(caseguid.toString());
        rightsDetails.setSerialnum(aicUser.getRegionID().substring(0, 6) + uuidUtil.getCurAcceptNo());
        rightsDetails.setUserid(aicUser.getPrimaryKey());
        rightsDetails.setRequestmark("0");
        rightsDetails.setFeedbackmark("0");
        iRightsDetailsService.save(rightsDetails);
        return RestResult.ok();

    }

    /**
     * 维权信息分页
     * @param pageRequestDto 分页参数体
     * @author lroyia
     * @since 2020年6月1日 14:44:21
     * @return 分页结果
     */
    @ApiOperation(
            tags = "RightsdetailsController",
            value = "移动端维权详情信息分页接口")
    @PostMapping("list")
    public RestResult listData(HttpServletRequest request, @RequestBody PageRequestDto pageRequestDto){
        if(pageRequestDto == null) return RestResult.error(ResultCode.PARAM_IS_BLANK);

        LoginMessage msg = getLoginMessage(request);

        return RestResult.ok(iRightsDetailsService.defaultPaging(pageRequestDto, wrapper -> {
            // 校验并获取自定义参数
            PageRequestDto.checkCustomParamMapEmpty(pageRequestDto);
            Map<String, Object> customParamMap = pageRequestDto.getCustomParamMap();
            // 取登记日期范围
            String registerDateBeginStr = StringUtils.tranObject(customParamMap.get("registerDateBegin"));
            String registerDateEndStr = StringUtils.tranObject(customParamMap.get("registerDateEnd"));
            if(StringUtils.isNotBlank(registerDateBeginStr)){
                wrapper.lambda().ge(RightsDetails::getRegistrationdate, registerDateBeginStr + " 00:00:00");
            }
            if(StringUtils.isNotBlank(registerDateEndStr)){
                wrapper.lambda().le(RightsDetails::getRegistrationdate, registerDateEndStr+ " 23:59:59");
            }
            // 是否需要限制当前用户
            String isCurUser = StringUtils.tranObject(customParamMap.get("isCurUser"));
            if(StringUtils.isNotBlank(isCurUser)){
                // 被要求限制用户筛选，但用户未登录，则传入空字串作为筛选条件，反则直接取用户id
                if(msg == null){
                    wrapper.lambda().eq(RightsDetails::getUserid, StringUtils.EMPTY);
                }else{
                    wrapper.lambda().eq(RightsDetails::getUserid, msg.getUserId());
                }
            }
        }, null));
    }

    /**
     * 维权信息详情
     * @param id 数据id
     * @author lroyia
     * @since 2020年6月1日 14:47:32
     * @return 详情信息
     */
    @ApiOperation(tags = "RightsdetailsController", value = "移动端维权详情信息获取接口")
    @GetMapping("detail/{id}")
    public RestResult getDetail(@ApiParam(value = "数据id", required = true) @PathVariable String id){
        return RestResult.ok(iRightsDetailsService.getById(id));
    }

    /**
     * 处理结果提交
     * @param id 维权信息id
     * @param processing 处理结果
     * @return 提交结果
     */
    @Transactional
    @ApiOperation(tags = "RightsdetailsController", value = "移动端维权详情信息处理结果提交接口")
    @PostMapping("processing")
    public RestResult processingResultSubmit(HttpServletRequest request, @ApiParam(value = "维权数据id", required = true) String id, @ApiParam(value = "处理结果", required = true) String processing){
        // 参数校验
        if(StringUtils.isBlank(id) || StringUtils.isBlank(processing)) return RestResult.error(ResultCode.PARAM_IS_BLANK);

        // 数据状态校验
        RightsDetails record = iRightsDetailsService.getById(id);
        if(record == null) return RestResult.error(ResultCode.PARAM_IS_INVALID);
        if(StringUtils.equals(record.getFeedbackmark(), "1") || StringUtils.equals(record.getRequestmark(), "1")) return RestResult.error(ResultCode.DATA_ALREADY_EXISTED);

        // 获取当前登录信息
        LoginMessage loginMessage = getLoginMessage(request);

        Date now = new Date();

        // 记录处理结果
        RightsDetails updateRecord = new RightsDetails();
        updateRecord.setId(id);
        updateRecord.setProcessing(processing);
        updateRecord.setProcessingdate(now);
        updateRecord.setFeedbackmark("1");
        iRightsDetailsService.updateById(updateRecord);

        // 记录处理操作信息
        RightsProcessingLog log = new RightsProcessingLog();
        log.setProcessing(processing);
        log.setCreator(loginMessage.getUserId());
        log.setCreateTime(now);
        log.setRightsId(id);
        rightsProcessingLogService.save(log);
        return RestResult.ok(now);
    }

    /**
     * 转换申请
     * @param request 请求
     * @param id 详情id
     * @param reason 请求原因
     * @param branchOffice 分局
     * @return 记录申请结果
     */
    @Transactional
    @PostMapping("transform")
    @ApiOperation(tags = "RightsdetailsController", value = "移动端维权详情信息工单转换")
    public RestResult transformRightsMsgRequest(HttpServletRequest request,
                                                @ApiParam(value = "需要转换的数据id", required = true) String id,
                                                @ApiParam(value = "申请原因", required = true) String reason,
                                                @ApiParam(value = "分局", required = true) String branchOffice){
        // 参数空校验
        if(StringUtils.isBlank(id) || StringUtils.isBlank(reason) || StringUtils.isBlank(branchOffice)) return RestResult.error(ResultCode.PARAM_IS_BLANK);

        // 详情校验
        RightsDetails record = iRightsDetailsService.getById(id);
        if(record == null) return RestResult.error(ResultCode.PARAM_IS_INVALID);
        if(StringUtils.equals(record.getFeedbackmark(), "1") || StringUtils.equals(record.getRequestmark(), "1")) return RestResult.error(ResultCode.BUSINESS_LOGIC_ERROR);

        // 获取登录信息
        LoginMessage loginMessage = getLoginMessage(request);

        Date now = new Date();

        // 记录处理结果
        RightsDetails updateRecord = new RightsDetails();
        updateRecord.setId(id);
        updateRecord.setRequestmark("1");
        updateRecord.setRequestcontent(reason);
        updateRecord.setRequestdate(now);
        iRightsDetailsService.updateById(updateRecord);

        // 记录申请信息
        RightsTransformRequest rq = new RightsTransformRequest();
        rq.setBranchOffice(branchOffice);
        rq.setCreator(loginMessage.getUserId());
        rq.setUpdater(loginMessage.getUserId());
        rq.setCreateTime(now);
        rq.setUpdatetime(now);
        rq.setReason(reason);
        rq.setRightsId(id);
        rightsTransformRequestService.save(rq);
        return RestResult.ok(now);
    }

    /**
     * 获取最心的维权信息转换工单申请记录
     * @param rightId 维权数据id
     * @return 最新的申请记录
     */
    @GetMapping("last_transform_request/{rightId}")
    @ApiOperation(tags = "RightsdetailsController", value = "移动端获取维权详情信息最新的工单转换申请")
    public RestResult getLastTransformRequest(@ApiParam(value = "维权信息id", required = true) @PathVariable String rightId){
        return RestResult.ok(rightsTransformRequestService.getLastOne(rightId));
    }
}
