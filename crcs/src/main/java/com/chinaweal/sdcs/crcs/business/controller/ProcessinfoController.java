package com.chinaweal.sdcs.crcs.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.Complaintform;
import com.chinaweal.sdcs.crcs.business.entity.Economyform;
import com.chinaweal.sdcs.crcs.business.entity.Opinion;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.mapper.ComplaintformMapper;
import com.chinaweal.sdcs.crcs.business.mapper.EconomyformMapper;
import com.chinaweal.sdcs.crcs.business.mapper.OpinionMapper;
import com.chinaweal.sdcs.crcs.business.mapper.TasklistMapper;
import com.chinaweal.sdcs.crcs.business.service.ICaseprocesformService;
import com.chinaweal.sdcs.crcs.business.service.IProcessinfoService;
import com.chinaweal.sdcs.crcs.statistical.crawler.service.IRediCrawlerService;
import com.chinaweal.sdcs.crcs.util.RequestUtil;
import com.chinaweal.youfool.framework.rest.RestResult;
import com.chinaweal.youfool.framework.rest.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
@Api(tags = "ProcessinfoController", description = "流程接口类")
@Controller
@RequestMapping("/processinfo")
public class ProcessinfoController {

    @Qualifier("processinfoServiceImpl")
    @Autowired
    private IProcessinfoService iProcessinfoService;
    @Autowired
    private OpinionMapper opinionMapper;
    @Autowired
    private TasklistMapper tasklistMapper;
    @Autowired
    private ComplaintformMapper complaintformMapper;
    @Autowired
    private EconomyformMapper economyformMapper;

    @Qualifier("caseprocesformServiceImpl")
    @Autowired
    private ICaseprocesformService iCaseprocesformService;

    @Qualifier("rediCrawlerServiceImpl")
    @Autowired
    private IRediCrawlerService iRediCrawlerService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${crcs.restLog.path}")
    private String restLogPath;


