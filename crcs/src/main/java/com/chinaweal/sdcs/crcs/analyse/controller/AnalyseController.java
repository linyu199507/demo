package com.chinaweal.sdcs.crcs.analyse.controller;

import com.chinaweal.sdcs.crcs.analyse.entity.TimeRecord;
import com.chinaweal.sdcs.crcs.analyse.service.IHotWordService;
import com.chinaweal.sdcs.crcs.analyse.service.IQuestionService;
import com.chinaweal.sdcs.crcs.analyse.service.ITimeRecordService;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.text.Keymap;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HuangXiao
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/analyse")
@Api(tags = "分析统计")
public class AnalyseController {

    @Resource
    private IHotWordService iHotWordService;
    @Resource
    private IQuestionService iQuestionService;
    @Resource
    private ITimeRecordService iTimeRecordService;

   /* @GetMapping("/startAyHotWord")
    @ApiOperation(value = "开始分析统计热词")
    //@Scheduled(fixedDelay = 86400000) //隔一天刷新
    public RestResult ayHotWord() throws Exception {
        Map<String,Object> map=DateUtil.getThisYear();
        String start = map.get("start").toString();
        String end = map.get("end").toString();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date sdate=format.parse(start);
        Date edate=format.parse(end);
        map.put("start",sdate);
        map.put("end",edate);
        iHotWordService.analyseHotWord(map);
        return RestResult.ok();
    }*/

//    @GetMapping("/startAyQuestion")
//    @ApiOperation(value = "开始分析统计问题分类")
//    public RestResult ayQuestion(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime begin,
//                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end) {
//        iQuestionService.analyseQuestion(begin, end);
//        return RestResult.ok();
//    }

    /**
     * 获取工单的内容的前100的热词
     */
    @ApiOperation(value = "获取工单的内容的前100的热词")
    @GetMapping("/getTop100")
    public RestResult getTop100() {
        return RestResult.ok(iHotWordService.getTop100());
    }

    /**
     * 获取意见分析数据
     */
    @ApiOperation(value = "获取意见分析数据")
    @GetMapping("/getOpinAnalysisAndLabel")
    public RestResult getOpinAnalysisAndLabel(@RequestParam(required = false) String codeId) {
        if ("all".equals(codeId)) {
            codeId = null;
        }
        return RestResult.ok(iQuestionService.getOpinAnalysisAndLabel(codeId));
    }

    /**
     * 根据意见分析标签，查询列表
     */
    @ApiOperation(value = "根据意见分析标签，查询列表")
    @GetMapping("/listContentByOpLabel")
    public RestResult listContentByOpLabel(@RequestParam(required = false) String codeId, @RequestParam(required = false) String label, @RequestParam(required = false) Integer page) {
        if ("all".equals(codeId)) {
            codeId = null;
        }
        return RestResult.ok(iQuestionService.listContentByOpAndLabel(codeId, label, page));
    }

    /**
     * 本月投诉热点列表
     */
    @ApiOperation(value = "本月投诉举报热点")
    @GetMapping("/getCompAndReportHotByLabel")
    public RestResult getCompAndReportHot() {
        return RestResult.ok(iQuestionService.getCompAndReportHot());
    }

    /**
     * 各类主要投诉问题高频词统计 msx
     */
   /* @ApiOperation(value = "各类主要投诉问题高频词统计")
    @PostMapping("/getMainTypeComplaint")
    public RestResult getMainComplaint(@RequestBody Map<String, Object> requestMap) throws Exception {
        TimeRecord timeRecord = iTimeRecordService.getById(ITimeRecordService.HOT_WORD);
        LocalDateTime end = LocalDateTime.now();
        Map<String,Object> map=DateUtil.getThisYear();
        String startdate = map.get("start").toString();
        String enddate = map.get("end").toString();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date sdate=format.parse(startdate);
        Date edate=format.parse(enddate);
        map.put("start",sdate);
        map.put("end",edate);
        String type = requestMap.get("type").toString();
        map.put("type",type);
        //记录最后一次时间
        timeRecord.setLastDate(end);
        iTimeRecordService.updateById(timeRecord);
        List<Map<String, Object>> mapList = iHotWordService.mainComplaint(map);
        return RestResult.ok(iHotWordService.mainComplaint(map));
    }*/

    /**
     * 通用类前100的热词
     */
    @ApiOperation(value = "获取各类前100的热词")
    @PostMapping("/getHotWordTop100")
    public RestResult getAllTop100(@RequestBody Map<String,Object> requestMap) {
        Map<String,Object> map=new HashMap<>();
        map.put("type",requestMap.get("type").toString());
        return RestResult.ok(iHotWordService.getSameTop100(map));
    }

    /**
     * 通用类前100的热词
     */
    @ApiOperation(value = "获取投诉热点热词")
    @PostMapping("/getcomplaintHotWord")
    public RestResult getComplaintHotWord(@RequestBody Map<String,Object> requestMap) {
        Map<String,Object> map=new HashMap<>();
        return RestResult.ok(iHotWordService.getComplaintHotWord(map));
    }

}

