package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.mapper.*;
import com.chinaweal.sdcs.crcs.business.service.*;
import com.chinaweal.sdcs.crcs.system.entity.Loglist;
import com.chinaweal.sdcs.crcs.system.mapper.LoglistMapper;
import com.chinaweal.sdcs.crcs.util.BaseDataUtil;
import com.chinaweal.sdcs.crcs.util.CreateEntityInfoUtil;
import com.chinaweal.sdcs.crcs.util.RequestUtil;
import com.chinaweal.sdcs.crcs.util.UuidUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
@Service
public class TasklistServiceImpl extends ServiceImpl<TasklistMapper, Tasklist> implements ITasklistService {

    @Autowired
    private ConsultationformMapper consultationformMapper;
    @Autowired
    private CommomformMapper commomformMapper;
    @Autowired
    private TasklistMapper tasklistMapper;
    @Autowired
    private ProcessinfoMapper processinfoMapper;
    @Autowired
    private ComplaintformMapper complaintformMapper;
    @Autowired
    private EconomyformMapper economyformMapper;
    @Autowired
    private UuidUtil uuidUtil;
    @Autowired
    private LoglistMapper loglistMapper;
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
    @Qualifier("handledemandServiceImpl")
    @Autowired
    private IHandledemandService iHandledemandService;


