package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.UsersApp;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface TasklistMapper extends BaseMapper<Tasklist> {

    IPage<Tasklist> searchTasklist(Page<Tasklist> page, @Param("aicUser") AICUser aicUser, @Param("map") Map<String, Object> requestMap);

    List<Tasklist> selectTasklist(@Param("aicUser") AICUser aicUser);

    int selectTasklistCount(@Param("ename") String ename, @Param("systemsource") String systemsource, @Param("expectedBegin") String expectedBegin, @Param("expectedEnd") String expectedEnd, @Param("isnewassign") String isnewassign,@Param("sldjend") String sldjend ,@Param("sldjbegin") String sldjbegin,@Param("feedbackbegin") String feedbackbegin,@Param("feedbackend") String feedbackend);

    IPage<Tasklist> searchBusinessInfo(Page<Tasklist> page, @Param("map") Map<String, String> requestMap);

    Tasklist searchBusinessInfo(@Param("map") Map<String, String> requestMap);

    int searchFinishListCount(@Param("userid") String userid, @Param("systemsource") String systemsource);

    IPage<Tasklist> searchEndTasklist(Page<Tasklist> page, AICUser aicUser, Map<String, Object> requestMap);

    IPage<Tasklist> searchFinishListData(Page<Tasklist> page, @Param("userid") String userid, @Param("map") Map<String, Object> requestMap);
    //短信
     List<UsersApp> messageList();

     List<UsersApp> twoMessage();

     //liny --2020-04-02
     List<Tasklist> selectBusinessdeptNull(@Param("map")Map<String, Object> requestMap);

    int selectWarningCount(@Param("userid") String userid,@Param("isregisterbegin") String isregisterbegin ,@Param("isregisterend") String isregisterend,@Param("feedbackbegin") String feedbackbegin,@Param("feedbackend") String feedbackend,@Param("finalbackbegin") String finalbackbegin,@Param("finalbackend") String finalbackend);

}
