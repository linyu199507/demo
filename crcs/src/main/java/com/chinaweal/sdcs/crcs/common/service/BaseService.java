package com.chinaweal.sdcs.crcs.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.common.entity.dto.page.PageRequestDto;
import com.chinaweal.sdcs.crcs.common.service.functions.CustomFilterFunction;
import com.chinaweal.sdcs.crcs.common.service.functions.CustomWrapperPackingFunction;

/**
 * 我的iService接口
 * @param <T> service对应实体
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020/6/1 14:30
 */
public interface BaseService<T extends Model> extends IService<T>{

    /**
     * 默认分页
     * @param pageRequestDto 分页请求
     * @param customWrapperPackingFunction 自定义过程包装
     * @param customFilterFunction 自定义结果过滤
     * @return 分页
     */
    IPage<T> defaultPaging(PageRequestDto pageRequestDto, CustomWrapperPackingFunction<T> customWrapperPackingFunction, CustomFilterFunction<T> customFilterFunction);
}