    @Override
    public Map<String, String> startTaskInfo(AICUser aicUser, Map<String, Object> requestMap) {
        HashMap searchMap = new HashMap();
        Consultationform consultationform = null; //咨询类表单信息
        Commomform commomform = null; //咨询类表单信息
        Complaintform complaintform = null; //投诉类表单信息
        Economyform economyform = null; //经济违法类表单信息
        String caseguid = "";
        String labelname = "";
        String focus = "";
        String rqsttitle = "";
        String accordtype = "";
        boolean result = false;
        String bustype = ""; //业务类型
        try {
            String method = (String) requestMap.get("method"); //提交方式，保存或者受理（save or submit）
            String formType = (String) requestMap.get("formType"); //提交方式，保存或者受理（save or submit）
            String nodeId = (String) requestMap.get("node"); //当前节点
            String nextNodeId = (String) requestMap.get("nextNodeId"); //下一步处理的节点
            String worktype = (String) requestMap.get("worktype");  //工单是否紧急
            searchMap.put("formType", formType);
            searchMap.put("nextNodeId", nextNodeId);
            //通用表单
            if ("commommodule".equals(formType)) {
                bustype = "咨询类";
//                if (requestMap.get("commommodule") != null) {
//                    consultationform = (Consultationform) RequestUtil.mapToEntity(Consultationform.class, requestMap.get("commommodule"));
//                    if (consultationform != null) {
//                        if (StringUtils.isBlank(consultationform.getId())) {
//                            consultationform.setId(UUID.randomUUID().toString());
//                            if (StringUtils.isBlank(consultationform.getCaseguid())) {
//                                consultationform.setCaseguid(UUID.randomUUID().toString());
//                            }
//                            searchMap.put("customerinfo", consultationform);
//
//                            consultationformMapper.insert(consultationform);
//                        } else {
//                            searchMap.put("customerinfo", consultationform);
//
//                            consultationformMapper.updateById(consultationform);
//                        }
//                        caseguid = consultationform.getCaseguid();
//                        focus = consultationform.getFocus();
//                        rqsttitle = consultationform.getRqsttitle();
//                        accordtype = consultationform.getBusinesstype();
//                    }
//                }
                if (requestMap.get("commommodule") != null) {
                    commomform = (Commomform) RequestUtil.mapToEntity(Commomform.class, requestMap.get("commommodule"));
                    if (commomform != null) {
                        if (StringUtils.isBlank(commomform.getId())) {
                            commomform.setId(UUID.randomUUID().toString());
                            if (StringUtils.isBlank(commomform.getCaseguid())) {
                                commomform.setCaseguid(UUID.randomUUID().toString());
                            }
                            searchMap.put("customerinfo", commomform);

                            commomformMapper.insert(commomform);
                        } else {
                            searchMap.put("customerinfo", commomform);

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
                bustype = "投诉类";
                if (requestMap.get("complaintmodule") != null) {
                    complaintform = (Complaintform) RequestUtil.mapToEntity(Complaintform.class, requestMap.get("complaintmodule"));
                    if (complaintform != null) {
                        if (StringUtils.isBlank(complaintform.getId())) {
                            complaintform.setId(UUID.randomUUID().toString());
                            if (StringUtils.isBlank(complaintform.getCaseguid())) {
                                complaintform.setCaseguid(UUID.randomUUID().toString());
                            }

                            searchMap.put("complaintform", complaintform);
                            complaintformMapper.insert(complaintform);
                        } else {
                            searchMap.put("complaintform", complaintform);
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
                bustype = "举报类";
                if (requestMap.get("comeconomicmodule") != null) {
                    economyform = (Economyform) RequestUtil.mapToEntity(Economyform.class, requestMap.get("comeconomicmodule"));
                    if (economyform != null) {
                        if (StringUtils.isBlank(economyform.getId())) {
                            economyform.setId(UUID.randomUUID().toString());
                            if (StringUtils.isBlank(economyform.getCaseguid())) {
                                economyform.setCaseguid(UUID.randomUUID().toString());
                            }
                            searchMap.put("economyform", economyform);
                            economyformMapper.insert(economyform);
                        } else {
                            searchMap.put("economyform", economyform);
                            economyformMapper.updateById(economyform);
                        }
                        caseguid = economyform.getCaseguid();
                        focus = economyform.getFocus();
                        rqsttitle = economyform.getRqsttitle();
                        accordtype = economyform.getBusinesstype();
                    }
                }
            }
            //待办列表信息
            QueryWrapper<Tasklist> tasklistWrapper = new QueryWrapper<>();
            tasklistWrapper.lambda().eq(Tasklist::getCaseguid, caseguid);
            Tasklist tasklist = tasklistMapper.selectOne(tasklistWrapper);
            if (tasklist != null) {
                tasklist.setLabelname(labelname);
                tasklist.setFocus(focus);
                tasklist.setRqsttitle(rqsttitle);
                tasklist.setAccordtype(accordtype);
                tasklist.setNextnodeid(nextNodeId);
                tasklist.setOperate(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
                tasklist.setSenderid(aicUser.getPrimaryKey());
                tasklist.setRegionid(aicUser.getRegionID());//TODO 相关机关
                tasklist.setWorktype(worktype);
                tasklist.setBustype(bustype);
                if ("complaintmodule".equals(formType)) {
                    tasklist.setComplainanted(complaintform.getSubjectname());
                }else if ("economymodule".equals(formType) || "comeconomicmodule".equals(formType)) {
                    tasklist.setComplainanted(economyform.getSubjectname());
                }else {
                    tasklist.setComplainanted(commomform.getEntname());
                }
//                Date date = updateSdexpirationdate(tasklist, worktype);
//                tasklist.setSdexpirationdate(date);
                tasklistMapper.updateById(tasklist);
            } else {
                tasklist = CreateEntityInfoUtil.createTasklist(aicUser);
                tasklist.setTserialnum(aicUser.getRegionID().substring(0, 6) + uuidUtil.getCurAcceptNo());
                tasklist.setCaseguid(caseguid);
                tasklist.setLabelname(labelname);
                tasklist.setFocus(focus);
                tasklist.setRqsttitle(rqsttitle);
                tasklist.setAccordtype(accordtype);
                tasklist.setFormtype(BaseDataUtil.getFormType(formType));
                tasklist.setNextnodeid(nextNodeId);
                tasklist.setOperate(BaseDataUtil.getNodeNameByNodeId(nextNodeId));
                tasklist.setSenderid(aicUser.getPrimaryKey());
                tasklist.setWorktype(worktype);
                tasklist.setBustype(bustype);
                if ("complaintmodule".equals(formType)) {
                    tasklist.setComplainanted(complaintform.getSubjectname());
                }else if ("comeconomicmodule".equals(formType)) {
                    tasklist.setComplainanted(economyform.getSubjectname());
                }else {
                    tasklist.setComplainanted(commomform.getEntname());
                }
//                Date date = updateSdexpirationdate(tasklist, worktype);
//                tasklist.setSdexpirationdate(date);
                tasklistMapper.insert(tasklist);
            }
            searchMap.put("tasklist", tasklist);
            if ("submit".equals(method)) {
                QueryWrapper<Processinfo> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(Processinfo::getCaseguid, caseguid)
                        .eq(Processinfo::getStatus, "0")
                        .eq(Processinfo::getHandleruserid, aicUser.getPrimaryKey());
                Processinfo processinfo = processinfoMapper.selectOne(queryWrapper);
                //如果没有流程表记录，则新增
                if (processinfo == null) {
                    processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
                    processinfo.setNodeid(nextNodeId);
                    processinfo.setFromnodeid(nodeId);
                    processinfo.setIsmain("1");//处理人员
                    processinfoMapper.insert(processinfo);
                }
            }
            result = true;
            searchMap.put("result", result);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            searchMap.put("result", result);
        }
        searchMap.put("str", "true");
        return searchMap;
    }

    @Override
    public void deleteByIds(String[] ids, AICUser aicUser) {
        Loglist loglist = null;
        for (String id : ids) {
            Tasklist tasklist = tasklistMapper.selectById(id);
            if (tasklist != null) {
                tasklist.setState("1");
                tasklistMapper.updateById(tasklist);
                //保存删除日志
                loglist = new Loglist();
                loglist.setId(UUID.randomUUID().toString());
                loglist.setOperationid(aicUser.getPrimaryKey());
                loglist.setOperationname(aicUser.getName());
                loglist.setOperationinfo("删除工单号为：" + tasklist.getTserialnum() + " 的待办业务。");
                loglist.setOperationid("deleteBatchIds");
                loglist.setCaseguid(tasklist.getCaseguid());
                loglist.setOperationtime(new Date());
                loglist.setUpdatedate(new Date());
                loglistMapper.insert(loglist);
            }
        }
    }

    @Override
    public Map<String, String> saveBmfwBaseInfo(Map<String, Object> requestMap) {
        HashMap searchMap = new HashMap();
        Customerinfo customerinfo = null;
        Commomform commomform = null;
        Economyform economyform = null;
        Complaintform complaintform = null;
        Handledemand handledemand = null;
        String caseguid = "";
        if (requestMap.get("customerinfo") != null) {
            customerinfo = (Customerinfo) RequestUtil.mapToEntity(Customerinfo.class, requestMap.get("customerinfo"));
            caseguid = customerinfo.getCaseguid();
        }
        if (requestMap.get("commomform") != null) {
            commomform = (Commomform) RequestUtil.mapToEntity(Commomform.class, requestMap.get("commomform"));
        }
        if (requestMap.get("economyform") != null) {
            economyform = (Economyform) RequestUtil.mapToEntity(Economyform.class, requestMap.get("economyform"));
        }
        if (requestMap.get("complaintform") != null) {
            complaintform = (Complaintform) RequestUtil.mapToEntity(Complaintform.class, requestMap.get("complaintform"));
        }
        if (requestMap.get("handledemand") != null) {
            handledemand = (Handledemand) RequestUtil.mapToEntity(Handledemand.class, requestMap.get("handledemand"));
        }
        boolean result = false;
        try {
            //话务信息
            if (customerinfo != null) {
                iCustomerinfoService.saveOrUpdate(customerinfo);
            }
            //通用表单
            if (commomform != null) {
                iCommomformService.saveOrUpdate(commomform);
            }
            //经济违法表单
            if (economyform != null) {
                iEconomyformService.saveOrUpdate(economyform);
            }
            //消费投诉表单
            if (complaintform != null) {
                iComplaintformService.saveOrUpdate(complaintform);
            }
            //处办要求
            if (handledemand != null) {
                iHandledemandService.saveOrUpdate(handledemand);
            }
            result = true;
            searchMap.put("result", result);
            searchMap.put("caseguid", caseguid);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            searchMap.put("result", result);
        }
        return searchMap;
    }

    @Override
    public IPage<Tasklist> searchTasklist(int currentPage, int size, AICUser aicUser, Map<String, Object> requestMap) {
        Page<Tasklist> page = new Page();
        page.setCurrent(currentPage);
        page.setSize(size);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isNotBlank((String) requestMap.get("enddate"))) {
            try {
                Date sDate = sdf.parse((String) requestMap.get("enddate"));
                Calendar c = Calendar.getInstance();
                c.setTime(sDate);
                c.add(Calendar.DAY_OF_MONTH, 1);
                sDate = c.getTime();
                String endDate = sdf.format(sDate);
                requestMap.put("enddate", endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return tasklistMapper.searchTasklist(page, aicUser, requestMap);
    }
    @Override
    public Map<String, Object> searchTasklistCount(AICUser aicUser) {
        Map<String, Object> map = new HashMap<>();
        String ename = aicUser.getEname();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String expectedBegin = format.format(now);
        String expectedEnd = format.format(now.getTime() + 12 * 60 * 60 * 1000);
        //msx 受理登记48小时内还没立案提醒
        String sldjbegin = format.format(now);
        String sldjend=format.format(now.getTime() + 48 * 60 * 60 * 1000);
        //msx 受理登记24小时还没有初步反馈提醒
        String feedbackbegin=format.format(now);
        String feedbackend=format.format(now.getTime() + 24 * 60 * 60 * 1000);

        int integer0 = tasklistMapper.selectTasklistCount(ename, "01", "", "","","","","","");
        map.put("sldjdb", integer0);
        int integer1 = tasklistMapper.selectTasklistCount(ename, "02", "", "","","","","","");
        map.put("bmfwdb", integer1);
        int integer2 = tasklistMapper.selectTasklistCount(ename, "03", "", "","","","","","");
        map.put("ywcldb", integer2);
        int expectedCount = tasklistMapper.selectTasklistCount(ename, "", expectedBegin, expectedEnd,"","","","","");
        map.put("expectedCount", expectedCount);
        int integer3 = tasklistMapper.selectTasklistCount(ename, "", "", "","1","","","","");
        map.put("xpgd", integer3);
        //msx 立案预警数
        int sldjendCount = tasklistMapper.selectTasklistCount(ename, "", "","","",sldjend,sldjbegin,"","");
        map.put("sldjendCount", sldjendCount);
        //msx 初步反馈预警数
        int feedbackCount = tasklistMapper.selectTasklistCount(ename, "", "","","","","",feedbackbegin,feedbackend);
        map.put("feedbackCount", feedbackCount);
        return map;
    }

    @Override
    public IPage<Tasklist> searchEndTasklist(int currentPage, int size, AICUser aicUser, Map<String, Object> requestMap) {
        Page<Tasklist> page = new Page();
        page.setCurrent(currentPage);
        page.setSize(size);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (StringUtils.isNotBlank((String) requestMap.get("enddate"))) {
            try {
                Date sDate = sdf.parse((String) requestMap.get("enddate"));
                Calendar c = Calendar.getInstance();
                c.setTime(sDate);
                c.add(Calendar.DAY_OF_MONTH, 1);
                sDate = c.getTime();
                String endDate = sdf.format(sDate);
                requestMap.put("enddate", endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return tasklistMapper.searchEndTasklist(page, aicUser, requestMap);
    }


    @Override
    public void signTasklist(AICUser aicUser, String caseguid, String fromnodeid, String ismain) {
        //待办列表信息
        QueryWrapper<Tasklist> tasklistWrapper = new QueryWrapper<>();
        tasklistWrapper.lambda().eq(Tasklist::getCaseguid, caseguid);
        Tasklist tasklist = tasklistMapper.selectOne(tasklistWrapper);
        //需要签收的环节
        if ("accept".equals(fromnodeid)) {
            if (tasklist != null) {
                tasklist.setIssignin("1");
                tasklist.setSigninaccount(aicUser.getEname());
                tasklist.setSigninname(aicUser.getName());
                tasklist.setSigninuserid(aicUser.getPrimaryKey());
                tasklist.setSignindeptid(aicUser.getUnitID());
                tasklist.setRegionid(aicUser.getRegionID());//TODO 相关机关
                tasklist.setUpdatedate(new Date());
                tasklistMapper.updateById(tasklist);
            }
            //签收后将流程表其他用户的信息失效
            UpdateWrapper<Processinfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("status", "1")
                    .eq("caseguid", caseguid)
                    .ne("handleruserid", aicUser.getPrimaryKey());
            processinfoMapper.update(new Processinfo(), updateWrapper);
        }
        //分派部门
        else if ("moveToLowerDept".equals(fromnodeid) || "moveToLowerOrg".equals(fromnodeid)) {
            if ("1".equals(ismain)) {
                if (tasklist != null) {
                    tasklist.setIssignin("0");
                    tasklist.setSigninaccount(aicUser.getEname());
                    tasklist.setSigninname(aicUser.getName());
                    tasklist.setSigninuserid(aicUser.getPrimaryKey());
                    tasklist.setSignindeptid(aicUser.getUnitID());
                    tasklist.setRegionid(aicUser.getRegionID());//TODO 相关机关
                    tasklist.setUpdatedate(new Date());
                    tasklistMapper.updateById(tasklist);
                }
                //签收后将流程表其他用户的信息失效
                UpdateWrapper<Processinfo> updateWrapper = new UpdateWrapper<>();
                if ("moveToLowerDept".equals(fromnodeid)) {
                    updateWrapper.set("status", "1")
                            .eq("caseguid", caseguid)
                            .ne("handleruserid", aicUser.getPrimaryKey())
                            .eq("handledeptid", aicUser.getUnitID());
                } else {
                    updateWrapper.set("status", "1")
                            .eq("caseguid", caseguid)
                            .ne("handleruserid", aicUser.getPrimaryKey())
                            .eq("ismain", "1");
                }
                processinfoMapper.update(new Processinfo(), updateWrapper);
            }
        } else {
            if (tasklist != null) {
                tasklist.setIssignin("0");
                tasklist.setSigninaccount(aicUser.getEname());
                tasklist.setSigninname(aicUser.getName());
                tasklist.setSigninuserid(aicUser.getPrimaryKey());
                tasklist.setSignindeptid(aicUser.getUnitID());
                tasklist.setRegionid(aicUser.getRegionID());//TODO 相关机关
                tasklist.setUpdatedate(new Date());
                tasklistMapper.updateById(tasklist);
            }
        }
    }

    @Override
    public Map<String, Object> selectTasklist(AICUser aicUser) {
        Map<String, Object> map = new HashMap();
        int warning = 0;
        int yellowCard = 0;
        int redCard = 0;
        List<Tasklist> tasklist = tasklistMapper.selectTasklist(aicUser);
        Calendar sdexpirationdate = Calendar.getInstance();
        Date workDay = null;
        Date workDay1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        for (int i = 0; i < tasklist.size(); i++) {
            Date date = tasklist.get(i).getSdexpirationdate();
            if (date != null) {
                sdexpirationdate.setTime(date);
                if (tasklist.get(i).getWorktype() != null && "紧急".equals(tasklist.get(i).getWorktype())) {
                    try {
                        if ("handleFeedback".equals(tasklist.get(i).getNextnodeid()) || "registerCase".equals(tasklist.get(i).getNextnodeid())
                                || "noRegisterCase".equals(tasklist.get(i).getNextnodeid())) {//处理反馈和是否立案要在一个工作日内处理
                            workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 1, sdexpirationdate.getTime().getHours(),
                                    sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                            workDay1 = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 2, sdexpirationdate.getTime().getHours(),
                                    sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                            String endDate = sdf.format(workDay);
                            String endDate1 = sdf.format(workDay1);
                            String startDate = sdf.format(date);
                            long date2 = CreateEntityInfoUtil.judgmentDate(startDate, endDate);
                            long date3 = CreateEntityInfoUtil.judgmentDate(startDate, endDate1);
                            if (date2 > 0 && date3 < 0) {
                                yellowCard++;
                            }
                            if (date3 > 0) {
                                redCard++;
                            }
                        } else {
                            String startDate = sdf.format(new Date());
                            String endDate = sdf.format(tasklist.get(i).getSdexpirationdate());
                            long result = CreateEntityInfoUtil.judgmentDate(startDate, endDate);
                            double date2 = result * 1.0 / (1000 * 60);
                            if (date2 > 0 && date2 < 10) {
                                warning++;
                            }
                            if (date2 > 30 && date2 < 60 && "interimReply".equals(tasklist.get(i).getNextnodeid())) {
                                yellowCard++;
                            }
                            if (date2 > 60 && "interimReply".equals(tasklist.get(i).getNextnodeid())) {
                                redCard++;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    String endDate = null;
                    String endDate1 = null;
                    String startDate = null;
                    if ("interimReply".equals(tasklist.get(i).getNextnodeid()) || "01".equals(tasklist.get(i).getFormtype())) {//通用表单和初步答复环节
                        sdexpirationdate.set(Calendar.HOUR, sdexpirationdate.get(Calendar.HOUR) + 22);
                        workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 0, sdexpirationdate.getTime().getHours(),
                                sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                        sdexpirationdate.set(Calendar.HOUR, sdexpirationdate.get(Calendar.HOUR) + 44);
                        workDay1 = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), 0, sdexpirationdate.getTime().getHours(),
                                sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                        endDate = sdf.format(workDay);
                        endDate1 = sdf.format(workDay1);
                        startDate = sdf.format(new Date());
                    } else {
                        int day = 0;
                        int day1 = 0;
                        if ("handleFeedback".equals(tasklist.get(i).getNextnodeid()) || "registerCase".equals(tasklist.get(i).getNextnodeid())
                                || "noRegisterCase".equals(tasklist.get(i).getNextnodeid())) {

                            if ("02".equals(tasklist.get(i).getFormtype())) {
                                day = 22;
                                day1 = 44;
                            } else {
                                day = 7;
                                day1 = 14;
                            }

                        } else {
                            if ("02".equals(tasklist.get(i).getFormtype())) {
                                day = 5;
                                day1 = 10;
                            } else {
                                day = 3;
                                day1 = 6;
                            }
                        }
                        workDay = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), day, sdexpirationdate.getTime().getHours(),
                                sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                        workDay1 = CreateEntityInfoUtil.getWorkDay(sdexpirationdate.getTime(), day1, sdexpirationdate.getTime().getHours(),
                                sdexpirationdate.getTime().getSeconds(), sdexpirationdate.getTime().getMinutes());
                        endDate = sdf.format(workDay);
                        endDate1 = sdf.format(workDay1);
                        startDate = sdf.format(new Date());
                    }
                    try {
                        long date2 = CreateEntityInfoUtil.judgmentDate(startDate, endDate);
                        long date3 = CreateEntityInfoUtil.judgmentDate(startDate, endDate1);
                        if (date3 > 0) {
                            redCard++;
                        }
                        if (date3 < 0 && date2 > 0) {
                            yellowCard++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        map.put("warning", warning);
        map.put("yellowCard", yellowCard);
        map.put("redCard", redCard);
        return map;
    }

    @Override
    public IPage<Tasklist> searchBusinessInfo(int current, int size, Map<String, String> requestMap) {
        Page<Tasklist> page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        return tasklistMapper.searchBusinessInfo(page, requestMap);
    }

    @Override
    public Tasklist searchBusinessInfo(Map<String, String> requestMap) {
        return tasklistMapper.searchBusinessInfo(requestMap);
    }

    /**
     * 首页已办业务统计
     *
     * @param aicUser
     * @return
     */
    @Override
    public Map<String, Object> searchFinishListCount(AICUser aicUser) {
        Map<String, Object> map = new HashMap<>();
        String userid = aicUser.getPrimaryKey();
        int integer0 = tasklistMapper.searchFinishListCount(userid, "01");
        map.put("sldjybdb", integer0);
        int integer1 = tasklistMapper.searchFinishListCount(userid, "02");
        map.put("bmfwybdb", integer1);
        int integer2 = tasklistMapper.searchFinishListCount(userid, "03");
        map.put("ywclybdb", integer2);
        return map;
    }

    /**
     * 分页查询已办工单列表
     *
     * @param current
     * @param size
     * @param aicUser
     * @param requestMap
     * @return
     */
    @Override
    public IPage<Tasklist> searchFinishListData(int current, int size, AICUser aicUser, Map<String, Object> requestMap) {
        Page<Tasklist> page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isNotBlank((String) requestMap.get("enddate"))) {
            try {
                Date sDate = sdf.parse((String) requestMap.get("enddate"));
                Calendar c = Calendar.getInstance();
                c.setTime(sDate);
                c.add(Calendar.DAY_OF_MONTH, 1);
                sDate = c.getTime();
                String endDate = sdf.format(sDate);
                requestMap.put("enddate", endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String userid = aicUser.getPrimaryKey();
        return tasklistMapper.searchFinishListData(page, userid, requestMap);
    }

    @Override
    public Map messageList() {

        return null;
    }

    /**
     * 受理登记暂存设置到期时间
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

    @Override
    public Map<String, Object> searchWarnCount(AICUser aicUser) {
        Map<String, Object> map = new HashMap<>();
        String userid = aicUser.getPrimaryKey();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        //msx 受理登记48小时内还没立案提醒
        String isregisterbegin = format.format(now);
        String isregisterend=format.format(now.getTime() + 48 * 60 * 60 * 1000);
        //msx 受理登记24小时还没有初步反馈提醒
        String feedbackbegin=format.format(now);
        String feedbackend=format.format(now.getTime() + 24 * 60 * 60 * 1000);
        //msx 受理登记48小时还没有进行最终反馈
        String finalbackbegin=format.format(now);
        String finalbackend=format.format(now.getTime() + 48 * 60 * 60 * 1000);

        //msx 立案预警数
        int sldjendCount = tasklistMapper.selectWarningCount(userid, isregisterbegin,isregisterend,"","","","");
        map.put("isregisterCount", sldjendCount);
        //msx 初步反馈预警数
        int feedbackCount = tasklistMapper.selectWarningCount(userid,"","",feedbackbegin,feedbackend,"","");
        map.put("isfeedbackCount", feedbackCount);
        //最终反馈数
        int finalbackCount = tasklistMapper.selectWarningCount(userid,"","","","",finalbackbegin,finalbackend);
        map.put("isfinalbackCount", finalbackCount);
        return map;
    }

}
