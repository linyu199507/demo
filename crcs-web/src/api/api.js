import axios from 'axios';
//全局的baseURL
// axios.defaults.baseURL = process.env.CRCS_URL;
// axios.defaults.withCredentials = true;
// axios.defaults.headers.common['Access-Control-Allow-Origin']='*'
// export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data); };
//登录
export const requestLogin = params => {
    return axios.post(`/crcs/user/login`, params).then(res => res.data);
};
export const developmentLogin = params => {
    return axios.post(`/crcs/user/loginByAdmin`, params).then(res => res.data);
};
//待办确认页面列表
export const listComplaints = params => {
    return axios.post(`/crcs/datagriddata/list`, params).then(res => res.data);
};

//待办确认详情
export const confirmBaseInfo = params => {
    return axios.post(`/crcs/tasklist/confirmBaseInfo`, params).then(res => res.data);
};

//受理登记表单暂存
export const saveBaseInfo = params => {
    return axios.post(`/crcs/tasklist/saveBaseInfo`, params).then(res => res.data);
};

//受理登记表单受理
export const next = params => {
    return axios.post(`/crcs/processinfo/next`, params).then(res => res.data);
};

//受理登记表单详情
export const selectBaseInfo = params => {
    return axios.post(`/crcs/tasklist/selectBaseInfo`, params).then(res => res.data);
};

export const selectDatagriddataId = params => {
    return axios.post(`/crcs/bmfwSearch/searchBaseInfo`, params).then(res => res);
};

//本系统待办详细信息
export const searchBaseInfo = params => {
    return axios.post(`/crcs/businessSearch/searchBaseInfo`, params).then(res => res.data);
};

//查询单位列表
export const findOrgUnitByRegionId = params => {
    return axios.get(`/crcs/user/findOrgUnitByRegionId`, { params: params });
};

//查询部门列表
export const findOrgDeptByOrgId = params => {
    return axios.get(`/crcs/user/findOrgDeptByOrgId`, { params: params });
};

export const getUserList = params => {
    return axios.get(`/user/list`, { params: params });
};

export const getUserListPage = params => {
    return axios.get(`/user/listpage`, { params: params });
};

export const removeUser = params => {
    return axios.get(`/user/remove`, { params: params });
};

export const batchRemoveUser = params => {
    return axios.get(`/user/batchremove`, { params: params });
};

export const editUser = params => {
    return axios.get(`/user/edit`, { params: params });
};

export const addUser = params => {
    return axios.get(`/user/add`, { params: params });
};

export const listDistribution = params => {
    return axios.get(`/distribution/listpage`, { params: params });
};

// export const listComplaint = params => { return axios.get(`${base}/complaint/listpage`, { params: params }); };
export const listTasklist = params => {
    return axios.post(`/crcs/tasklist/list`, params).then(res => res.data);
};

export const modifyPassword = params => {
    return axios.post(`/crcs/user/modifyPassword`, params).then(res => res.data);
};
//待办删除
export const deleteTasklist = params => {
    return axios.get(`/crcs/tasklist/deleteTasklist`, { params: params });
};

//查询12345待办详情信息
export const selectBusinessBaseInfo = params => {
    return axios.post(`/crcs/businessSearch/searchBaseInfo`, params).then(res => res.data);
};

//综合查询-->业务记录查询
export const searchBusinessInfo = params => {
    return axios.post(`/crcs/businessSearch/searchBusinessInfo`, params).then(res => res.data);
};

//综合查询-->业务记录查询Excel导出
export const searchBusinessList = params => {
    axios({
        method: 'post',
        url: '/crcs/businessSearch/searchBusinessList',
        data: params,
        responseType: 'blob'
    }).then(response => {
        let url = window.URL.createObjectURL(new Blob([response.data]))
        console.log(response)
        let link = document.createElement('a')
        link.style.display = 'none'
        link.href = url
        link.setAttribute('download', '综合查询结果导出.xls')

        document.body.appendChild(link)
        link.click()
    }).catch((error) => {

    })
    // axios.post(`/crcs/businessSearch/searchBusinessList`, params).then(res => res.data);
};
/*export const searchBusinessInfo = params => {
    return axios.get(`${base}crcs/businessSearch/searchBusinessInfo`, { params: params });};*/

