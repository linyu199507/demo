package com.chinaweal.sdcs.crcs.analyse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.analyse.entity.HotWord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author chinawea
 * @since 2020-01-14
 */
public interface IHotWordService extends IService<HotWord> {


    void test(String text);

    /**
     * 分析词频 msx
     */
    void analyseHotWord(LocalDateTime begin, LocalDateTime end);

    /**
     * 获取工单的内容的前100的热词
     */
    List<Map<String, Object>> getTop100();

    //通用类高频词统计 msx

    List<Map<String, Object>> getSameTop100(Map<String,Object> map);
    //投诉热点高频词
    List<Map<String, Object>> getComplaintHotWord(Map<String,Object> map);
}
