import axios from 'axios';

export const requestLogin = params => {
    return axios.post(`/crcs/user/login`, params).then(res => res.data);
};
export const todoList = params => {
    return axios.post(`/crcs/tasklist/list`, params).then(res => res.data);
};
export const listDetail = params => {
    return axios.post(`/crcs/businessSearch/searchBaseInfo`, params).then(res => res.data);
};
// 12345待办基本信息保存
export const saveBmfwBaseInfo = params => {
    return axios.post(`/crcs/tasklist/saveBmfwBaseInfo`, params).then(res => res.data);
}
// 本系统待办详细信息

export const searchBaseInfo = params => {
    return axios.post(`/crcs/businessSearch/searchBaseInfo`, params).then(res => res.data);
};

// 查询单位列表
export const findOrgUnitByRegionId = params => {
  return axios.get(`/crcs/user/findOrgUnitByRegionId`, { params: params }).then(res => res.data);
};

// 投诉热点地区查询接口
export const searchComplaintsArea= params => {
  return axios.post(`/crcs/statistics/complaintsArea`, params ).then(res => res.data);
};

// 投诉热点地区查询接口
export const searchBusinessTotal= params => {
  return axios.post(`/crcs/statistics/businessTotal`, params ).then(res => res.data);
};

//综合查询-->业务记录查询
export const searchBusinessInfo = params => {
  return axios.post(`/crcs/businessSearch/searchBusinessInfo`, params).then(res => res.data);
};

//受理登记表单受理
export const next = params => {
  return axios.post(`/crcs/processinfo/next`, params).then(res => res.data);
};

//投诉热点商家Top10查询接口
export const searchTenBusiness= params => {
  return axios.post(`/crcs/statistics/tenBusiness`, params ).then(res => res.data);
};

// 投诉热点行业查询接口
export const searchComplaintRanking= params => {
  return axios.post(`/crcs/statistics/complaintRanking`, params ).then(res => res.data);
};

// 投诉举报热点(商品服务类)变化情况统计--查询接口
export const searchHotQuestionChange= params => {
  return axios.post(`/crcs/statistics/hotQuestionChange`, params ).then(res => res.data);
};

// 测试移动端数据接口
export const selectEnterprise = params => {
  return axios.post('/crcs/rights/selectEnterprise', params ).then(res => res.data);
};

// 维权信息表保存接口
export const save = params => {
  return axios.post('/crcs/rightsdetails/save', params ).then(res => res.data);
};

// 维权信息表分页查询接口
export const rightsList = params => {
  return axios.post('/crcs/rightsdetails/list', params ).then(res => res.data);
};
// 获取维权信息详情
export const rightsDetail = id => {
  return axios.get('/crcs/rightsdetails/detail/'+id).then(res => res.data);
};
