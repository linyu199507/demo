package com.chinaweal.sdcs.crcs.analyse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.analyse.entity.Question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2020-01-19
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 分类问题的分类记录
     */
    void analyseQuestion(LocalDateTime begin, LocalDateTime end);

    /**
     * 获取意见分析的统计，和意见分析标签列表
     */
    Map<String, Object> getOpinAnalysisAndLabel(String codeId);

    /**
     * 根据意见分析标签，查询列表
     */
    Map<String, Object> listContentByOpAndLabel(String codeId, String label, Integer page);

    /**
     * 本月投诉举报热点
     */
    List<Map<String, Object>> getCompAndReportHot();
}
