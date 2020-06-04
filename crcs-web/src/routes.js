import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import BacklogNotarize from './views/BacklogNotarize.vue'
import LocalBacklog from './views/nav1/LocalBacklog.vue'
import accraditation from './views/nav1/logParticulars/accraditation.vue'
import examine from './views/nav1/logParticulars/examine.vue'
import leaderExamine from './views/nav1/logParticulars/leaderExamine.vue'
import endFlow from './views/nav1/logParticulars/endFlow.vue'
import AcceptanceEnforce from './views/nav1/enforce/AcceptanceEnforce'
import OtherAcceptanceEnforce from './views/nav1/enforce/OtherAcceptanceEnforce'
import ExamineEnforce from './views/nav1/enforce/ExamineEnforce'
import ComplaintApprovalEnforce from './views/nav1/enforce/ComplaintApprovalEnforce'
import ProcessingResultsEnforce from './views/nav1/enforce/ProcessingResultsEnforce'
import AcceptanceConsult from './views/nav1/consult/AcceptanceConsult'
import OtherAcceptanceConsult from './views/nav1/consult/OtherAcceptanceConsult'
import ExamineConsult from './views/nav1/consult/ExamineConsult'
import ComplaintApprovalConsult from './views/nav1/consult/ComplaintApprovalConsult'
import ProcessingResultsConsult from './views/nav1/consult/ProcessingResultsConsult'
import particulars from './views/nav1/logParticulars/particulars.vue'
import undertake from './views/nav1/logParticulars/undertake.vue'
import Organizer from './views/nav1/logParticulars/Organizer.vue'
import DisposeCase from './views/nav1/logParticulars/DisposeCase.vue'
import ReportQuery from './views/report/ReportQuery'
import QueryResults from './views/report/QueryResults'
import LeaderAudit from './views/nav1/logParticulars/LeaderAudit'
import login from './views/aicorg/login'
import basicData from './views/aicorg/basicData'
import dataConfiguration from './views/aicorg/dataConfiguration'
import ComprehensiveQuery from './views/comprehensive/ComprehensiveQuery'
import ComprehensiveList from './views/comprehensive/ComprehensiveList'
import OtherAcceptanceEnforceTemp from './views/nav1/enforceTemp/OtherAcceptanceEnforce'
import ConsultFrom from './views/nav1/enforceTemp/ConsultFrom'
import ComplaintsFrom from './views/nav1/enforceTemp/ComplaintsFrom'
import DisposeCases from './views/nav1/enforceTemp/DisposeCases'
import FromExamine from './views/nav1/enforceTemp/FromExamine'
import LeadershipAudit from './views/nav1/enforceTemp/LeadershipAudit'
import ApplicationDelay from './views/acceptanceRegistration/ApplicationDelay'
import ComplaintApprovalEnforceTemp from './views/nav1/enforceTemp/ComplaintApprovalEnforce'
import ProcessingResultsEnforceTemp from './views/nav1/enforceTemp/ProcessingResultsEnforce'
import ApplyPostpone from "./views/nav1/logParticulars/ApplyPostpone";
import RegionAudit from './views/nav1/enforceTemp/RegionAudit'
/*import UnhandledQuery from "./views/comprehensive/UnhandledQuery.vue"
import MoredeadlineQuery from "./views/comprehensive/MoredeadlineQuery.vue"*/
import TasklistInfoLocal from "./views/acceptanceRegistration/TasklistInfoLocal.vue"
import Developing from "./views/systemTips/Developing.vue"
import TypeStatistical from "./views/systemTips/TypeStatistical.vue"
import Warning from "./views/systemTips/Warning.vue"
import HighFrequency from "./views/systemTips/HighFrequency.vue"
import PieChart from "./views/systemTips/pieChart.vue"
// 数据统计
import monChart from "./views/systemTips/monChart.vue"

import modifypassword from "./views/aicorg/modifypassword.vue"
// 引入控制面板页面
import ControlPannel from './views/nav1/controlPannel.vue'
// 引入领导视觉面板
import ControlPannelLeader from './views/nav1/controlPannelLeader.vue'
//引入工单已办
import EndOfBusiness from './views/nav1/EndOfBusiness.vue'
// 引入材料查询
import MaterialQuery from './views/comprehensive/materialQuery.vue'
import MaterialQueryResult from './views/comprehensive/MaterialQueryResult.vue'
import MaterialDetail from './views/comprehensive/MaterialQueryDetail.vue'

