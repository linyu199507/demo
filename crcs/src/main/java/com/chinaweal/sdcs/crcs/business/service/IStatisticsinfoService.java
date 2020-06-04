package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.Statisticsinfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IStatisticsinfoService extends IService<Statisticsinfo> {

    Map<String, Object> uploadMethod(MultipartFile file, HttpServletRequest request,Map aicUser);

    IPage<Statisticsinfo> searchStatisticsinfos(int size, int current, Map<String, Object> requestMap);

    void deleteByIds(String[] ids, AICUser aicUser);
}
