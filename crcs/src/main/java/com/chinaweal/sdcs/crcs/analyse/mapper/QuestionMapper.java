package com.chinaweal.sdcs.crcs.analyse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.analyse.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2020-01-19
 */
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 根据工单时间，查询消费投诉的内容，取相关的字段
     */
    List<Map<String, Object>> listCompByCreateDateAndComCodeId(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("codeId") String codeId);

    /**
     * 根据工单时间，查询经济举报的内容，取相关的字段
     */
    List<Map<String, Object>> listEconByCreateDateAndComCodeId(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("codeId") String codeId);

    /**
     * 获取标签列表
     */
    List<Map<String, Object>> listOpinionLabelTop(@Param("question") String question);


    /**
     * 查询投诉举报总数
     * 时间不传查询所有
     */
    Integer countCompRerSumByDate(@Param("codeId") String codeId, @Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);

    /**
     * 查询投诉举报调解数
     * 时间不传查询所有
     */
    Integer countCompRerMediateSum(@Param("codeId") String codeId, @Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);

    /**
     * 分组查询获取最高的值
     */
    List<Map<String, Object>> listCountGroupLabel(@Param("currMonthBegin") LocalDateTime currMonthBegin, @Param("currMonthEnd") LocalDateTime currMonthEnd,
                                                  @Param("lastMonthBegin") LocalDateTime lastMonthBegin, @Param("lastMonthEnd") LocalDateTime lastMonthEnd);
}
