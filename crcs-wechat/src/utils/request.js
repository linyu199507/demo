import axios from 'axios'
import { Message } from 'element-ui'
import { getToken } from './auth'

const service = axios.create({
  baseURL: '/crcs', // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})
// request interceptor
service.interceptors.request.use(
  config => {
    if (getToken()) {
      // 携带token
      config.headers['x-token'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    const { status, data } = response
    if (status === 401 || status === 403) {
      // 没有登录或者没有权限
      Message({
        message: '没有登录或者没有权限',
        type: 'error',
        duration: 5 * 1000
      })
    }
    return data
  },
  error => {
    console.log('err' + error) // for debug
    // Message({
    //   message: error.message,
    //   type: 'error',
    //   duration: 5 * 1000
    // })
    // return Promise.reject(error)
  }
)

export default service
