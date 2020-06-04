//12345平台接口
import axios from 'axios';

//业务待办详细信息查询
export const searchBaseInfo = params => {
    return axios.post(`/crcs/businessSearch/searchBaseInfo`, params).then(res => res.data);
};
//下一步操作
export const nextWork = params => {
    return axios.post(`/crcs/processinfo/next`, params).then(res => res.data);
};
//根据权限和部门id查询用户列表
export const findUsersByDeptIdAndPermission = params => {
    return axios.get(`/crcs/user/findUsersByDeptIdAndPermission`, { params: params }).then(res => res.data);
};
//查询单位列表
export const findOrgUnitByRegionId = params => {
    return axios.get(`/crcs/user/findOrgUnitByRegionId`, { params: params }).then(res => res.data);
};
//查询部门列表
export const findOrgDeptByOrgId = params => {
    return axios.get(`/crcs/user/findOrgDeptByOrgId`, { params: params }).then(res => res.data);
};
//受理登记待办签收
export const signTasklist = params => {
    return axios.post(`/crcs/tasklist/signTasklist`, params).then(res => res.data);
}
//根据权限查询用户列表
export const findUsersByPermission = params => {
    return axios.get(`/crcs/user/findUsersByPermission`, { params: params }).then(res => res.data);
}

//根据权限和单位id查询用户列表
export const findUsersByOrgAndPermission = params => {
    return axios.get(`/crcs/user/findUsersByOrgAndPermission`, { params: params });
};

// 基础数据一级列表
export const selectBaseCodeByType = params => {
    return axios.get(`/crcs/basecodeSearch/selectBaseCodeByType`, { params: params });
};
// 基础数据一级列表
export const selectBaseCodeTree = params => {
    return axios.get(`/crcs/basecodeSearch/selectBaseCodeTree`, { params: params });
};

//查看部门是否有查看该业务权限
export const findUsersByDeptIdAndJurisdiction = params => {
    return axios.get(`/crcs/user/findUsersByDeptIdAndPermission`, { params: params });
};

// 基础数据父集列表
export const selectDataList = params => {
    return axios.post(`/crcs/basecodeSearch/selectDataList`, params).then(res => res.data);
};

// 材料列表查询
export const getMaterialLists= params => {
    return axios.post(`/crcs/materialinfo/searchMaterials`, params ).then(res => res.data);
}

// 分局部门人员级联查询接口
export const getOptions= params => {
    return axios.post(`/crcs/t_orgunits/orgunits`, params ).then(res => res.data);
}

// 区局部门人员级联查询接口
export const getOptionsOfDept= params => {
    return axios.post(`/crcs/t_orgunits/deptData`, params ).then(res => res.data);
}

//下一步操作
export const toProcessing = params => {
    return axios.post(`/crcs/processinfo/isProcessing`, params).then(res => res.data);
};