import DeadlineManagement from "./views/system/DeadlineManagement.vue"
import DeadlineManagementAdd from "./views/system/DeadlineManagementAdd.vue"
import DeadlineManagementEdit from "./views/system/DeadlineManagemenEdit.vue"

import basicDataModify from './views/aicorg/basicDataModify'
import ListDetail from './views/listDetail'
import NotDealListDetail from './views/notDealListDetail'
import WasDealList12315 from './views/12315system/wasDealList12315.vue'
import DealListContent from './views/12315system/dealListContent.vue'
import DevelopmentLogin from './views/developmentLogin.vue'
// 新的受理登记
import Consulations from './views/sldj/sldj.vue'
import sldjApproval from './views/sldj/sldjApproval.vue'
import sldjDispose from './views/sldj/sldjDispose.vue'
import sldjFeedback from './views/sldj/sldjFeedback.vue'
import sldjPostpone from './views/sldj/sldjPostpone.vue'
import sldjRegionAudit from './views/sldj/sldjRegionAudit.vue'
import sldjRegister from './views/sldj/sldjRegister.vue'

import doneList12315 from '../src/views/alreadydone/12315Search.vue'
import doneList12345 from '../src/views/alreadydone/12345Search.vue'
import sldjDealList from "../src/views/alreadydone/sldjSearch.vue"
//新分派工单列表
import NewFormList from "./views/NewFormList.vue"
//引入即将到期待办列表页面
import expected from './views/expected/expected.vue'


