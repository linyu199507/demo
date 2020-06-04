package com.chinaweal.sdcs.crcs.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.aicorg.services.OrgUM;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.service.*;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.*;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.*;
import com.chinaweal.sdcs.crcs.crawler.ywcl.entity.*;
import com.chinaweal.sdcs.crcs.crawler.ywcl.service.*;
import com.chinaweal.sdcs.crcs.util.BaseDataUtil;
import com.chinaweal.sdcs.crcs.util.CreateEntityInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 定时任务配置
 */
@Component
@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Qualifier("datagriddataServiceImpl")
    @Autowired
    private IDatagriddataService iDatagriddataService;

    @Qualifier("seatsServiceImpl")
    @Autowired
    private ISeatsService iSeatsService;

    @Qualifier("commommoduleServiceImpl")
    @Autowired
    private ICommommoduleService iCommommoduleService;

    @Qualifier("comeconomicmoduleServiceImpl")
    @Autowired
    private IComeconomicmoduleService iComeconomicmoduleService;

    @Qualifier("complaintmoduleServiceImpl")
    @Autowired
    private IComplaintmoduleService iComplaintmoduleService;

    @Qualifier("askdepthandleServiceImpl")
    @Autowired
    private IAskdepthandleService iAskdepthandleService;

    @Qualifier("inrequestServiceImpl")
    @Autowired
    private IInrequestService iInrequestService;

    @Qualifier("customerinfoServiceImpl")
    @Autowired
    private ICustomerinfoService iCustomerinfoService;

    @Qualifier("commomformServiceImpl")
    @Autowired
    private ICommomformService iCommomformService;

    @Qualifier("economyformServiceImpl")
    @Autowired
    private IEconomyformService iEconomyformService;

    @Qualifier("complaintformServiceImpl")
    @Autowired
    private IComplaintformService iComplaintformService;

    @Qualifier("opinionServiceImpl")
    @Autowired
    private IOpinionService iOpinionService;

    @Qualifier("processinfoServiceImpl")
    @Autowired
    private IProcessinfoService iProcessinfoService;

    @Qualifier("handledemandServiceImpl")
    @Autowired
    private IHandledemandService iHandledemandService;

    @Qualifier("tasklistServiceImpl")
    @Autowired
    private ITasklistService iTasklistService;

    @Autowired
    private OrgUM orgUM;

    @Qualifier("accessoryServiceImpl")
    @Autowired
    private IAccessoryService iAccessoryService;

    @Qualifier("materialinfoServiceImpl")
    @Autowired
    private IMaterialinfoService iMaterialinfoService;

    @Qualifier("ywclshuntlistServiceImpl")
    @Autowired
    private IYwclshuntlistService iYwclshuntlistService;

    @Qualifier("ywcltapdetailsServiceImpl")
    @Autowired
    private IYwcltapdetailsService iYwcltapdetailsService;

    @Qualifier("ywclaccessoryServiceImpl")
    @Autowired
    private IYwclaccessoryService iYwclaccessoryService;

    @Qualifier("ywclfeedbackServiceImpl")
    @Autowired
    private IYwclfeedbackService iYwclfeedbackService;

    @Qualifier("ywclflowServiceImpl")
    @Autowired
    private IYwclflowService iYwclflowService;

    @Qualifier("ywclfeedbackformServiceImpl")
    @Autowired
    private IYwclfeedbackformService iYwclfeedbackformService;

    @Qualifier("ywclflowformServiceImpl")
    @Autowired
    private IYwclflowformService iYwclflowformService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 启动12345系统待办定时任务（执行完成上一次任务之后5分钟后再执行下一次任务）
     */
