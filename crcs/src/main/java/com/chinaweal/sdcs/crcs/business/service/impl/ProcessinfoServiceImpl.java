package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.aicorg.pojo.AICPermission;
import com.chinaweal.aicorg.services.OrgUM;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.mapper.*;
import com.chinaweal.sdcs.crcs.business.service.IDispatchbackinfoService;
import com.chinaweal.sdcs.crcs.business.service.IMaterialinfoService;
import com.chinaweal.sdcs.crcs.business.service.IProcessinfoService;
import com.chinaweal.sdcs.crcs.util.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author chinaweal
 * @since 2019-08-18
 */
@Service
public class ProcessinfoServiceImpl extends ServiceImpl<ProcessinfoMapper, Processinfo> implements IProcessinfoService {

    @Autowired
    private OpinionMapper opinionMapper;
    @Autowired
    private ConsultationformMapper consultationformMapper;
    @Autowired
    private CommomformMapper commomformMapper;
    @Autowired
    private TasklistMapper tasklistMapper;
    @Autowired
    private HandledemandMapper handledemandMapper;
    @Autowired
    private ProcessinfoMapper processinfoMapper;
    @Autowired
    private ComplaintformMapper complaintformMapper;
    @Autowired
    private EconomyformMapper economyformMapper;
    @Autowired
    private CaseprocesformMapper caseprocesformMapper;
    @Resource
    private IMaterialinfoService iMaterialinfoService;
    @Autowired
    private UuidUtil uuidUtil;
    @Autowired
    private OrgUM orgUM;
    @Autowired
    private UsersAppMapper usersappMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Resource
    private BackfillServiceImpl backfillService;
    @Value("${backfill.isOpen12345}")
    private boolean isOpen12345Backfill;
    @Value("${backfill.isOpen12315}")
    private boolean isOpen12315Backfill;
    @Resource
    private IDispatchbackinfoService iDispatchbackinfoService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public boolean saveOpinion(AICUser aicUser, Map<String, Object> requestMap) {
        boolean result = false;
        Opinion opinion = null;
        String formType = (String) requestMap.get("formType"); //表单类型
        if (requestMap.get("opinion") != null) {
            opinion = (Opinion) RequestUtil.mapToEntity(Opinion.class, requestMap.get("opinion"));
            if (opinion != null) {
                opinion.setHandler(aicUser.getName());
                opinion.setHandledept(aicUser.getOrgName());
                opinion.setOperate("");
                opinion.setNodename("");
                opinion.setHandletime(new Date());
                opinion.setUpdatedate(new Date());
                if (StringUtils.isBlank(opinion.getId())) {
                    opinion.setId(UUID.randomUUID().toString());
                    if (StringUtils.isBlank(opinion.getCaseguid())) {
                        opinion.setCaseguid(UUID.randomUUID().toString());
                    }
                    opinionMapper.insert(opinion);
                } else {
                    opinionMapper.updateById(opinion);
                }
            }
            result = true;
        }
        return result;
    }

