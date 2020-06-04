package com.chinaweal.sdcs.crcs.analyse.scheduled;

import com.chinaweal.sdcs.crcs.analyse.entity.TimeRecord;
import com.chinaweal.sdcs.crcs.analyse.service.IHotWordService;
import com.chinaweal.sdcs.crcs.analyse.service.IQuestionService;
import com.chinaweal.sdcs.crcs.analyse.service.ITimeRecordService;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * 词频的定时任务
 *
 * @author HuangXiao
 */
@Component
public class HowWordScheduled {

    @Resource
    private IHotWordService iHotWordService;

    @Resource
    private IQuestionService iQuestionService;
    @Resource
    private ITimeRecordService iTimeRecordService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 一天计算一次词频
     */
    //@Scheduled(fixedDelay = 300000)
//    @Scheduled(fixedDelay = 86400000)
    public void fiveCount(){
        TimeRecord timeRecord = iTimeRecordService.getById(ITimeRecordService.HOT_WORD);
        if (timeRecord == null) {
            return;
        }
        LocalDateTime end = LocalDateTime.now();
        try {
            iHotWordService.analyseHotWord(timeRecord.getLastDate(), end);
            //记录最后一次时间
            timeRecord.setLastDate(end);
            iTimeRecordService.updateById(timeRecord);
        } catch (Exception e) {
            logger.error("5分钟计算一次词频异常！", e);
        }
    }

    /**
     * 5分钟计算一次意见分析
     */
//    @Scheduled(fixedDelay = 300000)
    public void ideaCount() {
        TimeRecord timeRecord = iTimeRecordService.getById(ITimeRecordService.IDEA);
        if (timeRecord == null) {
            return;
        }
        LocalDateTime end = LocalDateTime.now();
        try {
            iQuestionService.analyseQuestion(timeRecord.getLastDate(), end);
            //记录最后一次时间
            timeRecord.setLastDate(end);
            iTimeRecordService.updateById(timeRecord);
        } catch (Exception e) {
            logger.error("5分钟计算一次意见分析异常！", e);
        }

    }
}