//    @Scheduled(fixedDelay = 300000)
    public void runBmfwScheduleTask() {
        String nodeId = "bmfwToDetermine"; //默认当前节点
        String nextNodeId = "begin"; //默认下一节点
        String caseguid = null;
        // 查询未处理的12345待办数据
        QueryWrapper<Datagriddata> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Datagriddata::getStatus, '0').ne(Datagriddata::getState, "1");
        queryWrapper.orderByDesc("createdate");
        List<Datagriddata> datagriddataList = iDatagriddataService.list(queryWrapper);
        for (Datagriddata datagriddata : datagriddataList) {
            caseguid = datagriddata.getCaseguid();
            //话务信息
            QueryWrapper<Seats> seatsWrapper = new QueryWrapper<Seats>();
            seatsWrapper.lambda().eq(Seats::getCaseguid, caseguid);
            Seats seats = iSeatsService.getOne(seatsWrapper, false);

            //通用表单
            QueryWrapper<Commommodule> commommoduleWrapper = new QueryWrapper<Commommodule>();
            commommoduleWrapper.lambda().eq(Commommodule::getCaseguid, caseguid);
            Commommodule commommodule = iCommommoduleService.getOne(commommoduleWrapper, false);

            //经济违法表单
            QueryWrapper<Comeconomicmodule> comeconomicmoduleWrapper = new QueryWrapper<Comeconomicmodule>();
            comeconomicmoduleWrapper.lambda().eq(Comeconomicmodule::getCaseguid, caseguid);
            Comeconomicmodule comeconomicmodule = iComeconomicmoduleService.getOne(comeconomicmoduleWrapper, false);

            //消费投诉表单
            QueryWrapper<Complaintmodule> complaintmoduleWrapper = new QueryWrapper<Complaintmodule>();
            complaintmoduleWrapper.lambda().eq(Complaintmodule::getCaseguid, caseguid);
            Complaintmodule complaintmodule = iComplaintmoduleService.getOne(complaintmoduleWrapper, false);

            //处办要求
            QueryWrapper<Inrequest> inrequestWrapper = new QueryWrapper<Inrequest>();
            inrequestWrapper.lambda().eq(Inrequest::getCaseguid, caseguid);
            Inrequest inrequest = iInrequestService.getOne(inrequestWrapper, false);

            //处理意见
            QueryWrapper<Askdepthandle> askdepthandleWrapper = new QueryWrapper<Askdepthandle>();
            askdepthandleWrapper.lambda().eq(Askdepthandle::getCaseguid, caseguid);
            List<Askdepthandle> askdepthandleList = iAskdepthandleService.list(askdepthandleWrapper);

            //附件列表
            QueryWrapper<Accessory> accessoryWrapper = new QueryWrapper<Accessory>();
            accessoryWrapper.lambda().eq(Accessory::getCaseguid, caseguid);
            List<Accessory> accessoryList = iAccessoryService.list(accessoryWrapper);

            Boolean flag = false;
            //先判断所有的话务信息、表单登记信息是否都存在，防止出现信息不完整的情况 2019.11.22
            Boolean result = (seats != null) && (commommodule != null || comeconomicmodule != null || complaintmodule != null);
            if (result) {
                //启动系统流程待办
                flag = startBmfwTask(seats, commommodule, comeconomicmodule, complaintmodule, inrequest, datagriddata, askdepthandleList, accessoryList, nodeId, nextNodeId);
            }
            if (flag) {
                //更新状态为是否已处理
                datagriddata.setStatus("1");
                datagriddata.setUpdatedate(new Date());
                iDatagriddataService.updateById(datagriddata);
            }
        }
    }

    /**
     * 启动12345系统流程待办
     *
     * @param seats
     * @param commommodule
     * @param comeconomicmodule
     * @param complaintmodule
     * @param inrequest
     * @param datagriddata
     * @param askdepthandleList
     * @return
     */
    public Boolean startBmfwTask(Seats seats, Commommodule commommodule, Comeconomicmodule comeconomicmodule, Complaintmodule complaintmodule, Inrequest inrequest, Datagriddata datagriddata, List<Askdepthandle> askdepthandleList, List<Accessory> accessoryList, String nodeId, String nextNodeId) {
        boolean flag = false;
        try {
            //默认查询顺德区局有权限的用户
            List<AICUser> aicUsers = orgUM.findUsersByOrgAndPermission("6ba12fbe-0100-1000-e000-03fec0a85893", "bmfwDispatch");
            String caseguid = datagriddata.getCaseguid();
            //12345业务转待办先查询有没有记录 2019.12.24
            QueryWrapper<Tasklist> tasklistQueryWrapper = new QueryWrapper<Tasklist>();
            tasklistQueryWrapper.lambda().eq(Tasklist::getCaseguid, caseguid);
            Tasklist tasklist = iTasklistService.getOne(tasklistQueryWrapper, false);
            if (tasklist == null) {
                tasklist = CreateEntityInfoUtil.createTasklist(datagriddata);
                tasklist.setIscbdf("0");//liny
            } else {
                tasklist.setIssignin("0");
                tasklist.setState("0");
                tasklist.setSigndate(new Date());
                tasklist.setCreatedate(new Date());
                tasklist.setIscbdf("0");

            }

            //话务信息
            iCustomerinfoService.saveOrUpdate(CreateEntityInfoUtil.createCustomerinfo(seats));
            //通用表单
            Commomform commomform = CreateEntityInfoUtil.createCommomform(commommodule);
            if (commomform != null) {
                iCommomformService.saveOrUpdate(commomform);
                tasklist.setFormtype(BaseDataUtil.getFormType(BaseDataUtil.TASKLIST_FORMTYPE_COMMOMMODULE));
                tasklist.setBustype("咨询类");
                tasklist.setContent(commomform.getRqstcontent()); //登记内容
                tasklist.setSdexpirationdate(inrequest.getResulttime());//获取处理反馈时间
            }
            //经济违法表单
            Economyform economyform = CreateEntityInfoUtil.createEconomyform(comeconomicmodule);
            if (economyform != null) {
                iEconomyformService.saveOrUpdate(economyform);
                tasklist.setFormtype(BaseDataUtil.getFormType(BaseDataUtil.TASKLIST_FORMTYPE_COMECONOMICMODULE));
                tasklist.setBustype("举报类");
                tasklist.setContent(economyform.getProblemdescribe()); //登记内容
                tasklist.setSdexpirationdate(inrequest.getAccepttime());//获取受理时间
            }
            //消费投诉表单
            Complaintform complaintform = CreateEntityInfoUtil.createComplaintform(complaintmodule);
            if (complaintform != null) {
                iComplaintformService.saveOrUpdate(complaintform);
                tasklist.setFormtype(BaseDataUtil.getFormType(BaseDataUtil.TASKLIST_FORMTYPE_COMPLAINTMODULE));
                tasklist.setBustype("投诉类");
                tasklist.setContent(complaintform.getProblemdescribe()); //登记内容
                tasklist.setSdexpirationdate(inrequest.getAccepttime());//获取受理时间
            }
            //处理意见
            if (askdepthandleList != null && askdepthandleList.size() > 0) {
                for (Askdepthandle askdepthandle : askdepthandleList) {
                    Opinion opinion = CreateEntityInfoUtil.createOpinion(askdepthandle);
                    if (opinion != null) {
                        iOpinionService.saveOrUpdate(opinion);
                    }
                }
            }
            //处办要求
            Handledemand handledemand = CreateEntityInfoUtil.createHandledemand(inrequest);
            if (handledemand != null) {
//                tasklist.setSdexpirationdate(inrequest.getAccepttime());//获取处理反馈时间
                iHandledemandService.saveOrUpdate(handledemand);
            }
            //流程表记录
            for (AICUser aicUser : aicUsers) {
                Processinfo processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
                if (processinfo != null) {
                    processinfo.setNodeid(nextNodeId);//当前节点此处要待办确定。
                    processinfo.setFromnodeid(nextNodeId);//下一个节点
                    processinfo.setIsmain("1");
                    iProcessinfoService.saveOrUpdate(processinfo);
                }
                tasklist.setHandledeptname(aicUser.getOrgName());//当前处理机关
            }
            //附件清单
            if (accessoryList != null && accessoryList.size() > 0) {
                for (Accessory accessory : accessoryList) {
                    Materialinfo materialinfo = CreateEntityInfoUtil.createMaterialinfo(accessory);
                    if (materialinfo != null) {
                        iMaterialinfoService.saveOrUpdate(materialinfo);
                    }
                }
            }
            flag = true;
            //待办信息
            tasklist.setNextnodeid(nextNodeId);//下一个节点
            tasklist.setFromnodeid(nodeId);
            tasklist.setOperate(BaseDataUtil.getNodeNameByNodeId(nextNodeId));

//            Date date = updateSdexpirationdate(tasklist, datagriddata.getTeti());
//            tasklist.setSdexpirationdate(date);
            iTasklistService.saveOrUpdate(tasklist);
        } catch (Exception e) {
            logger.error("12345待办转换失败，业务id：{}", datagriddata.getCaseguid(), e);
            flag = false;
        }
        return flag;
    }

    /**
     * 12345系统流程待办设置到期时间
     *
     * @param tasklist
     * @param teti
     * @return Date
     */
    public Date updateSdexpirationdate(Tasklist tasklist, String teti) {
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
                    sdexpirationdate.set(Calendar.HOUR, sdexpirationdate.get(Calendar.HOUR) + 22);
                    workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 0, sdexpirationdate.getTime().getHours(),
                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                } else {
                    int day = 0;
                    if ("02".equals(tasklist.getFormtype())) {//消费投诉要在5个工作日内受理
                        day = 5;
                    }
                    if ("03".equals(tasklist.getFormtype())) {//经济违法要在3个工作日内受理
                        day = 3;
                    }
                    workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), day, sdexpirationdate.getTime().getHours(),
                            sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                }
            }
            return workDay;
        } else {
            return null;
        }
    }

    /**
     * 启动12315系统待办定时任务（执行完成上一次任务之后5分钟后再执行下一次任务）
     */
