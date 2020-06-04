import axios from 'axios';



//各业务类型工单量--查询接口
export const searchbustype= params => {
    return axios.post(`/crcs/overall/bustype`, params ).then(res => res.data);
};

//全区维权办结情况及分布--查询接口
export const searchallArea= params => {
    return axios.post(`/crcs/overall/allArea`, params ).then(res => res.data);
};

//全区举报基本情况--查询接口
export const searchEconomydata= params => {
    return axios.post(`/crcs/economy/economydata`, params ).then(res => res.data);
};

//涉嫌违法情况情况--查询接口
export const searchEconomyIllegal= params => {
    return axios.post(`/crcs/economy/economyIllegal`, params ).then(res => res.data);
};

//全区投诉总量及调解查--查询接口
export const searchComplaintdata= params => {
    return axios.post(`/crcs/complaint/complaintdata`, params ).then(res => res.data);
};

//各单位投诉量动态变化--查询接口
export const searchDataOfTwelve= params => {
    return axios.post(`/crcs/complaint/dataOfTwelve`, params ).then(res => res.data);
};

//投诉热点性质--查询接口
export const searchHotNature= params => {
    return axios.post(`/crcs/complaint/hotNature`, params ).then(res => res.data);
};

//家用电器类产品具体投诉品类--查询接口
export const searchAppliancesCategory= params => {
    return axios.post(`/crcs/complaint/appliancesCategory`, params ).then(res => res.data);
};

//家用电器类产品具体投诉性质--查询接口
export const searchAppliancesNature= params => {
    return axios.post(`/crcs/complaint/appliancesNature`, params ).then(res => res.data);
};

//家用电器类产品具体投诉品牌--查询接口
export const searchAppliancesBrand= params => {
    return axios.post(`/crcs/complaint/appliancesBrand`, params ).then(res => res.data);
};

//被投诉主体TOP10--查询接口
export const searchSubjectTen= params => {
    return axios.post(`/crcs/complaint/subjectTen`, params ).then(res => res.data);
};

//被投诉热点商圈TOP10--查询接口
export const searchBusinesscircle= params => {
    return axios.post(`/crcs/complaint/businesscircle`, params ).then(res => res.data);
};

//日用百货类产品具体投诉品类--查询接口
export const searchDailyCategory= params => {
    return axios.post(`/crcs/complaint/dailyCategory`, params ).then(res => res.data);
};

//日用百货类产品具体投诉性质--查询接口
export const searchDailyNature= params => {
    return axios.post(`/crcs/complaint/dailyNature`, params ).then(res => res.data);
};

//日用百货类产品具体投诉品牌--查询接口
export const searchDailyBrand= params => {
    return axios.post(`/crcs/complaint/dailyBrand`, params ).then(res => res.data);
};

//交通工具类产品具体投诉品类--查询接口
export const searchTrafficCategory= params => {
    return axios.post(`/crcs/complaint/trafficCategory`, params ).then(res => res.data);
};

//统计分析高频词接口
export const analysisHotWord= params => {
    return axios.post(`/crcs/analyse/startAyHotWord`, params ).then(res => res.data);
};

//交通工具类产品具体投诉性质--查询接口
export const searchTrafficNature= params => {
    return axios.post(`/crcs/complaint/trafficNature`, params ).then(res => res.data);
};

//交通工具类产品具体投诉品牌--查询接口
export const searchTrafficBrand= params => {
    return axios.post(`/crcs/complaint/trafficBrand`, params ).then(res => res.data);
};

//各类主要投诉问题高频词统计--查询接口 msx
export const searchMainTypeComplaint= params => {
    return axios.post(`/crcs/analyse/getHotWordTop100`, params ).then(res => res.data);
};

//装修建材类产品具体投诉品类--查询接口
export const searchDecoration= params => {
    return axios.post(`/crcs/complaint/decoration`, params ).then(res => res.data);
};

//房屋类产品具体投诉品类--查询接口,投诉品类
export const searchHouseCategory= params => {
    return axios.post(`/crcs/complaint/housing`, params ).then(res => res.data);
};

//食品类产品具体投诉品类--查询接口,投诉品类
export const searchFoodCategory= params => {
    return axios.post(`/crcs/complaint/food`, params ).then(res => res.data);
};

//食品类产品具体投诉环节-查询接口,
export const searchFoodLinkCategory= params => {
    return axios.post(`/crcs/complaint/foodLink`, params ).then(res => res.data);
};

//药品类产品具体投诉环节-查询接口,
export const searchMedicineLinkCategory= params => {
    return axios.post(`/crcs/complaint/medicineLink`, params ).then(res => res.data);
};

//保健品类产品具体投诉环节-查询接口,
export const searchHealthFoodLinkCategory= params => {
    return axios.post(`/crcs/complaint/HealthFoodLink`, params ).then(res => res.data);
};

//化妆品类产品具体投诉环节-查询接口
export const searchCosmeticsLinkCategory= params =>{
    return axios.post(`/crcs/complaint/CosmeticsLink`,params).then(res => res.data);
}


//医疗器械类产品具体投诉环节-查询接口
export const searchMedicalLinkCategory= params =>{
    return axios.post(`/crcs/complaint/MedicalLink`,params).then(res => res.data);
}

//投诉热点类别-查询接口
export const searchComplainthotspotdata= params =>{
    return axios.post(`/crcs/complaint/complaintHotSpot`,params).then(res => res.data);
}

//投诉热点高频词统计--查询接口 msx
export const searchComplaintHotWord= params => {
    return axios.post(`/crcs/analyse/getcomplaintHotWord`, params ).then(res => res.data);
};