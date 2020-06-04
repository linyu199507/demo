import babelpolyfill from 'babel-polyfill'
import Vue from 'vue'
import App from './App'
// 全局引入ecahrts
import echarts from 'echarts'
// 引入广东地图
import guangdong from 'echarts/map/json/province/guangdong'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
// 引入第三方的字体图标库
import './assets/font/iconfont.css'
//import './assets/theme/theme-green/index.css'
import VueRouter from 'vue-router'
import store from './vuex/store'
import Vuex from 'vuex'
//import NProgress from 'nprogress'
//import 'nprogress/nprogress.css'
import routes from './routes'
import Mock from './mock'
import moment from 'moment'
import { getToken } from './utils/auth'

Mock.bootstrap();
// 引入顺德地图数据
import './assets/json/shunde.json'
import 'font-awesome/css/font-awesome.min.css'
import '../src/styles/index.scss'

Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(Vuex)

Vue.prototype.$echarts = echarts

echarts.registerMap('guangdong', guangdong)
//NProgress.configure({ showSpinner: false });

const router = new VueRouter({
    routes
})

router.beforeEach(async (to, from, next) => {
    //NProgress.start();
    if (to.path === '/login' || to.path === '/developmentLogin') {
        await store.dispatch('removeUserInfo')
        next()
    }
    else if (getToken()) {
        //判断是否以获取用户信息，没有则通过token请求用户信息
        if (store.getters.user) {
            next()
        } else {
            try {
                await store.dispatch('getUserInfo')
                next()
            } catch (error) {
                //跳回登录页面
                // next('/login')
                next('/login')
            }
        }
    } else {
        next('/login')
    }
})

//router.afterEach(transition => {
//NProgress.done();
//});

/*把二级的路由，根据/截取分为多级路由，应用于面包削*/
const myRoute = {};
for (let item of routes) {
    //替换点号，防止json造成多级
    let keyStr = item.path.replace(/[.*]/g, '_');
    if (keyStr.substr(0, 1) === '/') {
        keyStr = keyStr.slice(1, keyStr.length)
    }
    if (keyStr.substr(keyStr.length - 1, keyStr.length) === '/') {
        keyStr = keyStr.slice(0, keyStr.length - 1)
    }
    keyStr = '._$' + keyStr.replace(/\//g, '._$');
    //初始化每个key的值，防止为空
    let innerKey = '';
    for (let value of keyStr.split('._$')) {
        if (value === '') continue;
        innerKey += '._$' + value;
        //为空，则赋值
        if (!eval(`myRoute${innerKey}`)) {
            eval(`myRoute${innerKey}={aliasName:"${value}"}`);
        }
    }
    eval(`myRoute${keyStr}=item`);
    for (let index in item.children) {
        keyStr = item.children[index].path.replace(/[.*]/g, '_');
        if (keyStr.substr(0, 1) === '/') {
            keyStr = keyStr.slice(1, keyStr.length)
        }
        if (keyStr.substr(keyStr.length - 1, keyStr.length) === '/') {
            keyStr = keyStr.slice(0, keyStr.length - 1)
        }
        keyStr = '._$' + keyStr.replace(/\//g, '._$');

        //初始化每个key的值，防止为空
        innerKey = '';
        for (let value of keyStr.split('._$')) {
            if (value === '') continue;
            innerKey += '._$' + value;
            //为空，则赋值
            if (!eval(`myRoute${innerKey}`)) {
                eval(`myRoute${innerKey}={aliasName:"${value}"}`);
            }
        }
        eval(`myRoute${keyStr}=item.children[${index}]`);
    }
}
Vue.prototype.GLOBAL = { myRoute };
Vue.prototype.$moment = moment;
new Vue({
    //el: '#app',
    //template: '<App/>',
    router,
    store,
    //components: { App }
    render: h => h(App)
}).$mount('#app')