//    @Scheduled(fixedDelay = 300000)
    public void runYwclScheduleTask() {
        // 查询未处理的12315待办数据
        QueryWrapper<Ywclshuntlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Ywclshuntlist::getStatus, '0').ne(Ywclshuntlist::getState, "1");
        queryWrapper.orderByDesc("regtime");
        List<Ywclshuntlist> ywclshuntlists = iYwclshuntlistService.list(queryWrapper);
        for (Ywclshuntlist ywclshuntlist : ywclshuntlists) {
            //待办列表
            QueryWrapper<Ywcltapdetails> ywcltapdetailsQueryWrapper = new QueryWrapper<Ywcltapdetails>();
            ywcltapdetailsQueryWrapper.lambda().eq(Ywcltapdetails::getCaseid, ywclshuntlist.getCaseid());
            Ywcltapdetails ywcltapdetail = iYwcltapdetailsService.getOne(ywcltapdetailsQueryWrapper, false);

            //附件列表
            QueryWrapper<Ywclaccessory> accessoryWrapper = new QueryWrapper<Ywclaccessory>();
            accessoryWrapper.lambda().eq(Ywclaccessory::getCaseid, ywclshuntlist.getCaseid());
            List<Ywclaccessory> accessoryList = iYwclaccessoryService.list(accessoryWrapper);

            //反馈流程流转信息
            QueryWrapper<Ywclfeedback> ywclfeedbackQueryWrapper = new QueryWrapper<Ywclfeedback>();
            ywclfeedbackQueryWrapper.lambda().eq(Ywclfeedback::getCaseid, ywclshuntlist.getCaseid());
            List<Ywclfeedback> ywclfeedbackList = iYwclfeedbackService.list(ywclfeedbackQueryWrapper);

            //流转信息
            QueryWrapper<Ywclflow> ywclflowQueryWrapper = new QueryWrapper<Ywclflow>();
            ywclflowQueryWrapper.lambda().eq(Ywclflow::getCaseid, ywclshuntlist.getCaseid());
            List<Ywclflow> ywclflowList = iYwclflowService.list(ywclflowQueryWrapper);

            //先判断所有的话务信息、表单登记信息是否都存在，防止出现信息不完整的情况 2019.11.22
            Boolean flag = false;
            if (ywcltapdetail != null) {
                //启动系统流程待办
                flag = startYwclTask(ywclshuntlist, ywcltapdetail, accessoryList, ywclfeedbackList, ywclflowList);
            }
            if (flag) {
                //更新状态为是否已处理
                ywclshuntlist.setStatus("1");
                ywclshuntlist.setUpdatedate(new Date());
                iYwclshuntlistService.updateById(ywclshuntlist);
            }
        }
    }

    /**
     * 启动12315待办
     *
     * @param ywclshuntlist
     * @param ywcltapdetail
     * @param accessoryList
     * @param ywclfeedbackList
     * @param ywclflowList
     * @return
     */
    private Boolean startYwclTask(Ywclshuntlist ywclshuntlist, Ywcltapdetails ywcltapdetail, List<Ywclaccessory> accessoryList, List<Ywclfeedback> ywclfeedbackList, List<Ywclflow> ywclflowList) {
        boolean flag = false;
        try {
            //默认查询顺德区局有权限的用户
            List<AICUser> aicUsers = orgUM.findUsersByOrgAndPermission("6ba12fbe-0100-1000-e000-03fec0a85893", "ywclDispatch");
            String caseguid = UUID.randomUUID().toString();
            Tasklist tasklist = CreateEntityInfoUtil.createTasklistByYwclshuntlist(ywclshuntlist);
            tasklist.setCaseguid(caseguid);
            tasklist.setIscbdf("0");
            //经济违法表单
            if ("举报".equals(ywclshuntlist.getInfotype())) {
                Economyform economyform = CreateEntityInfoUtil.createEconomyformByYwcltapdetails(ywcltapdetail);
                if (economyform != null) {
                    economyform.setCaseguid(caseguid);
                    iEconomyformService.saveOrUpdate(economyform);
                }
                tasklist.setSdexpirationdate(economyform.getFeedbacktime());
                tasklist.setFormtype(BaseDataUtil.getFormType(BaseDataUtil.TASKLIST_FORMTYPE_COMECONOMICMODULE));
            }
            if ("投诉".equals(ywclshuntlist.getInfotype())) {
                //消费投诉表单
                Complaintform complaintform = CreateEntityInfoUtil.createComplaintformByYwcltapdetails(ywcltapdetail);
                if (complaintform != null) {
                    complaintform.setCaseguid(caseguid);
                    iComplaintformService.saveOrUpdate(complaintform);
                }
                tasklist.setSdexpirationdate(complaintform.getFeedbacktime());
                tasklist.setFormtype(BaseDataUtil.getFormType(BaseDataUtil.TASKLIST_FORMTYPE_COMPLAINTMODULE));
            }

            //流程表记录
            for (AICUser aicUser : aicUsers) {
                Processinfo processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
                if (processinfo != null) {
                    processinfo.setNodeid(tasklist.getNextnodeid());//当前节点此处要待办确定。
                    processinfo.setFromnodeid(tasklist.getNextnodeid());//下一个节点
                    processinfo.setIsmain("1");
                    iProcessinfoService.saveOrUpdate(processinfo);
                }
                tasklist.setHandledeptname(aicUser.getOrgName());//当前处理机关
            }

            //附件清单
            if (accessoryList != null && accessoryList.size() > 0) {
                for (Ywclaccessory accessory : accessoryList) {
                    Materialinfo materialinfo = CreateEntityInfoUtil.createMaterialinfoByYwclaccessory(accessory);
                    if (materialinfo != null) {
                        materialinfo.setCaseguid(caseguid);
                        iMaterialinfoService.saveOrUpdate(materialinfo);
                    }
                }
            }

            //反馈流程流转信息
            if (ywclfeedbackList != null && ywclfeedbackList.size() > 0) {
                for (Ywclfeedback ywclfeedback : ywclfeedbackList) {
                    Ywclfeedbackform ywclfeedbackform = CreateEntityInfoUtil.createYwclfeedbackformByYwclfeedback(ywclfeedback);
                    if (ywclfeedbackform != null) {
                        ywclfeedbackform.setCaseguid(caseguid);
                        iYwclfeedbackformService.saveOrUpdate(ywclfeedbackform);
                    }
                }
            }

            //流转信息
            if (ywclflowList != null && ywclflowList.size() > 0) {
                for (Ywclflow ywclflow : ywclflowList) {
                    Ywclflowform ywclflowform = CreateEntityInfoUtil.createYwclflowformByYwclflow(ywclflow);
                    if (ywclflowform != null) {
                        ywclflowform.setCaseguid(caseguid);
                        iYwclflowformService.saveOrUpdate(ywclflowform);
                    }
                }
            }

            flag = true;
            //待办信息
//            Date date = updateSdexpirationdate(tasklist, "");
//            tasklist.setSdexpirationdate(date);
            tasklist.setOperate(BaseDataUtil.getNodeNameByNodeId(tasklist.getNextnodeid()));
            iTasklistService.saveOrUpdate(tasklist);
        } catch (Exception e) {
            logger.error("全国12315待办转换失败，登记编号：{}", ywclshuntlist.getCaseid(), e);
            flag = false;
        }
        return flag;
    }
}
