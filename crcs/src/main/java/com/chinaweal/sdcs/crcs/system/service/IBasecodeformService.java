package com.chinaweal.sdcs.crcs.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;

import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-09-29
 */
public interface IBasecodeformService extends IService<Basecodeform> {

    void addData(Map<String, Object> requestMap);

    IPage<Basecodeform> findBasecodeformByMap(int page, int size, Map<String, String> requestMap);

    void deleteByIds(String[] ids, AICUser aicUser);

    /**
     * 查询基础数据列表
     *
     * @param type     类型
     * @param orgLevel 级别
     * @return list
     */
    List<Basecodeform> listBaseCodeByTypeAndLevel(String type, String orgLevel);


    List<String> listDistinctType();

    Integer getMaxLevelByType(String type);
}
