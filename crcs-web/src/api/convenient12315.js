//12315平台接口
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
//查询领导
export const findUsersByPermission = params => {
    return axios.get(`/crcs/user/findUsersByPermission`, { params: params }).then(res => res.data);
}

//分派下级单位查询是否存在有该权限人员
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
// 12315待办基本信息保存
export const saveBmfwBaseInfo = params => {
    return axios.post(`/crcs/tasklist/saveBmfwBaseInfo`, params).then(res => res.data);
}

// 12315分流审批
export const shuntDispatch = params => {
    return axios.post(`/crcs/processinfo/dispatch`, params).then(res => res);
}

// 材料列表查询
export const getMaterialLists= params => {
    return axios.post(`/crcs/materialinfo/searchMaterials`, params ).then(res => res.data);
}

