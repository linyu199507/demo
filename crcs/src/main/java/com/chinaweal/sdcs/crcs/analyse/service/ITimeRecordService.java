package com.chinaweal.sdcs.crcs.analyse.service;

import com.chinaweal.sdcs.crcs.analyse.entity.TimeRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author chinaweal
 * @since 2020-01-16
 */
public interface ITimeRecordService extends IService<TimeRecord> {


    /**
     * 热词
     */
    String HOT_WORD = "hot_word";
    /**
     * 意见分析
     */
    String IDEA = "idea";
}
