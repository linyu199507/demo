import Vue from 'vue'
import Vuex from 'vuex'
import * as actions from './actions'
import * as getters from './getters'

Vue.use(Vuex)

// 应用初始状态
const state = {
    unitList: [],
    user: undefined,
}

// 定义所需的 mutations
const mutations = {
    //更新下级单位列表
    UPDATEUNITLIST(state, payload) {
        state.unitList = payload
    },
    //更新用户信息
    UPDATEUSERINFO(state, payload) {
        state.user = payload
    },
}

// 创建 store 实例
export default new Vuex.Store({
    actions,
    getters,
    state,
    mutations
})