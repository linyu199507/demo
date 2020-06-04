package com.chinaweal.sdcs.crcs.util;

import java.util.*;

public class BaseDataUtil {

    // 流程节点对应的中文名称Map
    private static final Map<String, String> WORKFLOWNODE_MAP = newHashMap(16);

    private static final Map<String, String> TASKLIST_FORMTYPE_MAP = newHashMap(10);

    public static final String WORKFLOW_NODE_INTERIMREPLY = "interimReply"; // 初步答复
    public static final String WORKFLOW_NODE_ACCEPT = "accept"; // 受理
    public static final String WORKFLOW_NODE_NOACCEPT = "noAccept"; // 不受理
    public static final String WORKFLOW_NODE_REGISTERCASE = "registerCase";  // 立案
    public static final String WORKFLOW_NODE_NOREGISTERCASE = "noRegisterCase"; // 不予立案
    public static final String WORKFLOW_NODE_LEADERAPR = "leaderApr"; // 领导审核
    public static final String WORKFLOW_NODE_MOVETOLOWERORG = "moveToLowerOrg"; // 分派下级机关
    public static final String WORKFLOW_NODE_MOVETOLOWERDEPT = "moveToLowerDept"; // 分派其他部门
    public static final String WORKFLOW_NODE_MOVETOLOWERPERSONNEL = "moveToLowerPersonnel"; // 分派下级人员
    public static final String WORKFLOW_NODE_APPLYDELAY = "applyDelay"; // 申请延期
    public static final String WORKFLOW_NODE_END = "end"; // 结束
    public static final String WORKFLOW_NODE_BACKFORWARD = "backForward"; // 退回
    public static final String WORKFLOW_NODE_STAGING = "staging"; // 暂存
    public static final String WORKFLOW_NODE_HANDLEFEEDBACK = "handleFeedback"; // 处理反馈
    public static final String WORKFLOW_NODE_BEGIN = "begin"; // 开始
    public static final String WORKFLOW_NODE_PENDING = "pending"; // 待处理
    public static final String WORKFLOW_NODE_NEXTPROCESSING = "nextProcessing";//下一级部门处理
    public static final String WORKFLOW_NODE_SUPERIORPROCESSING = "superiorProcessing";//上级部门处理
    public static final String WORKFLOW_NODE_BMFWTODETERMINE = "bmfwToDetermine";//12345待办确定
    public static final String WORKFLOW_NODE_REGIONAUDIT = "regionAudit";//区局处理
    public static final String WORKFLOW_NODE_COPYTOOPINION = "copyToOpinion";//抄送意见
    public static final String WORKFLOW_NODE_TODISPATCH = "todispatch";//待分流
    public static final String WORKFLOW_NODE_WITHDRAW = "withdraw";//待办收回
    public static final String WORKFLOW_NODE_COLLABORATIVE = "collaborative";//部门协同

    public static final String WORKFLOW_NODE_INTERIMREPLY_INTERIMREPLY = "interimReply_interimReply"; // 初步答复_初步答复
    public static final String WORKFLOW_NODE_ACCEPT_ACCEPT = "accept_accept"; // 受理_受理
    public static final String WORKFLOW_NODE_NOACCEPT_END = "noAccept_end"; // 不受理
    public static final String WORKFLOW_NODE_NOREGISTERCASE_BACKFORWARD = "noRegisterCase_backForward"; // 不予立案_退回上一步
    public static final String WORKFLOW_NODE_REGISTERCASE_REGISTERCASE = "registerCase_registerCase";  // 立案
    public static final String WORKFLOW_NODE_REGISTERCASE_LEADERAPR = "registerCase_leaderApr";  // 立案_送领导审核
    public static final String WORKFLOW_NODE_LEADERAPR_END = "leaderApr_end";  // 领导审核_反馈
    public static final String WORKFLOW_NODE_REGISTERCASE_MOVETOLOWERORG = "registerCase_moveToLowerOrg";  // 立案_分派下级机关
    public static final String WORKFLOW_NODE_REGISTERCASE_MOVETOLOWERDEPT = "registerCase_moveToLowerDept";  // 立案_分派下级部门
    public static final String WORKFLOW_NODE_LEADERAPR_BACKFORWARD = "leaderApr_backForward"; // 领导审核_退回上一步
    public static final String WORKFLOW_NODE_MOVETOLOWERORG_LEADERAPR = "moveToLowerOrg_leaderApr"; // 下级机关_送领导审核