// 12345待办基本信息保存
export const saveBmfwBaseInfo = params => {
    return axios.post(`/crcs/tasklist/saveBmfwBaseInfo`, params).then(res => res.data);
}

//受理登记待办签收
export const signTasklist = params => {
    return axios.post(`/crcs/tasklist/signTasklist`, params).then(res => res.data);
}

//分派人员列表查询
export const findUsersByDeptIdAndPermission = params => {
    return axios.get(`/crcs/user/findUsersByDeptIdAndPermission`, { params: params });
};

//分派下级单位查询是否存在有该权限人员
export const findUsersByOrgAndPermission = params => {
    return axios.get(`/crcs/user/findUsersByOrgAndPermission`, { params: params });
};

//待办查询结果导出
export const excelexport = params => {
    return axios.post(`/crcs/businessSearch/excelexport`, params).then(res => res.data);
}
//待办查询结果详情页导出World
export const wordexport = params => {
    return axios.post(`/crcs/businessSearch/wordexport`, params).then(res => res.data);
}

//业务时限配置查询
export const searchDeadlinemanagement = params => {
    return axios.post(`/crcs/deadlinemanagement/searchDeadlinemanagement`, params).then(res => res.data);
};

//业务时限配置增加
export const saveDeadlinemanagement = params => {
    return axios.post(`/crcs/deadlinemanagement/saveDeadlinemanagement`, params).then(res => res.data);
};
//业务时限配置删除
export const deleteDeadlinemanagement = params => {
    return axios.get(`/crcs/deadlinemanagement/deleteDeadlinemanagement`, { params: params });
};
// 获取统计数据
export const totalFirstData = params => {
    return axios.post(`/crcs/countList/firstCartCount`, { params: params });
}
// 获取办结率统计
export const getPercentage = params => {
    return axios.get(`/crcs/countList/finishCount`, { params: params });
}
// 业务量统计
export const getTrafficCount = params => {
    return axios.get(`/crcs/countList/trafficCount`, { params: params });
}
//工单已办查询接口
export const endListTasklist = params => {
    return axios.post(`/crcs/tasklist/endlist`, params).then(res => res.data);
};
// 在办统计
export const dealListTotal = params => {
    return axios.get(`/crcs/countList/onJobStaCount`, { params: params })
};
// 红黄工单统计
export const cardsCount = params => {
    return axios.post(`/crcs/tasklist/cardsCount`, params).then(res => res.data);
};
// 获取前十的商家
export const getTenBusiness = params => {
    return axios.get(`/crcs/countList/businessTopCount`, { params: params })
}
// 查询日期数据
export const getDate = params => {
    return axios.get(`/crcs/countreport/countBusiness`, { params: params })
}
// 获取投诉量前十品牌
export const getTenBrand = params => {
    return axios.get(`/crcs/countreport/countBusinessBySubject`, { params })
}
//基础数据查询接口
export const listBasicData = params => {
    return axios.post(`/crcs/basecodeSearch/listBasicData`, params).then(res => res.data);
};
// 各分局的各类业务量的统计及趋势
export const getCountByReginon = params => {
    return axios.get(`/crcs/countreport/countByReginon`, { params: params });
}
export const getCountByReginonFinish = params => {
    return axios.get(`/crcs/countreport/countByReginonFinish`, { params: params });
}
// 基础数据添加接口
export const addBasicData = params => {
    return axios.post(`/crcs/basecodeSearch/addBasicData`, params).then(res => res.data);
};
// 获取品牌各业务量
export const getBrandClassify = params => {
    return axios.get(`/crcs/countreport/countBusinessBySubject`, { params: params })
}
// 投诉热点地区
export const getHotZoom = params => {
    return axios.get(`/crcs/countList/regionCount`, { params: params })
}
// 各分局的各类业务量的统计
export const getByRegion = params => {
    return axios.get(`/crcs/countreport/countByReginon`, { params: params })
}

