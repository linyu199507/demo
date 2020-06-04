import * as userApi from '../api/user'
export const updateUnitList = ({ commit }, payload) => {
    commit('UPDATEUNITLIST', payload)
}
export const getUserInfo = ({ commit }) => {
    return new Promise((resolve, reject) => {
        userApi.getUserInfo().then((response) => {
            if (response && response.code === 0) {
                commit('UPDATEUSERINFO', response.data)
                localStorage.setItem("user", JSON.stringify(response.data));
                resolve()
            } else {
                reject()
            }
        })
    })
}
export const removeUserInfo = ({ commit }) => {
    commit('UPDATEUSERINFO', null)
}