//历史统计数据页面
import Statisticsinfo from './views/report/Statisticsinfo.vue'
// 图形统计
import Advancedbusiness from './views/systemTips/Advancedbusiness.vue'
// 维权服务站工单列表
import rightsList from './views/rightsProtection/rightsList.vue'
// 维权服务站工单详情
import rightsForm from './views/rightsProtection/rightsForm.vue'

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true

    },
    {
        path: '/developmentLogin',
        component: DevelopmentLogin,
        name: '',
        hidden: true

    },
    // 新打开窗口
    {
        path: '/listDetail',
        component: ListDetail,
        name: '',
        hidden: true,
        children: [
            { path: '/listDetail', component: () => import('./views/12345system/acceptance-of-next.vue'), name: '处理反馈' },
            { path: '/listDetail/gdsh', component: () => import('./views/12345system/work-review.vue'), name: '工单审核' },
            { path: '/listDetail/lacl', component: () => import('./views/12345system/filing.vue'), name: '立案处理' },
            { path: '/listDetail/slbslfpth', component: () => import('./views/12345system/preliminary-of-next.vue'), name: '工单处理' },
            { path: '/listDetail/ldsp', component: () => import('./views/12345system/processing-approval.vue'), name: '领导审批' },
            { path: '/listDetail/qjcl', component: () => import('./views/12345system/processing-regionAudit.vue'), name: '区局处理' },
            { path: '/listDetail/yqsp', component: () => import('./views/12345system/delay-approval.vue'), name: '延期审批' },
            { path: '/listDetail/clfpysth', component: () => import('./views/12345system/acceptance-of-next.vue'), name: '处理反馈' },
            { path: '/listDetail/12315DealBusiness', component: () => import('./views/12315system/12315DealBusiness.vue'), name: '12315业务处理' },
            { path: '/listDetail/12315DealBusiness/processingForm', component: () => import('./views/12315system/processingForm.vue'), name: '核查业务' },
            { path: '/listDetail/12315tsgdsh', component: () => import('./views/12315system/Complaintprocessing.vue'), name: '投诉工单审核' },
            { path: '/listDetail/12315tsgdcl', component: () => import('./views/12315system/12315dispose-complaint.vue'), name: '投诉工单处理' },
            { path: '/listDetail/12315tsgdfk', component: () => import('./views/12315system/12315feedback-complaint.vue'), name: '投诉工单反馈' },
            { path: '/listDetail/12315tsldsp', component: () => import('./views/12315system/12315approval-complaint.vue'), name: '投诉领导审批' },
            { path: '/listDetail/12315tsqjcl', component: () => import('./views/12315system/12315regionAudit-complaint.vue'), name: '投诉区局处理' },
            { path: '/listDetail/12315tsyqsp', component: () => import('./views/12315system/12315postpone-complaint.vue'), name: '投诉延期审批' },
            { path: '/listDetail/12315jbgdsh', component: () => import('./views/12315system/Econmyprocessing.vue'), name: '经济违法工单审核' },
            { path: '/listDetail/12315jbgdcl', component: () => import('./views/12315system/12315dispose-econmy.vue'), name: '经济违法工单处理' },
            { path: '/listDetail/12315jbgdfk', component: () => import('./views/12315system/12315feedback-econmy.vue'), name: '经济违法工单反馈' },
            { path: '/listDetail/12315jbldsp', component: () => import('./views/12315system/12315approval-econmy.vue'), name: '经济违法领导审批' },
            { path: '/listDetail/12315jbqjcl', component: () => import('./views/12315system/12315regionAudit-econmy.vue'), name: '经济违法区局处理' },
            { path: '/listDetail/12315jbyqsp', component: () => import('./views/12315system/12315postpone-econmy.vue'), name: '经济违法延期审批' },
            { path: '/listDetail/12315detail', component: () => import('./views/12315system/dealListContent.vue'), name: '12315系统待办详情' },
            { path: '/listDetail/12315jblacl', component: () => import('./views/12315system/12315register-econmy.vue'), name: '立案核查' },
        ]

    }, {
        path: '/notDealListDetail',
        component: NotDealListDetail,
        name: '',
        hidden: true,
        children: [
            { path: '/notDealListDetail/qtsl', name: '处理反馈', component: OtherAcceptanceEnforceTemp },
            { path: '/notDealListDetail/sldjla', name: '立案环节', component: DisposeCases },
            { path: '/notDealListDetail/ldsh', name: '领导审批', component: LeadershipAudit },
            { path: '/notDealListDetail/qjcl', name: '区局处理', component: RegionAudit },
            { path: '/notDealListDetail/sqyq', name: '延期审批', component: ApplicationDelay },
        ]
    },{//受理登记子窗口页面
        path: '/acceptRegister',
        component: NotDealListDetail,
        name: '',
        hidden: true,
        children: [
            { path: '/acceptRegister/gdcl', name: '工单处理', component: sldjDispose },
            { path: '/acceptRegister/gdfk', name: '工单反馈', component: sldjFeedback },
            { path: '/acceptRegister/lacl', name: '立案处理', component: sldjRegister },
            { path: '/acceptRegister/ldsh', name: '领导审批', component: sldjApproval },
            { path: '/acceptRegister/yqsp', name: '延期审批', component: sldjPostpone },
            { path: '/acceptRegister/qjcl', name: '区局处理', component: sldjRegionAudit },
        ]
    },

    {
        path: '/',//默认根目录
        name: '',
        hidden: true,
        redirect: { path: '/controlpannelLeader' },
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    {
        path: '/wddb',
        name: '我的待办',
        component: Home,
        iconCls: 'fa fa-envelope',//图标样式class
        // redirect: { path: '/wddb/dfpyw' },
        children: [
            //我的待办->业务分配

            //新分派工单列表
            { path: '/wddb/xfpgd', component: NewFormList, name: '新派工单列表' },

            { path: '/wddb/bxtdb', component: LocalBacklog, name: '受理登记待办' },
            { path: '/wddb/sldj', component: Consulations, name: '受理登记' },
            { path: '/wddb/busState', component: () => import('./views/nav1/LocalBusState.vue'), name: '工单回填失败列表' },


            // 12315系统待办
            { path: '/wddb/12315db', name: '12315系统待办', component: WasDealList12315 },
            { path: '/wddb/12315db/DealContent', name: '12315系统待办详情', component: DealListContent },

            //我的待办->12345系统待办
            { path: '/wddb/12345db', component: () => import('./views/12345system/upcoming-list.vue'), name: '12345系统待办' },
            { path: '/wddb/12345db/slbslfpth', component: () => import('./views/12345system/preliminary-of-next.vue'), name: '工单处理' },
            { path: '/wddb/12345db/clfpysth', component: () => import('./views/12345system/acceptance-of-next.vue'), name: '处理反馈' },
            { path: '/wddb/12345db/ldsp', component: () => import('./views/12345system/processing-approval.vue'), name: '领导审批' },
            { path: '/wddb/12345db/yqsp', component: () => import('./views/12345system/delay-approval.vue'), name: '延期审批' },
            { path: '/wddb/12345db/lacl', component: () => import('./views/12345system/filing.vue'), name: '立案处理' },
            { path: '/wddb/12345db/qjcl', component: () => import('./views/12345system/processing-regionAudit.vue'), name: '区局处理' },


            //受理登记
            { path: '/sldj', name: '受理登记' },
            // 控制面板
            { path: '/controlpannel', name: '我的桌面', component: ControlPannel },
            // 领导视觉面板
            { path: '/ControlPannelLeader', name: '我的桌面', component: ControlPannelLeader },
            { path: '/controlpannelLeader/gdyb', component: EndOfBusiness, name: '工单已办' },

            //受理登记页面  12.28
            { path: '/sldj/zxdj', name: '受理登记', component: Consulations },
            { path: '/wddb/bxtdb/gdcl', name: '工单处理', component: sldjDispose },
            { path: '/wddb/bxtdb/gdfk', name: '工单反馈', component: sldjFeedback },
            { path: '/wddb/bxtdb/lacl', name: '立案处理', component: sldjRegister },
            { path: '/wddb/bxtdb/ldsh', name: '领导审批', component: sldjApproval },
            { path: '/wddb/bxtdb/yqsp', name: '延期审批', component: sldjPostpone },
            { path: '/wddb/bxtdb/qjcl', name: '区局处理', component: sldjRegionAudit },


        ]
    },
    {
        path: '/xtgl',
        component: Home,
        name: '系统管理',
        iconCls: 'fa fa-id-card-o',
        redirect: { path: '/xtgl/jcsj' },
        children: [
            //系统管理->组织架构
            // {path: '/xtgl/zjjg', name: '组织架构', redirect: {path: '/xtgl/zjjg/yhgl'}},
            // {path: '/xtgl/zjjg/yhgl', name: '用户管理', component: login},
            // {path: '/xtgl/zjjg/qxgl', name: '权限管理'},
            { path: '/xtgl/zjjg', name: '组织架构管理', component: login },
            // {path: '/xtgl/zjjg', name: '组织架构管理', component: Developing},
            //系统管理
            // {path: '/xtgl/fwpzgl', name: '服务配置管理'},
            // {path: '/xtgl/jcsj', name: '基础数据配置'},
            // {path: '/xtgl/czrzcx', name: '操作日志查询'},
            // {path: '/xtgl/ywsxpz', name: '业务时限配置'},
            // {path: '/xtgl/dxgnpz', name: '短信功能配置'},
            // {path: '/xtgl/spygl', name: '审批语管理'},
            { path: '/xtgl/fwpzgl', name: '服务配置管理', component: Developing },
            { path: '/xtgl/jcsj', name: '基础数据配置', component: basicData },
            { path: '/xtgl/jcsj/pz', name: '配置', component: dataConfiguration },
            { path: '/xtgl/jcsj/edit', name: '基础数据配置编辑', component: basicDataModify },
            { path: '/xtgl/czrzcx', name: '操作日志查询', component: Developing },
            { path: '/xtgl/ywsxpz', name: '业务时限配置', component: DeadlineManagement },
            { path: '/xtgl/ywsxpz/add', name: '业务时限配置增加', component: DeadlineManagementAdd },
            { path: '/xtgl/ywsxpz/edit', name: '业务时限配置编辑', component: DeadlineManagementEdit },
            { path: '/xtgl/dxgnpz', name: '短信功能配置', component: Developing },
            { path: '/xtgl/spygl', name: '审批语管理', component: Developing },
        ]
    },
    {
        path: '/grxiwh',
        component: Home,
        name: '个人信息维护',
        iconCls: 'fa fa-address-card',
        redirect: { path: '/grxiwh/cyspy' },
        children: [
            //个人信息维护
            // {path: '/grxiwh/cyspy', name: '常用审批语'},
            { path: '/grxiwh/mmxg', name: '密码修改', component: modifypassword },
            { path: '/grxiwh/cyspy', name: '常用审批语', component: Developing },
            { path: '/grxiwh/mmxg', name: '密码修改', component: Developing }
        ]
    },
    {
        path: '/tjfx',
        component: Home,
        name: '统计分析',
        redirect: { path: '/tjfx/zhcx' },
        children: [
            //统计分析->综合查询
            { path: '/tjfx/zhcx', name: '综合查询' },
            { path: '/tjfx/zhcx/ywjlcx', name: '业务记录查询', component: ComprehensiveQuery },
            { path: '/tjfx/zhcx/cljlcx', name: '材料记录查询', component: MaterialQuery },
            { path: '/tjfx/zhcx/cljlcx/cljlcxjg', name: '材料记录查询结果列表', component: MaterialQueryResult },
            { path: '/tjfx/zhcx/cljlcx/clxq', name: '材料详情', component: MaterialDetail },
            { path: '/tjfx/zhcx/ywjlcx/cxjg', name: '查询结果', component: ComprehensiveList },
            { path: '/tjfx/zhcx/ywjlcx/cxjg/ywjlxq', name: '业务记录详情', component: TasklistInfoLocal },
            { path: '/tjfx/zhcx/cqjlcx/12345lists/ywjlxq', name: '12345列表详情', component: TasklistInfoLocal },
            { path: '/tjfx/zhcx/wbjlcx/12315lists/ywjlxq', name: '12315列表详情', component: TasklistInfoLocal },
            { path: '/tjfx/zhcx/sldjcx/sldj/ywjlxq', name: '受理待办列表详情', component: TasklistInfoLocal },
            { path: '/tjfx/zhcx/wbjlcx', name: '12315已办列表查询', component: doneList12315 },
            { path: '/tjfx/zhcx/cqjlcx', name: '12345已办列表查询', component: doneList12345 },
            { path: '/tjfx/zhcx/sldjcx', name: '受理登记已办列表查询', component: sldjDealList },
            { path: '/tjfx/zhcx/expected', name: '即将到期', component: expected },
            { path: '/tjfx/zhcx/wqfwz', name: '维权服务站工单列表', component: rightsList },
            { path: '/tjfx/zhcx/wqfwz/gdxq', name: '维权服务站工单详情', component: rightsForm },
            //统计分析->统计表表
            { path: '/tjfx/tjbb', name: '统计报表', redirect: { path: '/tjfx/tjbb/ywbb' } },
            //统计分析->统计报表->常用报表
            { path: '/tjfx/tjbb/cybb', name: '常用图表', redirect: { path: '/tjfx/tjbb/cybb/gs' } },
            // { path: '/tjfx/tjbb/cybb/business', name: '智能服务平台图表统计---投诉举报概况', component: monChart },
            { path: '/tjfx/tjbb/cybb/Advancedbusiness', name: '智能服务平台图表统计---投诉举报概况', component:  Advancedbusiness},
            { path: '/tjfx/tjbb/cybb/type', name: '智能服务平台图表统计---类型统计', component: TypeStatistical },
            { path: '/tjfx/tjbb/cybb/high', name: '智能服务平台图表统计---高频词统计', component: HighFrequency },
            { path: '/tjfx/tjbb/cybb/warn', name: '智能服务平台图表统计---预警', component: Warning },
            //统计分析->统计表表
            { path: '/tjfx/tjbb/bbcx', name: '报表查询', component: ReportQuery },
            { path: '/tjfx/tjbb/bbcx/cxjg', name: '查询结果', component: QueryResults },
            { path: '/tjfx/tjbb/ywbb', name: '业务报表', component: PieChart },
            { path: '/tjfx/tjbb/xbbb', name: '消保报表', redirect: { path: '/tjfx/tjbb/xbbb/slcljb' } },
            { path: '/tjfx/tjbb/lstjsj', name: '历史统计数据', component: Statisticsinfo },
            //统计分析->统计表表->消保报表
            // {path: '/tjfx/tjbb/xbbb/slcljb', name: '受理处理举报情况统计表', component: AcceptanceCP},
            { path: '/tjfx/tjbb/xbbb/slcljb', name: '受理处理举报情况统计表', component: Developing },
        ]
    }
];
export default routes;
