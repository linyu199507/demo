package com.chinaweal.sdcs.crcs.business.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.aicorg.services.OrgUM;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.service.*;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.*;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.*;
import com.chinaweal.sdcs.crcs.util.*;
import com.chinaweal.youfool.framework.rest.RestResult;
import com.chinaweal.youfool.framework.rest.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
@Api(tags = "TasklistController", description = "列表接口类")
@Controller
@RequestMapping("/tasklist")
public class TasklistController {

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

    @Qualifier("tasklistServiceImpl")
    @Autowired
    private ITasklistService iTasklistService;

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

    @Qualifier("inrequestServiceImpl")
    @Autowired
    private IInrequestService iInrequestService;

    @Qualifier("handledemandServiceImpl")
    @Autowired
    private IHandledemandService iHandledemandService;

    @Qualifier("consultationformServiceImpl")
    @Autowired
    private IConsultationformService iConsultationformService;

    @Autowired
    private OrgUM orgUM;
    /**
     * 业务待办列表查询
     *
     * @param requestMap
     * @return
     */
    @ApiOperation(
            tags = "TasklistController",
            value = "业务待办列表查询")
    @PostMapping("list")
    @ResponseBody
    public RestResult list(@ApiParam(value = "查询相关参数", required = true)
                           @RequestBody Map<String, Object> requestMap) {
        AICUser aicUser = null;
        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }
        int size = ((Integer) requestMap.get("size")).intValue();
        int currentPage = ((Integer) requestMap.get("page")).intValue();
        int total = ((Integer) requestMap.get("total")).intValue();
        if (total <= size) {
            currentPage = 1;
        }
        IPage<Tasklist> tasklistIPage = iTasklistService.searchTasklist(currentPage, size, aicUser, requestMap);
      
