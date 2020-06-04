// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import FastClick from 'fastclick';
import App from './App';
import Vuex from 'vuex';
import { AlertPlugin } from 'vux';
import moment from 'moment';
import router from './router/index';
import echarts from 'echarts'
import ElementUI from 'element-ui';
import { getToken } from './utils/auth';
import store from './vuex/store';
import 'element-ui/lib/theme-chalk/index.css';
// eslint-disable-next-line
import 'lib-flexible';
// eslint-disable-next-line
import VConsole from 'vconsole'; //微信调试输出
import {
  XButton, Search, Panel, Tabbar, TabbarItem, Alert, Tab,
  TabItem, SwiperItem, Swiper, Flow, FlowState, FlowLine,
  PopupHeader, Popup, Group, Radio, Cell, XSwitch, XTextarea,
  PopupPicker, XInput, XAddress, Datetime, Checklist, Scroller, XTable, Card, Flexbox, FlexboxItem
} from 'vux';

// eslint-disable-next-line
// new VConsole();

router.beforeEach(async (to, from, next) => {
  // NProgress.start();
  if (to.path === '/Login') {
    await store.dispatch('removeUserInfo');
    next();
  } else if (getToken()) {
    // 判断是否以获取用户信息，没有则通过token请求用户信息
    if (store.getters.user) {
      next();
    } else {
      try {
        await store.dispatch('getUserInfo');
        next();
      } catch (error) {
        // 跳回登录页面
        // next('/login')
        next('/Login');
      }
    }
  } else {
    next('/Login');
  }
});


Vue.component('x-button', XButton);
Vue.component('tabbar', Tabbar);
Vue.component('tabbar-item', TabbarItem);
Vue.component('search', Search);
Vue.component('tab', Tab);
Vue.component('tab-item', TabItem);
Vue.component('panel', Panel);
Vue.component('swiper', Swiper);
Vue.component('swiper-item', SwiperItem);
Vue.component('flow', Flow);
Vue.component('flow-state', FlowState);
Vue.component('flow-line', FlowLine);
Vue.component('popup-header', PopupHeader);
Vue.component('popup', Popup);
Vue.component('group', Group);
Vue.component('radio', Radio);
Vue.component('cell', Cell);
Vue.component('alert', Alert);
Vue.component('x-switch', XSwitch);
Vue.component('x-textarea', XTextarea);
Vue.component('popup-picker', PopupPicker);
Vue.component('x-input', XInput);
Vue.component('x-address', XAddress);
Vue.component('datetime', Datetime);
Vue.component('checklist', Checklist);
Vue.component('scroller', Scroller);
Vue.component('x-table', XTable);
Vue.component('card', Card)
Vue.component('flexbox', Flexbox)
Vue.component('flexbox-item', FlexboxItem)

FastClick.attach(document.body);

Vue.config.productionTip = false;

Vue.use(ElementUI);
Vue.use(AlertPlugin);
Vue.prototype.$moment = moment;
Vue.prototype.$echarts = echarts
/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App),
}).$mount('#app-box');
