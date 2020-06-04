//分析
import request from '../utils/request'

/**
 * 热词词频100
 */
export function getTop100() {
    return request({
        url: '/analyse/getTop100',
        method: 'get',
    })
}

/**
 * 获取意见分析数据
 */
export function getOpinAnalysisAndLabel(params) {
    return request({
        url: '/analyse/getOpinAnalysisAndLabel',
        method: 'get',
        params
    })
}
/**
 * 根据意见分析标签，查询列表
 */
export function listContentByOpLabel(params) {
    return request({
        url: '/analyse/listContentByOpLabel',
        method: 'get',
        params
    })
}

/**
 * 本月投诉举报热点
 */
export function getCompAndReportHotByLabel() {
    return request({
        url: '/analyse/getCompAndReportHotByLabel',
        method: 'get'
    })
}