        return RestResult.ok(tasklistIPage);
    }

    /**
     * 工单已办列表查询
     *
     * @param requestMap
     * @return
     */
    @ApiOperation(
            tags = "TasklistController",
            value = "工单已办列表查询")
    @PostMapping("searchFinishListData")
    @ResponseBody
    public RestResult searchFinishListData(@ApiParam(value = "查询相关参数", required = true)
                                           @RequestBody Map<String, Object> requestMap) {
        int size = requestMap.get("size") != null ? ((Integer) requestMap.get("size")).intValue() : 10;
        int current = requestMap.get("page") != null ? ((Integer) requestMap.get("page")).intValue() : 1;
        AICUser aicUser = null;
        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }
        IPage<Tasklist> tasklistIPage = iTasklistService.searchFinishListData(current, size, aicUser, requestMap);
        return RestResult.ok(tasklistIPage);
    }

    /**
     * 待办信息确认
     *
     * @return
     */
    @ApiOperation(
            tags = "TasklistController",
            value = "待办信息确认")
    @PostMapping("confirmBaseInfo")
    @ResponseBody
    public RestResult confirmBaseInfo(@ApiParam(value = "待办信息确认参数", required = true)
                                      @RequestBody Map<String, Object> requestMap) {
        AICUser aicUser = null;
        Seats seats = null;
        Commommodule commommodule = null;
        Comeconomicmodule comeconomicmodule = null;
        Complaintmodule complaintmodule = null;
        Inrequest inrequest = null;
        Datagriddata datagriddata = null;
        String nodeId = (String) requestMap.get("node"); //当前节点
        String nextNodeId = (String) requestMap.get("nextNodeId"); //下一步处理的节点

        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }
        if (requestMap.get("seats") != null) {
            seats = (Seats) RequestUtil.mapToEntity(Seats.class, requestMap.get("seats"));
        }
        if (requestMap.get("commommodule") != null) {
            commommodule = (Commommodule) RequestUtil.mapToEntity(Commommodule.class, requestMap.get("commommodule"));
        }
        if (requestMap.get("comeconomicmodule") != null) {
            comeconomicmodule = (Comeconomicmodule) RequestUtil.mapToEntity(Comeconomicmodule.class, requestMap.get("comeconomicmodule"));
        }
        if (requestMap.get("complaintmodule") != null) {
            complaintmodule = (Complaintmodule) RequestUtil.mapToEntity(Complaintmodule.class, requestMap.get("complaintmodule"));
        }
        if (requestMap.get("inrequest") != null) {
            inrequest = (Inrequest) RequestUtil.mapToEntity(Inrequest.class, requestMap.get("inrequest"));
        }
        if (requestMap.get("datagriddata") != null) {
            datagriddata = (Datagriddata) RequestUtil.mapToEntity(Datagriddata.class, requestMap.get("datagriddata"));
        }
        try {
            if (seats != null) {
                iSeatsService.saveOrUpdate(seats);
            }
            if (commommodule != null) {
                iCommommoduleService.saveOrUpdate(commommodule);
            }
            if (comeconomicmodule != null) {
                iComeconomicmoduleService.saveOrUpdate(comeconomicmodule);
            }
            if (complaintmodule != null) {
                iComplaintmoduleService.saveOrUpdate(complaintmodule);
            }
            if (inrequest != null) {
                iInrequestService.saveOrUpdate(inrequest);
            }

            //处理意见
            QueryWrapper<Askdepthandle> askdepthandleWrapper = new QueryWrapper<Askdepthandle>();
            askdepthandleWrapper.lambda().eq(Askdepthandle::getCaseguid, datagriddata.getCaseguid());
            List<Askdepthandle> askdepthandleList = iAskdepthandleService.list(askdepthandleWrapper);

            //启动系统流程待办
            Boolean flag = startTask(aicUser, seats, commommodule, comeconomicmodule, complaintmodule, inrequest, datagriddata, askdepthandleList, nodeId, nextNodeId);
            if (flag) {
                //更新状态为是否已处理
                datagriddata.setStatus("1");
                datagriddata.setUpdatedate(new Date());
                iDatagriddataService.updateById(datagriddata);
            } else {
                return RestResult.error(ResultCode.MYSTERIOUS_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return RestResult.error(ResultCode.MYSTERIOUS_ERROR);
        }
        return RestResult.ok();
    }

    /**
     * 启动12345系统流程待办
     *
     * @param aicUser
     * @param seats
     * @param commommodule
     * @param comeconomicmodule
     * @param complaintmodule
     * @param inrequest
     * @param datagriddata
     * @param askdepthandleList
     * @return
     */
    public Boolean startTask(AICUser aicUser, Seats seats, Commommodule commommodule, Comeconomicmodule comeconomicmodule, Complaintmodule complaintmodule, Inrequest inrequest, Datagriddata datagriddata, List<Askdepthandle> askdepthandleList, String nodeId, String nextNodeId) {
        boolean flag = false;
        try {
            String caseguid = datagriddata.getCaseguid();
            Tasklist tasklist = CreateEntityInfoUtil.createTasklist(datagriddata, aicUser);

            //话务信息
            iCustomerinfoService.saveOrUpdate(CreateEntityInfoUtil.createCustomerinfo(seats));
            //通用表单
            Commomform commomform = CreateEntityInfoUtil.createCommomform(commommodule);
            if (commomform != null) {
                iCommomformService.saveOrUpdate(commomform);
                tasklist.setFormtype(BaseDataUtil.getFormType(BaseDataUtil.TASKLIST_FORMTYPE_COMMOMMODULE));
            }
            //经济违法表单
            Economyform economyform = CreateEntityInfoUtil.createEconomyform(comeconomicmodule);
            if (economyform != null) {
                iEconomyformService.saveOrUpdate(economyform);
                tasklist.setFormtype(BaseDataUtil.getFormType(BaseDataUtil.TASKLIST_FORMTYPE_COMECONOMICMODULE));
            }
            //消费投诉表单
            Complaintform complaintform = CreateEntityInfoUtil.createComplaintform(complaintmodule);
            if (complaintform != null) {
                iComplaintformService.saveOrUpdate(complaintform);
                tasklist.setFormtype(BaseDataUtil.getFormType(BaseDataUtil.TASKLIST_FORMTYPE_COMPLAINTMODULE));
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
                iHandledemandService.saveOrUpdate(handledemand);
            }
            //流程表记录
            Processinfo processinfo = CreateEntityInfoUtil.createProcessinfo(caseguid, aicUser);
            if (processinfo != null) {
                processinfo.setNodeid(nextNodeId);//当前节点此处要待办确定。
                processinfo.setFromnodeid(nextNodeId);//下一个节点
                processinfo.setIsmain("1");
                iProcessinfoService.saveOrUpdate(processinfo);
            }
            flag = true;

            //待办信息
            tasklist.setNextnodeid(nextNodeId);//下一个节点
            tasklist.setFromnodeid(nodeId);
            iTasklistService.saveOrUpdate(tasklist);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 受理登记信息保存
     *
     * @param requestMap
     * @return
     */
    @ApiOperation(
            tags = "TasklistController",
            value = "受理登记信息保存")
    @PostMapping("saveBaseInfo")
    @ResponseBody
    public RestResult saveBaseInfo(@ApiParam(value = "受理登记信息", required = true)
                                   @RequestBody Map<String, Object> requestMap) {
        AICUser aicUser = null;
        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }
        Map searchMap = iTasklistService.startTaskInfo(aicUser, requestMap);

        String result = searchMap.get("result").toString();

        if (result.equals("true")) {
            return RestResult.ok(searchMap);
        } else {
            return RestResult.error(ResultCode.MYSTERIOUS_ERROR);
        }

    }

    @ApiOperation(
            tags = "TasklistController",
            value = "待办删除")
    @GetMapping("deleteTasklist")
    @ResponseBody
    public RestResult deleteTasklist(@ApiParam(value = "待办列表信息", required = true)
                                     @RequestParam String[] ids, @RequestParam Object user) {
        AICUser aicUser = null;
        if (user != null) {
            JSONObject userJson = JSONObject.parseObject(user.toString());
            aicUser = JSON.toJavaObject(userJson, AICUser.class);
        }
        if (aicUser == null) {
            aicUser = new AICUser();
        }
        iTasklistService.deleteByIds(ids, aicUser);
        return RestResult.ok();
    }

    @ApiOperation(
            tags = "TasklistController",
            value = "12345待办基本信息保存")
    @PostMapping("saveBmfwBaseInfo")
    @ResponseBody
    public RestResult saveBmfwBaseInfo(@ApiParam(value = "表单基本信息", required = true)
                                       @RequestBody Map<String, Object> requestMap) {
        AICUser aicUser = null;
        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }
        Map searchMap = iTasklistService.saveBmfwBaseInfo(requestMap);

        String result = searchMap.get("result").toString();

        Map<String, Object> map = new HashMap<>();


        if (requestMap.get("complaintform") != null) {
            Complaintform complaintform = (Complaintform) RequestUtil.mapToEntity(Complaintform.class, requestMap.get("complaintform"));
            map.put("complaintform", complaintform);
            map.put("type", "complaintform");
        }
        if (requestMap.get("commomform") != null) {
            Commomform commomform = (Commomform) RequestUtil.mapToEntity(Commomform.class, requestMap.get("commomform"));
            map.put("commomform", commomform);
            map.put("type", "commomform");
        }
        if (requestMap.get("economyform") != null) {
            Economyform economyform = (Economyform) RequestUtil.mapToEntity(Economyform.class, requestMap.get("economyform"));
            map.put("economyform", economyform);
            map.put("type", "economyform");
        }

        if (result.equals("true")) {
//            return RestResult.ok(searchMap.get("caseguid"));
            return RestResult.ok(map);
        } else {
            return RestResult.error(ResultCode.MYSTERIOUS_ERROR);
        }
    }

    @ApiOperation(
            tags = "TasklistController",
            value = "待办业务签收")
    @PostMapping("signTasklist")
    @ResponseBody
    public RestResult signTasklist(@ApiParam(value = "用户信息", required = true)
                                   @RequestBody Map<String, Object> requestMap) {
        AICUser aicUser = null;
        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }
        String caseguid = (String) requestMap.get("caseguid");
        //上一步处理的节点
//        String fromnodeid = (String) requestMap.get("fromnodeid");
        String fromnodeid = (String) requestMap.get("nextnodeid");
        String ismain = (String) requestMap.get("ismain");
        iTasklistService.signTasklist(aicUser, caseguid, fromnodeid, ismain);
        return RestResult.ok();
    }

    /**
     * 红黄牌统计
     *
     * @param requestMap
     * @return
     */
    @ApiOperation(
            tags = "TasklistController",
            value = "红黄牌统计")
    @PostMapping("cardsCount")
    @ResponseBody
    public RestResult cardsCount(@ApiParam(value = "查询相关参数", required = true)
                                 @RequestBody Map<String, Object> requestMap) {
        AICUser aicUser = null;
        if (requestMap.get("user") != null) {
            aicUser = (AICUser) RequestUtil.mapToEntity(AICUser.class, requestMap.get("user"));
        }
        Map<String, Object> map = iTasklistService.selectTasklist(aicUser);
        return RestResult.ok(map);
    }

}
