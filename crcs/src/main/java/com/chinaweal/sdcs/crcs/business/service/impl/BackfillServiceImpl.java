package com.chinaweal.sdcs.crcs.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.mapper.ZhistoryMapper;
import com.chinaweal.sdcs.crcs.system.entity.BackfillCallLog;
import com.chinaweal.sdcs.crcs.system.service.IBackfillCallLogService;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeContrastService;
import com.chinaweal.sdcs.crcs.util.BaseDataUtil;
import com.chinaweal.sdcs.crcs.util.UuidUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 回填调用实现
 */
@Service
public class BackfillServiceImpl {

    @Value("${backfill.url12345}")
    public String backfill12345Url;
    @Value("${backfill.url12315}")
    public String backfill12315Url;
    @Resource
    private IBasecodeContrastService iBasecodeContrastService;
    @Resource
    private IBackfillCallLogService iBackfillCallLogService;
    @Autowired
    private ZhistoryMapper zhistorymapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 初步答复
     */
    String INITIAL_REPLY = "initialReply";
    /**
     * 处理反馈
     */
    String PROCESS_FEEDBACK = "processFeedback";
    /**
     * 受理
     */
    String ACCEPTANCE = "acceptance";
    /**
     * 不受理
     */
    String NOT_ACCEPTANCE = "notAcceptance";
    /**
     * 分派下级
     */
    String ASSIGN_SUBORDINATE = "assignSubordinate";
    /**
     * 退回
     */
    String SEND_BACK = "sendBack";
    /**
     * 申请延期
     */
    String APPLY_EXTENSION = "applyExtension";
    /**
     * 立案处理
     */
    String CASE_FILING = "caseFiling";

    /**
     * 来函来访-受理登记 --- 12315工单回填12345受理登记
     */
    String ACCEPTANCE_REGISTRATION = "acceptanceRegistration";


    /**
     * 分流
     */
    String SHUNT = "shunt";
    /**
     * 进度反馈
     */
    String PROGRESS_FEEDBACK = "progressFeedback";
    /**
     * 办结反馈
     */
    String FINISHING_FEEDBACK = "finishingFeedback";
    /**
     * 办结延期申请
     */
    String POSTPONED_COMPLETION = "postponedCompletion";
    /**
     * 分流撤销自办
     */
    String REVOKE_SELF = "revokeSelf";
    /**
     * 初查/排查反馈
     */
    String PRELIMINARY_FEEDBACK = "preliminaryFeedback";