    @Override
    public Map<String, String> updateWorkFlow(AICUser aicUser, Map<String, Object> requestMap) throws Exception {
//        Consultationform consultationform = null; //咨询类表单信息
        Commomform commomform = null; //咨询类表单信息
        Complaintform complaintform = null; //投诉类表单信息
        Economyform economyform = null; //经济违法类表单信息
        Caseprocesform caseprocesform = null; //经济违法立法信息
        Opinion opinion = null;
        String caseguid = "";
        String labelname = "";
        String focus = "";
        String rqsttitle = "";
        String accordtype = "";
        String isregistercase = "";
        Map<String, String> responseMap = new HashMap<>();
        String formType = (String) requestMap.get("formType"); //表单类型
        String nodeId = (String) requestMap.get("node"); //当前节点
        String nextNodeId = (String) requestMap.get("nextNodeId"); //下一步处理的节点
        String orgid = (String) requestMap.get("orgid");  //机构id
        String deptid = (String) requestMap.get("deptid");  //部门id
        List deptids = (ArrayList) requestMap.get("ortherDept");  //查看部门，人员
        String worktype = (String) requestMap.get("worktype");  //工单是否紧急
        String backReason = (String) requestMap.get("backreason");//退回原因选择项
        String noAcceptReason = (String) requestMap.get("noAcceptreason");//不受理原因选择项
        Map<String, Object> delayData = (Map) requestMap.get("delayData");//申请延期表单
        String isnewassign = (String) requestMap.get("isnewassign");//是否新派单标识（0：不是，1：是）
        String senderid = ""; //发送人id
        String handledeptname = ""; //处理机关或者部门名称 2019.11.28
        Date acceptTime = new Date();
        Date filingTime = new Date();
        Date feedbackTime = new Date();
        Opinion needOpinion = new Opinion();
        Message message=new Message();
        Map<String, String> messagemap = new HashMap<>();//保存短信参数

        //记录当前操作用户，只用于回填。
        AICUser currentHandleUser = orgUM.findUserByPrimaryKey(aicUser.getPrimaryKey());
        if ("0".equals(requestMap.get("ismain"))) {
            //抄送意见填写
            caseguid = (String) requestMap.get("caseguid");
            opinion = new Opinion();
            opinion.setId(UUID.randomUUID().toString());
            opinion.setHandler(aicUser.getName());
//            opinion.setHandledept(aicUser.getOrgName());
            opinion.setHandledept(aicUser.getUnitName());
            opinion.setCaseguid(caseguid);
            if("collaborative".equals(nextNodeId)){
                opinion.setOperate("最终反馈");//todo
                opinion.setNodename("最终反馈");
            }else{
                opinion.setOperate(BaseDataUtil.getNodeNameByNodeId(nextNodeId));//todo
                opinion.setNodename(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
            }
            opinion.setHandleopinion(requestMap.get("opinion") != null ? requestMap.get("opinion").toString() : null);
            opinion.setHandletime(new Date());
            opinion.setUpdatedate(new Date());
            opinion.setStatus("1");
            opinionMapper.insert(opinion);
            //msx 抄送意见填写后，不再显示在待办列表中
            if("1".equals(opinion.getStatus())){
                QueryWrapper<Processinfo> processinfoWrapper = new QueryWrapper<>();
                if("collaborative".equals(nextNodeId)){
                    processinfoWrapper.lambda().eq(Processinfo::getCaseguid, caseguid).
                            eq(Processinfo::getHandleruserid,aicUser.getPrimaryKey());
                }else {
                    processinfoWrapper.lambda().eq(Processinfo::getCaseguid, caseguid);
                }
                List<Processinfo> processinfos = processinfoMapper.selectList(processinfoWrapper);
                for(Processinfo processinfo:processinfos){
                    processinfo.toString();
                    processinfo.getId();
                    UpdateWrapper<Processinfo> processinfoupdateWrapper = new UpdateWrapper<>();
                    if("collaborative".equals(nextNodeId)){
                        processinfoupdateWrapper.set("status", "1")
                                .eq("ismain", "0")
                                .eq("caseguid",caseguid)
                                .eq("handleruserid",aicUser.getPrimaryKey());
                    }else {
                        processinfoupdateWrapper.set("status", "1")
                                .eq("ismain", "0")
                                .eq("caseguid",caseguid);
                    }
                    processinfoMapper.update(new Processinfo(), processinfoupdateWrapper);
                }
            }
            responseMap.put("caseguid", caseguid);
            responseMap.put("str", "true");
            return responseMap;
        }

        //咨询表单
        if ("commommodule".equals(formType)) {
            if (requestMap.get("data") != null) {
                commomform = (Commomform) RequestUtil.mapToEntity(Commomform.class, requestMap.get("data"));
                if (commomform != null) {
                    if ("begin".equals(requestMap.get("node"))) {
                        if ("accept".equals(requestMap.get("nextNodeId")) || "moveToLowerOrg".equals(requestMap.get("nextNodeId"))
                                || "moveToLowerDept".equals(requestMap.get("nextNodeId"))) {
//                            consultationform.setAcceptdate(acceptTime);

                        }
                    }
                    commomform.setUpdatedate(new Date());
                    if (StringUtils.isBlank(commomform.getId())) {
                        commomform.setId(UUID.randomUUID().toString());
                        if (StringUtils.isBlank(commomform.getCaseguid())) {
                            commomform.setCaseguid(UUID.randomUUID().toString());
                        }
                        commomformMapper.insert(commomform);
                    } else {
                        commomformMapper.updateById(commomform);
                    }
                    caseguid = commomform.getCaseguid();
                    focus = commomform.getFocus();
                    rqsttitle = commomform.getRqsttitle();
                    accordtype = commomform.getBusinesstype();
                }
            }
        }
        //消费投诉表单
        else if ("complaintmodule".equals(formType)) {
            if (requestMap.get("data") != null) {
                complaintform = (Complaintform) RequestUtil.mapToEntity(Complaintform.class, requestMap.get("data"));
                if (complaintform.getCertificate() != null && "身份证".equals(complaintform.getCertificate())) {
                    String str = JunitIDCardUtils.IdentityCardVerification(complaintform.getIdnumber());
                    if (!"true".equals(str)) {
                        responseMap.put("str", str);
                        return responseMap;
                    }
                }
                if (complaintform != null) {
                    if ("begin".equals(requestMap.get("node"))) {
                        if ("accept".equals(requestMap.get("nextNodeId")) || "moveToLowerOrg".equals(requestMap.get("nextNodeId"))
                                || "moveToLowerDept".equals(requestMap.get("nextNodeId"))) {
                            if("manualEntry".equals(requestMap.get("type"))){
                            }else{
                                complaintform.setAcceptdate(acceptTime);

                            }
                        }
                    }
                    complaintform.setUpdatedate(new Date());
                    if ("leaderApr".equals(requestMap.get("nextNodeId"))) {
                        complaintform.setFeedbackdate(feedbackTime);
                    }
                    if ("regionAudit".equals(requestMap.get("nextNodeId")) && "leaderApr".equals(requestMap.get("node"))) {
                        complaintform.setFeedbackdate(feedbackTime);
                    }
                    if (StringUtils.isBlank(complaintform.getId())) {
                        complaintform.setId(UUID.randomUUID().toString());
                        if (StringUtils.isBlank(complaintform.getCaseguid())) {
                            complaintform.setCaseguid(UUID.randomUUID().toString());
                        }
                        complaintformMapper.insert(complaintform);
                    } else {
                        complaintformMapper.updateById(complaintform);
                    }
                    caseguid = complaintform.getCaseguid();
                    focus = complaintform.getFocus();
                    rqsttitle = complaintform.getRqsttitle();
                    accordtype = complaintform.getBusinesstype();
                }
            }
        }
        //经济违法表单
        else {
            if (requestMap.get("data") != null) {
                economyform = (Economyform) RequestUtil.mapToEntity(Economyform.class, requestMap.get("data"));
                QueryWrapper<Tasklist> queryWrapper=new QueryWrapper<>();
                queryWrapper.lambda().eq(Tasklist::getCaseguid,economyform.getCaseguid());
                Tasklist tasklist = tasklistMapper.selectOne(queryWrapper);
                if (economyform.getCertificate() != null && "身份证".equals(economyform.getCertificate())) {
                    String str = JunitIDCardUtils.IdentityCardVerification(economyform.getIdnumber());
                    if (!"true".equals(str)) {
                        responseMap.put("str", str);
                        return responseMap;
                    }
                }
                economyform.setUpdatedate(new Date());
                if (economyform != null) {
                    if ("begin".equals(requestMap.get("node"))) {
                        if ("accept".equals(requestMap.get("nextNodeId")) || "moveToLowerOrg".equals(requestMap.get("nextNodeId"))
                                || "moveToLowerDept".equals(requestMap.get("nextNodeId"))) {
                            if("manualEntry".equals(requestMap.get("type"))){
                            }else {
                                economyform.setAcceptdate(acceptTime);
                            }
                        }
                    }
                    if ("registerCase".equals(requestMap.get("nextNodeId"))) {
                        if(!"01".equals(tasklist.getSystemsource())){
                        economyform.setFilingcasesdate(filingTime);
                        }
                    }
                    if ("leaderApr".equals(requestMap.get("nextNodeId"))) {
                        if(!"01".equals(tasklist.getSystemsource())) {
                            economyform.setFeedbackdate(filingTime);
                        }
                    }
                    if ("regionAudit".equals(requestMap.get("nextNodeId")) && "leaderApr".equals(requestMap.get("node"))) {
                        if(!"01".equals(tasklist.getSystemsource())) {
                            economyform.setFeedbackdate(feedbackTime);
                        }
                    }
                    if (StringUtils.isBlank(economyform.getId())) {
                        economyform.setId(UUID.randomUUID().toString());
                        if (StringUtils.isBlank(economyform.getCaseguid())) {
                            economyform.setCaseguid(UUID.randomUUID().toString());
                        }
                        economyformMapper.insert(economyform);
                    } else {
                        economyformMapper.updateById(economyform);
                    }
                    caseguid = economyform.getCaseguid();
                    focus = economyform.getFocus();
                    rqsttitle = economyform.getRqsttitle();
                    accordtype = economyform.getBusinesstype();
                }
            }
        }
        //添加经济违法立案处理数据或处理反馈信息
        if (requestMap.get("caseprocesform") != null) {
            caseprocesform = (Caseprocesform) RequestUtil.mapToEntity(Caseprocesform.class, requestMap.get("caseprocesform"));
            if (caseprocesform != null) {
                if (caseprocesform.getIsregister() == null) {
                    isregistercase = "";
                } else {
                    isregistercase = caseprocesform.getIsregister();
                }
                if (StringUtils.isBlank(caseprocesform.getId())) {
                    caseprocesform.setId(UUID.randomUUID().toString());
                    caseprocesform.setNodeid(BaseDataUtil.getNodeNameByNodeId(caseprocesform.getNodeid()));
                    caseprocesform.setUpdatedate(new Date());
                    caseprocesform.setCaseguid(caseguid);
                    caseprocesformMapper.insert(caseprocesform);
                } else {
                    caseprocesform.setNodeid(BaseDataUtil.getNodeNameByNodeId(caseprocesform.getNodeid()));
                    caseprocesform.setCaseguid(caseguid);
                    caseprocesform.setUpdatedate(new Date());
                    caseprocesformMapper.updateById(caseprocesform);
                }
            }
        }
        //不是退回节点，再去判断是否有初步答复
        if (!BaseDataUtil.WORKFLOW_NODE_BACKFORWARD.equals(nextNodeId)) {
            //业务流转的过程先判断是否有初步答复的意见，如果没有，后台默认生成一条 2019.10.10
            QueryWrapper<Processinfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Processinfo::getNodeid, "interimReply").eq(Processinfo::getCaseguid, caseguid);
            List<Processinfo> processinfoList = processinfoMapper.selectList(queryWrapper);
            if (processinfoList == null || processinfoList.size() == 0) {
                Date update = new Date();
                //流程表记录 先更新，后插入
                UpdateWrapper<Processinfo> processinfoWrapper = new UpdateWrapper<>();
                processinfoWrapper.set("status", "1")
                        .eq("caseguid", caseguid)
                        .eq("status", "0");
                processinfoMapper.update(new Processinfo(), processinfoWrapper);
                nodeId = "interimReply";
                Processinfo processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
                processinfo.setNodeid("interimReply");
                processinfo.setFromnodeid("begin");
                processinfo.setStatus("1");
                processinfo.setIsmain("1");//liny
                processinfo.setUpdatedate(update);
                processinfoMapper.insert(processinfo);//liny

                //生成默认意见
                opinion = new Opinion();
                opinion.setId(UUID.randomUUID().toString());
                opinion.setHandler(aicUser.getName());
                opinion.setHandledept(aicUser.getOrgName());
                opinion.setCaseguid(caseguid);
                if("collaborative".equals(requestMap.get("nextNodeId"))){
                    opinion.setOperate("部门协同");
                    opinion.setNodename("受理");
                }else{
                    opinion.setOperate(BaseDataUtil.getNodeNameByNodeId("interimReply"));
                    opinion.setNodename(BaseDataUtil.getNodeNameByNodeId("interimReply"));
                }

                opinion.setHandleopinion("收悉，请跟进处理。");
                opinion.setHandletime(update);
                opinion.setUpdatedate(update);
                opinion.setStatus("1");
                opinionMapper.insert(opinion);
                if (isOpen12345Backfill) {
                    //调用初步答复回填
                    QueryWrapper<Tasklist> tasklistWrapper = new QueryWrapper<>();
                    tasklistWrapper.lambda().eq(Tasklist::getCaseguid, caseguid);
                    Tasklist tasklist = tasklistMapper.selectOne(tasklistWrapper);
                    if (tasklist != null && "02".equals(tasklist.getSystemsource())) {
                        //12345系统则，调用回填
                        backfillService.executeInitialReply(currentHandleUser.getEname(), currentHandleUser.getRegionID(), tasklist.getCaseguid(), formType,
                                opinion.getHandleopinion(), null);
                    }
                }
            }
        }
        //判断是否有意见
        QueryWrapper<Opinion> opinionWrapper = new QueryWrapper<>();
        opinionWrapper.lambda().eq(Opinion::getCaseguid, caseguid);
        opinionWrapper.lambda().eq(Opinion::getStatus, "0");
        Opinion oneOpinion = opinionMapper.selectOne(opinionWrapper);
        opinion = new Opinion();
        if (oneOpinion != null) {
            opinion.setId(oneOpinion.getId());
            opinion.setHandler(aicUser.getName());
            opinion.setHandledept(aicUser.getOrgName());
            opinion.setCaseguid(caseguid);
            opinion.setOperate(BaseDataUtil.getNodeNameByNodeId(nodeId));//todo
            opinion.setNodename(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
            opinion.setHandleopinion(requestMap.get("opinion") != null ? requestMap.get("opinion").toString() : null);
            opinion.setHandletime(new Date());
            opinion.setUpdatedate(new Date());
            opinion.setStatus("1");
            int update = opinionMapper.update(opinion, opinionWrapper);
            System.out.println("kjafs");
            System.out.println(update);
        } else {
            opinion.setId(UUID.randomUUID().toString());
            opinion.setHandler(aicUser.getName());
            opinion.setHandledept(aicUser.getOrgName());
            opinion.setCaseguid(caseguid);
            if("collaborative".equals(nextNodeId)){
                opinion.setOperate("分派意见");//todo
                opinion.setNodename("分派意见");
            }else{
                opinion.setOperate(BaseDataUtil.getNodeNameByNodeId(nodeId));//todo
                opinion.setNodename(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
            }
            opinion.setHandleopinion(requestMap.get("opinion") != null ? requestMap.get("opinion").toString() : null);
            opinion.setHandletime(new Date());
            opinion.setUpdatedate(new Date());
            opinion.setStatus("1");
            int insert = opinionMapper.insert(opinion);
            System.out.println("vnvnbn");
            System.out.println(insert);
        }

        //待办列表信息
        QueryWrapper<Tasklist> tasklistWrapper = new QueryWrapper<>();
        tasklistWrapper.lambda().eq(Tasklist::getCaseguid, caseguid);
        Tasklist tasklist = tasklistMapper.selectOne(tasklistWrapper);

        //msx 受理登记延期申请
        if("applyDelay".equals(nextNodeId) && "01".equals(tasklist.getSystemsource())){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//             Date delaytime1 = sdf.parse(requestMap.get("delaytime").toString());
            if(requestMap.get("delayday")!=null) {
                Object delayday = requestMap.get("delayday");
                int i = Integer.parseInt(delayday.toString());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(tasklist.getSdexpirationdate());

                //立案延期15工作日
                Date delayDate = CreateEntityInfoUtil.getWorkDay(calendar.getTime(), i, calendar.getTime().getHours(),
                        calendar.getTime().getSeconds(), calendar.getTime().getMinutes());
                tasklist.setFirstblqx(delayDate);
            }
            if(requestMap.get("finaldelayday")!=null) {
                //最终反馈延期30自然日
                int j = Integer.parseInt(requestMap.get("finaldelayday").toString());
                Date sdexpirationdate = tasklist.getSdexpirationdate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(sdexpirationdate);
                cal.add(Calendar.DATE, j);
                Date finaldelayday = cal.getTime();

                tasklist.setFirstblqx(finaldelayday);
            }
            //保存申请延期时间
//            tasklist.setFirstblqx(delaytime1);
            tasklistMapper.updateById(tasklist);
        }

        if("superiorProcessing".equals(nextNodeId) && "agree".equals(requestMap.get("status")) && "01".equals(tasklist.getSystemsource())){
            Date delaytime = tasklist.getFirstblqx();
            System.out.println("同意222");
            System.out.println(delaytime);
            if("comeconomicmodule".equals(formType)){
                if(tasklist.getIsregistercase()==null){
                    tasklist.setSdexpirationdate(delaytime);
                    economyform.setFilingcasesdate(delaytime);
                }else if(tasklist.getIsregistercase()!=null){
                    tasklist.setSdexpirationdate(delaytime);
                    economyform.setFinalfeedbackdate(delaytime);
                }
                tasklistMapper.updateById(tasklist);
                economyformMapper.updateById(economyform);
            }else if("commommodule".equals(formType)){
                tasklist.setSdexpirationdate(delaytime);
                commomform.setFinalfeedbackdate(delaytime);
                tasklistMapper.updateById(tasklist);
                commomformMapper.updateById(commomform);
            }else if("complaintmodule".equals(formType)){
                tasklist.setSdexpirationdate(delaytime);
                complaintform.setFeedbacktime(delaytime);
                tasklistMapper.updateById(tasklist);
                complaintformMapper.updateById(complaintform);
            }
        }

//        List<Tasklist> tasklists = tasklistMapper.selectList(tasklistWrapper);
//        Tasklist tasklist = tasklists.get(tasklists.size()-1);
        QueryWrapper<Handledemand> handledemandWrapper = new QueryWrapper<>();
        handledemandWrapper.lambda().eq(Handledemand::getCaseguid, caseguid).orderByAsc(Handledemand::getUpdatedate);
        //Handledemand handledemand = handledemandMapper.selectOne(handledemandWrapper);
        //msx 重派单handledemand表出现两条数据无法分派下级，这里做一个判断。
        List<Handledemand> handledemandList = handledemandMapper.selectList(handledemandWrapper);
        if(handledemandList.size()==0){
            Handledemand handledemand = handledemandMapper.selectOne(handledemandWrapper);
            if (tasklist != null) {
                senderid = tasklist.getSenderid();
                tasklist.setLabelname(labelname);
                tasklist.setFocus(focus);
                tasklist.setRqsttitle(rqsttitle);
                tasklist.setAccordtype(accordtype);
                tasklist.setFormtype(BaseDataUtil.getFormType(formType));
                tasklist.setOperate(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
                tasklist.setIssignin("0");
                tasklist.setNextnodeid(nextNodeId);
                tasklist.setFromnodeid(nodeId);
                tasklist.setWorktype(worktype);
                tasklist.setIsregistercase(isregistercase);
                tasklist.setIscbdf("0");
                //12345工单受理、是否立案、处理反馈环节需要增加时限控制
                if ("accept".equals(nextNodeId) || "registerCase".equals(nextNodeId) || "noRegisterCase".equals(nextNodeId)) {
                    if ("accept".equals(nextNodeId)) {
                        //如果下一步处理是受理，将是否受理标志改成1（已受理）
                        tasklist.setIsaccept("1");
                        tasklist.setTaskstartdate(acceptTime);
                        //12345消费投诉工单受理更新到期时间
                        if ("02".equals(tasklist.getSystemsource())) {
                            if("03".equals(tasklist.getFormtype())){
//                            Handledemand handledemand = (Handledemand) RequestUtil.mapToEntityTwo(Handledemand.class, requestMap.get("handledemand"));
                                tasklist.setSdexpirationdate(handledemand.getCaseputtime());
                                tasklistMapper.updateById(tasklist);
                            }else {
                                Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                                tasklist.setSdexpirationdate(date);
                                handledemand.setResulttime(date);
                                handledemandMapper.updateById(handledemand);
                            }
                        }
                        if("01".equals(tasklist.getSystemsource()) && "03".equals(tasklist.getFormtype())){
                            //tasklist.setSdexpirationdate(economyform.getFilingcasesdate());//受理登记受理后改变到期时间
                            //msx 受理后应立案时间
                            if(tasklist.getFeedback()==null) {
                                tasklist.setSdexpirationdate(economyform.getFeedbackdate());
                                tasklistMapper.updateById(tasklist);
                            }else if("1".equals(tasklist.getFeedback())){
                                tasklist.setSdexpirationdate(economyform.getFilingcasesdate());
                                tasklistMapper.updateById(tasklist);
                            }
                        }
                        if("01".equals(tasklist.getSystemsource()) && "02".equals(tasklist.getFormtype())){
                            if(tasklist.getFeedback()==null) {
                                tasklist.setSdexpirationdate(complaintform.getFeedbackdate());
                                tasklistMapper.updateById(tasklist);
                            }else if("1".equals(tasklist.getFeedback())){
                                tasklist.setSdexpirationdate(complaintform.getFeedbacktime());
                                tasklistMapper.updateById(tasklist);
                            }
                        }
                    }
                    if ("registerCase".equals(nextNodeId) || "noRegisterCase".equals(nextNodeId)) {
                        //12345经济违法工单立案更新到期时间
                        if ("02".equals(tasklist.getSystemsource())) {
                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                            tasklist.setSdexpirationdate(date);
                            tasklistMapper.updateById(tasklist);
                            handledemand.setResulttime(date);
                            handledemandMapper.updateById(handledemand);
                        }
                        //12345添加立案时间
                        if ("02".equals(tasklist.getSystemsource())) {
                            tasklist.setFilingdate(filingTime);
                        }
                        //12315立案后的到期时间以获取的12315系统到期时间为准
                        //2020-3-1 到期时间调整 --liny
//                        if ("03".equals(tasklist.getSystemsource()) && "registerCase".equals(nextNodeId)) {
//                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
//                            tasklist.setSdexpirationdate(date);//linyu2
//                        }
//                        if ("03".equals(tasklist.getSystemsource()) && "accept".equals(nextNodeId)) {
//                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
//                            tasklist.setSdexpirationdate(date);//linyu2
//                        }
                        //受理登记添加立案时间
                        if ("01".equals(tasklist.getSystemsource())) {
//                            tasklist.setFilingdate(filingTime);//受理登记添加立案时间
//                            tasklist.setSdexpirationdate(economyform.getFinalfeedbackdate());//受理登记添加最终反馈时间
                                if("registerCase".equals(nextNodeId)){
                                    tasklist.setIsregistercase("是");
                                }else if("noRegisterCase".equals(nextNodeId)){
                                    tasklist.setIsregistercase("否");
                                }
                                tasklist.setSdexpirationdate(economyform.getFinalfeedbackdate());
                                tasklistMapper.updateById(tasklist);

//                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
//                            tasklist.setSdexpirationdate(date);
//                            tasklistMapper.updateById(tasklist);
                        }
                    } else {
                        if ("03".equals(tasklist.getSystemsource())) {
                            if ("complaintmodule".equals(formType)) {
                                //msx 12315投诉受理后到期时间
                                Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                                tasklist.setSdexpirationdate(date);//linyu1
                            }
                            if ("comeconomicmodule".equals(formType)) {
                                Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                                tasklist.setSdexpirationdate(date);
                            }
                        } else {
//                        Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
//                        tasklist.setSdexpirationdate(date);
                        }
                    }
                }
                //受理登记直接分派下级时，需要设置到期时间
                if ("01".equals(tasklist.getSystemsource()) && "moveToLowerOrg".equals(nextNodeId) && !"1".equals(tasklist.getIsaccept())) {
//                    Date date = setSdexpirationdate(tasklist, worktype);
                    tasklist.setSdexpirationdate(commomform.getFinalfeedbackdate());
                }
                //msx 受理登记咨询单分派下级，部门设置到期时间
                if("01".equals(tasklist.getSystemsource()) && "01".equals(tasklist.getFormtype())){
                    tasklist.setBlqx(commomform.getAcceptdate());
                    tasklist.setExpiredate(commomform.getFinalfeedbackdate());
                    if("moveToLowerOrg".equals(nextNodeId) || "moveToLowerDept".equals(nextNodeId)){
                        System.out.println("55551");
//                        Date date = setSdexpirationdate(tasklist, worktype);
                        tasklist.setSdexpirationdate(commomform.getFinalfeedbackdate());
                    }
                }
                //msx 受理登记投诉单分派下级，部门设置到期时间
                if("01".equals(tasklist.getSystemsource()) && "02".equals(tasklist.getFormtype())){
                    if("moveToLowerOrg".equals(nextNodeId) || "moveToLowerDept".equals(nextNodeId) || "moveToLowerPersonnel".equals(nextNodeId)){
                        if(tasklist.getFeedback()==null){
                            tasklist.setSdexpirationdate(complaintform.getFeedbackdate());
                        }else if("1".equals(tasklist.getFeedback())){
                            tasklist.setSdexpirationdate(complaintform.getFeedbacktime());
                        }
                    }
                }
                //msx 受理登记举报单
                if("01".equals(tasklist.getSystemsource())&&"03".equals(tasklist.getFormtype())){
                    if("0".equals(tasklist.getIsaccept())){
                        tasklist.setSdexpirationdate(economyform.getFeedbackdate());
                    }else if("1".equals(tasklist.getFeedback()) && tasklist.getIsregistercase()==null){
                        tasklist.setSdexpirationdate(economyform.getFilingcasesdate());
                    }else if("是".equals(tasklist.getIsregistercase()) || "否".equals(tasklist.getIsregistercase())){
                        System.out.println("第一个");
                        tasklist.setSdexpirationdate(economyform.getFinalfeedbackdate());
                    }
                }
                tasklist.setSenderid(aicUser.getPrimaryKey());
                tasklist.setRegionid(aicUser.getRegionID());//TODO 相关机关
                //区局保存部门名称
                if ("440681".equals(aicUser.getRegionID())) {
                    handledeptname = aicUser.getUnitName();
                    tasklist.setHandledeptid(aicUser.getUnitID());
                } else {
                    handledeptname = aicUser.getOrgName();
                }
                tasklist.setHandledeptname(handledeptname);
                tasklist.setUpdatedate(new Date());
                //增加工单处理开始时间 2019.12.6
                if (BaseDataUtil.WORKFLOW_NODE_INTERIMREPLY.equals(nodeId)) {
                    tasklist.setTaskstartdate(new Date());
                }
                tasklistMapper.updateById(tasklist);
                needOpinion.setHandler(aicUser.getName());//受理保存人员名称
                needOpinion.setHandledept(aicUser.getOrgName());//保存部门名称
            } else {
                tasklist = CreateEntityInfoUtil.createTasklist(aicUser);
                tasklist.setTserialnum(aicUser.getRegionID().substring(0, 6) + uuidUtil.getCurAcceptNo());
                tasklist.setCaseguid(caseguid);
                tasklist.setLabelname(labelname);
                tasklist.setFocus(focus);
                tasklist.setRqsttitle(rqsttitle);
                tasklist.setAccordtype(accordtype);
                tasklist.setFormtype(BaseDataUtil.getFormType(formType));
                tasklist.setBustype(BaseDataUtil.getBusType(tasklist.getFormtype()));//业务类型
                tasklist.setOperate(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
                tasklist.setNextnodeid(nextNodeId);
                tasklist.setFromnodeid(nodeId);
                tasklist.setWorktype(worktype);
                tasklist.setIsregistercase(isregistercase);
                tasklist.setIscbdf("0");
                responseMap.put("tserialnum",tasklist.getTserialnum());//受理登记直接分派下级和部门发短信要用到此工单号
                tasklist.setIsaccept("0"); //受理初始状态为0
                if ("accept".equals(nextNodeId) || "registerCase".equals(nextNodeId) || "noRegisterCase".equals(nextNodeId)) {
                    //12345工单受理、是否立案、处理反馈环节需要增加时限控制
                    if ("accept".equals(nextNodeId)) {
                        //如果下一步处理是受理，将是否受理标志改成1（已受理）
                        tasklist.setIsaccept("1");
                        //增加处理机关 2019.12.13
                        tasklist.setHandleregion(aicUser.getRegionID());
                        //12345消费投诉工单受理更新到期时间
                        if ("02".equals(tasklist.getSystemsource())) {
                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                            tasklist.setSdexpirationdate(date);
                            tasklistMapper.updateById(tasklist);
                        }
                        if("01".equals(tasklist.getSystemsource())&&"01".equals(tasklist.getFormtype())){
//                            tasklist.setBlqx(commomform.getAcceptdate());
//                            Date date=updateSdexpirationdate(tasklist,formType,nextNodeId);
                            tasklist.setSdexpirationdate(commomform.getFinalfeedbackdate());
                            tasklistMapper.updateById(tasklist);
                        }
                        //受理登记投诉受理后到期时间
                        if("01".equals(tasklist.getSystemsource())&&"02".equals(tasklist.getFormtype())){
//                            System.out.println("jfjf");
//                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                            tasklist.setSdexpirationdate(complaintform.getFeedbackdate());
                            tasklistMapper.updateById(tasklist);
                        }
                        //受理登记举报受理后
                        if("01".equals(tasklist.getSystemsource())&&"03".equals(tasklist.getFormtype())){
                            tasklist.setSdexpirationdate(economyform.getFeedbackdate());
                            tasklistMapper.updateById(tasklist);
                        }

                    }
                    if ("registerCase".equals(nextNodeId) || "noRegisterCase".equals(nextNodeId)) {
                        //12345经济违法工单立案更新到期时间
                        if ("02".equals(tasklist.getSystemsource())) {
                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                            tasklist.setSdexpirationdate(date);
                            tasklistMapper.updateById(tasklist);
                        }
                        //12315立案后的到期时间以获取的12315系统到期时间为准
                        if ("03".equals(tasklist.getSystemsource()) && "registerCase".equals(nextNodeId)) {
                            if ("complaintmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(complaintform.getTodealwithtime());
                            }
                            if ("comeconomicmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(economyform.getTodealwithtime());
                            }
                        }
                        //受理登记举报单立案后添加到期时间
                        if("01".equals(tasklist.getSystemsource()) && "03".equals(tasklist.getFormtype())){
                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                            tasklist.setSdexpirationdate(date);
                            tasklistMapper.updateById(tasklist);
                        }
                    } else {
                        if ("03".equals(tasklist.getSystemsource())) {
                            if ("complaintmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(complaintform.getTodealwithtime());
                            }
                            if ("comeconomicmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(economyform.getTodealwithtime());
                            }
                        } else {
//                        Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
//                        tasklist.setSdexpirationdate(date);
                        }
                    }
                }

                //受理登记咨询直接分派下级时，需要设置到期时间
                if ("01".equals(tasklist.getSystemsource()) && "moveToLowerOrg".equals(nextNodeId) && !"1".equals(tasklist.getIsaccept())&&"01".equals(tasklist.getFormtype())) {
//                    tasklist.setBlqx(commomform.getAcceptdate());
//                    Date date = setSdexpirationdate(tasklist, worktype);
                    tasklist.setSdexpirationdate(commomform.getFinalfeedbackdate());
                }
                //msx 受理登记咨询单分派下级，部门设置到期时间
                if("01".equals(tasklist.getSystemsource()) && "01".equals(tasklist.getFormtype())){
                    System.out.println("2316");
                    tasklist.setBlqx(commomform.getAcceptdate());
                    tasklist.setExpiredate(commomform.getFinalfeedbackdate());
                    if("moveToLowerOrg".equals(nextNodeId) || "moveToLowerDept".equals(nextNodeId)){
//                        System.out.println("5555");
//                        Date date = setSdexpirationdate(tasklist, worktype);
                        tasklist.setSdexpirationdate(commomform.getFinalfeedbackdate());
                    }
                }
                //msx 受理登记投诉单分派下级，部门,人员设置到期时间
                if("01".equals(tasklist.getSystemsource()) && "02".equals(tasklist.getFormtype())){
                    if("moveToLowerOrg".equals(nextNodeId) || "moveToLowerDept".equals(nextNodeId)){
                        System.out.println("89090");
                        tasklist.setSdexpirationdate(complaintform.getFeedbackdate());
                    }else if("1".equals(tasklist.getIsaccept()) && "1".equals(tasklist.getFeedback())){
                        tasklist.setSdexpirationdate(complaintform.getFeedbacktime());
                    }
                }

                //msx 受理登记举报单
                if("01".equals(tasklist.getSystemsource())&&"03".equals(tasklist.getFormtype())){
                    if("0".equals(tasklist.getIsaccept())){
                        System.out.println("8989898");
                        tasklist.setSdexpirationdate(economyform.getFeedbackdate());
                    }else if("1".equals(tasklist.getFeedback()) && tasklist.getIsregistercase()==null){
                        tasklist.setSdexpirationdate(economyform.getFilingcasesdate());
                    }else if("是".equals(tasklist.getIsregistercase()) || "否".equals(tasklist.getIsregistercase())){
                        System.out.println("第二个");
                        tasklist.setSdexpirationdate(economyform.getFinalfeedbackdate());
                    }
                }


                tasklist.setSenderid(aicUser.getPrimaryKey());
                //区局保存部门名称
                if ("440681".equals(aicUser.getRegionID())) {
                    handledeptname = aicUser.getUnitName();
                    tasklist.setHandledeptid(aicUser.getUnitID());
                } else {
                    handledeptname = aicUser.getOrgName();
                }
                if("01".equals(tasklist.getSystemsource())){
                    if ("complaintmodule".equals(formType)) {
                        tasklist.setComplainanted(complaintform.getSubjectname());
                    }else if ("comeconomicmodule".equals(formType)) {
                        tasklist.setComplainanted(economyform.getSubjectname());
                    }else {
                        tasklist.setComplainanted(commomform.getEntname());
                    }
                }
                tasklist.setHandledeptname(handledeptname);
                tasklistMapper.insert(tasklist);
                needOpinion.setHandler(aicUser.getName());//保存人员名称
                needOpinion.setHandledept(aicUser.getOrgName());//保存部门名称
            }
        }else{
            //取最后最新的一条数据 msx
            Handledemand handledemand =handledemandList.get(handledemandList.size()-1);
            if (tasklist != null) {
                senderid = tasklist.getSenderid();
                tasklist.setLabelname(labelname);
                tasklist.setFocus(focus);
                tasklist.setRqsttitle(rqsttitle);
                tasklist.setAccordtype(accordtype);
                tasklist.setFormtype(BaseDataUtil.getFormType(formType));
                tasklist.setOperate(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
                tasklist.setIssignin("0");
                tasklist.setNextnodeid(nextNodeId);
                tasklist.setFromnodeid(nodeId);
                tasklist.setWorktype(worktype);
                tasklist.setIsregistercase(isregistercase);
                tasklist.setIscbdf("0");
                //12345工单受理、是否立案、处理反馈环节需要增加时限控制
                if ("accept".equals(nextNodeId) || "registerCase".equals(nextNodeId) || "noRegisterCase".equals(nextNodeId)) {
                    if ("accept".equals(nextNodeId)) {
                        //如果下一步处理是受理，将是否受理标志改成1（已受理）
                        tasklist.setIsaccept("1");
                        tasklist.setTaskstartdate(acceptTime);
                        //12345消费投诉工单受理更新到期时间
                        if ("02".equals(tasklist.getSystemsource())) {
                            if("03".equals(tasklist.getFormtype())){
//                            Handledemand handledemand = (Handledemand) RequestUtil.mapToEntityTwo(Handledemand.class, requestMap.get("handledemand"));
                                tasklist.setSdexpirationdate(handledemand.getCaseputtime());
                                tasklistMapper.updateById(tasklist);
                            }else {
                                Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                                tasklist.setSdexpirationdate(date);
                                handledemand.setResulttime(date);
                                handledemandMapper.updateById(handledemand);
                            }
                        }
                        if("01".equals(tasklist.getSystemsource()) && "03".equals(tasklist.getFormtype())){
                            tasklist.setSdexpirationdate(economyform.getFilingcasesdate());//受理登记受理后改变到期时间
                        }
                        if("01".equals(tasklist.getSystemsource()) && "01".equals(tasklist.getFormtype())){
                            System.out.println("咨询受理受理");
                        }
                    }
                    if ("registerCase".equals(nextNodeId) || "noRegisterCase".equals(nextNodeId)) {
                        //12345经济违法工单立案更新到期时间
                        if ("02".equals(tasklist.getSystemsource())) {
                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                            tasklist.setSdexpirationdate(date);
                            tasklistMapper.updateById(tasklist);
                            handledemand.setResulttime(date);
                            handledemandMapper.updateById(handledemand);
                        }
                        //12345添加立案时间
                        if ("02".equals(tasklist.getSystemsource())) {
                            tasklist.setFilingdate(filingTime);
                        }
                        //12315立案后的到期时间以获取的12315系统到期时间为准
                        if ("03".equals(tasklist.getSystemsource()) && "registerCase".equals(nextNodeId)) {
                            tasklist.setSdexpirationdate(economyform.getTodealwithtime());
                        }
                        //受理登记添加立案时间
                        if ("01".equals(tasklist.getSystemsource()) && "registerCase".equals(nextNodeId)) {
                            System.out.println("jkljkl");
                            tasklist.setFilingdate(filingTime);//受理登记添加立案时间
                            tasklist.setSdexpirationdate(economyform.getFinalfeedbackdate());//受理登记添加最终反馈时间
                        }
                    } else {
                        if ("03".equals(tasklist.getSystemsource())) {
                            if ("complaintmodule".equals(formType)) {
                                //msx 1215投诉受理后到期时间
                                Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                                tasklist.setSdexpirationdate(date);
                            }
                            if ("comeconomicmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(economyform.getTodealwithtime());
                            }
                        } else {
//                        Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
//                        tasklist.setSdexpirationdate(date);
                        }
                    }
                }
                //受理登记直接分派下级时，需要设置到期时间
                if ("01".equals(tasklist.getSystemsource()) && "moveToLowerOrg".equals(nextNodeId) && !"1".equals(tasklist.getIsaccept())) {
                    Date date = setSdexpirationdate(tasklist, worktype);
                    tasklist.setSdexpirationdate(date);
                }
                tasklist.setSenderid(aicUser.getPrimaryKey());
                tasklist.setRegionid(aicUser.getRegionID());//TODO 相关机关
                //区局保存部门名称
                if ("440681".equals(aicUser.getRegionID())) {
                    handledeptname = aicUser.getUnitName();
                    tasklist.setHandledeptid(aicUser.getUnitID());
                } else {
                    handledeptname = aicUser.getOrgName();
                }
                tasklist.setHandledeptname(handledeptname);
                tasklist.setUpdatedate(new Date());
                //增加工单处理开始时间 2019.12.6
                if (BaseDataUtil.WORKFLOW_NODE_INTERIMREPLY.equals(nodeId)) {
                    tasklist.setTaskstartdate(new Date());
                }
                tasklistMapper.updateById(tasklist);
                needOpinion.setHandler(aicUser.getName());//受理保存人员名称
                needOpinion.setHandledept(aicUser.getOrgName());//保存部门名称
            } else {
                tasklist = CreateEntityInfoUtil.createTasklist(aicUser);
                tasklist.setTserialnum(aicUser.getRegionID().substring(0, 6) + uuidUtil.getCurAcceptNo());
                tasklist.setCaseguid(caseguid);
                tasklist.setLabelname(labelname);
                tasklist.setFocus(focus);
                tasklist.setRqsttitle(rqsttitle);
                tasklist.setAccordtype(accordtype);
                tasklist.setFormtype(BaseDataUtil.getFormType(formType));
                tasklist.setBustype(BaseDataUtil.getBusType(tasklist.getFormtype()));//业务类型
                tasklist.setOperate(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
                tasklist.setNextnodeid(nextNodeId);
                tasklist.setFromnodeid(nodeId);
                tasklist.setWorktype(worktype);
                tasklist.setIsregistercase(isregistercase);
                if ("accept".equals(nextNodeId) || "registerCase".equals(nextNodeId) || "noRegisterCase".equals(nextNodeId)) {
                    //12345工单受理、是否立案、处理反馈环节需要增加时限控制
                    if ("accept".equals(nextNodeId)) {
                        //如果下一步处理是受理，将是否受理标志改成1（已受理）
                        tasklist.setIsaccept("1");
                        //增加处理机关 2019.12.13
                        tasklist.setHandleregion(aicUser.getRegionID());
                        //12345消费投诉工单受理更新到期时间
                        if ("02".equals(tasklist.getSystemsource())) {
                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                            tasklist.setSdexpirationdate(date);
                            tasklistMapper.updateById(tasklist);
                        }
                    }
                    if ("registerCase".equals(nextNodeId) || "noRegisterCase".equals(nextNodeId)) {
                        //12345经济违法工单立案更新到期时间
                        if ("02".equals(tasklist.getSystemsource())) {
                            Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
                            tasklist.setSdexpirationdate(date);
                            tasklistMapper.updateById(tasklist);
                        }
                        //12315立案后的到期时间以获取的12315系统到期时间为准
                        if ("03".equals(tasklist.getSystemsource()) && "registerCase".equals(nextNodeId)) {
                            if ("complaintmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(complaintform.getTodealwithtime());
                            }
                            if ("comeconomicmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(economyform.getTodealwithtime());
                            }
                        }
                    } else {
                        if ("03".equals(tasklist.getSystemsource())) {
                            if ("complaintmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(complaintform.getTodealwithtime());
                            }
                            if ("comeconomicmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(economyform.getTodealwithtime());
                            }
                        } else {
//                        Date date = updateSdexpirationdate(tasklist, formType, nextNodeId);
//                        tasklist.setSdexpirationdate(date);
                        }
                    }
                }
                //受理登记直接分派下级时，需要设置到期时间
                if ("01".equals(tasklist.getSystemsource()) && "moveToLowerOrg".equals(nextNodeId) && !"1".equals(tasklist.getIsaccept())) {
                    Date date = setSdexpirationdate(tasklist, worktype);
                    tasklist.setSdexpirationdate(date);
                }
                tasklist.setSenderid(aicUser.getPrimaryKey());
                //区局保存部门名称
                if ("440681".equals(aicUser.getRegionID())) {
                    handledeptname = aicUser.getUnitName();
                    tasklist.setHandledeptid(aicUser.getUnitID());
                } else {
                    handledeptname = aicUser.getOrgName();
                }
                if("01".equals(tasklist.getSystemsource())){
                    if ("complaintmodule".equals(formType)) {
                        tasklist.setComplainanted(complaintform.getSubjectname());
                    }else if ("comeconomicmodule".equals(formType)) {
                        tasklist.setComplainanted(economyform.getSubjectname());
                    }else {
                        tasklist.setComplainanted(commomform.getEntname());
                    }
                }
                tasklist.setHandledeptname(handledeptname);
                tasklistMapper.insert(tasklist);
                needOpinion.setHandler(aicUser.getName());//保存人员名称
                needOpinion.setHandledept(aicUser.getOrgName());//保存部门名称
            }
        }
        //流程表记录 先更新，后插入
        Processinfo processinfo;
        QueryWrapper<Processinfo> proWrapper = new QueryWrapper<>();
        proWrapper.lambda().eq(Processinfo::getCaseguid, caseguid);
        proWrapper.lambda().eq(Processinfo::getNodeid, nodeId);
        proWrapper.lambda().eq(Processinfo::getStatus, "0");
        proWrapper.lambda().eq(Processinfo::getIsmain, "1");
        List<Processinfo> processinfoList = processinfoMapper.selectList(proWrapper);
        if (processinfoList.size() > 0) {
            for (Processinfo processinfos : processinfoList) {
                processinfos.setStatus("1");
                processinfos.setUpdatedate(new Date());
                processinfos.setEnddate(new Date());
                processinfoMapper.updateById(processinfos);
            }

        }
        //分配下级单位需要分配多个人处理
        List<AICUser> userList = null;
        UpdateWrapper<Processinfo> updateWrapper = new UpdateWrapper<>();
        if ("moveToLowerOrg".equals(nextNodeId)) {
            //查询待分配机关有权限的用户列表
            userList = orgUM.findUsersByOrgAndPermission(orgid, "subordinate");
            if (userList != null) {
                for (AICUser user : userList) {
                    processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, user);
                    processinfo.setNodeid(nextNodeId);
                    processinfo.setFromnodeid(nodeId);
                    processinfo.setIsmain("1");//分派下级机关，下级机关的人员可以处理业务
                   // processinfo.setUpdatedate(new Date());
                    processinfoMapper.insert(processinfo);
                    //增加处理机关 2019.10.22
                    tasklist.setHandleregion(user.getRegionID());
                    //区局保存部门名称，分局保存分局名称 2019.11.28
                    if ("440681".equals(user.getRegionID())) {
                        handledeptname = user.getUnitName();
                        tasklist.setHandledeptid(user.getUnitID());
                    } else {
                        handledeptname = user.getOrgName();
                    }
                    //12315分派区级部门时修改到期时间
                    if ("03".equals(tasklist.getSystemsource()) && !"1".equals(tasklist.getIsaccept())) {
                        if ("complaintmodule".equals(formType)) {
                            tasklist.setSdexpirationdate(complaintform.getFeedbacktime());
                        }
                        if ("comeconomicmodule".equals(formType)) {
                            tasklist.setSdexpirationdate(economyform.getFeedbacktime());
                        }
                    }
                    //12345分派区级部门时间修改到期时间2020.01.02 msx
                    if ("02".equals(tasklist.getSystemsource()) && !"1".equals(tasklist.getIsaccept())) {
                        if ("complaintmodule".equals(formType)) {
                            tasklist.setSdexpirationdate(complaintform.getFeedbacktime());
                            System.out.println("00000");
                        }
                        if ("comeconomicmodule".equals(formType)) {
                            tasklist.setSdexpirationdate(economyform.getFeedbacktime());
                        }
                    }
                    tasklist.setAssigndate(new Date());//分派下级将分派的时间改为交办时间
                    tasklist.setHandledeptname(handledeptname);
                    opinion.setHandler(handledeptname);//分派下级保存分派的分局名称
                    opinion.setHandledept(handledeptname);
                    needOpinion.setHandler(handledeptname);//分派下级保存分派的分局名称
                    needOpinion.setHandledept(handledeptname);
                }
            }
            if (deptids != null) {
                for (int i = 0; i < deptids.size(); i++) {
                    //查看部门
                    userList = orgUM.findNodeUsers((String) deptids.get(i), "dealdepartment");
                    if (userList != null) {
                        for (AICUser user : userList) {
                            processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, user);
                            processinfo.setNodeid(nextNodeId);
                            processinfo.setFromnodeid(nodeId);
                            processinfoMapper.insert(processinfo);
                        }
                    }
                }
            }
        }
        //分配其他部门
        else if ("moveToLowerDept".equals(nextNodeId)) {
            //增加处理部门 2019.10.22
            tasklist.setHandledeptid(deptid);
            tasklist.setHandleregion(aicUser.getRegionID()); //增加处理机构id
            //查询该部门下有对应权限的用户列表
            userList = orgUM.findNodeUsers(deptid, "dealdepartment");
            if (userList != null) {
                for (AICUser user : userList) {
                    processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, user);
                    processinfo.setNodeid(nextNodeId);
                    processinfo.setFromnodeid(nodeId);
                    processinfo.setIsmain("1");//处理部门
                    processinfo.setUpdatedate(new Date());
                    processinfoMapper.insert(processinfo);
                    //区局保存部门名称，分局保存分局名称 2019.11.28
                    if ("440681".equals(user.getRegionID())) {
                        //区局部门新派单短信提醒
//                        String newcontent="%D0%C2%C5%C9%B5%A5%A3%BA%CB%B3%B5%C2%C7%F8%CA%D0%B3%A1%BC%E0%B6%BD%B9%DC%C0%ED%BE%D6%CF%FB%B7%D1%CE%AC%C8%A8%D2%B5%CE%F1%C8%DA%BA%CF%CF%B5%CD%B3%B7%D6%C5%C9%B9%A4%B5%A5"+tasklist.getSerialnum()+"%C7%EB%D3%DA22%D0%A1%CA%B1%C4%DA%CF%EC%D3%A6%B2%A2%B0%B4%B9%E6%B6%A8%CA%B1%BC%E4%B4%F0%B8%B4%A1%A3%A3%A8%C8%E7%D3%F6%BD%DA%BC%D9%C8%D5%CB%B3%D1%D3%BC%C6%CB%E3%A3%A9";
//                        String mType="97";
//                        String mPassword="v$HzWxpuiiNa6TmC";
//                        //根据userid查询号码
//                        String mobile = usersappMapper.selectMobile(user.getPrimaryKey());
//                        String relateDocUuid= user.getPrimaryKey();
//                        String phones= mobile+";"+ "W" +";"+ "1";
//                        //短信接口参数
//                        messagemap.put("mType",mType);
//                        messagemap.put("mPassword",mPassword);
//                        messagemap.put("phones",phones);
//                        messagemap.put("content",newcontent);
//                        messagemap.put("relateDocUuid",relateDocUuid);
//                        //记录短信
//                        message.setId(UuidUtil.getUuid());
//                        message.setCaseguid(tasklist.getCaseguid());
//                        message.setFormtype(tasklist.getBustype());
//                        message.setMessagetype("0");
//                        message.setMobile(mobile);
//                        message.setReceivetime(new Date());
//                        message.setUnitname(user.getUnitName());
//                        message.setName(user.getName());
//                        message.setEname(user.getEname());
//                        message.setUserid(user.getPrimaryKey());
//                        message.setSdexpirationdate(tasklist.getSdexpirationdate());
//                        message.setSerialnum(tasklist.getSerialnum());
//                        message.setContent(URLDecoder.decode(newcontent, "GBK"));
//                        message.setSystemsource(tasklist.getSystemsource());
//                        if(!"01".equals(tasklist.getSystemsource())){
////                                String result= MessageUtil.sendMessage("http://61.142.131.10:888/OaListenerServlet", messagemap);
//                            String result="Http Request is accept.";
//                            if("Http Request is accept.".equals(result)) {
//                                    message.setStatus("0"); //发送成功
//                                    messageMapper.insert(message);
//                            }else{
//                                    message.setStatus("1"); //发送失败
//                                    messageMapper.insert(message);
//                                }
//
//                        }else{
//
//                        }
                        handledeptname = user.getUnitName();
                        tasklist.setHandledeptid(user.getUnitID());
                        tasklist.setAssigndate(new Date());
                        if ("03".equals(tasklist.getSystemsource()) && !"1".equals(tasklist.getIsaccept())) {
                            if ("complaintmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(complaintform.getFeedbacktime());
                            }
                            if ("comeconomicmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(economyform.getFeedbacktime());
                            }
                        }
                        //12345分派其它部门时间修改到期时间2020.01.02 msx
                        if ("02".equals(tasklist.getSystemsource()) && !"1".equals(tasklist.getIsaccept())) {
                            if ("complaintmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(complaintform.getFeedbacktime());
                            }
                            if ("comeconomicmodule".equals(formType)) {
                                tasklist.setSdexpirationdate(economyform.getFeedbacktime());
                            }
                        }
                        //受理登记分派部门设置到期时间 msx
                        if("01".equals(tasklist.getSystemsource())){
                            System.out.println("8u8u");
                            Date date=setSdexpirationdate(tasklist,nextNodeId);
                            tasklist.setSdexpirationdate(date);
                            //tasklistMapper.updateById(tasklist);
                        }
                    } else {
                        handledeptname = user.getUnitName();
                    }

                }

                tasklist.setHandledeptname(handledeptname);
                opinion.setHandler(handledeptname);//分派部门保存分派的部门名称
                opinion.setHandledept(handledeptname);
                needOpinion.setHandler(handledeptname);//分派部门保存分派的部门名称
                needOpinion.setHandledept(handledeptname);
            }
            if (deptids != null) {
                for (int i = 0; i < deptids.size(); i++) {
                    //查看部门
                    userList = orgUM.findNodeUsers((String) deptids.get(i), "dealdepartment");
                    if (userList != null) {
                        for (AICUser user : userList) {
                            processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, user);
                            processinfo.setNodeid(nextNodeId);
                            processinfo.setFromnodeid(nodeId);
                            processinfo.setUpdatedate(new Date());
                            processinfoMapper.insert(processinfo);
                        }
                    }
                }
            }

        }
        // 分配人员
        else if ("moveToLowerPersonnel".equals(nextNodeId) || "leaderApr".equals(nextNodeId)
           || "collaborative".equals(nextNodeId)   || "applyDelay".equals(nextNodeId) || "regionAudit".equals(nextNodeId)) {
            if(!"collaborative".equals(nextNodeId)){
                aicUser = orgUM.getUserInfo(deptid);
            }
            processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
            processinfo.setNodeid(nextNodeId);
            processinfo.setFromnodeid(nodeId);
            processinfo.setIsmain("1");//处理人员
            processinfo.setUpdatedate(new Date());
            processinfoMapper.insert(processinfo);
            if (deptids != null) {
                for (int i = 0; i < deptids.size(); i++) {
                    aicUser = orgUM.findUserByPrimaryKey((String) deptids.get(i));
                    processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
                    processinfo.setNodeid(nextNodeId);
                    processinfo.setFromnodeid(nodeId);
                    processinfo.setUpdatedate(new Date());
                    processinfoMapper.insert(processinfo);
                }
            }
            //区局保存部门名称，分局保存分局名称 2019.11.28
            if ("440681".equals(aicUser.getRegionID())) {
                handledeptname = aicUser.getUnitName();
                tasklist.setHandledeptid(aicUser.getUnitID());
            } else {
                handledeptname = aicUser.getName();
            }
            tasklist.setHandledeptname(handledeptname);
            opinion.setHandler(aicUser.getName());//保存处理人员或区局人员名称
            opinion.setHandledept(aicUser.getName());
            needOpinion.setHandler(aicUser.getName());//保存处理人员或区局人员名称
            needOpinion.setHandledept(handledeptname);
        } else if (("backForward".equals(nextNodeId) && !"1".equals(requestMap.get("isEnd")))
                || ("noAccept".equals(nextNodeId) && !"1".equals(requestMap.get("isEnd")))) {
            aicUser = orgUM.getUserInfo(senderid);
            processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
            processinfo.setNodeid(nextNodeId);
            processinfo.setFromnodeid(nodeId);
            processinfo.setIsmain("1");//处理人员
            processinfo.setUpdatedate(new Date());
            processinfoMapper.insert(processinfo);
            //区局保存部门名称，分局保存分局名称 2019.11.28
            if ("440681".equals(aicUser.getRegionID())) {
                handledeptname = aicUser.getUnitName();
                tasklist.setHandledeptid(aicUser.getUnitID());
            } else {
                handledeptname = aicUser.getOrgName();
            }
            tasklist.setHandledeptname(handledeptname);
            opinion.setHandler(aicUser.getName());//保存接受人员名称
            opinion.setHandledept(handledeptname);//保存部门名称
            needOpinion.setHandler(aicUser.getName());//保存接受人员名称
            needOpinion.setHandledept(handledeptname);//保存部门名称
        } else if ("withdraw".equals(nextNodeId)) {//待办收回
            processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
            processinfo.setNodeid(nextNodeId);
            processinfo.setFromnodeid(nodeId);
            processinfo.setIsmain("1");//处理人员
            processinfo.setUpdatedate(new Date());
            processinfoMapper.insert(processinfo);
            //区局保存部门名称，2019.11.28
            tasklist.setHandledeptname(aicUser.getUnitName());
            tasklist.setHandledeptid(aicUser.getUnitID());
            opinion.setHandler(aicUser.getName());//工单收回保存人员名称
            opinion.setHandledept(aicUser.getOrgName());//保存部门名称
            needOpinion.setHandler(aicUser.getName());//工单收回保存人员名称
            needOpinion.setHandledept(aicUser.getOrgName());//保存部门名称
        } else {
            if ("superiorProcessing".equals(nextNodeId)) {
                aicUser = orgUM.findUserByPrimaryKey(senderid);
                //修改流程表记录状态
                updateWrapper.set("status", "1")
                        .eq("caseguid", caseguid)
                        .eq("status", "0");
                processinfoMapper.update(new Processinfo(), updateWrapper);
                //区局保存部门名称，分局保存分局名称 2019.11.28
                if ("440681".equals(aicUser.getRegionID())) {
                    handledeptname = aicUser.getUnitName();
                    tasklist.setHandledeptid(aicUser.getUnitID());
                } else {
                    handledeptname = aicUser.getOrgName();
                }
                tasklist.setHandledeptname(handledeptname);
            }
            processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
            processinfo.setNodeid(nextNodeId);
            processinfo.setFromnodeid(nodeId);
            processinfo.setIsmain("1");//处理人员
            processinfo.setUpdatedate(new Date());
            processinfoMapper.insert(processinfo);
            //如果是结束环节，将所有的流程环节状态改成1
            if ("1".equals(requestMap.get("isEnd")) || "end".equals(nextNodeId)) {
                if ("noAccept".equals(nextNodeId) || "backForward".equals(nextNodeId)) {
                    tasklist.setHandleregion(aicUser.getRegionID()); //增加处理机构id 2019.12.13
                }
                updateWrapper.set("status", "1")
                        .eq("caseguid", caseguid)
                        .eq("status", "0");
                processinfoMapper.update(new Processinfo(), updateWrapper);
                //增加工单处理结束时间 2019.12.6
                tasklist.setTaskenddate(new Date());
            }
        }
        if (!"1".equals(requestMap.get("isEnd")) && !"end".equals(nextNodeId)) {
            needOpinion.setId(UUID.randomUUID().toString());
            needOpinion.setCaseguid(caseguid);
            needOpinion.setOperate(BaseDataUtil.getNodeNameByNodeId(nextNodeId));//todo
            needOpinion.setNodename(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
            needOpinion.setHandleopinion("待处理");
            needOpinion.setHandletime(new Date());
            needOpinion.setUpdatedate(new Date());
            needOpinion.setStatus("0");
            opinionMapper.insert(needOpinion);//linyuB
        }
        if(StringUtils.isNotBlank(isnewassign) && "1".equals(isnewassign)){
            tasklist.setIsnewassign(isnewassign);
        }else {
            tasklist.setIsnewassign("0");
        }
        //set 处理部门    ----linyu   注意
        tasklistMapper.updateById(tasklist);
        responseMap.put("id", tasklist.getId());
        responseMap.put("caseguid", caseguid);
        responseMap.put("str", "true");

        //调用12345平台回填接口
        try {
            if (isOpen12345Backfill && tasklist != null && "02".equals(tasklist.getSystemsource())) {
                if (BaseDataUtil.WORKFLOW_NODE_MOVETOLOWERORG.equals(nextNodeId)) {
                    //分派下级
                    JSONArray orgTreeJSON = orgUM.getOrgTreeJSON(orgid);
                    backfillService.executeAssignSubordinate(currentHandleUser.getEname(), currentHandleUser.getRegionID(), tasklist.getCaseguid(), formType,
                            orgTreeJSON.getJSONObject(0).getString("text"), opinion.getHandleopinion());
                } else if (BaseDataUtil.WORKFLOW_NODE_REGISTERCASE.equals(nextNodeId)
                        || BaseDataUtil.WORKFLOW_NODE_NOREGISTERCASE.equals(nextNodeId)) {
                    //经济违法立案
                    backfillService.executeCaseFiling(currentHandleUser.getEname(), currentHandleUser.getRegionID(), tasklist.getCaseguid(), caseprocesform);
                } else if (BaseDataUtil.WORKFLOW_NODE_ACCEPT.equals(nextNodeId)) {
                    //受理
                    backfillService.executeAcceptance(currentHandleUser.getEname(), currentHandleUser.getRegionID(), tasklist.getCaseguid(), formType,
                            opinion.getHandleopinion());
                } else if (BaseDataUtil.WORKFLOW_NODE_NOACCEPT.equals(nextNodeId)) {
                    //不受理
                    backfillService.executeNotAcceptance(currentHandleUser.getEname(), currentHandleUser.getRegionID(), tasklist.getCaseguid(), formType,
                            noAcceptReason, opinion.getHandleopinion());
                } else if (BaseDataUtil.WORKFLOW_NODE_WITHDRAW.equals(nextNodeId)) {
                    //消保科收回，使用分局账号回填退回
                    //根据业务主键和节点等于分派下级的查询最新的一条记录，获取该工当原先分派那个分局了
                    QueryWrapper<Processinfo> pq = new QueryWrapper<>();
                    pq.lambda().eq(Processinfo::getCaseguid, tasklist.getCaseguid()).eq(Processinfo::getIsmain,"1")
                            .eq(Processinfo::getNodeid, BaseDataUtil.WORKFLOW_NODE_MOVETOLOWERORG).orderByDesc(Processinfo::getStartdate);
                    List<Processinfo> processinfos = processinfoMapper.selectList(pq);
                    if (processinfos != null && !processinfos.isEmpty()) {
                        Processinfo p = processinfos.get(0);
                        AICUser assignHandleUser = orgUM.findUserByPrimaryKey(p.getHandleruserid());
                        List<String> annexPaths = iMaterialinfoService.listLocalPathsAndUpdateBackfillStateByCaseguid(caseguid);
                        backfillService.executeSendBack(assignHandleUser.getEname(), assignHandleUser.getRegionID(), tasklist.getCaseguid(), formType, "其他原因",
                                opinion.getHandleopinion(), annexPaths);
                    }
                } else if (BaseDataUtil.WORKFLOW_NODE_BACKFORWARD.equals(nextNodeId) && "1".equals(requestMap.get("isEnd"))) {
                    //消保科退回
                    List<String> annexPaths = iMaterialinfoService.listLocalPathsAndUpdateBackfillStateByCaseguid(caseguid);
                    backfillService.executeSendBack(currentHandleUser.getEname(), currentHandleUser.getRegionID(), tasklist.getCaseguid(), formType, backReason,
                            opinion.getHandleopinion(), annexPaths);
                } else if (BaseDataUtil.WORKFLOW_NODE_APPLYDELAY.equals(nextNodeId)) {
                    //申请延期
                    backfillService.executeApplyExtension(currentHandleUser.getEname(), currentHandleUser.getRegionID(), tasklist.getCaseguid(), formType, opinion.getHandleopinion(),
                            (Integer) delayData.get("limit"), (String) delayData.get("unit"));
                } else if (BaseDataUtil.WORKFLOW_NODE_REGIONAUDIT.equals(nextNodeId) || BaseDataUtil.WORKFLOW_NODE_END.equals(nextNodeId)) {
                    boolean permissionB = true;
                    Map<String, AICPermission> aicPermissions = currentHandleUser.getAicPermissions();
                    if ("440681".equals(currentHandleUser.getRegionID())) {
                        //区局部门的账号，需要判断是否有权限回填
                        if (!aicPermissions.containsKey("bmfwDispatch")) {
                            permissionB = false;
                        }
                    }
                    if (permissionB) {
                        //处理反馈
                        List<String> annexPaths = iMaterialinfoService.listLocalPathsAndUpdateBackfillStateByCaseguid(caseguid);
                        if (BaseDataUtil.TASKLIST_FORMTYPE_COMMOMMODULE.equals(formType)) {
                            //通用表单
                            //判断是否领导审批的结果
                            String handleOpinion = BaseDataUtil.WORKFLOW_NODE_LEADERAPR.equals(nodeId)
                                    ? caseprocesform.getHandleopinion() : opinion.getHandleopinion();
                            backfillService.executeConsultationProcessFeedback(currentHandleUser.getEname(), currentHandleUser.getRegionID(), tasklist.getCaseguid(),
                                    handleOpinion, annexPaths);
                        } else if (BaseDataUtil.TASKLIST_FORMTYPE_COMPLAINTMODULE.equals(formType)) {
                            //消费投诉
                            backfillService.executeConsumptionProcessFeedback(currentHandleUser.getEname(), currentHandleUser.getRegionID(), tasklist.getCaseguid(), complaintform.getRegion(),
                                    caseprocesform, annexPaths);
                        } else {
                            //经济违法
                            backfillService.executeEconomyProcessFeedback(currentHandleUser.getEname(), currentHandleUser.getRegionID(), tasklist.getCaseguid(), economyform.getRegion(),
                                    caseprocesform, annexPaths);
                        }
                    }
                }
            } else if (isOpen12315Backfill && tasklist != null && "03".equals(tasklist.getSystemsource())) {
                //12315平台回填
                if (BaseDataUtil.WORKFLOW_NODE_APPLYDELAY.equals(nextNodeId)) {
                    //办结延期申请
                    backfillService.executeHandleExtensionApplication(opinion.getHandleopinion(), tasklist.getFormtype(), tasklist.getTserialnum());
                } else if (BaseDataUtil.WORKFLOW_NODE_ACCEPT.equals(nextNodeId)) {
                    //受理
                    backfillService.executePreliminaryFeedback(currentHandleUser.getEname(), tasklist, opinion.getHandleopinion(), noAcceptReason);
//                    //todo 调用12315回填12345
//                    if (BaseDataUtil.TASKLIST_FORMTYPE_COMPLAINTMODULE.equals(formType)) {
//                        // 投诉
//                        backfillService.executeComplaintFormSwitch(currentHandleUser.getEname(),currentHandleUser.getRegionID(),complaintform,tasklist,caseprocesform,opinion.getHandleopinion());
//                    } else {
//                        //举报
//                        backfillService.executeEconomyFormSwitch(currentHandleUser.getEname(),currentHandleUser.getRegionID(),tasklist,economyform,caseprocesform,opinion.getHandleopinion());
//                    }
                } else if (BaseDataUtil.WORKFLOW_NODE_NOACCEPT.equals(nextNodeId)) {
                    //不受理
                    backfillService.executePreliminaryFeedback(currentHandleUser.getEname(), tasklist, opinion.getHandleopinion(), noAcceptReason);

                } else if (BaseDataUtil.WORKFLOW_NODE_END.equals(nextNodeId) || "1".equals(requestMap.get("isEnd"))) {
                    //办结反馈
                    if (BaseDataUtil.TASKLIST_FORMTYPE_COMPLAINTMODULE.equals(formType)) {
                        // 投诉
                        backfillService.executeComplainFinishingFeedback(currentHandleUser.getEname(), tasklist, complaintform, opinion, caseprocesform);
                    } else {
                        //举报
                        backfillService.executeReportFinishingFeedback(currentHandleUser.getEname(), tasklist, economyform, opinion, caseprocesform);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("回填调用失败!", e);
        }
        return responseMap;
    }

    /**
     * 受理环节后重新设置到期时间
     *
     * @param tasklist
     * @param formType
     * @param nextnodeid
     * @return Date
     */
    public Date updateSdexpirationdate(Tasklist tasklist, String formType, String nextnodeid) throws ParseException {
        Calendar sdexpirationdate = Calendar.getInstance();
        Date workDay = null;
        Date dateOne = tasklist.getFirstblqx();



        if (tasklist.getAssigndate() != null) {
            if ("02".equals(tasklist.getSystemsource())) {
                if ("accept".equals(tasklist.getNextnodeid()) || "registerCase".equals(tasklist.getNextnodeid()) ||  "noRegisterCase".equals(tasklist.getNextnodeid())) {
                    sdexpirationdate.setTime(new Date());
                }
            } else if ("03".equals(tasklist.getSystemsource())){
                sdexpirationdate.setTime(tasklist.getFirstblqx());
            }else {
                sdexpirationdate.setTime(tasklist.getAssigndate());
                //受理登记举报单立案或不立案后到期时间
                if ("registerCase".equals(tasklist.getNextnodeid()) || "noRegisterCase".equals(tasklist.getNextnodeid()) ||  "noRegisterCase".equals(tasklist.getNextnodeid())) {
                    workDay =CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 90, sdexpirationdate.getTime().getHours(),
                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                    System.out.println("nnnnn"+workDay);
                }
                //举报单受理后的应立案时间
                if("accept".equals(tasklist.getNextnodeid()) && !"是".equals(tasklist.getIsregistercase()) && "03".equals(tasklist.getFormtype())){ //举报单受理后15天要立案
                    workDay =CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 15, sdexpirationdate.getTime().getHours(),
                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                }
            }

//            if ("03".equals(tasklist.getSystemsource())) {//linyu测试
//                Date workDayOne = null;
//                if ("accept".equals(tasklist.getNextnodeid()) &&  "complaintmodule".equals(formType)) {
//                    Date date = tasklist.getFirstblqx();
//                    workDayOne =CreateEntityInfoUtil.getWorkDay(date, 45, sdexpirationdate.getTime().getHours(),
//                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
//                    workDay = workDayOne;
//                }else if ("registerCase".equals(tasklist.getNextnodeid()) && "comeconomicmodule".equals(formType)){
//                    Date date = tasklist.getFirstblqx();
//                    Calendar ca = Calendar.getInstance();
//                    ca.add(Calendar.DATE, 90);
//                    date = ca.getTime();
//                    workDay = date ;
//                }else if ("noRegisterCase".equals(tasklist.getNextnodeid())){
//                    sdexpirationdate.setTime(tasklist.getAssigndate());
//                }
//            } else {
//                sdexpirationdate.setTime(tasklist.getAssigndate());
//            }

            if (tasklist.getWorktype() != null && "紧急".equals(tasklist.getWorktype()) && "02".equals(tasklist.getSystemsource())) {
                if ("handleFeedback".equals(nextnodeid) || "registerCase".equals(nextnodeid) || "noRegisterCase".equals(nextnodeid)) {//处理反馈和是否立案要在一个工作日内处理
                    workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 1, sdexpirationdate.getTime().getHours(),
                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                } else {
                    sdexpirationdate.set(Calendar.MINUTE, sdexpirationdate.get(Calendar.MINUTE) + 30);
                    workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 0, sdexpirationdate.getTime().getHours(),
                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                }
            } else {
                if ("commommodule".equals(formType) && "02".equals(tasklist.getSystemsource())) {//通用表单和初步答复环节
                    sdexpirationdate.set(Calendar.HOUR, sdexpirationdate.get(Calendar.HOUR) + 22);
                    workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 0, sdexpirationdate.getTime().getHours(),
                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                } else if ("02".equals(tasklist.getSystemsource())){
                    int day = 0;
                    if ("complaintmodule".equals(formType)) {//消费投诉
                        if ("accept".equals(nextnodeid)) {
                            day = 20;
                        }
                    }
                    if ("comeconomicmodule".equals(formType)) {//经济违法
//                        if ("accept".equals(nextnodeid)) {
                        if ("registerCase".equals(tasklist.getNextnodeid()) ||  "noRegisterCase".equals(tasklist.getNextnodeid())) {
                            day = 60;
                        }
                    }
                    workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), day, sdexpirationdate.getTime().getHours(),
                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                }else if("03".equals(tasklist.getSystemsource())){//linyu测试
                    Date workDayOne = null;
                    if ("accept".equals(tasklist.getNextnodeid()) &&  "complaintmodule".equals(formType)) {
//                        Date date = tasklist.getFirstblqx();
                        workDayOne =CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 45, sdexpirationdate.getTime().getHours(),
                                sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                        workDay = workDayOne;
                    }else if ("accept".equals(tasklist.getNextnodeid()) && "comeconomicmodule".equals(formType)){
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String date = format.format(dateOne);
                        dateOne = format.parse(date);
                        Calendar ca = Calendar.getInstance();
                        ca.setTime(dateOne);
                        ca.add(Calendar.DATE, 90);
//                        ca.
                        dateOne = ca.getTime();
                        workDay = dateOne ;
                    }else if ("noRegisterCase".equals(tasklist.getNextnodeid())){
//                        sdexpirationdate.setTime(tasklist.getAssigndate());
                    }
                    //受理登记咨询单分派人员前初步反馈时间5天到期时间
                }else if("commommodule".equals(formType) && "01".equals(tasklist.getSystemsource())&& "1".equals(tasklist.getIsaccept())){//msx 咨询受理登记最终反馈时间
                    Calendar comsdexpirationdate = Calendar.getInstance();
                    Date commomacceptdate = tasklist.getBlqx();
                    comsdexpirationdate.setTime(commomacceptdate);
                    Date DateOne =CreateEntityInfoUtil.getWorkDay(comsdexpirationdate.getTime(), 5, comsdexpirationdate.getTime().getHours(),
                            comsdexpirationdate.getTime().getSeconds(), comsdexpirationdate.getTime().getMinutes());
                    workDay = DateOne;
                }
                    //受理登记投诉表单受理后增加45天到期时间
                else if("complaintmodule".equals(formType) && "01".equals(tasklist.getSystemsource()) && "1".equals(tasklist.getIsaccept())){
                    Date cbfkDate =CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 45, sdexpirationdate.getTime().getHours(),
                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                    workDay = cbfkDate;
                    //受理登记投诉表单没受理则增加7天到期时间
                }else if("complaintmodule".equals(formType) && "01".equals(tasklist.getSystemsource()) && "0".equals(tasklist.getIsaccept())){
                    Date cbfkDate =CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 7, sdexpirationdate.getTime().getHours(),
                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                    workDay = cbfkDate;
                }
            }
            return workDay;
        } else {
            return null;
        }
    }

    /**
     * 受理登记直接分派下级设置到期时间
     *
     * @param tasklist
     * @param teti
     * @return Date
     */
    public Date setSdexpirationdate(Tasklist tasklist, String teti) {
        Calendar sdexpirationdate = Calendar.getInstance();

        if (tasklist.getAssigndate() != null) {
            sdexpirationdate.setTime(tasklist.getAssigndate());
            Date workDay = null;
            if ("紧急".equals(teti)) {
                sdexpirationdate.set(Calendar.MINUTE, sdexpirationdate.get(Calendar.MINUTE) + 30);//紧急工单要在30分钟内受理
                workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 0, sdexpirationdate.getTime().getHours(),
                        sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
            } else {
                if ("01".equals(tasklist.getFormtype())) {//通用表单要在22小时内受理
//                    sdexpirationdate.set(Calendar.HOUR, sdexpirationdate.get(Calendar.HOUR) + 22);
//                    workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 0, sdexpirationdate.getTime().getHours(),
//                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                    if("01".equals(tasklist.getSystemsource())){
//                        if("0".equals(tasklist.getIsaccept())){

                        Calendar comsdexpirationdate = Calendar.getInstance();
                        comsdexpirationdate.setTime(tasklist.getBlqx());

                        if(("moveToLowerOrg".equals(tasklist.getNextnodeid()) || "moveToLowerDept".equals(tasklist.getNextnodeid())) && !"1".equals(tasklist.getIsaccept())) {
//                        msx 咨询分派下级加5天
                            if(tasklist.getFeedback()==null) {
                                workDay = CreateEntityInfoUtil.getWorkDay(comsdexpirationdate.getTime(), 5, comsdexpirationdate.getTime().getHours(),
                                        comsdexpirationdate.getTime().getSeconds(), comsdexpirationdate.getTime().getMinutes());
                                System.out.println("999999" + workDay);
                            }
                        }
//                        //区局处理为最终反馈时间
//                        if("regionAudit".equals(tasklist.getNextnodeid())||"leaderApr".equals(tasklist.getNextnodeid())){
////                            workDay = CreateEntityInfoUtil.getWorkDay(comsdexpirationdate.getTime(), 15, comsdexpirationdate.getTime().getHours(),
////                                    comsdexpirationdate.getTime().getSeconds(), comsdexpirationdate.getTime().getMinutes());
////                            System.out.println("999999" + workDay);
//                            //直接使用录入时填写的最终反馈时间
//                            workDay=tasklist.getExpiredate();
//                        }
//                        }else{
//                            workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 15, sdexpirationdate.getTime().getHours(),
//                                    sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
//                            System.out.println("88888888"+workDay);
//                        }
                    }
                }
                else {
                    System.out.println("elseelse");
                    int day = 0;
                    if ("02".equals(tasklist.getFormtype())) {//消费投诉要在5个工作日内受理
//                        day = 5;
                    }
                    if ("03".equals(tasklist.getFormtype())) {//经济违法要在3个工作日内受理
                        day = 3;
                    }
//                    workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), day, sdexpirationdate.getTime().getHours(),
//                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                }
            }
            return workDay;
        } else {
            return null;
        }
    }

    @Override
    public void dispatch(AICUser aicUser, Map<String, Object> requestMap) {
        String caseguid = (String) requestMap.get("caseguid"); //业务主键
        String feedbackPeriod = (String) requestMap.get("feedbackPeriod"); //初查反馈期限
//        String deadlineForProcessing = (String) requestMap.get("deadlineForProcessing"); //办理期限
        String diversionSituation = (String) requestMap.get("diversionSituation");  //分流情况
        String subDiversion = (String) requestMap.get("subDiversion");  //分流情况（自办）
        String acceptDept = (String) requestMap.get("acceptDept");  //接收部门、反馈人
        String subDesc = (String) requestMap.get("subDesc");  //附言
        String deptType = (String) requestMap.get("deptType");  //办结审批部门类型
        String chooseOperations = (String) requestMap.get("chooseOperations");//操作
        String nodeId = "todispatch"; //当前节点
        String nextNodeId = "begin"; //下一节点
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expiredate = null;
        Date feedbackdate = null;
        try {
            if (StringUtils.isNotBlank(feedbackPeriod)) {
                feedbackdate = sdf.parse(feedbackPeriod);
            }
//            if (StringUtils.isNotBlank(deadlineForProcessing)) {
//                expiredate = sdf.parse(deadlineForProcessing);
//            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        QueryWrapper<Tasklist> tasklistWrapper = new QueryWrapper<>();
        tasklistWrapper.lambda().eq(Tasklist::getCaseguid, caseguid);
        Tasklist tasklist = tasklistMapper.selectOne(tasklistWrapper);
        if (tasklist != null) {
            tasklist.setNextnodeid(nextNodeId);
            tasklist.setBlqx(expiredate);
            tasklist.setOperate(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
            tasklist.setFirstblqx(feedbackdate);
            tasklistMapper.updateById(tasklist);
        }
        //保存分流反馈信息
        Dispatchbackinfo dispatchbackinfo = new Dispatchbackinfo();
        dispatchbackinfo.setId(UUID.randomUUID().toString());
        dispatchbackinfo.setCaseguid(caseguid);
        dispatchbackinfo.setExpiredate(expiredate);
        dispatchbackinfo.setFeedbackdate(feedbackdate);
        dispatchbackinfo.setHandledate(new Date());
        dispatchbackinfo.setHandledept(aicUser.getUnitName());
        dispatchbackinfo.setHandledeptid(aicUser.getUnitID());
        dispatchbackinfo.setHandler(aicUser.getEname());
        dispatchbackinfo.setHandleruserid(aicUser.getPrimaryKey());
        dispatchbackinfo.setHandledepttype(deptType);
        dispatchbackinfo.setNodeid(chooseOperations); //具体操作（分流或送审）
        dispatchbackinfo.setNodename(subDiversion); //自办
        dispatchbackinfo.setOpinion(subDesc);
        dispatchbackinfo.setNexthandledept(acceptDept); //分流部门、或反馈人员
        iDispatchbackinfoService.save(dispatchbackinfo);

        //流程表记录 先更新，后插入
        UpdateWrapper<Processinfo> processinfoWrapper = new UpdateWrapper<>();
        processinfoWrapper.set("status", "1")
                .eq("caseguid", caseguid)
                .eq("status", "0");
        processinfoMapper.update(new Processinfo(), processinfoWrapper);

        Processinfo processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
        processinfo.setNodeid(nextNodeId);
        processinfo.setFromnodeid(nodeId);
        processinfo.setIsmain("1");
        processinfoMapper.insert(processinfo);

        //审批意见
        Opinion opinion = new Opinion();
        opinion.setId(UUID.randomUUID().toString());
        opinion.setHandler(aicUser.getName());
        opinion.setHandledept(aicUser.getOrgName());
        opinion.setCaseguid(caseguid);
        opinion.setOperate("分流");
        opinion.setNodename(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
        opinion.setHandleopinion(subDesc);
        opinion.setHandletime(new Date());
        opinion.setUpdatedate(new Date());
        opinionMapper.insert(opinion);


        //判断是否调用12315回填接口
        if (isOpen12315Backfill && tasklist != null) {
            backfillService.executeShunt(aicUser.getEname(), requestMap, tasklist);
        }
    }


    public String insertAccept(String caseguid, AICUser aicUser) {
        //流程表记录 先更新，后插入
        UpdateWrapper<Processinfo> processinfoWrapper = new UpdateWrapper<>();
        processinfoWrapper.set("status", "1")
                .eq("caseguid", caseguid)
                .eq("status", "0");
        processinfoMapper.update(new Processinfo(), processinfoWrapper);
        String nodeId = "accept";
        Processinfo processinfoAccept = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
        processinfoAccept.setNodeid("accept");
        processinfoAccept.setFromnodeid(nodeId);
        processinfoAccept.setStatus("1");
        processinfoAccept.setUpdatedate(new Date());
        processinfoMapper.insert(processinfoAccept);//linyu----添加受理时间!!!

        //生成默认意见
        Opinion opinion = new Opinion();
        opinion.setId(UUID.randomUUID().toString());
        opinion.setHandler(aicUser.getName());
        opinion.setHandledept(aicUser.getOrgName());
        opinion.setCaseguid(caseguid);
        opinion.setOperate(BaseDataUtil.getNodeNameByNodeId("accept"));
        opinion.setNodename(BaseDataUtil.getNodeNameByNodeId("accept"));
        opinion.setHandleopinion("调解");
        opinion.setHandletime(new Date());
        opinion.setUpdatedate(new Date());
        opinionMapper.insert(opinion);

        return nodeId;
    }
}
