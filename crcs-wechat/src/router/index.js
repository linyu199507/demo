import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from '../login/login.vue';
import ListOf12345 from '../12345list/listOf12345.vue';
import Pending from '../../src/12345list/pending.vue';
import DetailOf12345 from '../../src/12345list/detailOf12345.vue';
import User from '../../src/user/user.vue';
import accept12345form from '../../src/12345list/accept12345form.vue';
import processFeedback from '../../src/12345list/processFeedback12345.vue';
import mainIndex from '../../src/12345list/main.vue';
// 搜索页
import Search from '../12345list/search.vue';
import dispatchedPersonnel from '../../src/12345list/dispatchedPersonnel.vue';
// 综合查询
import comprehensiveSearch from '../comprehensiveSearch/index.vue';
import comprehensiveSearchResult from '../comprehensiveSearch/searchResult.vue';
// 综合查询详情页
import comprehensiveSearchResultDetail from '../comprehensiveSearch/searchResultDetail'

// 12345待分派咨询表
import consultFrom from '../../src/12345list/consultFrom.vue';
// 12345咨询单详情页

import economy from '../../src/12345list/economy.vue';
// 12315待分流的投诉表单
import complainFrom from '../../src/12345list/complainFrom.vue';
// 12345受理后咨询单
import assignSubordinateOfConsult from '../../src/12345list/assignSubordinateOfConsult.vue';
// 12345受理后的经济举报表单
import acceptedOfEconomyDetail from '../../src/12345list/acceptedOfEconomyDetail.vue';
// 立案处理
import economyOfCaseFiling from '../../src/12345list/economyOfCaseFiling.vue';
// 待分流
import toBeDiverted from '../../src/12315list/toBeDiverted.vue';
import beginToDealComplain from '../../src/12315list/beginToDealComplain.vue';
import acceptedof12315complain from '../../src/12315list/acceptedof12315complain.vue';
import feedbackOf12315complain from '../../src/12315list/feekbackOf12315complain.vue';
import acceptedOf12315Report from '../../src/12315list/acceptedOf12315Report.vue';
// 图表统计
import ComplaintHotSpot from '../../src/chartsOfSystem/complaintHotSpot.vue';
import ComparisonOfSimilarCases from '../../src/chartsOfSystem/ComparisonOfSimilarCases.vue';
// 领导桌面
import MainLeader from '../../src/12345list/mainLeader.vue';
Vue.use(VueRouter);
// 维权服务登记页面
import RegistrationForm from '../../src/rights/registrationForm.vue';
import rightsList from '../../src/rights/rightsList';
import rightsDetail from '../../src/rights/rightsDetail';


export default new VueRouter({
  routes: [
    {
      path: '/Login',
      name: 'Login',
      component: Login,
    },
    // 首页
    {
      path: '/',
      name: 'main',
      redirect: '/main/mainIndex',
      component: ListOf12345,
      children: [
        // 分派到下级的咨询单
        {
          path: '/main/assignSubordinateOfConsult',
          name: 'assignSubordinateOfConsult',
          component: assignSubordinateOfConsult,
        },
        {
          path: '/main/mainIndex',
          name: 'mainIndex',
          component: mainIndex,
        },
        {
          path: '/main/MainLeader',
          name: 'mainLeader',
          component: MainLeader,
        },
      ],
    },
    // 维权服务
    {
      path: '/main/RegistrationForm',
      name: 'RegistrationForm',
      component: RegistrationForm,
    },
    {
      path: '/main/rights/list',
      name: 'rightsList',
      component: rightsList,
    },
    {
      path: '/main/rights/detail',
      name: 'rightsDetail',
      component: rightsDetail,
    },
    // 搜索
    {
      path: '/main/search',
      name: 'Search',
      component: Search,
    },
    // 综合查询
    {
      path: '/main/comprehensiveSearch',
      name: 'ComprehensiveSearch',
      component: comprehensiveSearch,
    },
    // 综合查询结果
    {
      path: '/main/comprehensiveSearch/result',
      name: 'comprehensiveSearchResult',
      component: comprehensiveSearchResult,
    },
    // 综合查询结果详情受理登记
    {
      path: '/main/comprehensiveSearch/result/detail',
      name: 'comprehensiveSearchResultDetail',
      component: comprehensiveSearchResultDetail,
    },
    {
      path: '/main/Pending',
      name: 'Pending',
      component: Pending,
    },
    {
      path: '/main/user',
      name: 'User',
      component: User,
    },
    // 12345经济表
    {
      path: '/main/Economy',
      name: 'economy',
      component: economy,
    },
    // 12345待分流的投诉表
    {
      path: '/main/complainFrom',
      name: 'complainFrom',
      component: complainFrom,
    },
    // 12315分流表单
    {
      path: '/main/toBeDiverted',
      name: 'toBeDiverted',
      component: toBeDiverted,
    },
    // 经济表单详情页
    {
      path: '/main/acceptedOf12315Report',
      name: 'acceptedOf12315Report',
      component: acceptedOf12315Report,
    },
    // <!-- 消费表单受理后详情 -->
    {
      path: '/main/DetailOf12345',
      name: 'DetailOf12345',
      component: DetailOf12345,
    },
    {
      path: '/main/ConsultDetailOf12345',
      name: 'consultFrom',
      component: consultFrom,
    },
    // 12315消费投诉
    {
      path: '/main/acceptedof12315complain',
      name: 'acceptedof12315complain',
      component: acceptedof12315complain,
    },
    // 12315投诉处理反馈
    {
      path: '/main/feedbackOf12315complain',
      name: 'feedbackOf12315complain',
      component: feedbackOf12315complain,
    },
    {
      path: '/main/Economy/acceptedOfEconomyDetail',
      name: 'acceptedOfEconomyDetail',
      component: acceptedOfEconomyDetail,
    },
    // 立案
    {
      path: '/main/Economy/economyOfCaseFiling',
      name: 'economyOfCaseFiling',
      component: economyOfCaseFiling,
    },
    // 开始处理页面
    {
      path: '/main/beginToDealComplain',
      name: 'beginToDealComplain',
      component: beginToDealComplain,
    },
    {
      path: '/main/DetailOf12345/accept12345form',
      name: 'detailOf12d345',
      component: accept12345form,
    },
    {
      path: '/main/DetailOf12345/processFeedback',
      name: 'processFeedback',
      component: processFeedback,
    },
    {
      path: '/main/DetailOf12345/dispatchedPersonnel',
      name: 'dispatchedPersonnel',
      component: dispatchedPersonnel,
    },
    {
      path: '/main/ComplaintHotSpot',
      name: 'ComplaintHotSpot',
      component: ComplaintHotSpot,
    },
    {
      path: '/main/ComparisonOfSimilarCases',
      name: 'ComparisonOfSimilarCases',
      component: ComparisonOfSimilarCases,
    },
    // 领导桌面

  ],
});
