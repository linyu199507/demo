package com.chinaweal.sdcs.crcs.analyse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.analyse.entity.HotWord;
import com.sun.jna.platform.win32.Sspi;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author HuangXiao
 * @since 2020-01-14
 */
public interface HotWordMapper extends BaseMapper<HotWord> {


    /**
     * 根据时间范围查询投诉的内容相关字段 msx
     */
    List<Map<String, String>> listComplaintContent(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);

    /**
     * 查询热词top列表
     */
    List<HotWord> listHowTop(@Param("size") int size, @Param("notWords") List<String> notWords);


    List<HotWord> listCommonTop(@Param("size") int size, @Param("notWords") List<String> notWords,@Param("type") Object type);


    List<HotWord> listComplaintHotWord(@Param("map") Map<String, Object> map);
}