// 投诉各地区的投诉占比
export const getByReginonFinish = params => {
    return axios.get(`/crcs/countreport/countByReginonFinish`, { params: params })
}
// 删除基础数据
export const deleteBasicData = params => {
    // console.log(params);
    return axios.get(`/crcs/basecodeSearch/deleteBasicData`, { params: params });
};

// 基础数据修改接口
export const updateBasicData = params => {
    return axios.post(`/crcs/basecodeSearch/updateBasicData`, params).then(res => res.data);
};
// 基础数据一级列表
export const selectBaseCodeByType = params => {
    return axios.get(`/crcs/basecodeSearch/selectBaseCodeByType`, { params: params });
};
// 基础数据一级列表
export const selectBaseCodeTree = params => {
    return axios.get(`/crcs/basecodeSearch/selectBaseCodeTree`, { params: params });
};
export const uploadFile = params => {
    let config = {
        headers:{
            "Content-Type":"multipart/form-data"
        }
    }
    return axios.post(`/crcs/filesUpload/upload`,params,config).then(res => res.data)
}

export const findUsersByPermission = params => {
    return axios.get(`/crcs/user/findUsersByPermission`, { params: params }).then(res => res.data);
}
// 业务列表查询
export const businessList= params => {
    return axios.post(`/crcs/materialinfo/searchFilesInfo`, params ).then(res => res.data);
}
// 材料列表查询
export const getMaterialLists= params => {
    return axios.post(`/crcs/materialinfo/searchMaterials`, params ).then(res => res.data);
}
// 基础数据查询按钮接口
export const selectBasicData = params => {
    return axios.post(`/crcs/basecodeSearch/selectBasicData`,params).then(res => res.data);
};
// 根据codeid查询对应coment值(基础数据)
export const selectBaseCodeByCodeid = params => {
    return axios.get(`/crcs/basecodeSearch/selectBaseCodeByCodeid`, { params: params });
};
export const getDealNum = params => {
    return axios.post(`/crcs/countList/searchFinishList`, {params:params}).then(res => res.data);
};
export const getFinishListData = params => {
    return axios.post(`/crcs/tasklist/searchFinishListData`, params).then(res => res.data);
};
//查询历史统计数据列表
export const searchStatisticsinfos = params => {
    return axios.post(`/crcs/statisticsinfo/searchStatisticsinfos`, params).then(res => res.data);
};

//历史统计数据删除
export const deleteStatisticsinfos = params => {
    return axios.get(`/crcs/statisticsinfo/deleteStatisticsinfos`, { params: params });
};

//咨询表单查询接口
export const searchConsultationformList = params => {
    return axios.post(`/crcs/consultationform/searchConsultationformList`, params ).then(res => res.data);
};

//投诉表单查询接口
export const searchComplaintformList = params => {
    return axios.post(`/crcs/complaintform/searchComplaintformList`, params ).then(res => res.data);
};

//举报表单查询接口
export const searchEconomyformList= params => {
    return axios.post(`/crcs/economyform/searchEconomyformList`, params ).then(res => res.data);
};

