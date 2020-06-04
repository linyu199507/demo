import Cookies from 'js-cookie'

const TokenKey = 'x-token'
const Param12345Key = 'param12345'
const Param12315Key = 'param12315'
const ParamDbKey = 'paramdb'
const FlagKey = 'flag'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  // token过期时间
  // const inFifteenMinutes = new Date(new Date().getTime() + 15 * 60 * 1000) 15分钟
  const inFifteenMinutes = 12 / 24 // 12小时
  return Cookies.set(TokenKey, token, { expires: inFifteenMinutes })
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

//12345
export function getParam12345() {
  return Cookies.get(Param12345Key)
}

export function setParam12345(param) {
  const inFifteenMinutes = 12 / 24 // 12小时
  return Cookies.set(Param12345Key, param, { expires: inFifteenMinutes })
}

export function removeParam12345() {
  return Cookies.remove(Param12345Key)
}

//12315
export function getParam12315() {
  return Cookies.get(Param12315Key)
}

export function setParam12315(param) {
  const inFifteenMinutes = 12 / 24 // 12小时
  return Cookies.set(Param12315Key, param, { expires: inFifteenMinutes })
}

export function removeParam12315() {
  return Cookies.remove(Param12315Key)
}

//受理待办
export function getParamDb() {
  return Cookies.get(ParamDbKey)
}

export function setParamDb(param) {
  const inFifteenMinutes = 12 / 24 // 12小时
  return Cookies.set(ParamDbKey, param, { expires: inFifteenMinutes })
}

export function removeParamDb() {
  return Cookies.remove(ParamDbKey)
}

//即将到期工单弹框提醒标记
export function getFlag() {
  return Cookies.get(FlagKey)
}

export function setFlag(param) {
  const inFifteenMinutes = 4 / 24 // 12小时
  return Cookies.set(FlagKey, param, { expires: inFifteenMinutes })
}

export function removeFlag() {
  return Cookies.remove(FlagKey)
}
