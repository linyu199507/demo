package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.Processinfo;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface IProcessinfoService extends IService<Processinfo> {

    boolean saveOpinion(AICUser aicUser, Map<String, Object> requestMap);

    Map<String, String> updateWorkFlow(AICUser aicUser, Map<String, Object> requestMap) throws UnsupportedEncodingException, Exception;

    void dispatch(AICUser aicUser, Map<String, Object> requestMap);
}