//各地区的业务量查询接口
export const searchRegionData= params => {
    return axios.post(`/crcs/statistics/regionalBusiness`, params ).then(res => res.data);
};
//投诉热点行业查询接口
export const searchComplaintRanking= params => {
    return axios.post(`/crcs/statistics/complaintRanking`, params ).then(res => res.data);
};
//投诉热点商家Top10查询接口
export const searchTenBusiness= params => {
    return axios.post(`/crcs/statistics/tenBusiness`, params ).then(res => res.data);
};
//投诉热点地区查询接口
export const searchComplaintsArea= params => {
    return axios.post(`/crcs/statistics/complaintsArea`, params ).then(res => res.data);
};
//各地区办结率统计查询接口
export const searchregionComplete= params => {
    return axios.post(`/crcs/statistics/regionComplete`, params ).then(res => res.data);
};
//投诉、举报性质(商品服务类)统计分析查询接口
export const searchNatureAnalysis= params => {
    return axios.post(`/crcs/statistics/natureAnalysis`, params ).then(res => res.data);
};
//举报性质(涉嫌违法行为)统计分析查询接口
export const searchIllegalBehavior= params => {
    return axios.post(`/crcs/statistics/illegalBehavior`, params ).then(res => res.data);
};
//投诉举报热点事件(问题分类)查询接口
export const searchHotQuestion= params => {
    return axios.post(`/crcs/statistics/hotQuestion`, params ).then(res => res.data);
};
//案件,受理,立案,办结,诉转案总数--查询接口
export const searchOverallBusiness= params => {
    return axios.post(`/crcs/statistics/overallBusiness`, params ).then(res => res.data);
};
//投诉举报热点(商品服务类)变化情况统计--查询接口
export const searchHotQuestionChange= params => {
    return axios.post(`/crcs/statistics/hotQuestionChange`, params ).then(res => res.data);
};
//回填失败工单数--查询接口
export const searchBackOrder= params => {
    return axios.post(`/crcs/Back/searchBackOrder`, params ).then(res => res.data);
};

//回填失败工单数(首页)--查询接口
export const searchBackOrderOfHome= params => {
    return axios.post(`/crcs/Back/searchBackOrderOfHome`, params ).then(res => res.data);
};

//回填失败工单删除
export const deleteBackOrder = params => {
    return axios.get(`/crcs/Back/deleteBackOrder`, { params: params });
};

//回填失败工单确认
export const changeBackOrder = params => {
    return axios.get(`/crcs/Back/changeBackOrder`, { params: params });
};

//附件删除
export const deleteUploadFile = params => {
    return axios.post(`/crcs/materialinfo/deletefile`, params);
};

// 区局部门人员级联查询接口
export const getOptionsOfDept= params => {
    return axios.post(`/crcs/t_orgunits/deptData`, params ).then(res => res.data);
}

// 分局部门人员级联查询接口
export const getOptions= params => {
    return axios.post(`/crcs/t_orgunits/orgunits`, params ).then(res => res.data);
}

// 人员级联查询接口
export const getAllData= params => {
    return axios.post(`/crcs/t_orgunits/getAllData`, params ).then(res => res.data);
}

//测试大屏数据接口
export const testBigData = params => {
    return axios.get(`/crcs/screenDisplay/searchRatioOfToday`, { params: params });
};

//受理登记短信发送对象接口
export const getMessagePerson= params => {
    return axios.post(`/crcs/t_orgunits/getMessageCascadeData`, params ).then(res => res.data);
}

//发短信接口
export const sendMessage= params => {
    return axios.post(`/crcs/t_orgunits/sendmessage`, params ).then(res => res.data);
}

//12315、12345短信发送对象接口
export const getSMSPerson= params => {
    return axios.post(`/crcs/t_orgunits/getSMSPersonData`, params ).then(res => res.data);
}

// 获取受理登记预警数据
export const sldjwarnData = params => {
    return axios.post(`/crcs/countList/warningCount`, { params: params });
}

//维权服务站列表查询接口
export const listRights = params => {
    return axios.post(`/crcs/rightsdetails/list`, params ).then(res => res.data)};

// 获取维权信息详情
export const rightsDetail = id => {
    return axios.get('/crcs/rightsdetails/detail/'+id).then(res => res.data);
};
