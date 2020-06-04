// 用户操作
import request from '../utils/request'

export function login(data) {
    return request({
        url: '/user/loginPC',
        method: 'post',
        data
    })
}
export function userLogin(data) {
    return request({
        url: '/user/userLogin',
        method: 'post',
        data
    })
}
export function getUserInfo() {
    return request('/user/searchUser')
}