    /**
     * 佛山12345 分派下级
     *
     * @param operateUsername      操作账号
     * @param caseguid             工单id
     * @param formType             表单类型
     * @param memberUnit           成员单位
     * @param distributionOpinions 分派意见
     */
    public boolean executeAssignSubordinate(String operateUsername, String regionId, String caseguid, String formType,
                                            String memberUnit, String distributionOpinions) {
        String switchFormType = byTextSwitch12345FormType(formType);
        //替换名称成员单位名称
        memberUnit = memberUnit.replace("佛山市", "佛山");
        String url = backfill12345Url + "/assignSubordinate";
        Map<String, Object> body = new HashMap<>();
        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", getFsUserNameByRegionId(regionId));
        body.put("caseguid", caseguid);
        body.put("formType", switchFormType);
        body.put("memberUnit", memberUnit);
        body.put("distributionOpinions", distributionOpinions);
        body.put("step", ASSIGN_SUBORDINATE);
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("佛山12345回填，分派下级请求发生异常", t);
            return false;
        }
    }

    /**
     * 佛山12345 初步答复
     *
     * @param operateUsername 操作账号
     * @param caseguid        工单id
     * @param formType        表单类型
     * @param firstOpinion    初步意见
     * @param annexPaths      文件附件
     * @return
     */
    public boolean executeInitialReply(String operateUsername, String regionId, String caseguid, String formType,
                                       String firstOpinion, String[] annexPaths) {
        String switchFormType = byTextSwitch12345FormType(formType);
        String url = backfill12345Url + "/initialReply";
        Map<String, Object> body = new HashMap<>();
        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", getFsUserNameByRegionId(regionId));
        body.put("caseguid", caseguid);
        body.put("formType", switchFormType);
        body.put("firstOpinion", firstOpinion);
        body.put("annexPaths", annexPaths);
        body.put("step", INITIAL_REPLY);
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("佛山12345回填，初步答复请求发生异常", t);
            return false;
        }
    }

    /**
     * 佛山12345 立案处理
     *
     * @param operateUsername 操作账号
     * @param caseguid        工单id
     * @param caseprocesform  案件信息
     * @return
     */
    public boolean executeCaseFiling(String operateUsername, String regionId, String caseguid, Caseprocesform caseprocesform) {
        String url = backfill12345Url + "/caseFiling";
        Map<String, Object> body = new HashMap<>();
        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", getFsUserNameByRegionId(regionId));
        body.put("caseguid", caseguid);
        body.put("limit", caseprocesform.getLimitdate());
        body.put("investigateOrNotReason", caseprocesform.getVerifiedopinion());
        body.put("step", CASE_FILING);
        //todo 中文不符合，先截取
        if (caseprocesform.getSubjecttype() != null) {
            String type = caseprocesform.getSubjecttype();
            type = type.substring(type.indexOf("-") + 1);
            body.put("subjectCategories", type);
        }
        body.put("subjectInvolved", caseprocesform.getSubjectname());
        body.put("trueCondition", caseprocesform.getIsverified());
        if ("属实".equals(caseprocesform.getIsverified())) {
            body.put("ifFiling", caseprocesform.getIsregister());
        }
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("佛山12345回填，立案处理请求发生异常", t);
            return false;
        }
    }

    /**
     * 佛山12345 受理
     *
     * @param operateUsername 操作账号
     * @param caseguid        工单id
     * @param formType        表单类型
     * @param mode            受理方式：调解、查处、调查
     * @return
     */
    public boolean executeAcceptance(String operateUsername, String regionId, String caseguid, String formType, String mode) {
        String url = backfill12345Url + "/acceptance";
        String switchFormType = byTextSwitch12345FormType(formType);
        Map<String, Object> body = new HashMap<>();
        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", getFsUserNameByRegionId(regionId));
        body.put("formType", switchFormType);
        body.put("caseguid", caseguid);
        body.put("mode", mode);
        body.put("step", ACCEPTANCE);
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("佛山12345回填，受理请求发生异常", t);
            return false;
        }
    }

    /**
     * 佛山12345 不受理
     *
     * @param operateUsername 操作账号
     * @param caseguid        工单id
     * @param formType        表单类型
     * @param reason          不受理原因
     * @param content         不受理内容
     * @return
     */
    public boolean executeNotAcceptance(String operateUsername, String regionId, String caseguid, String formType, String reason, String content) {
        String url = backfill12345Url + "/notAcceptance";
        String switchFormType = byTextSwitch12345FormType(formType);
        Map<String, Object> body = new HashMap<>();
        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", getFsUserNameByRegionId(regionId));
        body.put("formType", switchFormType);
        body.put("caseguid", caseguid);
        body.put("reason", reason);
        body.put("content", content);
        body.put("step", NOT_ACCEPTANCE);
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("佛山12345回填，不受理请求发生异常", t);
            return false;
        }
    }

    /**
     * 佛山12345 退回
     *
     * @param operateUsername 操作账号
     * @param caseguid        工单id
     * @param formType        表单类型
     * @param reason          退回原因
     * @param description     退回描述
     * @param annexPaths      附件绝对路径列表
     * @return
     */
    public boolean executeSendBack(String operateUsername, String regionId, String caseguid, String formType, String reason,
                                   String description, List<String> annexPaths) {
        String url = backfill12345Url + "/sendBack";
        String switchFormType = byTextSwitch12345FormType(formType);
        Map<String, Object> body = new HashMap<>();
        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", getFsUserNameByRegionId(regionId));
        body.put("formType", switchFormType);
        body.put("caseguid", caseguid);
        body.put("reason", reason);
        body.put("description", description);
        body.put("annexPaths", annexPaths);
        body.put("step", SEND_BACK);

        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("佛山12345回填，退回请求发生异常", t);
            return false;
        }
    }

    /**
     * 佛山12345 申请延期
     *
     * @param operateUsername 操作账号
     * @param caseguid        工单id
     * @param formType        表单类型
     * @param limit           延时时限，该属性对消费投诉类型无效
     * @param reason          延时原因
     * @param unit            单位
     * @return
     */
    public boolean executeApplyExtension(String operateUsername, String regionId, String caseguid, String formType, String reason,
                                         Integer limit, String unit) {
        String url = backfill12345Url + "/applyExtension";
        String switchFormType = byTextSwitch12345FormType(formType);
        Map<String, Object> body = new HashMap<>();
        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", getFsUserNameByRegionId(regionId));
        body.put("formType", switchFormType);
        body.put("caseguid", caseguid);
        body.put("reason", reason);
        body.put("limit", limit);
        body.put("unit", unit);
        body.put("step", APPLY_EXTENSION);
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("佛山12345回填，申请延期请求发生异常", t);
            return false;
        }
    }

    /**
     * 佛山12345 咨询类型,处理反馈
     *
     * @param operateUsername 操作账号
     * @param caseguid        工单id
     * @param processResult   处理结果
     * @param annexPaths      附件绝对路径列表
     * @return
     */
    public boolean executeConsultationProcessFeedback(String operateUsername, String regionId, String caseguid, String processResult, List<String> annexPaths) {
        String url = backfill12345Url + "/consultationProcessFeedback";
        Map<String, Object> body = new HashMap<>();
        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", getFsUserNameByRegionId(regionId));
        body.put("caseguid", caseguid);
        body.put("processResult", processResult);
        body.put("annexPaths", annexPaths);
        body.put("step", PROCESS_FEEDBACK);
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("佛山12345回填，咨询类型，处理反馈请求发生异常", t);
            return false;
        }
    }

    /**
     * 佛山12345 经济违法,处理反馈
     *
     * @param operateUsername 操作账号
     * @param caseguid        工单id
     * @param addressArea     核查经营地址区域
     * @param caseprocesform  案件信息
     * @param annexPaths      附件绝对路径列表
     * @return
     */
    public boolean executeEconomyProcessFeedback(String operateUsername, String regionId, String caseguid, String addressArea,
                                                 Caseprocesform caseprocesform, List<String> annexPaths) {
        String url = backfill12345Url + "/economyProcessFeedback";
        Map<String, Object> body = new HashMap<>();
        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", getFsUserNameByRegionId(regionId));
        body.put("caseguid", caseguid);
        body.put("addressArea", addressArea);
        body.put("processResult", caseprocesform.getHandleopinion());
        body.put("annexPaths", annexPaths);
        body.put("step", PROCESS_FEEDBACK);
        //todo 中文不符合，先截取
        if (caseprocesform.getSubjecttype() != null) {
            String type = caseprocesform.getSubjecttype();
            type = type.substring(type.indexOf("-") + 1);
            body.put("subjectCategories", type);
        }
        body.put("verificationBusinessAddress", caseprocesform.getSemakbusinessaddress());
        body.put("verificationName", caseprocesform.getSemaknama());
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("佛山12345回填，经济违法，处理反馈请求发生异常", t);
            return false;
        }
    }

    /**
     * 佛山12345 消费投诉,处理反馈
     *
     * @param operateUsername 操作账号
     * @param caseguid        工单id
     * @param addressArea     核查经营地址区域
     * @param caseprocesform  案件信息
     * @param annexPaths      附件绝对路径列表
     * @return
     */
    public boolean executeConsumptionProcessFeedback(String operateUsername, String regionId, String caseguid, String addressArea,
                                                     Caseprocesform caseprocesform, List<String> annexPaths) {
        String url = backfill12345Url + "/consumptionProcessFeedback";
        Map<String, Object> body = new HashMap<>();
        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", getFsUserNameByRegionId(regionId));
        body.put("caseguid", caseguid);
        body.put("addressArea", addressArea);
        body.put("annexPaths", annexPaths);
        body.put("completionDatetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(caseprocesform.getTransferreddate()));
        body.put("documentNumber", caseprocesform.getMediationnumber());
        body.put("doubleCompensate", caseprocesform.getDoublemoney());
        body.put("failurePerform", caseprocesform.getNoobligation());
        body.put("fraudSign", caseprocesform.getInfringingmarks());
        body.put("mediation", caseprocesform.getMediationis());
        body.put("modeRegulation", caseprocesform.getMediationmethods());
        body.put("oralReparation", caseprocesform.getMentalmoney());
        body.put("processResult", caseprocesform.getHandleopinion());
        body.put("resultsMediation", caseprocesform.getResultsofmediation());
        body.put("retrieveEconomicLoss", caseprocesform.getFinancialloss());
        //todo 中文不符合，先截取
        if (caseprocesform.getSubjecttype() != null) {
            String type = caseprocesform.getSubjecttype();
            type = type.substring(type.indexOf("-") + 1);
            body.put("subjectCategories", type);
        }
        body.put("typeTorts", caseprocesform.getInfringementtype());
        body.put("verificationBusinessAddress", caseprocesform.getSemakbusinessaddress());
        body.put("verificationName", caseprocesform.getSemaknama());
        body.put("winAmount", caseprocesform.getDisputemoney());
        body.put("step", PROCESS_FEEDBACK);
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("佛山12345回填，消费投诉，处理反馈请求发生异常", t);
            return false;
        }
    }

    /**
     * 根据机构获取佛山12345平台回填账号
     *
     * @param regionId
     * @return
     */
    String getFsUserNameByRegionId(String regionId) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("440681", "SD022");//顺德区管理员
        userMap.put("440681007", "xiaoxt");//勒流
        userMap.put("440681008", "dengby");//龙江
        userMap.put("440681009", "chenzy");//杏坛
        userMap.put("440681001", "tangph");//大良
        userMap.put("440681002", "lusl");//容桂
        userMap.put("440681003", "maiky");//伦教
        userMap.put("440681004", "huangzg");//北滘
        userMap.put("440681005", "panys");//陈村
        userMap.put("440681006", "weishl");//乐从
        userMap.put("440681010", "liangwm");//均安
        return userMap.get(regionId);
    }

    /**
     * 全国12315 分流回填
     */
    public boolean executeShunt(String operateUsername, Map<String, Object> requestMap, Tasklist tasklist) {
        String switchFormType = byTextSwitch12315FormType(tasklist.getFormtype());
        String url = backfill12315Url + "/shuntBackfill";
        Map<String, Object> body = new HashMap<>();
        body.put("approver", "be933549534742b9b0e43dffb1db2bc2");
        body.put("operateUsername", operateUsername);
        body.put("douser", requestMap.get("douser"));
        body.put("formType", switchFormType);
//        body.put("handleTime", requestMap.get("deadlineForProcessing"));
        body.put("initialTime", requestMap.get("feedbackPeriod"));
        body.put("jaSpBmLx", requestMap.get("deptType"));
        body.put("jsUnitCode", requestMap.get("jsUnitCode"));
        body.put("opmethod", requestMap.get("chooseOperations"));
        body.put("outmemo", requestMap.get("subDesc"));
        body.put("regId", tasklist.getTserialnum());
        body.put("sendpostscript", requestMap.get("desc"));
        body.put("shuntFirstLevel", requestMap.get("diversionSituation"));
        body.put("shuntSecondLevel", requestMap.get("subDiversion"));
        body.put("recdept", requestMap.get("acceptDept"));
        body.put("step", SHUNT);
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("全国12315分流回填，请求发生异常", t);
            return false;
        }
    }

    /**
     * 全国12315 办结延期申请
     */
    public boolean executeHandleExtensionApplication(String reason, String formType, String regId) {
        String switchFormType = byTextSwitch12315FormType(formType);
        String url = backfill12315Url + "/postponedCompletionBackfill";
        Map<String, Object> body = new HashMap<>();
        body.put("approver", "be933549534742b9b0e43dffb1db2bc2");
        body.put("formType", switchFormType);
        body.put("operateUsername", "gdfssd001");
        body.put("reason", reason);
        body.put("regId", regId);
        body.put("step", POSTPONED_COMPLETION);
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("全国12315办结延期申请回填，请求发生异常", t);
            return false;
        }
    }

    /**
     * 全国12315 初查/排查反馈
     */
    public boolean executePreliminaryFeedback(String operateUsername, Tasklist tasklist, String remark, String situation) {
        String switchFormType = byTextSwitch12315FormType(tasklist.getFormtype());
        //1：受理、0：不受理
        String acceptance = BaseDataUtil.WORKFLOW_NODE_ACCEPT.equals(tasklist.getNextnodeid()) ? "1" : "0";
        String url = backfill12315Url + "/preliminaryFeedbackBackfill";
        Map<String, Object> body = new HashMap<>();
        body.put("acceptance", acceptance);
        body.put("formType", switchFormType);
        body.put("operateUsername", operateUsername);
        if ("report".equals(switchFormType)) {
            //举报排查的行政强制措施
            body.put("measures", null);
        }

        body.put("remark", remark);
        body.put("situation", situation);//不受理情形
        body.put("regId", tasklist.getTserialnum());
        body.put("step", PRELIMINARY_FEEDBACK);
        body.put("blqx", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tasklist.getSdexpirationdate()));

        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("全国12315初查/排查反馈回填，请求发生异常", t);
            return false;
        }
    }

    /**
     * 全国12315 举报办结反馈
     */
    public boolean executeReportFinishingFeedback(String operateUsername, Tasklist tasklist, Economyform economyForm, Opinion opinion, Caseprocesform caseprocesform) {
        String url = backfill12315Url + "/reportFinishingFeedbackBackfill";
        Map<String, Object> body = new HashMap<>();
//        if ("不属于".equals(caseprocesform.getIsverified())) {
//            //终止受理反馈
//            body.put("terminationAcceptance", "20");
//            body.put("acceptanceCase", "11");
//            body.put("acceptanceReason", opinion.getHandleopinion());
//        } else {
//            //属实
//            body.put("terminationAcceptance", "10");
//        }
        body.put("terminationAcceptance", "10");
        body.put("reportingType", "是".equals(caseprocesform.getIsregister()) ? "11" : "12");
        body.put("approver", "be933549534742b9b0e43dffb1db2bc2");
        body.put("beReportedAddr", economyForm.getRqstaddress());
        body.put("beReportedName", economyForm.getSubjectname());
        body.put("brandName", economyForm.getBrand());//todo
        body.put("caseValue", economyForm.getAnestimate());
        body.put("confiscateAmount", caseprocesform.getConfiscateAmount());
        body.put("contacts", economyForm.getReportedcontact());
        body.put("creditCode", economyForm.getRegisterno());
        body.put("entType", iBasecodeContrastService.getCountryCodeByFsCodeAndType(economyForm.getProvidetypecodeid(), IBasecodeContrastService.ENTERPRISE));
        body.put("goodsName", economyForm.getObjname());
        body.put("handleDepart", caseprocesform.getHandleDepart());
        body.put("industryClassif", iBasecodeContrastService.getCountryCodeByFsCodeAndType(economyForm.getIndustrynamecodeid(), IBasecodeContrastService.INDUSTRY));
        //是否部门领导审核,否
        body.put("leadershipAudit", "0");
        body.put("objectCategory", iBasecodeContrastService.getCountryCodeByFsCodeAndType(economyForm.getObjectcategorycodeid(), IBasecodeContrastService.Obj));
        body.put("operateUsername", operateUsername);
        body.put("operator", caseprocesform.getOperator());
        body.put("operatorOpinion", caseprocesform.getOperatorOpinion());
        body.put("operatorPhone", caseprocesform.getOperatorPhone());
        body.put("penaltyAmount", caseprocesform.getPenaltyAmount());
        body.put("phone", economyForm.getReportedcontactnumber());
        body.put("postscript", caseprocesform.getPostscript());
        body.put("questionCategory", iBasecodeContrastService.getCountryCodeByFsCodeAndType(economyForm.getReportprovidelbcodeid(), IBasecodeContrastService.QUESTION));
        body.put("regId", tasklist.getTserialnum());
        body.put("result", opinion.getHandleopinion());
        body.put("saleAddr", caseprocesform.getSaleAddr());
        body.put("saleDistrict", caseprocesform.getSaleDistrict());
        body.put("unlicensed", caseprocesform.getUnlicensed());
        body.put("step", FINISHING_FEEDBACK);

        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("全国12315举报办结反馈回填，请求发生异常", t);
            return false;
        }
    }

    /**
     * 全国12315 投诉办结反馈
     */
    public boolean executeComplainFinishingFeedback(String operateUsername, Tasklist tasklist, Complaintform complaintform, Opinion opinion, Caseprocesform caseprocesform) {
        String url = backfill12315Url + "/complainFinishingFeedbackBackfill";
        Map<String, Object> body = new HashMap<>();
//        if ("不属实".equals(caseprocesform.getIsverified())) {
//            //终止受理反馈
//            body.put("terminationAcceptance", "20");
//            body.put("acceptanceCase", "11");
//            body.put("acceptanceReason", opinion.getHandleopinion());
//        } else {
//        body.put("terminationAcceptance", "10");
//        }
        //受理
        body.put("terminationAcceptance", "10");
        //是否诉转案
        body.put("caseTransfer", caseprocesform.getApplealtocase());
        //受理类型
        body.put("reportingType", "0".equals(caseprocesform.getApplealtocase()) ? "11" : "12");
        body.put("approver", "be933549534742b9b0e43dffb1db2bc2");
        body.put("beReportedAddr", complaintform.getRqstaddress());
        body.put("beReportedName", complaintform.getSubjectname());
        body.put("brandName", complaintform.getBrand());
        body.put("compensateMode", caseprocesform.getCompensateMode());
        body.put("evidence", caseprocesform.getEvidence());
        body.put("failurePerform", caseprocesform.getNoobligationcodeid() != null ? caseprocesform.getNoobligationcodeid().replace("K", "") : "");
        body.put("contacts", complaintform.getReportedcontact());
        body.put("creditCode", complaintform.getRegisterno());
        body.put("entType", iBasecodeContrastService.getCountryCodeByFsCodeAndType(complaintform.getProvidetypecodeid(), IBasecodeContrastService.ENTERPRISE));
        body.put("goodsName", complaintform.getObjname());
        body.put("handleDepart", caseprocesform.getHandleDepart());
        body.put("industryClassif", iBasecodeContrastService.getCountryCodeByFsCodeAndType(complaintform.getBusinesstypecodeid(), IBasecodeContrastService.INDUSTRY));
        //是否部门领导审核,否
        body.put("leadershipAudit", "0");
        body.put("mediateResult", caseprocesform.getResultsofmediation());
        body.put("mediationDocument", caseprocesform.getMediationnumber());
        body.put("mediationEndDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(caseprocesform.getTransferreddate()));
        body.put("objectCategory", iBasecodeContrastService.getCountryCodeByFsCodeAndType(complaintform.getObjclassifycodeid(), IBasecodeContrastService.Obj));
        body.put("operateUsername", operateUsername);
        body.put("operator", caseprocesform.getOperator());
        body.put("operatorOpinion", caseprocesform.getOperatorOpinion());
        body.put("operatorPhone", caseprocesform.getOperatorPhone());
        body.put("phone", complaintform.getLinknumber());
        body.put("postscript", caseprocesform.getPostscript());
        body.put("questionCategory", iBasecodeContrastService.getCountryCodeByFsCodeAndType(complaintform.getComplaintstypecodeid(), IBasecodeContrastService.QUESTION));
        body.put("redoubleAmount", caseprocesform.getDoublemoney());
        body.put("regId", tasklist.getTserialnum());
        body.put("result", opinion.getHandleopinion());
        body.put("retrieveAmount", caseprocesform.getFinancialloss());
        body.put("spiritualAmount", caseprocesform.getMentalmoney());
        body.put("saleAddr", caseprocesform.getSaleAddr());
        body.put("saleDistrict", caseprocesform.getSaleDistrict());
        body.put("tortType", iBasecodeContrastService.getCountryCodeByFsCodeAndType(caseprocesform.getInfringementtypecodeid(), IBasecodeContrastService.TORT));
        body.put("unlicensed", caseprocesform.getUnlicensed());
        body.put("winAmount", caseprocesform.getDisputemoney());
        body.put("mediationMode", caseprocesform.getMediationmethods());
        body.put("step", FINISHING_FEEDBACK);

        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("全国12315投诉办结反馈回填，请求发生异常", t);
            return false;
        }
    }



    /**
     * 全国12315 投诉表单回填的12345投诉表单
     */
    public boolean executeComplaintFormSwitch(String operateUsername, String regionId, Complaintform complaintform, Tasklist tasklist, Caseprocesform caseprocesform,String handleopinion) {
        String url = backfill12345Url + "/letterConsumption";//letterComeconomicmodule
        Map<String, Object> body = new HashMap<>();

        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", "SD022");
        body.put("formType", "consumption");
        body.put("caseguid", complaintform.getRegistrationnumber());

        body.put("rqstperson", complaintform.getReceivemode());//姓名
        body.put("rqstsex", complaintform.getGender());//性别
        body.put("rqstnumber", complaintform.getLinknumber());//联系电话
        body.put("linkphone", complaintform.getLinknumber());//来电电话
//        body.put("rqstaddress", complaintform.get());//地址

        String businesstypenew= complaintform.getBusinesstypenew().toString();
        boolean status = businesstypenew.contains("-");
        if(status){
            body.put("accordtypecode", businesstypenew.substring(businesstypenew.lastIndexOf("-")+1,businesstypenew.length()));//业务分类
        }else{
            body.put("accordtypecode", businesstypenew);//业务分类
        }

        if ("".equals(complaintform.getRqsttitle().toString())||complaintform.getRqsttitle().toString()==null) {
            body.put("rqsttitle", tasklist.getComplainant()+tasklist.getSerialnum());//标题为空就公司名+单号
        }else{
            body.put("rqsttitle", complaintform.getRqsttitle());//标题
        }


        String providelb = complaintform.getProvidetype().toString();
        if (providelb.contains("内资企业")) {
            body.put("providelb", "私营企业");//类型
        }else if(providelb.contains("外商投资企业")){
            body.put("providelb", "外商投资企业");//类型
        }else if(providelb.contains("其他")){
            body.put("providelb", "测试");//类型
        }else{
            body.put("providelb", providelb);//类型
        }
        String category=complaintform.getProvidelb().toString();
        if ("".equals(category)|| category=="") {
            body.put("category","其他");//类别
        }else{
            body.put("category", complaintform.getProvidelb());//类别
        }
        body.put("linknumber", complaintform.getUsertype());//反映类型
        body.put("rqstsource", "互联网络");//诉求来源

        body.put("subjectname", complaintform.getSubjectname());//客体名称
        body.put("subjectphonetype", "联系电话");//反映类型
        body.put("subjectphone", complaintform.getLinknumber());//联系方式complaintform.getSubjectphone()
        body.put("subjectaddresstype", "顺德区");//经营地址类型
        body.put("subjectaddress", complaintform.getSubjectaddress());//客体名称
        body.put("subjectzcaddtype", "顺德区");//反映类型
        body.put("subjectzcadd", complaintform.getSubjectzcadd());//实际注册地址

        if("商品-其他商品-其他".equals(complaintform.getObjclassify())){
            body.put("objclassify", "其他居民服务");//涉及客体分类
        }else{
            body.put("objclassify", complaintform.getObjclassify());//涉及客体分类
        }

        body.put("objname", complaintform.getObjname());//商品/服务名称
        body.put("commoditybrand", complaintform.getBrand());//商品品牌
        body.put("objmoney", complaintform.getObjmoney().toString());//涉及金额
        if ("".equals(complaintform.getIsweb()) ||  complaintform.getIsweb() == null) {
            body.put("isweb", "否");//是否网购
        }else{
            body.put("isweb", complaintform.getIsweb());//是否网购
        }

        body.put("problemfl", complaintform.getComplaintstype());//涉及问题分类
        body.put("rqstcontent", complaintform.getProblemdescribe());//问题陈述

        if("".equals(complaintform.getKeyword()) || complaintform.getKeyword()==null) {
            body.put("keyword", "无");//关键字字
        }else if (complaintform.getKeyword().length() > 40) {
            body.put("keyword", complaintform.getKeyword().toString().substring(0, 40));//关键字字
        } else {
            body.put("keyword", complaintform.getKeyword());//关键字字
        }

        if ("".equals(handleopinion) || handleopinion == null) {
            body.put("handleopinion", "无意见信息");  //处理意见
        } else {
            body.put("handleopinion", handleopinion);  //处理意见
        }
        body.put("step", ACCEPTANCE_REGISTRATION);


        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("12315投诉表单回填的12345投诉表单回填，请求发生异常", t);
            return false;
        }
    }

    /**
     * 全国12315 举报表单回填的123举报投诉表单
     */
    public boolean executeEconomyFormSwitch(String operateUsername, String regionId, Tasklist tasklist,Economyform economyform, Caseprocesform caseprocesform,String handleopinion) {
        String url = backfill12345Url + "/letterComeconomicmodule";
        Map<String, Object> body = new HashMap<>();

        body.put("operateUsername", operateUsername);
        body.put("backfillUsername", "SD022");
        body.put("formType", "economy");
        body.put("caseguid", economyform.getRegistrationnumber());


        body.put("rqstperson", economyform.getRqstperson());//姓名
        body.put("rqstsex", economyform.getGender());//性别
        body.put("rqstnumber", economyform.getLinknumber());//联系电话
        body.put("linkphone", economyform.getLinknumber());//来电电话
//        body.put("rqstaddress", economyform.get());//地址
        String businesstypenew= economyform.getBusinesstypenew().toString();
        boolean status = businesstypenew.contains("-");
        if(status){
            body.put("accordtypecode", businesstypenew.substring(businesstypenew.lastIndexOf("-")+1,businesstypenew.length()));//业务分类
        }else{
            body.put("accordtypecode", businesstypenew);//业务分类
        }
        if ("".equals(tasklist.getRqsttitle().toString())||tasklist.getRqsttitle().toString()==null) {
            body.put("rqsttitle", tasklist.getComplainant()+tasklist.getSerialnum());//标题为空就公司名+单号

        }else{
            body.put("rqsttitle", tasklist.getRqsttitle());//标题

        }

        String providelb = economyform.getProvidetype().toString();
        if (providelb.contains("内资企业")) {
            body.put("providelb", "私营企业");//类型
        }else if(providelb.contains("外商投资企业")){
            body.put("providelb", "外商投资企业");//类型
        }else if(providelb.contains("其他")){
            body.put("providelb", "测试");//类型
        }else{
            body.put("providelb", providelb);//类型
        }


        body.put("linknumber", economyform.getUsertype());//反映类型
        body.put("rqstsource", "互联网络");//诉求来源

        body.put("subjectname", economyform.getSubjectname());//客体名称
        body.put("subjectphonetype", "联系电话");//反映类型
        body.put("subjectphone", economyform.getLinknumber());//联系方式economyform.getSubjectphone()
        body.put("subjectaddresstype", "顺德区");//经营地址类型
        body.put("subjectaddress", economyform.getSubjectaddress());///经营地址类型
        body.put("subjectzcaddtype", "顺德区");//反映类型
        body.put("subjectzcadd", economyform.getSubjectzcadd());//实际注册地址

        if("商品-其他商品-其他".equals(economyform.getObjclassify())){
            body.put("objclassify", "其他居民服务");//涉及客体分类
        }else{
            body.put("objclassify", economyform.getObjclassify());//涉及客体分类
        }

        if ( economyform.getObjname().length() > 40) {
            body.put("objname", economyform.getObjname().toString().substring(0, 40));//商品/服务名称
        } else {
            body.put("objname", economyform.getObjname());//商品/服务名称
        }

        body.put("commoditybrand", economyform.getBrand());//商品品牌
        body.put("objmoney", economyform.getObjmoney());//涉及金额

        if ("".equals(economyform.getIsweb()) ||  economyform.getIsweb() == null) {
            body.put("isweb", "否");//是否网购
        }else{
            body.put("isweb", economyform.getIsweb());//是否网购
        }

        body.put("problemfl", economyform.getReportprovidelb());//涉及问题分类
        body.put("illegalrules", economyform.getIllegalrules());//涉嫌违法行为
        body.put("rqstcontent", economyform.getProblemdescribe());//问题陈述

        if("".equals(economyform.getKeyword()) || economyform.getKeyword()==null) {
            body.put("keyword", "无");//关键字字
        }else if (economyform.getKeyword().length() > 40) {
            body.put("keyword", economyform.getKeyword().toString().substring(0, 40));//关键字字
        } else {
            body.put("keyword", economyform.getKeyword());//关键字字
        }

        if ("".equals(handleopinion) || handleopinion == null) {
            body.put("handleopinion", "无意见信息");  //处理意见
        } else {
            body.put("handleopinion", handleopinion);  //处理意见
        }

        body.put("step", ACCEPTANCE_REGISTRATION);
        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("12315投诉表单回填的12345投诉表单回填，请求发生异常", t);
            return false;
        }
    }



    private JSONObject clientPost(Map<String, Object> body, String url) throws Throwable {
        logger.info("请求地址:{}，参数:{}", url, new ObjectMapper().writeValueAsString(body));
        BackfillCallLog entity = new BackfillCallLog();
        entity.setUuid(UuidUtil.getUuid());
        entity.setBody(JSONObject.toJSONString(body));
        entity.setRequestAddress(url);
        if (body.containsKey("regId")) {
            entity.setFormNumber((String) body.get("regId"));
        } else {
            entity.setFormNumber((String) body.get("caseguid"));
        }
        entity.setStorageTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        iBackfillCallLogService.save(entity);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, new HttpEntity<>(body, headers), JSONObject.class);

        entity.setResponseBody(JSONObject.toJSONString(response.getBody()));
        iBackfillCallLogService.updateById(entity);

        logger.info("响应状态码:{},结果集:{}", response.getStatusCodeValue(), response.getBody());
        return response.getBody();
    }

    private void asyncClientPost(Map<String, Object> body, String url) throws Throwable {
        logger.info("请求地址:{}，参数:{}", url, new ObjectMapper().writeValueAsString(body));
        BackfillCallLog entity = new BackfillCallLog();
        entity.setUuid(UuidUtil.getUuid());
        entity.setBody(JSONObject.toJSONString(body));
        entity.setRequestAddress(url);
        if (body.containsKey("regId")) {
            entity.setFormNumber((String) body.get("regId"));
        } else {
            entity.setFormNumber((String) body.get("caseguid"));
        }
        entity.setStorageTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setStep((String) body.get("step"));//回填步骤
        entity.setFailedMarks("0");//回填默认成功，下面再验证
        iBackfillCallLogService.save(entity);

        @SuppressWarnings("deprecation")
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ListenableFuture<ResponseEntity<JSONObject>> listenableFuture = asyncRestTemplate.postForEntity(url, new HttpEntity<>(body, headers), JSONObject.class);
        //异步调用后的回调函数
        listenableFuture.addCallback(response -> {
            try {
                entity.setResponseBody(JSONObject.toJSONString(response.getBody()));
//            iBackfillCallLogService.updateById(entity);
                if (response.getStatusCodeValue() != 200) {
                    entity.setFailedMarks("1");
                    iBackfillCallLogService.updateById(entity);
                    logger.info("异步请求地址:{},响应状态码:{},结果集:{}", url, response.getStatusCodeValue(), response.getBody());
                    // TODO: 2020/3/17 先根据cid查询，更新zh表
                    if (body.containsKey("isrcord")) {
                        if ("1".equals(body.get("isrcord"))) {
                            QueryWrapper<Zhistory> zhWrapper = new QueryWrapper<>();
                            zhWrapper.lambda().eq(Zhistory::getCaseid, body.get("caseguid"));
                            List<Zhistory> zhList = zhistorymapper.selectList(zhWrapper);
                            if (zhList.size() > 0) {
                                for (Zhistory zhistory : zhList) {
                                    zhistory.setRecord("1");
                                    zhistory.setUpdatedate(new Date());
                                    zhistorymapper.updateById(zhistory);
                                }
                            }
                        }
                    }
                } else {
                    entity.setFailedMarks("0");
                    iBackfillCallLogService.updateById(entity);
                    // TODO: 2020/3/17 先根据cid查询，更新zh表
                    if (body.containsKey("isrcord")) {
                        if ("1".equals(body.get("isrcord"))) {
                            QueryWrapper<Zhistory> zhWrapper = new QueryWrapper<>();
                            zhWrapper.lambda().eq(Zhistory::getCaseid, body.get("caseguid"));
                            List<Zhistory> zhList = zhistorymapper.selectList(zhWrapper);
                            if (zhList.size() > 0) {
                                for (Zhistory zhistory : zhList) {
                                    zhistory.setRecord("0");
                                    zhistory.setUpdatedate(new Date());
                                    zhistorymapper.updateById(zhistory);
                                }
                            }
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("异步回填异常！", e);
            }

        }, failure -> {
            entity.setFailedMarks("1");
            iBackfillCallLogService.updateById(entity);
            // TODO: 2020/3/17 先根据cid查询，更新zh表
            if (body.containsKey("isrcord")) {
                if ("1".equals(body.get("isrcord"))) {
                    QueryWrapper<Zhistory> zhWrapper = new QueryWrapper<>();
                    zhWrapper.lambda().eq(Zhistory::getCaseid, body.get("caseguid"));
                    List<Zhistory> zhList = zhistorymapper.selectList(zhWrapper);
                    if (zhList.size() > 0) {
                        for (Zhistory zhistory : zhList) {
                            zhistory.setRecord("1");
                            zhistory.setUpdatedate(new Date());
                            zhistorymapper.updateById(zhistory);
                        }
                    }
                }
            }
            logger.error("异步请求地址:{},故障:{}", url, failure.getMessage());
        });
    }

    /**
     * 12345类型转换
     */
    private String byTextSwitch12345FormType(String formType) {
        if (BaseDataUtil.TASKLIST_FORMTYPE_COMMOMMODULE.equals(formType)) {
            //咨询表单与通用
            formType = "consultation";
        } else if (BaseDataUtil.TASKLIST_FORMTYPE_COMPLAINTMODULE.equals(formType)) {
            //消费投诉
            formType = "consumption";
        } else if (BaseDataUtil.TASKLIST_FORMTYPE_COMECONOMICMODULE.equals(formType)) {
            //经济违法
            formType = "economy";
        }
        return formType;
    }

    /**
     * 12315类型转换
     */
    private String byTextSwitch12315FormType(String formType) {
        if ("01".equals(formType)) {
            //咨询
            formType = "consultation";
        } else if ("02".equals(formType)) {
            //投诉
            formType = "complaint";
        } else if ("03".equals(formType)) {
            //举报
            formType = "report";
        }
        return formType;
    }


    /**
     * 全国12315 投诉表单回填的12345投诉表单
     */
    public boolean executeComplaintFormSwitch2(String zjjson) {
        String url = "http://10.6.100.207:8083/crcs-crawler/backfill/12345" + "/letterConsumption";
//        String url = backfill12345Url+ "/letterConsumption";
        Map<String, Object> body = new HashMap<>();
        JSONObject json3 = JSONObject.parseObject(zjjson);

        body.put("operateUsername", json3.get("operateUsername").toString());
        body.put("backfillUsername", "SD022");
        body.put("formType", "consumption");
        body.put("caseguid", json3.get("caseguid").toString());

        body.put("rqstperson", json3.get("rqstperson").toString());//姓名
        body.put("rqstsex", json3.get("rqstsex").toString());//性别
        body.put("rqstnumber", json3.get("rqstnumber").toString());//联系电话
        body.put("linkphone", json3.get("rqstnumber").toString());//来电电话
//        body.put("rqstaddress", complaintform.get());//地址
        body.put("rqsttitle", json3.get("rqsttitle").toString());//标题


        //todo accordtypecode providelb
        String businesstypenew= json3.get("accordtypecode").toString();
        boolean status = businesstypenew.contains("-");
        if(status){
            body.put("accordtypecode", businesstypenew.substring(businesstypenew.lastIndexOf("-")+1,businesstypenew.length()));//业务分类
        }else{
            body.put("accordtypecode", businesstypenew);//业务分类
        }

        String providelb = json3.get("providelb").toString();
        if (providelb.contains("内资企业")) {
            body.put("providelb", "私营企业");//类型
        }else if(providelb.contains("外商投资企业")){
            body.put("providelb", "外商投资企业");//类型
        }else if(providelb.contains("其他")){
            body.put("providelb", "测试");//类型
        }else{
            body.put("providelb", providelb);//类型
        }

        body.put("category", json3.get("category").toString());//类别
        body.put("linknumber", json3.get("linknumber").toString());//反映类型
        body.put("rqstsource", "互联网络");//诉求来源

        body.put("subjectname", json3.get("subjectname").toString());//客体名称
        body.put("subjectphonetype", "联系电话");//反映类型
        body.put("subjectphone", json3.get("subjectphone").toString());//联系方式complaintform.getSubjectphone()
        body.put("subjectaddresstype", "顺德区");//经营地址类型
        body.put("subjectaddress", json3.get("subjectaddress").toString());//客体名称
        body.put("subjectzcaddtype", "顺德区");//反映类型
        body.put("subjectzcadd", json3.get("subjectzcaddtype").toString());//实际注册地址

        body.put("objclassify", json3.get("objclassify").toString());//涉及客体分类
        body.put("objname", json3.get("objname").toString());//商品/服务名称
        body.put("commoditybrand",json3.get("commoditybrand").toString());//商品品牌
        body.put("objmoney", json3.get("objmoney").toString());//涉及金额
        body.put("isweb", json3.get("isweb").toString());//是否网购


        body.put("problemfl",json3.get("problemfl").toString());//涉及问题分类
        body.put("rqstcontent", json3.get("rqstcontent").toString());//问题陈述
        body.put("keyword", json3.get("keyword").toString());//关键字
        body.put("handleopinion", json3.get("handleopinion").toString());  //处理意见
        body.put("step", ACCEPTANCE_REGISTRATION);
        body.put("isrcord", "1");//用来判断更新zh表


        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("12315投诉表单回填的12345投诉表单回填，请求发生异常", t);
            return false;
        }
    }

    /**
     * 全国12315 举报表单回填的123举报投诉表单
     */
    public boolean executeEconomyFormSwitch2(String zjjson) {
        String url = "http://10.6.100.207:8083/crcs-crawler/backfill/12345" + "/letterComeconomicmodule";
//        String url =backfill12345Url + "/letterComeconomicmodule";
        Map<String, Object> body = new HashMap<>();

        JSONObject json3 = JSONObject.parseObject(zjjson);
        body.put("operateUsername", json3.get("operateUsername").toString());
        body.put("backfillUsername", "SD022");
        body.put("formType", "economy");
        body.put("caseguid", json3.get("caseguid").toString());


        body.put("rqstperson", json3.get("rqstperson").toString());//姓名
        body.put("rqstsex", json3.get("rqstsex").toString());//性别
        body.put("rqstnumber", json3.get("rqstnumber").toString());//联系电话
        body.put("linkphone",json3.get("linkphone").toString());//来电电话
//        body.put("rqstaddress", economyform.get());//地址
        body.put("rqsttitle", json3.get("rqsttitle").toString());//标题

//        //todo accordtypecode providelb
        String businesstypenew = json3.get("accordtypecode").toString();
        boolean status = businesstypenew.contains("-");
        if(status){
            body.put("accordtypecode", businesstypenew.substring(businesstypenew.lastIndexOf("-")+1,businesstypenew.length()));//业务分类
        }else{
            body.put("accordtypecode", businesstypenew);//业务分类
        }

        String providelb = json3.get("providelb").toString();;
        if (providelb.contains("内资企业")) {
            body.put("providelb", "私营企业");//类型
        }else if(providelb.contains("外商投资企业")){
            body.put("providelb", "外商投资企业");//类型
        }else if(providelb.contains("其他")){
            body.put("providelb", "测试");//类型
        }else{
            body.put("providelb", providelb);//类型
        }


        body.put("linknumber", json3.get("linknumber").toString());//反映类型
        body.put("rqstsource", "互联网络");//诉求来源

        body.put("subjectname", json3.get("subjectname").toString());//客体名称
        body.put("subjectphonetype", "联系电话");//反映类型
        body.put("subjectphone",json3.get("subjectphone").toString());//联系方式economyform.getSubjectphone()
        body.put("subjectaddresstype", "顺德区");//经营地址类型
        body.put("subjectaddress", json3.get("subjectaddress").toString());//客体名称
        body.put("subjectzcaddtype", "顺德区");//反映类型
        body.put("subjectzcadd", json3.get("subjectzcaddtype").toString());//实际注册地址

        body.put("objclassify", json3.get("objclassify").toString());//涉及客体分类
        body.put("objname", json3.get("objname").toString());//商品/服务名称
        body.put("commoditybrand", json3.get("commoditybrand").toString());//商品品牌
        body.put("objmoney", json3.get("objmoney").toString());//涉及金额
        body.put("isweb", json3.get("isweb").toString());//是否网购


        body.put("problemfl", json3.get("problemfl").toString());//涉及问题分类
        body.put("illegalrules", json3.get("illegalrules").toString());//涉嫌违法行为
        body.put("rqstcontent", json3.get("rqstcontent").toString());//问题陈述
        body.put("keyword", json3.get("keyword").toString());//关键字字
        body.put("handleopinion", json3.get("handleopinion").toString());  //处理意见
        body.put("step", ACCEPTANCE_REGISTRATION);
        body.put("isrcord", "1");//用来判断更新zh表

        try {
            asyncClientPost(body, url);
            return true;
        } catch (Throwable t) {
            logger.error("12315投诉表单回填的12345投诉表单回填，请求发生异常", t);
            return false;
        }
    }



}