    public void restRecordLogger(String msg) {
        java.util.logging.Logger restLogger = java.util.logging.Logger.getLogger(ProcessinfoController.class.getName());
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(restLogPath + "/restLog-" + LocalDate.now() + ".log", true);
            fileHandler.setEncoding("UTF-8");
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return LocalDateTime.now() + " : " + record.getMessage() + "\n";
                }
            });
            restLogger.addHandler(fileHandler);
            restLogger.info(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileHandler != null)
                fileHandler.close();
        }
    }

    /**
     * 流程审批
     *
     * @param requestMap
     * @return
     */
    @ApiOperation(
            tags = "ProcessinfoController",
            value = "流程审批")
    @PostMapping("next")
    @ResponseBody
    public RestResult next(@ApiParam(value = "流程审批参数", required = true)
                           @RequestBody Map<String, Object> requestMap) {
        try {
            restRecordLogger(JSONObject.toJSONString(requestMap));
        } catch (Exception e) {
            logger.error("记录流程审批接口参数异常！", e);
        }
        Map<String, String> map = new HashMap();
        AICUser aicUser = null;
        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }
        try {
            map = iProcessinfoService.updateWorkFlow(aicUser, requestMap);
        } catch (Exception e) {
            logger.error("流程审批出错！", e);
            logger.error("流程审批出错请求的参数：{}", JSONObject.toJSONString(requestMap));
            return RestResult.error(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST, "流程更新出错。");
        }
        return RestResult.ok(map);

        /*if (requestMap.get("data") != null){
            if (formType.equals("complaintmodule")){
                complaintform = (Complaintform) RequestUtil.mapToEntity(Complaintform.class, data);
                caseguid = complaintform.getCaseguid();
            }else if (formType.equals("consultationform")){
                consultationform = (Consultationform) RequestUtil.mapToEntity(Consultationform.class, data);
                caseguid = consultationform.getCaseguid();
            }else if (formType.equals("comeconomicmodule")){
                economyform = (Economyform) RequestUtil.mapToEntity(Economyform.class, data);
                caseguid = economyform.getCaseguid();
            }else {
                Commomform commomform = (Commomform) RequestUtil.mapToEntity(Commomform.class, data);
                caseguid = commomform.getCaseguid();
            }

        }
        if (formType.equals("economyform")){
            crcsformType = "economy";
        }else if (formType.equals("complaintmodule")){
            crcsformType = "consumption";
        } else {
            crcsformType = "consultation";
        }
        if (nextNodeId.equals("interimReply")){
            map.put("caseguid",caseguid);
            map.put("firstOpinion",opinion);
            map.put("formType",crcsformType);
            map.put("operateUsername",operateUsername);
            url = url+"initialReply";
            System.out.println(map);
            HttpPostMethod.doPostMethod(url,map);
        }else if (nextNodeId.equals("accept")){
            map.put("caseguid",caseguid);
            map.put("mode",opinion);
            map.put("formType",crcsformType);
            map.put("operateUsername",operateUsername);
            System.out.println(map);
            url = url+"acceptance";
            HttpPostMethod.doPostMethod(url,map);
        }else if (nextNodeId.equals("moveToLowerOrg")){
            map.put("caseguid",caseguid);
            map.put("distributionOpinions",opinion);
            map.put("formType",crcsformType);
            map.put("memberUnit", (String) requestMap.get("orgid"));
            map.put("operateUsername",operateUsername);
            System.out.println(map);
            url = url+"assignSubordinate";
            HttpPostMethod.doPostMethod(url,map);
        }else if (nextNodeId.equals("end")){
            System.out.println(formType);
            if (formType.equals("comeconomicmodule")){
                QueryWrapper<Caseprocesform> wrapper = new QueryWrapper();
                wrapper.lambda().eq(Caseprocesform::getCaseguid,caseguid);
                Caseprocesform one = iCaseprocesformService.getOne(wrapper,false);
                map.put("addressArea",one.getBusinessaddress());
                map.put("caseguid",caseguid);
                map.put("investigationSituation","调查情况");
                map.put("operateUsername",operateUsername);
                map.put("processResult","需要前端数据");
                map.put("subjectCategories","需要前端数据");
                map.put("trueCondition","需要前端数据");
                map.put("verificationBusinessAddress","需要前端数据");
                map.put("verificationName","需要前端数据");
                url = url+"economyProcessFeedback";
                HttpPostMethod.doPostMethod(url,map);
            }else if (formType.equals("complaintmodule")){
                QueryWrapper<Caseprocesform> wrapper = new QueryWrapper();
                wrapper.lambda().eq(Caseprocesform::getCaseguid,caseguid);
                Caseprocesform one = iCaseprocesformService.getOne(wrapper,false);
                map.put("addressArea",one.getBusinessaddress());
                map.put("caseguid",caseguid);
                map.put("dateCompletion",one.getTransferreddate());
                map.put("documentNumber",one.getMediationnumber());
                map.put("doubleCompensate",one.getDoublemoney());
                map.put("mediation",one.getMediationis());
                map.put("modeRegulation",one.getMediationmethods());
                map.put("operateUsername",operateUsername);
                map.put("oralReparation",one.getMentalmoney());
                map.put("processResult",opinion);
                map.put("resultsMediation",one.getResultsofmediation());
                map.put("retrieveEconomicLoss",one.getFinancialloss());
                map.put("subjectCategories",one.getSubjecttype());
                map.put("typeTorts",one.getInfringementtype());
                map.put("verificationBusinessAddress",one.getSemakbusinessaddress());
                map.put("verificationName",one.getSemaknama());
                map.put("winAmount",one.getDisputemoney());
                url = url+"consumptionProcessFeedback";
                HttpPostMethod.doPostMethod(url,map);
            }else if (formType.equals("commommodule") || formType.equals("consultationform")){
                System.out.println(opinion);
                map.put("caseguid",caseguid);
                map.put("processResult","前端需要添加opinion数据");
                map.put("operateUsername",operateUsername);
                url = url+"consultationProcessFeedback";
                HttpPostMethod.doPostMethod(url,map);
            }
        }*/
    }

    @ApiOperation(
            tags = "ProcessinfoController",
            value = "分流审批")
    @PostMapping("dispatch")
    @ResponseBody
    public RestResult dispatch(@ApiParam(value = "分流审批参数", required = true)
                               @RequestBody Map<String, Object> requestMap) {
        try {
            restRecordLogger(JSONObject.toJSONString(requestMap));
        } catch (Exception e) {
            logger.error("记录流程审批接口参数异常！", e);
        }
        Map<String, String> map = new HashMap();
        AICUser aicUser = null;
        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }
        try {
            iProcessinfoService.dispatch(aicUser, requestMap);
        } catch (Exception e) {
            logger.error("分流审批出错！", e);
            logger.error("分流审批出错请求的参数：{}", JSONObject.toJSONString(requestMap));
            return RestResult.error(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST, "流程更新出错。");
        }
        return RestResult.ok(map);
    }

    @ApiOperation(
            tags = "ProcessinfoController",
            value = "在办反馈")
    @PostMapping("isProcessing")
    @ResponseBody
    public RestResult isProcessing(@ApiParam(value = "在办反馈", required = true)
                               @RequestBody Map<String, Object> requestMap) {
        AICUser aicUser = null;
        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }

        QueryWrapper<Tasklist> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Tasklist::getCaseguid,requestMap.get("caseguid"));
        Tasklist tasklist =  tasklistMapper.selectOne(queryWrapper);
            Opinion opinion = new Opinion();
            opinion.setId(UUID.randomUUID().toString());
            opinion.setHandler(aicUser.getName());
            opinion.setHandledept(aicUser.getOrgName());
            opinion.setCaseguid((String) requestMap.get("caseguid"));
        //在办反馈意见插入
        if("tabBus".equals(requestMap.get("type"))){
            opinion.setOperate("在办反馈");
            opinion.setNodename("在办反馈");
        }else if("preliminary".equals(requestMap.get("type"))){
            opinion.setOperate("初步反馈");
            opinion.setNodename("初步反馈");
            tasklist.setFeedback("1");
            if("02".equals(tasklist.getFormtype())){
                QueryWrapper<Complaintform> comquerywrapper=new QueryWrapper<>();
                comquerywrapper.lambda().eq(Complaintform::getCaseguid,tasklist.getCaseguid());
                Complaintform complaintform = complaintformMapper.selectOne(comquerywrapper);
                tasklist.setSdexpirationdate(complaintform.getFeedbacktime());
            }
            if("03".equals(tasklist.getFormtype())){
                QueryWrapper<Economyform> economyquerywrapper=new QueryWrapper<>();
                economyquerywrapper.lambda().eq(Economyform::getCaseguid,tasklist.getCaseguid());
                Economyform economyform = economyformMapper.selectOne(economyquerywrapper);
                tasklist.setSdexpirationdate(economyform.getFilingcasesdate());
            }
            tasklistMapper.updateById(tasklist);

        }
            opinion.setHandleopinion(requestMap.get("opinion") != null ? requestMap.get("opinion").toString() : null);
            opinion.setHandletime(new Date());
            opinion.setUpdatedate(new Date());
            opinion.setStatus("1");
            opinionMapper.insert(opinion);
        return RestResult.ok();
    }


}