    public static final String TASKLIST_FORMTYPE_COMMOMMODULE = "commommodule"; // 通用表单
    public static final String TASKLIST_FORMTYPE_COMPLAINTMODULE = "complaintmodule"; // 消费投诉
    public static final String TASKLIST_FORMTYPE_COMECONOMICMODULE = "comeconomicmodule"; // 经济违法

    private static final Map<String, String> TASKLIST_BUSTYPE_MAP = newHashMap(3);

    private static void initStaticWorkFlowMap() {
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_INTERIMREPLY, "初步答复"); // 初步答复
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_ACCEPT, "受理"); // 受理
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_NOACCEPT, "不受理"); // 不受理
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_REGISTERCASE, "立案"); // 立案
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_NOREGISTERCASE, "不予立案"); // 不予立案
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_LEADERAPR, "送领导审核"); // 领导审核
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_MOVETOLOWERORG, "分派下级机关"); // 分派下级机关
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_MOVETOLOWERDEPT, "分派其他部门"); // 分派其他部门
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_MOVETOLOWERPERSONNEL, "分派下级人员"); // 分派下级人员
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_APPLYDELAY, "申请延期"); // 申请延期
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_END, "结束"); // 结束
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_BACKFORWARD, "退回"); // 结束
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_STAGING, "暂存"); // 暂存
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_HANDLEFEEDBACK, "处理反馈"); // 处理反馈
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_BEGIN, "待分派"); // 开始改为待分派  2019-11-12
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_PENDING, "待处理"); // 待处理
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_NEXTPROCESSING, "下一级部门处理"); // 下一级部门处理
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_SUPERIORPROCESSING, "上级部门处理"); // 上级部门处理
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_BMFWTODETERMINE, "12345待办确定"); // 12345待办确定
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_REGIONAUDIT, "区局处理"); // 区局处理
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_COPYTOOPINION, "抄送意见"); // 抄送意见
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_TODISPATCH, "待分流"); // 待分流
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_WITHDRAW, "待办收回"); //
        WORKFLOWNODE_MAP.put(WORKFLOW_NODE_COLLABORATIVE, "初步反馈"); //
    }

    private static void initStaticFormTypeMap() {
        TASKLIST_FORMTYPE_MAP.put(TASKLIST_FORMTYPE_COMMOMMODULE, "01"); // 通用表单
        TASKLIST_FORMTYPE_MAP.put(TASKLIST_FORMTYPE_COMPLAINTMODULE, "02"); // 消费投诉
        TASKLIST_FORMTYPE_MAP.put(TASKLIST_FORMTYPE_COMECONOMICMODULE, "03"); // 经济违法
    }

    private static void initStaticBusTypeMap() {
        TASKLIST_BUSTYPE_MAP.put("01", "咨询"); // 咨询
        TASKLIST_BUSTYPE_MAP.put("02", "投诉"); // 投诉
        TASKLIST_BUSTYPE_MAP.put("03", "举报"); // 举报
    }

    /**
     * 创建一个新的HashMap对象
     *
     * @param size 集合中实际要存放的元素的数量
     * @return
     */
    private static Map<String, String> newHashMap(int size) {
        return new LinkedHashMap<String, String>((int) Math.ceil(size / 0.75));
    }

    static {
        initStaticWorkFlowMap();
        initStaticFormTypeMap();
        initStaticBusTypeMap();
    }

    public static String getNodeNameByNodeId(String s) {
        return WORKFLOWNODE_MAP.get(s);
    }

    public static String getFormType(String s) {
        return TASKLIST_FORMTYPE_MAP.get(s);
    }

    /**
     * 根据表单类型获取业务类型
     * @param formType
     * @return
     */
    public static String getBusType(String formType) {
        return TASKLIST_BUSTYPE_MAP.get(formType);
    }
}
