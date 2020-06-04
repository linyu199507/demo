package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface ITasklistService extends IService<Tasklist> {

    Map<String, String> startTaskInfo(AICUser aicUser, Map<String, Object> requestMap);

    void deleteByIds(String[] params, AICUser aicUser);

    Map<String, String> saveBmfwBaseInfo(Map<String, Object> requestMap);

    IPage<Tasklist> searchTasklist(int currentPage, int size, AICUser aicUser, Map<String, Object> requestMap);

    Map<String, Object> searchTasklistCount(AICUser aicUser);

    IPage<Tasklist> searchEndTasklist(int currentPage, int size, AICUser aicUser, Map<String, Object> requestMap);

    void signTasklist(AICUser aicUser, String caseguid, String fromnodeid, String ismain);

    Map<String, Object> selectTasklist(AICUser aicUser);

    IPage<Tasklist> searchBusinessInfo(int current, int size, Map<String, String> requestMap);

    Tasklist searchBusinessInfo(Map<String,String> requestMap);

    Map<String, Object> searchFinishListCount(AICUser aicUser);

    IPage<Tasklist> searchFinishListData(int current, int size, AICUser aicUser, Map<String, Object> requestMap);
    //短信
    Map messageList();

    Map<String, Object> searchWarnCount(AICUser aicUser);
}
