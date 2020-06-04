package com.chinaweal.sdcs.crcs.analyse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.analyse.entity.Question;
import com.chinaweal.sdcs.crcs.analyse.mapper.QuestionMapper;
import com.chinaweal.sdcs.crcs.analyse.service.IQuestionService;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.service.ITasklistService;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author chinaweal
 * @since 2020-01-19
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {


    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private IBasecodeformService iBasecodeformService;
    @Resource
    private ITasklistService iTasklistService;

    @Override
    public void analyseQuestion(LocalDateTime begin, LocalDateTime end) {

        //获取问题分类的一级code列表
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel, "1");
        List<Basecodeform> questionList = iBasecodeformService.list(queryWrapper);

        for (Basecodeform basecodeform : questionList) {
            //根据问题分获取工单内容列表
            //投诉
            List<Map<String, Object>> compListMap = questionMapper.listCompByCreateDateAndComCodeId(begin, end, basecodeform.getCodeid());
            //举报
            List<Map<String, Object>> econListMap = questionMapper.listEconByCreateDateAndComCodeId(begin, end, basecodeform.getCodeid());

            cutSaveLabel(compListMap, "投诉", basecodeform.getComment());
            cutSaveLabel(econListMap, "举报", basecodeform.getComment());
        }

    }

    @Override
    public Map<String, Object> getOpinAnalysisAndLabel(String codeId) {
        Map<String, Object> map = new HashMap<>();

        String question = null;
        if (codeId != null) {
            QueryWrapper<Basecodeform> bfQu = new QueryWrapper<>();
            bfQu.lambda().eq(Basecodeform::getType, "question12345")
                    .eq(Basecodeform::getOrglevel, "1")
                    .eq(Basecodeform::getCodeid, codeId);
            Basecodeform basecodeform = iBasecodeformService.getOne(bfQu, false);
            if (basecodeform == null) {
                return null;
            }
            question = basecodeform.getComment();
        }
        //投诉举报总数
        int count = questionMapper.countCompRerSumByDate(codeId, null, null);
        map.put("count", count);
        //同比
        LocalDateTime currZero = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
        LocalDateTime curr = LocalDateTime.now();
        int lastMonth = questionMapper.countCompRerSumByDate(codeId, currZero.minusMonths(1), curr.minusMonths(1));
        int currMonth = questionMapper.countCompRerSumByDate(codeId, currZero, curr);

        map.put("yearOnYear", formatYOnY(lastMonth, currMonth));
        //调解数
        int mediation = questionMapper.countCompRerMediateSum(codeId, null, null);
        map.put("mediation", mediation);

        //标签列表
        List<Map<String, Object>> listLabel = questionMapper.listOpinionLabelTop(question);
        map.put("listLabel", listLabel);
        return map;
    }

    @Override
    public Map<String, Object> listContentByOpAndLabel(String codeId, String label, Integer page) {
        Map<String, Object> body = new HashMap<>();
        String question = null;
        if (codeId != null) {
            QueryWrapper<Basecodeform> bfQu = new QueryWrapper<>();
            bfQu.lambda().eq(Basecodeform::getType, "question12345")
                    .eq(Basecodeform::getOrglevel, "1")
                    .eq(Basecodeform::getCodeid, codeId);
            Basecodeform basecodeform = iBasecodeformService.getOne(bfQu, false);
            if (basecodeform == null) {
                return null;
            }
            question = basecodeform.getComment();
        }
        //查询caseguid列表
        QueryWrapper<Question> qQy = new QueryWrapper<>();
        if (question != null) {
            qQy.lambda().eq(Question::getQuestion, question);
        }
        if (label != null) {
            qQy.lambda().eq(Question::getLabel, label);
        }
        qQy.lambda().orderByDesc(Question::getDateTime);
        qQy.select("distinct caseguid,date_time");
        Page<Question> pageObj = new Page<>(page, 4);
        IPage<Question> pageData = page(pageObj, qQy);

        List<Map<String, Object>> list = new ArrayList<>();
        for (Question record : pageData.getRecords()) {
            Map<String, Object> map = new HashMap<>();
            Tasklist tasklist = iTasklistService.getOne(new QueryWrapper<Tasklist>().lambda().eq(Tasklist::getCaseguid, record.getCaseguid()), false);
            map.put("caseguid", tasklist.getCaseguid());
            map.put("content", tasklist.getContent());
            list.add(map);
        }

        body.put("list", list);
        body.put("page", pageData.getCurrent());
        body.put("size", pageData.getSize());
        body.put("total", pageData.getTotal());
        return body;
    }

    @Override
    public List<Map<String, Object>> getCompAndReportHot() {
        List<Map<String, Object>> list = new ArrayList<>();
        LocalDateTime currZero = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
        LocalDateTime curr = LocalDateTime.now();
        List<Map<String, Object>> mapList = questionMapper.listCountGroupLabel(currZero, curr, currZero.minusMonths(1), curr.minusMonths(1));
        for (Map<String, Object> data : mapList) {
            Map<String, Object> map = new HashMap<>();
            int currCount = Integer.parseInt(String.valueOf(data.get("curr_count")));
            int lastCount = Integer.parseInt(String.valueOf(data.get("last_count")));
            map.put("label", data.get("label"));
            map.put("currCount", currCount);
            map.put("lastCount", lastCount);
            //同比
            map.put("yearOnYear", formatYOnY(lastCount, currCount));
            list.add(map);
        }
        return list;
    }

    /**
     * 格式化同比，保留两位数
     *
     * @param lastMonth 上期
     * @param currMonth 本期
     */
    BigDecimal formatYOnY(int lastMonth, int currMonth) {
        if (lastMonth != 0) {
            return BigDecimal.valueOf((currMonth - lastMonth) / (double) lastMonth).multiply(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return BigDecimal.valueOf(0);
        }
    }

    void cutSaveLabel(List<Map<String, Object>> list, String formType, String questionStr) {
        List<Question> questionList = new ArrayList<>();
        for (Map<String, Object> listMap : list) {
            Question question = new Question();
            question.setId(UUID.randomUUID().toString());
            question.setQuestion(questionStr);
            question.setFormType(formType);
            question.setCaseguid((String) listMap.get("caseguid"));
            question.setSpecQuestion((String) listMap.get("code_id"));
            if (listMap.get("createdate") != null) {
                question.setDateTime(((Timestamp) listMap.get("createdate")).toLocalDateTime());
            }
            question.setCreateTime(LocalDateTime.now());
            question.setUpdateTime(LocalDateTime.now());
            //解析标签
            String[] lables = ((String) listMap.get("lable")).split("[ |,|、]");
            for (String lable : lables) {
                lable = lable.trim();
                question.setLabel(lable);
            }
            questionList.add(question);
        }
        saveBatch(questionList);
    }
}
